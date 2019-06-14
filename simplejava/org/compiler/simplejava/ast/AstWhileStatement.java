package org.compiler.simplejava.ast;

public class AstWhileStatement extends AstStatement {
  private AstExpression testExpression;
  private AstStatement bodyStmt;
  private int line;

  public AstWhileStatement(AstExpression testExpression, AstStatement bodyStmt, int line) {
    this.testExpression = testExpression;
    this.bodyStmt = bodyStmt;
    this.line = line;
  }

  public AstExpression getTestExpression() {
    return testExpression;
  }

  public void setTestExpression(AstExpression testExpression) {
    this.testExpression = testExpression;
  }

  public AstStatement getBodyStmt() {
    return bodyStmt;
  }

  public void setBodyStmt(AstStatement bodyStmt) {
    this.bodyStmt = bodyStmt;
  }

  public int getLine() {
    return line;
  }

  public void setLine(int line) {
    this.line = line;
  }

  @Override
  public Object accept(AstVisitor visitor) {
    return visitor.visitWhileStatement(this);
  }
}
