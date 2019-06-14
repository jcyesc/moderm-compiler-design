package org.compiler.simplejava.ast;

public class AstForStatement extends AstStatement {

  private AstStatement initialization;
  private AstExpression testExpression;
  private AstStatement incrementStmt;
  private AstStatement bodyStmt;
  private int line;

  public AstForStatement(AstStatement initialization, AstExpression testExpression,
      AstStatement incrementStmt, AstStatement bodyStmt, int line) {
    this.initialization = initialization;
    this.testExpression = testExpression;
    this.incrementStmt = incrementStmt;
    this.bodyStmt = bodyStmt;
    this.line = line;
  }

  public AstStatement getInitialization() {
    return initialization;
  }

  public void setInitialization(AstStatement initialization) {
    this.initialization = initialization;
  }

  public AstExpression getTestExpression() {
    return testExpression;
  }

  public void setTestExpression(AstExpression testExpression) {
    this.testExpression = testExpression;
  }

  public AstStatement getIncrementStmt() {
    return incrementStmt;
  }

  public void setIncrementStmt(AstStatement incrementStmt) {
    this.incrementStmt = incrementStmt;
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
    return visitor.visitForStatement(this);
  }
}
