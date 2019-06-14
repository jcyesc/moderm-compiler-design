package org.compiler.simplejava.ast;

public class AstClassVariable extends AstVariable {

  private AstVariable base;
  private String variable;
  private int line;

  public AstClassVariable(AstVariable base, String variable, int line) {
    this.base = base;
    this.variable = variable;
    this.line = line;
  }

  public AstVariable getBase() {
    return base;
  }

  public void setBase(AstVariable base) {
    this.base = base;
  }

  public String getVariable() {
    return variable;
  }

  public void setVariable(String variable) {
    this.variable = variable;
  }

  public int getLine() {
    return line;
  }

  public void setLine(int line) {
    this.line = line;
  }

  public Object accept(AstVisitor visitor) {
    return visitor.visitClassVariable(this);
  }

}
