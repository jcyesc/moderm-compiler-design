package org.compiler.simplejava.ast;

public class AstMethodCallStatement extends AstStatement {

  private AstMethodVariable methodVariable;
  private int line;

  public AstMethodCallStatement(AstMethodVariable methodVariable, int line) {
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
    return visitor.visitMethodCallStatement(this);
  }
}
