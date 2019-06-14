package org.compiler.simplejava.ast;

public class AstBooleanLiteral extends AstExpression {

  private boolean value;
  private int line;

  public AstBooleanLiteral(boolean value, int line) {
    this.value = value;
    this.line = line;
  }

  public boolean isValue() {
    return value;
  }

  public void setValue(boolean value) {
    this.value = value;
  }

  public int getLine() {
    return line;
  }

  public void setLine(int line) {
    this.line = line;
  }

  @Override
  public Object accept(AstVisitor visitor) {
    return visitor.visitBooleanLiteral(this);
  }
}
