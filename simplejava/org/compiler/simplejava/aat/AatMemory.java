package org.compiler.simplejava.aat;

public class AatMemory extends AatExpression {
  private AatExpression memory;
  
  public AatMemory(AatExpression memory) {
    this.memory = memory;
  }

  public AatExpression getMemory() {
    return memory;
  }

  public void setMemory(AatExpression memory) {
    this.memory = memory;
  }

  public Object accept(AatVisitor visitor) {
    return visitor.visitMemory(this);

  }
}
