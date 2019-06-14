package org.compiler.simplejava.ast;

public class AstDoWhileStatement extends AstStatement {
  private AstExpression testExpression;
  private AstStatement body;
  private int line;

  public AstDoWhileStatement(AstExpression testExpression, AstStatement body, int line) {
    this.testExpression = testExpression;
    this.body = body;
    this.line = line;
  }

  public AstExpression getTestExpression() {
    return testExpression;
  }

  public void setTestExpression(AstExpression testExpression) {
    this.testExpression = testExpression;
  }

  public AstStatement getBody() {
    return body;
  }

  public void setBody(AstStatement body) {
    this.body = body;
  }

  public int getLine() {
    return line;
  }

  public void setLine(int line) {
    this.line = line;
  }

  @Override
  public Object accept(AstVisitor visitor) {
    return visitor.visitDoWhileStatement(this);
  }
}
