package org.compiler.simplejava.ast;

public class AstMethodCallExpression extends AstExpression {

  private AstMethodVariable methodVariable;
  private int line;

  public AstMethodCallExpression(AstMethodVariable methodVariable, int line) {
    this.methodVariable = methodVariable;
    this.line = line;
  }
  
  public AstMethodVariable getMethodVariable() {
    return methodVariable;
  }

  public void setMethodVariable(AstMethodVariable methodVariable) {
    this.methodVariable = methodVariable;
  }

  public int getLine() {
    return line;
  }

  public void setLine(int line) {
    this.line = line;
  }

  @Override
  public Object accept(AstVisitor visitor) {
    return visitor.visitMethodCallExpression(this);
  }
}
