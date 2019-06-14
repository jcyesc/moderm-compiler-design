package org.compiler.simplejava.ast;

public class AstVariableDefStatement extends AstStatement {

  private String name;
  private String type;
  private int arrayDimension;
  private int line;
  private AstExpression initExpression;

  public AstVariableDefStatement(String type, String name, int line) {
    this(type, name, 0, null, line);
  }

  public AstVariableDefStatement(String type, String name, AstExpression initExpression, int line) {
    this(type, name, 0, initExpression, line);
  }

  public AstVariableDefStatement(String type, String name, int arrayDimension, int line) {
    this(type, name, arrayDimension, null, line);
  }

  public AstVariableDefStatement(String type, String name, int arrayDimension,
      AstExpression initExpression, int line) {
    this.type = type;
    this.name = name;
    this.arrayDimension = arrayDimension;
    this.initExpression = initExpression;
    this.line = line;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getType() {
    return type;
  }

  public void setType(String type) {
    this.type = type;
  }

  public int getArrayDimension() {
    return arrayDimension;
  }

  public void setArrayDimension(int arrayDimension) {
    this.arrayDimension = arrayDimension;
  }

  public int getLine() {
    return line;
  }

  public void setLine(int line) {
    this.line = line;
  }

  public AstExpression getInitExpression() {
    return initExpression;
  }

  public void setInitExpression(AstExpression initExpression) {
    this.initExpression = initExpression;
  }

  @Override
  public Object accept(AstVisitor visitor) {
    return visitor.visitVariableDefStatement(this);
  }

  public boolean isArray() {
    return arrayDimension > 0;
  }
}
