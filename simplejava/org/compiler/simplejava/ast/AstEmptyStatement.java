package org.compiler.simplejava.ast;

public class AstEmptyStatement extends AstStatement {

  private int line;

  public AstEmptyStatement(int line) {
    this.line = line;
  }

  public int getLine() {
    return line;
  }

  public void setLine(int line) {
    this.line = line;
  }

  public Object accept(AstVisitor visitor) {
    return visitor.visitEmptyStatement(this);
  }

}
