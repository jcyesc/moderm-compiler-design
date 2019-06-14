package org.compiler.simplejava.ast;

public class AstPrintTree implements AstVisitor {

  private static final String NEW_LINE = "\n";
  private static final String SPACE = " ";
  static final int indentstep = 3;

  int indentlevel = 0;

  // Keeps all the information that has been printed.
  private StringBuilder astString = new StringBuilder();
  
  private void print(String word) {
    int i;
    for (i = 0; i < indentstep * indentlevel; i++) {
      System.out.print(SPACE);
      astString.append(SPACE);
    }
    System.out.println(word);
    astString.append(word + NEW_LINE);
  }

  public String getAstString() {
    return astString.toString();
  }

  public Object visitArrayVariable(AstArrayVariable array) {
    print("Array Variable (base/index)");
    indentlevel++;
    array.getBase().accept(this);
    array.getIndex().accept(this);
    indentlevel--;
    return null;
  }

  public Object visitAssignmentStatement(AstAssignmentStatement assign) {
    print("Assign (lhs/rhs)");
    indentlevel++;
    assign.getVariable().accept(this);
    assign.getValue().accept(this);
    indentlevel--;
    return null;
  }

  public Object visitBaseVariable(AstBaseVariable base) {
    print("Base Variable " + base.getName());
    return null;
  }

  public Object visitBooleanLiteral(AstBooleanLiteral boolliteral) {
    if (boolliteral.isValue())
      print("TRUE");
    else
      print("FALSE");
    return null;
  }

  public Object visitClass(AstClass classs) {
    print("Class: " + classs.getName());
    indentlevel++;
    if (classs.getInstanceVariableDefs() != null)
      classs.getInstanceVariableDefs().accept(this);
    
    AstFunctionDefinition constructor = classs.getConstructorDefinition();
    if (constructor != null) {
      print("Constructor:");
      indentlevel++;
      constructor.accept(this);
      indentlevel--;
    } else {
      print("No Constructor");
    }
    
    AstFunctionDefinitions methods =  classs.getMethodDefinitions();
    if (methods.getSize() > 0) {
      print("Methods:");
      indentlevel++;
      methods.accept(this);
      indentlevel--;
    } else {
      print("No Methods");
    }

    indentlevel--;
    return null;
  }

  public Object visitClasses(AstClasses classes) {
    int i;

    for (i = 0; i < classes.getSize(); i++)
      classes.getAstClass(i).accept(this);
    return null;
  }

  public Object visitClassVariable(AstClassVariable classvariable) {
    print("Class Variable:");
    indentlevel++;
    classvariable.getBase().accept(this);
    print(classvariable.getVariable());
    indentlevel--;
    return null;
  }

  public Object visitDoWhileStatement(AstDoWhileStatement dowhile) {
    print("Do-while (test/body)");
    indentlevel++;
    dowhile.getTestExpression().accept(this);
    dowhile.getBody().accept(this);
    indentlevel--;
    return null;
  }

  public Object visitEmptyStatement(AstEmptyStatement empty) {
    print("Empty Statement");
    return null;
  }

  public Object visitForStatement(AstForStatement forstmt) {
    print("For (initialize/test/increment/body)");
    indentlevel++;
    forstmt.getInitialization().accept(this);
    forstmt.getTestExpression().accept(this);
    forstmt.getIncrementStmt().accept(this);
    forstmt.getBodyStmt().accept(this);
    indentlevel--;
    return null;
  }

  public Object visitFormal(AstFormal formal) {
    String array = "";
    for (int i = 0; i < formal.getArrayDimension(); i++) {
      array = array + "[]";
    }
    print(formal.getType() + " " + formal.getName() + array);
    return null;
  }

  public Object visitFormals(AstFormals formals) {
    int i;
    if ((formals == null) || formals.getSize() == 0)
      print("No formal parameters");
    else
      print("Formals:");
    indentlevel++;
    for (i = 0; i < formals.getSize(); i++) {
      formals.getAstFormal(i).accept(this);
    }
    indentlevel--;
    return null;
  }

  public Object visitFunction(AstFunction function) {
    print(function.getTypeFunction() + " Definition: " + function.getName() + " Return type: "
        + function.getReturnType());
    // print return type
    indentlevel++;
    if (function.getFormals() != null)
      function.getFormals().accept(this);
    function.getBodyStmt().accept(this);
    indentlevel--;
    return null;
  }

  public Object visitFunctionDefinitions(AstFunctionDefinitions functiondefs) {
    int i;
    for (i = 0; i < functiondefs.getSize(); i++)
      functiondefs.elementAt(i).accept(this);
    return null;
  }

  public Object visitFunctionCallExpression(
      AstFunctionCallExpression functioncall) {
    int i;
    print("Function Call: " + functioncall.getName());
    indentlevel++;
    for (i = 0; i < functioncall.getSize(); i++)
      functioncall.getAstExpression(i).accept(this);
    indentlevel--;
    return null;
  }

  public Object visitFunctionCallStatement(AstFunctionCallStatement functioncall) {
    int i;
    print("Function Call: " + functioncall.getName());
    indentlevel++;
    for (i = 0; i < functioncall.getSize(); i++)
      functioncall.getAstExpression(i).accept(this);
    indentlevel--;
    return null;
  }

  public Object visitIfStatement(AstIfStatement ifstmt) {
    print("If (test/if body/else body)");
    indentlevel++;
    ifstmt.getTestExpression().accept(this);
    ifstmt.getThenStmt().accept(this);
    if (ifstmt.getElseStmt() != null)
      ifstmt.getElseStmt().accept(this);
    else
      print("No else statement");
    indentlevel--;
    return null;
  }

  public Object visitIntegerLiteral(AstIntegerLiteral literal) {
    print(Integer.toString(literal.getValue()));
    return null;
  }

  public Object visitNewArrayExpression(AstNewArrayExpression newarray) {
    String suffix = "";
    int i;
    for (i = 0; i < newarray.getArrayDimension(); i++)
      suffix = suffix + "[]";
    print("New Array of " + newarray.getType() + suffix);
    // print out dimensionality of element
    indentlevel++;
    newarray.getAstExpression().accept(this);
    indentlevel--;
    return null;
  }

  public Object visitNewClassExpression(AstNewClassExpression newclass) {
    print("New " + newclass.getType());

    indentlevel++;
    if (newclass.getExpressions().size() > 0) {
      print("Arguments:");
      indentlevel++;
      for(AstExpression expr : newclass.getExpressions()) {
        expr.accept(this);
      }
      indentlevel--;
    } else {
      print("No arguments");
    }
    indentlevel--;
    return null;
  }

  public Object visitOperatorExpression(AstOperatorExpression opexpr) {
    print(AstOperatorExpression.names[opexpr.getOperator()]);
    indentlevel++;
    opexpr.getLeftExpression().accept(this);
    opexpr.getRightExpression().accept(this);
    indentlevel--;
    return null;
  }

  public Object visitProgram(AstProgram program) {
    if (program.getClasses() != null)
      program.getClasses().accept(this);
    if (program.getFunctiondefinitions() != null)
      program.getFunctiondefinitions().accept(this);
    return null;
  }

  public Object visitPrototype(AstPrototype prototype) {
    int i;
    print("Prototype: " + prototype.getType() + " " + prototype.getName());
    indentlevel++;
    if (prototype.getFormals() != null) {
      for (i = 0; i < prototype.getFormals().getSize(); i++)
        prototype.getFormals().getAstFormal(i).accept(this);
      if (prototype.getFormals().getSize() == 0)
        print("No Formals");

    } else {
      print("No Formals");
    }
    indentlevel--;
    return null;
  }

  public Object visitReturnStatement(AstReturnStatement ret) {
    print("Return");
    indentlevel++;
    if (ret.getValue() != null)
      ret.getValue().accept(this);
    indentlevel--;
    return null;
  }

  public Object visitStatements(AstStatements statements) {
    int i;
    print("{");
    indentlevel++;
    for (i = 0; i < statements.getSize(); i++)
      statements.getAstStatment(i).accept(this);
    indentlevel--;
    print("}");
    return null;
  }

  public Object visitUnaryOperatorExpression(AstUnaryOperatorExpression operator) {
    print(AstUnaryOperatorExpression.names[operator.getOperator()]);
    indentlevel++;
    operator.getOperand().accept(this);
    indentlevel--;
    return null;
  }

  public Object visitInstanceVariableDef(AstInstanceVariableDef variabledef) {
    String array = "";
    for (int i = 0; i < variabledef.getArrayDimension(); i++) {
      array = array + "[]";
    }
    print(variabledef.getType() + " " + variabledef.getName() + array);
    return null;
  }

  public Object visitInstanceVariableDefs(AstInstanceVariableDefs variabledefs) {
    int i;
    for (i = 0; i < variabledefs.getSize(); i++) {
      variabledefs.getAstInstanceVariableDef(i).accept(this);
    }
    return null;
  }

  public Object visitVariableExpression(AstVariableExpression variableexpression) {
    variableexpression.getVariable().accept(this);
    return null;
  }

  public Object visitWhileStatement(AstWhileStatement whilestatement) {
    print("While (test/body)");
    indentlevel++;
    whilestatement.getTestExpression().accept(this);
    whilestatement.getBodyStmt().accept(this);
    indentlevel--;
    return null;
  }

  public Object visitVariableDefStatement(AstVariableDefStatement variabledef) {
    String array = "";
    for (int i = 0; i < variabledef.getArrayDimension(); i++) {
      array = array + "[]";
    }
    print(variabledef.getType() + " " + variabledef.getName() + array);
    indentlevel++;
    if (variabledef.getInitExpression() != null) {
      variabledef.getInitExpression().accept(this);
    }
    indentlevel--;
    return null;

  }
  
  @Override
  public Object visitMethodVariable(AstMethodVariable methodVariable) {
    print(String.format("Method Variable (base/%s())", methodVariable.getMethodName()));
    indentlevel++;
    methodVariable.getBase().accept(this);

    if (methodVariable.getExpressions().size() > 0) {
      print("Arguments:");
      indentlevel++;
      for(AstExpression expr : methodVariable.getExpressions()) {
        expr.accept(this);
      }
      indentlevel--;
    } else {
      print("No arguments");
    }

    indentlevel--;
    return null;
  }

  @Override
  public Object visitMethodCallStatement(
      AstMethodCallStatement methodCallStatement) {
    print("Method Call Statement:");
    indentlevel++;
    methodCallStatement.getMethodVariable().accept(this);
    indentlevel--;
    return null;
  }

  @Override
  public Object visitMethodCallExpression(
      AstMethodCallExpression methodCallExpression) {
    print("Method Call Expression:");
    indentlevel++;
    methodCallExpression.getMethodVariable().accept(this);
    indentlevel--;
    return null;
  }

}
