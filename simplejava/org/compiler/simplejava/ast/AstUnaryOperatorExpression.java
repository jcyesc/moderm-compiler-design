package org.compiler.simplejava.ast;

public class AstUnaryOperatorExpression extends AstExpression {
  private AstExpression operand;
  private int operator;
  private int line;

  public static final int BAD_OPERATOR = 0;
  public static final int NOT = 1;

  public static final String[] names = { "BAD_OPERATOR", "!" };

  public AstUnaryOperatorExpression(AstExpression operand, int operator, int line) {
    this.operator = operator;
    this.operand = operand;
    this.line = line;
  }

  public AstUnaryOperatorExpression(AstExpression operand, String operator, int line) {
    this.operand = operand;
    if (operator.compareTo("!") == 0) {
      this.operator = NOT;
    } else {
      this.operator = BAD_OPERATOR;
    }
    this.line = line;
  }
  
  public AstExpression getOperand() {
    return operand;
  }

  public void setOperand(AstExpression operand) {
    this.operand = operand;
  }

  public int getOperator() {
    return operator;
  }

  public void setOperator(int operator) {
    this.operator = operator;
  }

  public int getLine() {
    return line;
  }

  public void setLine(int line) {
    this.line = line;
  }

  public Object accept(AstVisitor visitor) {
    return visitor.visitUnaryOperatorExpression(this);
  }
}
