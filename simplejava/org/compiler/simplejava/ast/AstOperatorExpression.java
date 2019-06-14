package org.compiler.simplejava.ast;

public class AstOperatorExpression extends AstExpression {
  private AstExpression leftExpression;
  private AstExpression rightExpression;
  private int operator;
  private int line;

  public static final int BAD_OPERATOR = 0;
  public static final int PLUS = 1;
  public static final int MINUS = 2;
  public static final int MULTIPLY = 3;
  public static final int DIVIDE = 4;
  public static final int AND = 5;
  public static final int OR = 6;
  public static final int EQUAL = 7;
  public static final int NOT_EQUAL = 8;
  public static final int LESS_THAN = 9;
  public static final int LESS_THAN_EQUAL = 10;
  public static final int GREATER_THAN = 11;
  public static final int GREATER_THAN_EQUAL = 12;

  public static final String[] names = { "BAD_OPERATOR", "+", "-", "*", "/",
      "&&", "||", "==", "!=", "<", "<=", ">", ">=" };

  public AstOperatorExpression(AstExpression leftExpression, AstExpression rightExpression,
      int operator, int line) {
    this.leftExpression = leftExpression;
    this.rightExpression = rightExpression;
    this.operator = operator;
    this.line = line;
  }

  public AstOperatorExpression(AstExpression leftExpression, AstExpression rightExpression,
      String operator, int line) {
    this.leftExpression = leftExpression;
    this.rightExpression = rightExpression;
    this.line = line;

    if (operator.compareTo("+") == 0)
      this.operator = PLUS;
    else if (operator.compareTo("-") == 0)
      this.operator = MINUS;
    else if (operator.compareTo("*") == 0)
      this.operator = MULTIPLY;
    else if (operator.compareTo("/") == 0)
      this.operator = DIVIDE;
    else if (operator.toUpperCase().compareTo("&&") == 0)
      this.operator = AND;
    else if (operator.toUpperCase().compareTo("||") == 0)
      this.operator = OR;
    else if (operator.compareTo("==") == 0)
      this.operator = EQUAL;
    else if (operator.compareTo("<") == 0)
      this.operator = LESS_THAN;
    else if (operator.compareTo("<=") == 0)
      this.operator = LESS_THAN_EQUAL;
    else if (operator.compareTo(">") == 0)
      this.operator = GREATER_THAN;
    else if (operator.compareTo(">=") == 0)
      this.operator = GREATER_THAN_EQUAL;
    else if (operator.compareTo("!=") == 0)
      this.operator = NOT_EQUAL;
    else
      this.operator = BAD_OPERATOR;
  }

  public AstExpression getLeftExpression() {
    return leftExpression;
  }

  public void setLeftExpression(AstExpression leftExpression) {
    this.leftExpression = leftExpression;
  }

  public AstExpression getRightExpression() {
    return rightExpression;
  }

  public void setRightExpression(AstExpression rightExpression) {
    this.rightExpression = rightExpression;
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

  @Override
  public Object accept(AstVisitor visitor) {
    return visitor.visitOperatorExpression(this);
  }
}
