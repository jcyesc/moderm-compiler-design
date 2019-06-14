package org.compiler.simplejava.aat;

import java.util.List;

import org.compiler.simplejava.semantic.Type;

public class ExpressionRecord {
  private Type type;
  private AatExpression expressionTree;
  private AatStatement statementTree;
  private List<AatExpression> expressions;

  public ExpressionRecord() {
  }

  public ExpressionRecord(Type type, AatStatement stmtTree) {
    this.type = type;
    this.statementTree = stmtTree;
  }

  public ExpressionRecord(Type type, AatExpression expressionTree) {
    this.type = type;
    this.expressionTree = expressionTree;
  }

  public ExpressionRecord(Type type, List<AatExpression> expressions) {
    this.type = type;
    this.expressions = expressions;
  }

  public Type getType() {
    return type;
  }

  public void setType(Type type) {
    this.type = type;
  }

  public AatExpression getExpressionTree() {
    return expressionTree;
  }

  public void setExpressionTree(AatExpression expressionTree) {
    this.expressionTree = expressionTree;
  }

  public List<AatExpression> getExpressions() {
    return expressions;
  }

  public void setExpressions(List<AatExpression> expressions) {
    this.expressions = expressions;
  }

  public AatStatement getStatementTree() {
    return statementTree;
  }

  public void setStatementTree(AatStatement statementTree) {
    this.statementTree = statementTree;
  }

}
