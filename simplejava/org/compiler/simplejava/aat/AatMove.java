package org.compiler.simplejava.aat;

public class AatMove extends AatStatement {

  private AatExpression lhs;
  private AatExpression rhs;

  public AatMove(AatExpression lhs, AatExpression rhs) {
    this.lhs = lhs;
    this.rhs = rhs;
  }

  public AatExpression getLhs() {
    return lhs;
  }

  public void setLhs(AatExpression lhs) {
    this.lhs = lhs;
  }

  public AatExpression getRhs() {
    return rhs;
  }

  public void setRhs(AatExpression rhs) {
    this.rhs = rhs;
  }

  public Object accept(AatVisitor visitor) {
    return visitor.visitMove(this);
  }
}
