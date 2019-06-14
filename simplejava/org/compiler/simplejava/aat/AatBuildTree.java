package org.compiler.simplejava.aat;

import java.util.ArrayList;
import java.util.List;

import org.compiler.simplejava.semantic.Label;


/**
 * Abstract Assembly Trees
 *
 * Once that we have done the semantic analysis of the program and made sure that there are no
 * errors that can be caught at compile time, we need to generate the assembly code for our target
 * architecture. We are not, however, going to create the actual assembly code directly from the
 * Abstract Syntax Tree. Instead, we will create an Abstract Assembly Tree from the Abstract
 * Syntax Tree, and then generate the final assembly code from this abstract assembly. Why are
 * we going through this intermediate step, using yet another internal representation of the
 * compiled program? There are three main reasons for using an abstract assembly language in the
 * compilation process:
 *
 * - Translating directly from the abstract syntax tree to actual assembly is very difficult. There
 * is no always a good match between the functionality of the source language, and the functionality
 * of the actual assembly. So, using good software engineering techniques, we will break a difficult
 * problem into easier subproblems. A simplified, abstract assembly language will be much easier to
 * generate from the Abstract Syntax Tree than actual assembly. Once we have created the Abstract
 * Assembly Tree, it will be straightforward to generate the actual assembly language.
 * 
 * - Optimization of Abstract Assembly Tree is easier than optimization of actual assembly language.
 * 
 * - Writing a compiler for several different target assembly languages (such as MIPS and x86) is
 * easier if we go through an abstract assembly layer.
 *
 *
 * Abstract Assembly
 * 
 * The abstract assembly that we will be using is, by design, very simple. It can be difficult to
 * match comlex simpleJava statements into complex actual assembly, especially if there is not a
 * good match between the functionality of simpleJava statements and the functionality of the target
 * assembly language.
 *
 * Our abstract assembly is broken into statement trees and expression trees. Statement trees have
 * no values - they correspond to simple assembly instructions. Expression trees have values - they
 * correspond to the arguments of the instructions in the statement trees.
 *
 * There are five different types of expression trees:
 * 
 * - Constant: A constant expression tree consists of the value of the constant (this abstract
 * assembly uses only integers for all values, including all constants).
 * 
 * - Register: A register expression tree consists of a string which specifies which register is
 * being represented.
 * 
 * - Operator: An operator expression tree consists of two expression trees for the operands, and
 * the operator +, -, *, /, < .. etc.
 * 
 * - CallExpression: A call expression tree contains an assembly language label and an expression
 * tree for each of the actual parameters in the function call. The assembly language label is the
 * address of the beginning of the called function.
 * 
 * - Memory: A memory expression tree contains an expression subtree that denotes the memory
 * address described by the tree.
 * 
 * There are eight types of statement trees, as follows:
 * 
 * - Move: Move trees have two subtrees - the left subtree is te destination of the move, and the
 * right subtree is the value to move. The left subtree of the move needs to be either a register
 * expression tree (for moving data into a register) or a memory expression tree (for moving data
 * into memory). The right hand side of the move can be any arbitrary expression.
 * 
 * - Label: Label trees contain an assembly language label. They are used as the destination for
 * jumps conditional jumps, and function/procedure calls.
 *
 * - Jump: Unconditional jump trees contain an assembly language label. They represent transfer
 * of program control immediately to a new location, specified by the assembly language label.
 * 
 * - Conditional Jump: Conditional jump trees contain an expression subtree and an assembly language
 * label. They represent the transfer of program control to the assembly language label if the
 * expression evaluates to true (a non-zero value). If the expression evaluates to false (zero),
 * then a Conditional Jump is a no-op.
 * 
 * - Sequential: Sequential statement trees have two subtrees - a left subtree and a right subtree.
 * They represent the execution of the left subtree, followed by the execution of the right subtree.
 * 
 * - Call Statement: Call statement trees contain an  assembly language label, and an expression
 * tree for each of the actual parameters in the function call. Call statements represent void
 * function calls. The assembly language label is the address of the beginning of the called
 * function.
 *
 * - Empty: Empty statement trees contain no extra information. Empty statements are no-ops, which
 * are removed when the code is transferred into actual assembly.
 *
 * - Return: Return abstract assembly trees contain no extra information. A return statement
 * returns the flow of control to the calling procedure. Note that a return abstract assembly
 * instruction, unlike a simpleJava return statement, does not return a value - it merely changes
 * the flow of control. The implementation of a simpleJava return statement in abstract assembly
 * requires code to set the return value of the fucntion (by setting the result register) in
 * addition to the return abstract assembly instruction.
 *
 *
 * @author jcyescas
 *
 */
public class AatBuildTree {

  /**
   * The assembly tree for a function definition starts with the assembly language label for
   * the function, followed by a series of instructions to save the old values of the return
   * address, FP, and SP registers, followed by code to set the new values of the FP and SP,
   * followed by the body of the function (passed in), followed by a label for the end of the
   * function, followed by code to restore the old values of the return address, FP, and
   * SP registers, followed by an abstract assembly return statement, to return the flow of control
   * to the calling function. The end label is used to simplify the implementation of a simpleJava
   * return statement.
   *
   * When the old stack pointer, frame pointer, and return address pointer are restored, the
   * frame pointer need to be restored last if it is used to get the items on the stack.
   *
   * The complete assembly code for a function is thus:
   * 
   * <Label for start of function>
   *   <Code to set up activation record>
   *
   *   <Function body>
   *
   * <Label for end of function>
   *   <Code to clean up activation record>
   *   <return> 
   *
   * @param body
   * @param framesize
   * @param start
   * @param end
   * @return
   */
  public AatStatement functionDefinition(AatStatement body, int framesize,
      Label start, Label end) {
    return this.processFunctionOrMethodDef(body, framesize, start, end, true);
  }

  /**
   * Activation Records for Methods
   *
   * Modify the activation record for methods to include a "this" pointer. The "this" pointer will
   * always be at the beginning of the activation record.
   *
   * Activation records for methods differ from those of standard functions by the addition of a
   * "this" pointer. Each method call includes an implicit "0th parameter", which is the "this"
   * pointer for that method. Thus, while the code to set up the activation record for a function
   * needs to set the new FP to the value of the old SP, the code to set up the activation record
   * for a method needs to set the new FP to the value of the old SP plus the word size of the
   * machine so that the "this" parameter is at the beginning of the activation record for the
   * method. Thus, the FP will point to the "this" pointer, the 1st input parameter of the method
   * will be at location (FP + Machine Word size), and the first local variable will be at
   * location (FP - Machine Word size). 
   * 
   * @param body
   * @param framesize
   * @param start
   * @param end
   * @return
   */
  public AatStatement methodDefinition(AatStatement body, int framesize,
      Label start, Label end) {
    return this.processFunctionOrMethodDef(body, framesize, start, end, false);
  }

  /**
   * Processes function and method definitions accordingly.
   *
   * @param body
   * @param framesize
   * @param start
   * @param end
   * @param isFunction
   * @return
   */
  private AatStatement processFunctionOrMethodDef(AatStatement body, int framesize,
      Label start, Label end, boolean isFunction) {
    AatLabel aatLabelStart = new AatLabel(start);
    AatLabel aatLabelEnd = new AatLabel(end);

    // The construction of the tree is BOTTOM-UP.

    // Return statement.
    AatReturn returnToCaller = new AatReturn();

    // Restore old values of the return address, FP, and SP registers.
    // Restoring FP    
    AatStatement restoreStmt =
        restoreRegisterFromStack(Register.FP(), framesize + MachineDependent.WORDSIZE);
    
    AatStatement tree = new AatSequential(restoreStmt, returnToCaller);

    // Restoring SP
    restoreStmt = restoreRegisterFromStack(Register.SP(), framesize + MachineDependent.WORDSIZE * 2);
    tree = new AatSequential(restoreStmt, tree);

    // Restoring Return Address
    restoreStmt = restoreRegisterFromStack(Register.ReturnAddr(), framesize);
    tree = new AatSequential(restoreStmt, tree);

    if(!isFunction) {
      // Restoring the original value of FP for a METHOD
      AatMove restoreFP = new AatMove(new AatRegister(Register.FP()), 
          new AatOperator(new AatRegister(Register.FP()), new AatConstant(MachineDependent.WORDSIZE), AatOperator.MINUS));
      tree = new AatSequential(restoreFP, tree);
    }

    // Setting the end label
    tree = new AatSequential(aatLabelEnd, tree);

    // Executing the body
    tree = new AatSequential(body, tree);

    // Setting the new value for SP
    int offsetSp = framesize + MachineDependent.WORDSIZE * 3; // 3 Registers, SP, FP and return address.
    AatMove moveSp = new AatMove(new AatRegister(Register.SP()), 
        new AatOperator(new AatRegister(Register.SP()), new AatConstant(offsetSp), AatOperator.MINUS));
    tree = new AatSequential(moveSp, tree);

    // Setting the new value for FP
    AatMove moveFp;
    if (isFunction) {
      // Setting the new value for FP for a FUNCTION
      moveFp = new AatMove(new AatRegister(Register.FP()), new AatRegister(Register.SP()));  
    } else {
      // Setting the new value for FP for a METHOD
      moveFp = new AatMove(new AatRegister(Register.FP()), 
          new AatOperator(new AatRegister(Register.SP()), new AatConstant(MachineDependent.WORDSIZE), AatOperator.PLUS));
    }
    tree = new AatSequential(moveFp, tree);

    // Saving the SP register
    AatMove saveSp = saveRegisterToStack(Register.SP(), framesize + MachineDependent.WORDSIZE * 2);
    tree = new AatSequential(saveSp, tree);
 
    // Saving the FP register
    AatMove saveFp = saveRegisterToStack(Register.FP(), framesize + MachineDependent.WORDSIZE);
    tree = new AatSequential(saveFp, tree);

    // Saving the Return Address register
    AatMove saveRa = saveRegisterToStack(Register.ReturnAddr(), framesize);
    tree = new AatSequential(saveRa, tree);

    // Setting the start label
    tree = new AatSequential(aatLabelStart, tree);

    return tree;    
  }

  /**
   * Saves the given register in the stack at the offset that was passed. The value store in SP is
   * used in conjunction with the offset.
   * 
   * @param registerToSave
   * @param offset
   * @return AatMove
   */
  private AatMove saveRegisterToStack(Register registerToSave, int offset) {
    AatRegister spRegister = new AatRegister(Register.SP());
    AatOperator location = new AatOperator(spRegister, new AatConstant(offset), AatOperator.MINUS);
    AatMemory memory = new AatMemory(location);

    return new AatMove(memory, new AatRegister(registerToSave));
  }

  /**
   * Restore the old value of the given register from the stack. It uses the FP register and
   * the offset to get the old value. 
   *
   * @param toRegister
   * @param offset
   * @return AatMove
   */
  private AatMove restoreRegisterFromStack(Register toRegister, int offset) {
    AatMemory oldValue = new AatMemory(
        new AatOperator(new AatRegister(Register.FP()), new AatConstant(offset), AatOperator.MINUS));
    AatMove move = new AatMove(new AatRegister(toRegister), oldValue);

    return move;
  }

  /**
   * The default initialization value of all the variables is ZERO.
   *
   * @return AatExpression
   */
  public AatExpression getDefaultInitValue() {
    return new AatConstant(0);
  }

  /**
   * If statements are fairly straightforward - if the test is true, the "then" statement is
   * executed, otherwise the "else" statement is executed. When implementing an if statement, it is
   * easier to have the code that implements the "else" statement appear first, jumping to the
   * "then" statement only if the test is true. That is, the simpleJava statement:
   * 
   *  if (<test>) <statement> else <statement2>
   *  
   *  can be translated into pseudo-assembly as:
   *  
   *  if (<test>) goto IFTRUE
   *     <code for statement 2>
   *     goto IFEND
   *  IFTRUE:
   *    <code for statement1>
   *  IFEND:
   *
   * @param test
   * @param ifBody
   * @param elseBody
   * @return AatStatement
   */
  public AatStatement ifStatement(AatExpression test, AatStatement ifBody, AatStatement elseBody) {
    Label ifTrue = new Label("iftrue");
    Label ifEnd = new Label("ifend");
    elseBody = elseBody != null ? elseBody : new AatEmpty();

    AatSequential root = new AatSequential(ifBody, new AatLabel(ifEnd));
    root = new AatSequential(new AatLabel(ifTrue), root);
    root = new AatSequential(new AatJump(ifEnd), root);
    root = new AatSequential(elseBody, root);
    root = new AatSequential(new AatConditionalJump(test, ifTrue), root);

    return root;
  }

  /**
   * This method is called on a new expression, to allocate space from the heap. It is
   * implemented by a function call to the built-in allocate function. The built-in allocate
   * function takes a single input parameter - the size (in bytes) to allocate - and returns
   * a pointer to the newly allocated block.
   * 
   * Note: It receives the size in bytes.
   *
   * @param size in bytes to be allocated.
   * @return AatExpression
   */
  public AatExpression allocate(AatExpression size) {
    List<AatExpression> parameters = new ArrayList<AatExpression>();

    // Calculate the size by multiplying the given size times Machine Word.
    size = this.operatorExpression(size, this.constantExpression(MachineDependent.WORDSIZE), AatOperator.MULTIPLY);

    // Adding the size to the list of parameters.
    parameters.add(size);

    return new AatCallExpression(Label.absLabel("allocate"), parameters);
  }

  /**
   * While statements can be implemented as an Abstract Assembly Tree using conditional jumps.
   * 
   * goto WHILETEST
   * WHILESTART:
   *   <code for statement>
   * WHILETEST:
   *   if (<test>) goto WHILESTART
   * WHILEEND:
   * 
   * 
   * @param test
   * @param whilebody
   * @return AatStatement
   */
  public AatStatement whileStatement(AatExpression test, AatStatement whilebody) {
    Label whileStart = new Label("whileStart");
    Label whileTest = new Label("whileTest");
    Label whileEnd = new Label("whileEnd");

    AatSequential root =
        new AatSequential(new AatConditionalJump(test, whileStart), new AatLabel(whileEnd));
    root = new AatSequential(new AatLabel(whileTest), root);
    root = new AatSequential(whilebody, root);
    root = new AatSequential(new AatLabel(whileStart), root);
    root = new AatSequential(new AatJump(whileTest), root);

    return root;
  }

  /**
   * Do while statement is pretty similar to while:
   * 
   * DOWHILE_START:
   * <statement>
   * if (<test>) goto DOWHILE_START
   * DOWHILE_END
   * 
   * @param test
   * @param dowhilebody
   * @return
   */
  public AatStatement dowhileStatement(AatExpression test, AatStatement dowhilebody) {
    Label doWhileStart = new Label("doWhileStart");
    Label doWhileEnd = new Label("doWhileEnd");

    AatSequential root =
        new AatSequential(new AatConditionalJump(test, doWhileStart), new AatLabel(doWhileEnd));
    root = new AatSequential(dowhilebody, root);
    root = new AatSequential(new AatLabel(doWhileStart), root);

    return root;
  }

  /**
   * For statements are the same as the while statements, with added increment and initialization
   * statements. That is the simpleJava statement
   * 
   * for (<initialize> ; <test> ; <increment>)
   *   <statement>
   * 
   * is equivalent to:
   * 
   * <initialize>
   * while (<test>) {
   *   <statement>
   *   <increment>
   * }
   * 
   * and thus the pseudo-assembly for this for statement is just:
   * 
   * <initialize>
   * goto FORTEST
   * FORSTART:
   *   <statement>
   *   <increment>
   * FORTEST:
   *   if (<test>) goto FORSTART
   * 
   * @param init
   * @param test
   * @param increment
   * @param body
   * @return AatStatement
   */
  public AatStatement forStatement(AatStatement init, AatExpression test,
      AatStatement increment, AatStatement body) {
    Label forStart = new Label("forStart");
    Label forTest = new Label("forTest");
    Label forEnd = new Label("forEnd");

    AatSequential root =
        new AatSequential(new AatConditionalJump(test, forStart), new AatLabel(forEnd));
    root = new AatSequential(new AatLabel(forTest), root);
    root = new AatSequential(increment, root);
    root = new AatSequential(body, root);
    root = new AatSequential(new AatLabel(forStart), root);
    root = new AatSequential(new AatJump(forTest), root);
    root = new AatSequential(init, root);

    return root;
  }

  /**
   * Empty statement trees contain no extra information. Empty statements are no-ops, which are
   * removed when the code is transferred into actual assembly.
   *
   * @return AatStatement
   */
  public AatStatement emptyStatement() {
    return new AatEmpty();
  }

  /**
   * Call statement trees contain an assembly language label, and an expression tree for each
   * of the actual parameters in the function call. Call statements represent void function calls.
   * The assembly languague label is the address of the beginning of the called function.
   * 
   * @param actuals
   * @param name
   * @return AatStatement
   */
  public AatStatement callStatement(List<AatExpression> actuals, Label name) {
    return new AatCallStatement(name, actuals);
  }

  
  public AatStatement methodStatement(List<AatExpression> actuals, Label name) {
    // Adding the "this" parameter.
    List<AatExpression> parametersAndThis = new ArrayList<AatExpression>();
    AatMemory memory = new AatMemory(new AatRegister(Register.FP()));
    parametersAndThis.add(memory);
    for (AatExpression parameter : actuals) {
      parametersAndThis.add(parameter);
    }
    
    return new AatCallStatement(name, parametersAndThis);
  }
  
  /**
   * The assignment statement is represented by the Move statement. Move trees have two subtrees -
   * the left subtree is the destination of the move, and the right subtree is the value to move.
   * The left subtree of the move needs to be either a register expression tree (for moving data
   * into a register) or a memory expression tree (for moving data into memory). The right hand
   * side of the move can be any arbitrary expression.
   * 
   * An assignment statement is implemented by moving a value into a memory location. Since the
   * abstract assembly Move tree represents the movement of a value into a register or memory
   * location, an assignment statement can be represented in abstract assembly with a single
   * Move node.
   * 
   * 
   *
   * @param lhs
   * @param rhs
   * @return AatStatement
   */
  public AatStatement assignmentStatement(AatExpression lhs, AatExpression rhs) {
    return new AatMove(lhs, rhs);
  }

  /**
   * Sequential statement trees have two subtrees - a left subtree and a right subtree. They
   * represent the execution of the left subtree, followed by the execution of the right subtree.
   *
   * @param first
   * @param second
   * @return AatStatement
   */
  public AatStatement sequentialStatement(AatStatement first, AatStatement second) {
    return new AatSequential(first, second);
  }

  /**
   * Creating Abstract Assembly for Instance Variables
   * 
   * When a base variable is encountered, we now need to check to see if it is a local variable
   * or an instance variable. Local variables are handled as befored, but instance variables need
   * to be accessed through the "this" pointer.
   * 
   * Base variables, like x.a or y.a, are stored on the heap.
   *
   * @param offset
   * @return AatExpression
   */
  public AatExpression baseInstanceVariable(int offset) {
    AatMemory memory = new AatMemory(new AatRegister(Register.FP()));
    return new AatMemory(new AatOperator(memory, new AatConstant(offset), AatOperator.PLUS));
  }
  
  /**
   * Base variables, like x or y, are stored on the stack and accessed through  the frame pointer.
   *
   * @param offset
   * @return AatExpression
   */
  public AatExpression baseVariable(int offset) {
    return new AatMemory(new AatOperator(new AatRegister(Register.FP()),
        new AatConstant(offset), AatOperator.PLUS));
  }

  /**
   * Array variables are store on the heap, though a pointer to the base of the array is stored on
   * the stack.
   * 
   * @param base
   * @param index
   * @param elementSize
   * @return AatExpression
   */
  public AatExpression arrayVariable(AatExpression base, AatExpression index, int elementSize) {
    AatOperator offsetSizeArray =
        new AatOperator(new AatConstant(elementSize), index, AatOperator.MULTIPLY);
    AatOperator root = new AatOperator(base, offsetSizeArray, AatOperator.MINUS);

    return new AatMemory(root);
  }

  /**
   * Instance variables are very similar to array variables. The only difference is that in array
   * variables, the offset for the index needs to be calculated, while in instance variables, the
   * offset is known at compile time.
   *
   * @param base
   * @param offset
   * @return AatExpression
   */
  public AatExpression classVariable(AatExpression base, int offset) {
    return new AatMemory(new AatOperator(base, new AatConstant(offset), AatOperator.PLUS));
  }

  /**
   * Constant expression represent integer values.
   *
   * @param value
   * @return AatExpression
   */
  public AatExpression constantExpression(int value) {
    return new AatConstant(value);
  }
 
  /**
   * Constant boolean expressions represent boolean values.
   *
   * @param value
   * @return AatExpression
   */
  public AatExpression constantBoolExpression(boolean value) {
    return new AatConstant(value ? 1 : 0);
  }

  /**
   * An operator expression tree consists of two expression trees for the operands, and the
   * operator.
   *
   * @param left
   * @param right
   * @param operator
   * @return AatExpression
   */
  public AatExpression operatorExpression(AatExpression left, AatExpression right, int operator) {
    return new AatOperator(left, right, operator);
  }

  /**
   * A call expression tree contains an assembly language label and an expression tree for each
   * of the actual parameters in the function call. The assembly language label is the address of
   * the beginning of the called function.
   * 
   * @param actuals
   * @param name
   * @return AatExpression
   */
  public AatExpression callExpression(List<AatExpression> actuals, Label name) {
    return new AatCallExpression(name, actuals);
  }

  /**
   * A call expression tree contains an assembly language label and an expression tree for each
   * of the actual parameters in the function call. The assembly language label is the address of
   * the beginning of the called function.
   * 
   * @param actuals
   * @param name
   * @return AatExpression
   */
  public AatExpression methodExpression(List<AatExpression> actuals, Label name) {
    // Adding the "this" parameter.
    List<AatExpression> parametersAndThis = new ArrayList<AatExpression>();
    AatMemory memory = new AatMemory(new AatRegister(Register.FP()));
    parametersAndThis.add(memory);
    for (AatExpression parameter : actuals) {
      parametersAndThis.add(parameter);
    }
    
    return new AatCallExpression(name, parametersAndThis);
  }
  
  /**
   * The assembly tree for a return statement copies the value of the return statement into
   * the result register, and then jumps to the label at the end of the function.
   *
   * @param value
   * @param functionEnd
   * @return AatStatement.
   */
  public AatStatement returnStatement(AatExpression value, Label functionEnd) {
    AatStatement root;
    if (value != null) {
      root = new AatMove(new AatRegister(Register.Result()), value);
      root = new AatSequential(root, new AatJump(functionEnd));
    } else {
      root = new AatJump(functionEnd);
    }

    return root;
  }

  /**
   * The assembly tree for a "return this" statement returns the value of:
   * 
   *     Memory
   *       |
   *      FP
   *
   * @param functionEnd
   * @return AatStatement.
   */
  public AatStatement returnThisStatement(Label functionEnd) {
    AatMemory memory = new AatMemory(new AatRegister(Register.FP()));
    AatStatement root = new AatMove(new AatRegister(Register.Result()), memory);
    root = new AatSequential(root, new AatJump(functionEnd));

    return root;
  }
}

