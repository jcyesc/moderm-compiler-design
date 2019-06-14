package org.compiler.simplejava.ast;

public class AstReturnStatement extends AstStatement {

  private AstExpression value;
  private int line;

  public AstReturnStatement(AstExpression value, int line) {
    this.value = value;
    this.line = line;
  }

  public AstExpression getValue() {
    return value;
  }

  public void setValue(AstExpression value) {
    this.value = value;
  }

  public int getLine() {
    return line;
  }

  public void setLine(int line) {
    this.line = line;
  }

  public Object accept(AstVisitor visitor) {
    return visitor.visitReturnStatement(this);
  }
}
