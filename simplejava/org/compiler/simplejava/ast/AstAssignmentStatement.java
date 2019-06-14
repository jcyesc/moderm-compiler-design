package org.compiler.simplejava.ast;

public class AstAssignmentStatement extends AstStatement {

  private AstVariable variable;
  private AstExpression value;
  private int line;

  public AstAssignmentStatement(AstVariable variable, AstExpression value, int line) {
    this.variable = variable;
    this.value = value;
    this.line = line;
  }
  
  public AstVariable getVariable() {
    return variable;
  }

  public void setVariable(AstVariable variable) {
    this.variable = variable;
  }

  public AstExpression getValue() {
    return value;
  }

  public void setValue(AstExpression value) {
    this.value = value;
  }

  public int getLine() {
    return line;
  }

  public void setLine(int line) {
    this.line = line;
  }

  @Override
  public Object accept(AstVisitor visitor) {
    return visitor.visitAssignmentStatement(this);
  }
}
