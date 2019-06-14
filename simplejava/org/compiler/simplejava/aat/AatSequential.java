package org.compiler.simplejava.aat;

public class AatSequential extends AatStatement {

  private AatStatement left;
  private AatStatement right;

  public AatSequential(AatStatement left, AatStatement right) {
    this.left = left;
    this.right = right;
  }

  public AatStatement getLeft() {
    return left;
  }

  public void setLeft(AatStatement left) {
    this.left = left;
  }

  public AatStatement getRight() {
    return right;
  }

  public void setRight(AatStatement right) {
    this.right = right;
  }

  public Object accept(AatVisitor visitor) {
    return visitor.visitSequential(this);
  }
}
