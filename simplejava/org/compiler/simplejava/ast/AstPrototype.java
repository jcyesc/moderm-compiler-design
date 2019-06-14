package org.compiler.simplejava.ast;

public class AstPrototype extends AstFunctionDefinition {

  private String type;
  private String name;
  private AstFormals formals;
  private FunctionType typeFunction;
  private int line;

  public AstPrototype(String type, String name, AstFormals formals, int line) {
    this(type, name, formals, FunctionType.FUNCTION, line);
  }

  public AstPrototype(String type, String name, AstFormals formals,  FunctionType typeFunction, int line) {
    this.type = type;
    this.name = name;
    this.formals = formals;
    this.typeFunction = typeFunction;
    this.line = line;
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

  public String getType() {
    return type;
  }

  public void setType(String type) {
    this.type = type;
  }

  public Object accept(AstVisitor visitor) {
    return visitor.visitPrototype(this);
  }

  @Override
  public String toString() {
    return "AstPrototype: " + this.name;
  }
}
