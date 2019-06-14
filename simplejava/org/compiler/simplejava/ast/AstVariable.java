
package org.compiler.simplejava.ast;

public abstract class AstVariable {

    public abstract Object accept(AstVisitor V);
  
    public abstract int getLine();
}
