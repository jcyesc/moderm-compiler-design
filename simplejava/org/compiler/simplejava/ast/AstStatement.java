package org.compiler.simplejava.ast;

public abstract class AstStatement {

    public abstract Object accept(AstVisitor visitor);
}
