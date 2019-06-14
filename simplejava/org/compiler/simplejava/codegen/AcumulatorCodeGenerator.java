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

public class AcumulatorCodeGenerator implements AatVisitor {
  private final int STACKSIZE = 1000;
  private PrintWriter output;
  /* Feel Free to add more instance variables, if you like */

  public AcumulatorCodeGenerator(String output_filename) {
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

    // Storing the result of function in the accumulator
    emit(String.format("addi %s, %s, 0", Register.ACC(), Register.Result()));
    return null;
  }

  private void functionCall(Label functionName,  List<AatExpression> parameters) {
    // Putting the parameters in Stack
    for (int i = parameters.size() - 1, j = 0; i >= 0; i--, j++) {
      int spOffset = MachineDependent.WORDSIZE * j;
      AatExpression arg = parameters.get(i);
      arg.accept(this);
      emit(String.format("sw %s, %d(%s)", Register.ACC(), spOffset * -1, Register.SP()));
    }

    int offset = parameters.size() * MachineDependent.WORDSIZE;
    emit(String.format("addi %s, %s, %d", Register.SP(), Register.SP(), offset * -1));
    emit(String.format("jal %s", functionName.toString()));
    emit(String.format("addi %s, %s, %d", Register.SP(), Register.SP(), offset));
  }

  public Object visitMemory(AatMemory expression) {
    expression.getMemory().accept(this);
    emit(String.format("lw %s, 0(%s)", Register.ACC(), Register.ACC()));
    return null;
  }

  public Object visitOperator(AatOperator expression) {
    // Code for left operand
    expression.getLeft().accept(this);

    // Storing left operand on expression stack.
    emit(String.format("sw %s, 0(%s)", Register.ACC(), Register.ESP()));
    emit(String.format("addi %s, %s, %d", Register.ESP(), Register.ESP(), MachineDependent.WORDSIZE * -1));

    // Code for right operand
    expression.getRight().accept(this);

    // Pop left operand off expression stack and apply the operator
    emit(String.format("lw %s, %d(%s)", Register.Tmp1(), MachineDependent.WORDSIZE, Register.ESP()));
    emit(String.format("addi %s, %s, %d", Register.ESP(), Register.ESP(), MachineDependent.WORDSIZE));

    int operator = expression.getOperator();
    switch (operator) {
      case AatOperator.PLUS:
        emit(String.format("add %s, %s, %s", Register.ACC(), Register.Tmp1(), Register.ACC()));  
        break;
      case AatOperator.MINUS:
        emit(String.format("sub %s, %s, %s", Register.ACC(), Register.Tmp1(), Register.ACC()));
        break;
      case AatOperator.MULTIPLY:
        emit(String.format("mult %s, %s", Register.Tmp1(), Register.ACC()));
        emit(String.format("mflo %s", Register.ACC()));
        break;
      case AatOperator.DIVIDE:
        emit(String.format("div %s, %s", Register.Tmp1(), Register.ACC()));
        emit(String.format("mflo %s", Register.ACC()));
        break;
      case AatOperator.AND:
        // x AND y == x + y > 1
        emit(String.format("add %s, %s, %s", Register.ACC(), Register.Tmp1(), Register.ACC()));
        emit(String.format("addi %s, %s, %d", Register.Tmp2(), Register.Zero(), 1));
        emit(String.format("slt %s, %s, %s", Register.ACC(), Register.Tmp2(), Register.ACC()));
        break;
      case AatOperator.OR:
        // x OR y == x + y > 0
        emit(String.format("add %s, %s, %s", Register.ACC(), Register.Tmp1(), Register.ACC()));
        emit(String.format("slt %s, %s, %s", Register.ACC(), Register.Zero(), Register.ACC()));
        break;
      case AatOperator.EQUAL:
        // x == y is equivalent to !((y < x) || (x < y))
        // $t2 = (x < y)
        emit(String.format("slt %s, %s, %s", Register.Tmp2(), Register.Tmp1(), Register.ACC()));
        // $acc = (y < x)
        emit(String.format("slt %s, %s, %s", Register.ACC(), Register.ACC(), Register.Tmp1()));
        // $acc = (x < y) || (y < x)
        emit(String.format("add %s, %s, %s", Register.ACC(), Register.ACC(), Register.Tmp2()));
        // $acc = !((x < y) || (y < x))
        emit(String.format("sub %s, %s, %s", Register.ACC(), Register.Zero(), Register.ACC()));
        emit(String.format("addi %s, %s, %d", Register.ACC(), Register.ACC(), 1));
        break;
      case AatOperator.NOT_EQUAL:
        // x != y is equivalent to (y < x) || (x < y)
        // $t2 = (x < y)
        emit(String.format("slt %s, %s, %s", Register.Tmp2(), Register.Tmp1(), Register.ACC()));
        // $acc = (y < x)
        emit(String.format("slt %s, %s, %s", Register.ACC(), Register.ACC(), Register.Tmp1()));
        // $acc = (x < y) || (y < x)
        emit(String.format("add %s, %s, %s", Register.ACC(), Register.ACC(), Register.Tmp2()));
        break;
      case AatOperator.LESS_THAN:
        emit(String.format("slt %s, %s, %s", Register.ACC(), Register.Tmp1(), Register.ACC()));
        break;
      case AatOperator.LESS_THAN_EQUAL:
        // x <= y is equivalent to x -1 < y
        // x - 1
        emit(String.format("addi %s, %s, %d", Register.Tmp1(), Register.Tmp1(), -1));
        emit(String.format("slt %s, %s, %s", Register.ACC(), Register.Tmp1(), Register.ACC()));
        break;
      case AatOperator.GREATER_THAN:
        emit(String.format("slt %s, %s, %s", Register.ACC(), Register.ACC(), Register.Tmp1()));
        break;
      case AatOperator.GREATER_THAN_EQUAL:
        // x >= y is equivalent to x > y - 1
        // y - 1
        emit(String.format("addi %s, %s, %d", Register.ACC(), Register.ACC(), -1));
        emit(String.format("slt %s, %s, %s", Register.ACC(), Register.ACC(), Register.Tmp1()));
        break;
    }

    return null;
  }

  public Object visitRegister(AatRegister expression) {
    emit(String.format("addi %s, %s, %d", Register.ACC(), expression.getRegister(), 0));
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
    emit(String.format("bgtz %s, %s", Register.ACC(), statement.getLabel()));
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
      emit(String.format("addi %s, %s, 0", register.getRegister(), Register.ACC()));
    } else {
      // Memory access.
      // Setting the location
      ((AatMemory) statement.getLhs()).getMemory().accept(this);
      // Storing destination on expression stack. 
      emit(String.format("sw %s, 0(%s)", Register.ACC(), Register.ESP()));
      emit(String.format("addi %s, %s, %d", Register.ESP(), Register.ESP(), MachineDependent.WORDSIZE * -1));

      // Storing the value.
      statement.getRhs().accept(this);
      // Load destination into a register.
      emit(String.format("lw %s, %d(%s)", Register.Tmp1(), MachineDependent.WORDSIZE, Register.ESP()));
      emit(String.format("addi %s, %s, %d", Register.ESP(), Register.ESP(), MachineDependent.WORDSIZE));
      emit(String.format("sw %s, 0(%s)", Register.ACC(), Register.Tmp1()));
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
    // Storing the constant value in the accumulator.
    emit(String.format("addi %s, %s, %d", Register.ACC(), Register.Zero(), expression.getValue()));
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
