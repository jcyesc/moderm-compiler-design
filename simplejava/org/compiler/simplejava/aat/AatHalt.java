package org.compiler.simplejava.aat;

public class AatHalt extends AatStatement {

  public Object accept(AatVisitor visitor) {
    return visitor.visitHalt(this);
  }
}
