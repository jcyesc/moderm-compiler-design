package org.compiler.simplejava.ast;

import java.util.ArrayList;
import java.util.List;

public class AstFunctionCallStatement extends AstStatement {

  private String name;
  private List<AstExpression> expressions;
  private int line;

  public AstFunctionCallStatement(String name, int line) {
    this.expressions = new ArrayList<AstExpression>();
    this.name = name;
    this.line = line;
  }

  public AstFunctionCallStatement(String name, AstExpression formal, int line) {
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
    expressions.add(astExpression);
  }

  public int getSize() {
    return expressions.size();
  }

  public AstExpression getAstExpression(int index) {
    return expressions.get(index);
  }

  public List<AstExpression> getAstExpressions() {
    return expressions;
  }

  @Override
  public Object accept(AstVisitor visitor) {
    return visitor.visitFunctionCallStatement(this);
  }
}
