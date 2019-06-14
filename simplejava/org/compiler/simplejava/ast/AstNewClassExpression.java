package org.compiler.simplejava.ast;

import java.util.ArrayList;
import java.util.List;

public class AstNewClassExpression extends AstExpression {
  private String type;
  private List<AstExpression> expressions;
  private int line;
  
  
  public AstNewClassExpression(String type, int line) {
    this.expressions = new ArrayList<AstExpression>();
    this.type = type;
    this.line = line;
  }

  public String getType() {
    return type;
  }

  public void setType(String type) {
    this.type = type;
  }

  public void addAstExpression(AstExpression astExpression) {
    this.expressions.add(astExpression);
  }

  public List<AstExpression> getExpressions() {
    return expressions;
  }

  public void setExpressions(List<AstExpression> expressions) {
    this.expressions = expressions;
  }

  public int getLine() {
    return line;
  }

  public void setLine(int line) {
    this.line = line;
  }

  @Override
  public Object accept(AstVisitor visitor) {
    return visitor.visitNewClassExpression(this);
  }
}
