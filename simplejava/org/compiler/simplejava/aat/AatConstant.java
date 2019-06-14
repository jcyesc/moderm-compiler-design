package org.compiler.simplejava.aat;

public class AatConstant extends AatExpression {

  private int value;

  public AatConstant(int value) {
    this.value = value;
  }

  public int getValue() {
    return value;
  }

  public void setValue(int value) {
    this.value = value;
  }

  public Object accept(AatVisitor visitor) {
    return visitor.visitConstant(this);
  }

  @Override
  public String toString() {
    return String.valueOf(value);
  }
}
