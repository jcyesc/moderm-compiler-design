package org.compiler.simplejava.ast;

public class AstNewArrayExpression extends AstExpression {
  private String type;
  private AstExpression astExpression;
  private int line;
  private int arrayDimension;

  public AstNewArrayExpression(String type, AstExpression astExpression, int arrayDimension,
      int line) {
    this.type = type;
    this.astExpression = astExpression;
    this.arrayDimension = arrayDimension;
    this.line = line;
  }

  public AstNewArrayExpression(String type, AstExpression astExpression, int line) {
    this(type, astExpression, 0, line);
  }

  public String getType() {
    return type;
  }

  public void setType(String type) {
    this.type = type;
  }

  public AstExpression getAstExpression() {
    return astExpression;
  }

  public void setAstExpression(AstExpression astExpression) {
    this.astExpression = astExpression;
  }

  public int getLine() {
    return line;
  }

  public void setLine(int line) {
    this.line = line;
  }

  public int getArrayDimension() {
    return arrayDimension;
  }

  public void setArrayDimension(int arrayDimension) {
    this.arrayDimension = arrayDimension;
  }

  @Override
  public Object accept(AstVisitor visitor) {
    return visitor.visitNewArrayExpression(this);
  }
}
