package org.compiler.simplejava.ast;

import java.util.ArrayList;
import java.util.List;

public class AstFunctionCallExpression extends AstExpression {

  private String name;
  private List<AstExpression> expressions;
  private int line;

  public AstFunctionCallExpression(String name, int line) {
    this.expressions = new ArrayList<AstExpression>();
    this.name = name;
    this.line = line;
  }

  public AstFunctionCallExpression(String name, AstExpression formal, int line) {
    this(name, line);
    expressions.add(formal);
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public int getLine() {
    return line;
  }

  public void setLine(int line) {
    this.line = line;
  }

  public void addAstExpression(AstExpression astExpression) {
    this.expressions.add(astExpression);
  }

  public int getSize() {
    return this.expressions.size();
  }

  public AstExpression getAstExpression(int index) {
    return expressions.get(index);
  }

  public List<AstExpression> getAstExpressions() {
    return expressions;
  }

  public Object accept(AstVisitor visitor) {
    return visitor.visitFunctionCallExpression(this);
  }
}
