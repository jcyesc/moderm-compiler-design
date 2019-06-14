package org.compiler.simplejava.aat;

public abstract class AatExpression {

  public abstract Object accept(AatVisitor visitor);

}
