package org.compiler.simplejava.aat;

public abstract class AatStatement {

  public abstract Object accept(AatVisitor V);
}
