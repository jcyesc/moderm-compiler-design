package org.compiler.simplejava.aat;

public class AatReturn extends AatStatement {

  public Object accept(AatVisitor visitor) {
    return visitor.visitReturn(this);
  }
}
