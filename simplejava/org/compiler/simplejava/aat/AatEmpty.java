package org.compiler.simplejava.aat;

public class AatEmpty extends AatStatement {

  public Object accept(AatVisitor visitor) {
    return visitor.visitEmpty(this);
  }

}
