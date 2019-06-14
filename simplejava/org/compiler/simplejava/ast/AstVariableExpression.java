package org.compiler.simplejava.ast;

public class AstVariableExpression extends AstExpression {

  private AstVariable variable;
  private int line;

  public AstVariableExpression(AstVariable variable, int line) {
    this.variable = variable;
    this.line = line;
  }

  public AstVariable getVariable() {
    return variable;
  }

  public void setVariable(AstVariable variable) {
    this.variable = variable;
  }

  public int getLine() {
    return line;
  }

  public void setLine(int line) {
    this.line = line;
  }

  @Override
  public Object accept(AstVisitor visitor) {
    return visitor.visitVariableExpression(this);
  }
}
