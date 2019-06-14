package org.compiler.simplejava.ast;

public class AstIfStatement extends AstStatement {

  private AstExpression testExpression;
  private AstStatement thenStmt;
  private AstStatement elseStmt;
  private int line;

  public AstIfStatement(AstExpression testExpression, AstStatement thenStmt,
      AstStatement elseStmt, int line) {
    this.testExpression = testExpression;
    this.thenStmt = thenStmt;
    this.elseStmt = elseStmt;
    this.line = line;
  }

  public AstExpression getTestExpression() {
    return testExpression;
  }

  public void setTestExpression(AstExpression testExpression) {
    this.testExpression = testExpression;
  }

  public AstStatement getThenStmt() {
    return thenStmt;
  }

  public void setThenStmt(AstStatement thenStmt) {
    this.thenStmt = thenStmt;
  }

  public AstStatement getElseStmt() {
    return elseStmt;
  }

  public void setElseStmt(AstStatement elseStmt) {
    this.elseStmt = elseStmt;
  }

  public int getLine() {
    return line;
  }

  public void setLine(int line) {
    this.line = line;
  }

  @Override
  public Object accept(AstVisitor visitor) {
    return visitor.visitIfStatement(this);
  }
}
