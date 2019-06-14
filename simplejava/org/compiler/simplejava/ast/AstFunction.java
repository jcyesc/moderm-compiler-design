package org.compiler.simplejava.ast;

public class AstFunction extends AstFunctionDefinition {
  
  private String returnType;
  private String name;
  private AstFormals formals;
  private AstStatement bodyStmt;
  private FunctionType typeFunction;
  private int line;
  
  public AstFunction(String returnType, String name, AstFormals formals,
      AstStatement bodyStmt, int line) {
    this(returnType, name, formals, bodyStmt, FunctionType.FUNCTION, line);
  }

  public AstFunction(String returnType, String name, AstFormals formals,
      AstStatement bodyStmt, FunctionType typeFunction, int line) {
    this.returnType = returnType;
    this.name = name;
    this.formals = formals;
    this.bodyStmt = bodyStmt;
    this.typeFunction = typeFunction;
    this.line = line;
  }

  public String getReturnType() {
    return returnType;
  }

  public void setReturnType(String returnType) {
    this.returnType = returnType;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public AstFormals getFormals() {
    return formals;
  }

  public void setFormals(AstFormals formals) {
    this.formals = formals;
  }

  public AstStatement getBodyStmt() {
    return bodyStmt;
  }

  public void setBodyStmt(AstStatement bodyStmt) {
    this.bodyStmt = bodyStmt;
  }

  public FunctionType getTypeFunction() {
    return typeFunction;
  }

  public void setTypeFunction(FunctionType typeFunction) {
    this.typeFunction = typeFunction;
  }

  public int getLine() {
    return line;
  }

  public void setLine(int line) {
    this.line = line;
  }

  public Object accept(AstVisitor visitor) {
    return visitor.visitFunction(this);
  }

  @Override
  public String toString() {
    return "AstFunction: " + this.name;
  }
  
}
