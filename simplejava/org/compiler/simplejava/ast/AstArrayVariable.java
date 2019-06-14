package org.compiler.simplejava.ast;

public class AstArrayVariable extends AstVariable {
  private AstVariable base;
  private AstExpression index;
  private int line;

  public AstArrayVariable(AstVariable base, AstExpression index, int line) {
    this.base = base;
    this.index = index;
    this.line = line;
  }

  public AstVariable getBase() {
    return base;
  }

  public void setBase(AstVariable base) {
    this.base = base;
  }

  public AstExpression getIndex() {
    return index;
  }

  public void setIndex(AstExpression index) {
    this.index = index;
  }

  @Override
  public int getLine() {
    return line;
  }

  public void setLine(int line) {
    this.line = line;
  }

  @Override
  public Object accept(AstVisitor visitor) {
    return visitor.visitArrayVariable(this);
  }
}

