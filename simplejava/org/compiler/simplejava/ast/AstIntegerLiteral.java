package org.compiler.simplejava.ast;

public class AstIntegerLiteral extends AstExpression {
  private int value;
  private int line;

  public AstIntegerLiteral(int value, int line) {
    this.value = value;
    this.line = line;
  }

  public int getValue() {
    return value;
  }

  public void setValue(int value) {
    this.value = value;
  }

  public int getLine() {
    return line;
  }

  public void setLine(int line) {
    this.line = line;
  }

  public Object accept(AstVisitor visitor) {
    return visitor.visitIntegerLiteral(this);
  }

  @Override
  public String toString() {
    return String.valueOf(value);
  }
}
