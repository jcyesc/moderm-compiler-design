package org.compiler.simplejava.codegen;

import java.io.*;
import java.util.List;

import org.compiler.simplejava.aat.AatCallExpression;
import org.compiler.simplejava.aat.AatCallStatement;
import org.compiler.simplejava.aat.AatConditionalJump;
import org.compiler.simplejava.aat.AatConstant;
import org.compiler.simplejava.aat.AatEmpty;
import org.compiler.simplejava.aat.AatExpression;
import org.compiler.simplejava.aat.AatHalt;
import org.compiler.simplejava.aat.AatJump;
import org.compiler.simplejava.aat.AatLabel;
import org.compiler.simplejava.aat.AatMemory;
import org.compiler.simplejava.aat.AatMove;
import org.compiler.simplejava.aat.AatOperator;
import org.compiler.simplejava.aat.AatRegister;
import org.compiler.simplejava.aat.AatReturn;
import org.compiler.simplejava.aat.AatSequential;
import org.compiler.simplejava.aat.AatVisitor;
import org.compiler.simplejava.aat.Register;
import org.compiler.simplejava.semantic.Label;
import org.compiler.simplejava.semantic.MachineDependent;

public class SimpleTilingCodeGenerator implements AatVisitor {
  private final int STACKSIZE = 1000;
  private PrintWriter output;
  /* Feel Free to add more instance variables, if you like */

  public SimpleTilingCodeGenerator(String output_filename) {
    try {
      output = new PrintWriter(new FileOutputStream(output_filename));
    } catch (IOException e) {
      System.err.println("Could not open file " + output_filename
          + " for writing.");
    }
    /* Feel free to add code here, if you want to */
    emitSetupCode();
  }

  /**
   * Function calls
   *
   * To implement a function call, we need to copy all of the parameters of the function onto the
   * top of the call stack (as opposed to the expression stack), and then jump to the start of the
   * function. When the function returns, we need to pop the arguments off the top of the call stack,
   * and push the return value of the function on the top of the expression stack.
   */
  public Object visitCallExpression(AatCallExpression callExpr) {
    List<AatExpression> parameters = callExpr.getActuals();
    Label functionName = callExpr.getLabel();
    
    functionCall(functionName, parameters);

    // Storing the result of function on expression stack
    emit(String.format("sw %s, 0(%s)", Register.Result(), Register.ESP()));
    emit(String.format("addi %s, %s, %d", Register.ESP(), Register.ESP(), MachineDependent.WORDSIZE * -1));
    return null;
  }

  private void functionCall(Label functionName,  List<AatExpression> parameters) {
    // Putting the parameters in the Expression Stack
    for (AatExpression arg : parameters) {
      arg.accept(this);
    }

    // Loading the parameters in the Stack
    for (int i = 1; i <= parameters.size(); i++) {
      int spOffset = MachineDependent.WORDSIZE * (i - 1);
      emit(String.format("lw %s, %d(%s)", Register.Tmp1(), MachineDependent.WORDSIZE * i, Register.ESP()));
      emit(String.format("sw %s, %d(%s)", Register.Tmp1(), spOffset * -1, Register.SP()));
    }

    int offset = parameters.size() * MachineDependent.WORDSIZE;
    emit(String.format("addi %s, %s, %d", Register.SP(), Register.SP(), offset * -1));
    emit(String.format("addi %s, %s, %d", Register.ESP(), Register.ESP(), offset));
    emit(String.format("jal %s", functionName.toString()));
    emit(String.format("addi %s, %s, %d", Register.SP(), Register.SP(), offset));
  }

  public Object visitMemory(AatMemory expression) {
    expression.getMemory().accept(this);
    emit(String.format("lw %s, %d(%s)", Register.Tmp1(), MachineDependent.WORDSIZE, Register.ESP()));
    emit(String.format("lw %s, 0(%s)", Register.Tmp1(), Register.Tmp1()));
    emit(String.format("sw %s, %d(%s)", Register.Tmp1(), MachineDependent.WORDSIZE, Register.ESP()));
    return null;
  }

  public Object visitOperator(AatOperator expression) {
    expression.getLeft().accept(this);
    expression.getRight().accept(this);
    
    emit(String.format("lw %s, %d(%s)", Register.Tmp1(), MachineDependent.WORDSIZE * 2, Register.ESP()));
    emit(String.format("lw %s, %d(%s)", Register.Tmp2(), MachineDependent.WORDSIZE, Register.ESP()));

    int operator = expression.getOperator();
    switch (operator) {
      case AatOperator.PLUS:
        emit(String.format("add %s, %s, %s", Register.Tmp1(), Register.Tmp1(), Register.Tmp2()));  
        break;
      case AatOperator.MINUS:
        emit(String.format("sub %s, %s, %s", Register.Tmp1(), Register.Tmp1(), Register.Tmp2()));
        break;
      case AatOperator.MULTIPLY:
        emit(String.format("mult %s, %s", Register.Tmp1(), Register.Tmp2()));
        emit(String.format("mflo %s", Register.Tmp1()));
        break;
      case AatOperator.DIVIDE:
        emit(String.format("div %s, %s", Register.Tmp1(), Register.Tmp2()));
        emit(String.format("mflo %s", Register.Tmp1()));
        break;
      case AatOperator.AND:
        // x AND y == x + y > 1
        emit(String.format("add %s, %s, %s", Register.Tmp1(), Register.Tmp1(), Register.Tmp2()));
        emit(String.format("addi %s, %s %d", Register.Tmp2(), Register.Zero(), 1));
        emit(String.format("slt %s, %s %s", Register.Tmp1(), Register.Tmp2(), Register.Tmp1()));
        break;
      case AatOperator.OR:
        // x OR y == x + y > 0
        emit(String.format("add %s, %s, %s", Register.Tmp1(), Register.Tmp1(), Register.Tmp2()));
        emit(String.format("slt %s, %s %s", Register.Tmp1(), Register.Zero(), Register.Tmp1()));
        break;
      case AatOperator.EQUAL:
        // x == y is equivalent to !((y < x) || (x < y))
        // $t3 = (x < y)
        emit(String.format("slt %s, %s %s", Register.Tmp3(), Register.Tmp2(), Register.Tmp1()));
        // $t2 = (y < x)
        emit(String.format("slt %s, %s %s", Register.Tmp2(), Register.Tmp1(), Register.Tmp2()));
        // $t1 = (x < y) || (y < x)
        emit(String.format("add %s, %s, %s", Register.Tmp1(), Register.Tmp2(), Register.Tmp3()));
        // $t1 = !((x < y) || (y < x))
        emit(String.format("sub %s, %s, %s", Register.Tmp1(), Register.Zero(), Register.Tmp1()));
        emit(String.format("addi %s, %s %d", Register.Tmp1(), Register.Tmp1(), 1));
        break;
      case AatOperator.NOT_EQUAL:
        // x != y is equivalent to (y < x) || (x < y)
        // $t3 = (x < y)
        emit(String.format("slt %s, %s %s", Register.Tmp3(), Register.Tmp2(), Register.Tmp1()));
        // $t2 = (y < x)
        emit(String.format("slt %s, %s %s", Register.Tmp2(), Register.Tmp1(), Register.Tmp2()));
        // $t1 = (x < y) || (y < x)
        emit(String.format("add %s, %s, %s", Register.Tmp1(), Register.Tmp2(), Register.Tmp3()));
        break;
      case AatOperator.LESS_THAN:
        emit(String.format("slt %s, %s %s", Register.Tmp1(), Register.Tmp1(), Register.Tmp2()));
        break;
      case AatOperator.LESS_THAN_EQUAL:
        // x <= y is equivalent to x -1 < y
        // x - 1
        emit(String.format("addi %s, %s %d", Register.Tmp1(), Register.Tmp1(), -1));
        emit(String.format("slt %s, %s %s", Register.Tmp1(), Register.Tmp1(), Register.Tmp2()));
        break;
      case AatOperator.GREATER_THAN:
        emit(String.format("slt %s, %s %s", Register.Tmp1(), Register.Tmp2(), Register.Tmp1()));
        break;
      case AatOperator.GREATER_THAN_EQUAL:
        // x >= y is equivalent to x > y - 1
        // y - 1
        emit(String.format("addi %s, %s %d", Register.Tmp2(), Register.Tmp2(), -1));
        emit(String.format("slt %s, %s %s", Register.Tmp1(), Register.Tmp2(), Register.Tmp1()));
        break;
    }

    // Saving the value in the stack
    emit(String.format("sw %s, %d(%s)", Register.Tmp1(), MachineDependent.WORDSIZE *2, Register.ESP()));
    emit(String.format("addi %s, %s, %d", Register.ESP(), Register.ESP(), MachineDependent.WORDSIZE));
    return null;
  }

  public Object visitRegister(AatRegister expression) {
    emit(String.format("sw %s, 0(%s)", expression.getRegister(), Register.ESP()));
    emit(String.format("addi %s, %s, %d", Register.ESP(), Register.ESP(), MachineDependent.WORDSIZE * -1));
    return null;
  }

  /**
   * Call statements
   *
   * Call statements are very similar to function calls. The only difference is that there is no
   * return result for a call statement. So after the procedure ends, we do not need to copy the
   * result onto the expression stack.
   */
  public Object visitCallStatement(AatCallStatement callStmt) {
    List<AatExpression> parameters = callStmt.getActuals();
    Label functionName = callStmt.getLabel();
    functionCall(functionName, parameters);
    return null;
  }

  public Object visitConditionalJump(AatConditionalJump statement) {
    statement.getTest().accept(this);
    emit(String.format("lw %s, %d(%s)", Register.Tmp1(), MachineDependent.WORDSIZE, Register.ESP()));
    emit(String.format("addi %s, %s, %d", Register.ESP(), Register.ESP(), MachineDependent.WORDSIZE));
    emit(String.format("bgtz %s, %s", Register.Tmp1(), statement.getLabel()));
    return null;
  }

  public Object visitEmpty(AatEmpty statement) {
    return null;
  }

  public Object visitJump(AatJump statement) {
    emit(String.format("j %s", statement.getLabel()));
    return null;
  }

  public Object visitLabel(AatLabel statement) {
    emit(statement.getLabel() + ":");
    return null;
  }

  public Object visitMove(AatMove statement) {
    // Register access
    if (statement.getLhs() instanceof AatRegister) {
      statement.getRhs().accept(this);
      AatRegister register = (AatRegister) statement.getLhs();
      emit(String.format("lw %s, %d(%s)", register.getRegister(), MachineDependent.WORDSIZE, Register.ESP()));
      emit(String.format("addi %s, %s, %d", Register.ESP(), Register.ESP(), MachineDependent.WORDSIZE));
    } else {
      // Memory access.
      // Setting the location
      ((AatMemory) statement.getLhs()).getMemory().accept(this);
      // Storing the value.
      statement.getRhs().accept(this);
      emit(String.format("lw %s, %d(%s)", Register.Tmp1(), MachineDependent.WORDSIZE * 2, Register.ESP()));
      emit(String.format("lw %s, %d(%s)", Register.Tmp2(), MachineDependent.WORDSIZE, Register.ESP()));
      emit(String.format("sw %s, 0(%s)", Register.Tmp2(), Register.Tmp1()));
      emit(String.format("addi %s, %s, %d", Register.ESP(), Register.ESP(), MachineDependent.WORDSIZE * 2));
    }
    return null;
  }

  public Object visitReturn(AatReturn statement) {
    emit("jr " + Register.ReturnAddr());
    return null;
  }

  public Object visitHalt(AatHalt halt) {
    /*
     * Don't need to implement halt -- you can leave this as it is, if you like
     */
    return null;
  }

  /**
   * Sequential Trees
   *
   * Sequential trees are an interesting special case - after we have created the code for the
   * left subtree and the right subtree, what do we need to do? Nothing!. Thus a sequential tile
   * covers the sequential node, and has no associated code.
   */
  public Object visitSequential(AatSequential statement) {
    statement.getLeft().accept(this);
    statement.getRight().accept(this);
    return null;
  }

  public Object visitConstant(AatConstant expression) {
    emit(String.format("addi %s, %s, %d", Register.Tmp1(), Register.Zero(), expression.getValue()));
    emit(String.format("sw %s, 0(%s)", Register.Tmp1(), Register.ESP()));
    emit(String.format("addi %s, %s, %d", Register.ESP(), Register.ESP(), MachineDependent.WORDSIZE * -1));
    return null;
  }
  
  private void emit(String assem) {
    assem = assem.trim();
    if (assem.charAt(assem.length() - 1) == ':')
      output.println(assem);
    else
      output.println("\t" + assem);
  }

  public void generateLibrary() {
    emit("Print:");
    emit("lw $a0, 4(" + Register.SP() + ")");
    emit("li $v0, 1");
    emit("syscall");
    emit("li $v0,4");
    emit("la $a0, sp");
    emit("syscall");
    emit("jr $ra");
    emit("Println:");
    emit("li $v0,4");
    emit("la $a0, cr");
    emit("syscall");
    emit("jr $ra");
    emit("Read:");
    emit("li $v0,5");
    emit("syscall");
    emit("jr $ra");
    emit("allocate:");
    emit("la " + Register.Tmp1() + ", HEAPPTR");
    emit("lw " + Register.Result() + ",0(" + Register.Tmp1() + ")");
    emit("lw " + Register.Tmp2() + ", 4(" + Register.SP() + ")");
    emit("sub " + Register.Tmp2() + "," + Register.Result() + ","
        + Register.Tmp2());
    emit("sw " + Register.Tmp2() + ",0(" + Register.Tmp1() + ")");
    emit("jr $ra");
    emit(".data");
    emit("cr:");
    emit(".asciiz \"\\n\"");
    emit("sp:");
    emit(".asciiz \" \"");
    emit("HEAPPTR:");
    emit(".word 0");
    output.flush();
  }

  private void emitSetupCode() {
    emit(".globl main");
    emit("main:");
    emit("addi " + Register.ESP() + "," + Register.SP() + ",0");
    emit("addi " + Register.SP() + "," + Register.SP() + ","
        + -MachineDependent.WORDSIZE * STACKSIZE);
    emit("addi " + Register.Tmp1() + "," + Register.SP() + ",0");
    emit("addi " + Register.Tmp1() + "," + Register.Tmp1() + ","
        + -MachineDependent.WORDSIZE * STACKSIZE);
    emit("la " + Register.Tmp2() + ", HEAPPTR");
    emit("sw " + Register.Tmp1() + ",0(" + Register.Tmp2() + ")");
    emit("sw " + Register.ReturnAddr() + "," + MachineDependent.WORDSIZE + "("
        + Register.SP() + ")");
    emit("jal main1");
    emit("li $v0, 10");
    emit("syscall");
  }
}
