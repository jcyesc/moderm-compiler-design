package org.compiler.simplejava.ast;

public abstract class AstExpression {

    public abstract Object accept(AstVisitor visitor);
    public abstract int getLine();
}
