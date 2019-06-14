package org.compiler.simplejava.aat;

public class AatRegister extends AatExpression {

  private Register register;

  public AatRegister(Register register) {
    this.register = register;
  }

  public Register getRegister() {
    return register;
  }

  public void setRegister(Register register) {
    this.register = register;
  }

  public Object accept(AatVisitor visitor) {
    return visitor.visitRegister(this);
  }
}
