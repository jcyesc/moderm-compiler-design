package org.compiler.simplejava.ast;

public class AstBaseVariable extends AstVariable {

  private String name;
  private int line;

  public AstBaseVariable(String name, int line) {
    this.name = name;
    this.line = line;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public int getLine() {
    return line;
  }

  public void setLine(int line) {
    this.line = line;
  }

  public Object accept(AstVisitor visitor) {
    return visitor.visitBaseVariable(this);
  }
}
