
package org.compiler.simplejava.aat;

public class AatOperator extends AatExpression {

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
  public static final int NOT = 13;

  public static final String[] names = { "BAD_OPERATOR", "+", "-", "*", "/",
      "&&", "||", "==", "!=", "<", "<=", ">", ">=", "!" };

  private AatExpression left;
  private AatExpression right;
  private int operator;

  public AatOperator(AatExpression left, AatExpression right, int operator) {
    this.left = left;
    this.right = right;
    this.operator = operator;
  }

  public AatExpression getLeft() {
    return left;
  }

  public void setLeft(AatExpression left) {
    this.left = left;
  }

  public AatExpression getRight() {
    return right;
  }

  public void setRight(AatExpression right) {
    this.right = right;
  }

  public int getOperator() {
    return operator;
  }

  public void setOperator(int operator) {
    this.operator = operator;
  }

  public Object accept(AatVisitor visitor) {
    return visitor.visitOperator(this);
  }
}
