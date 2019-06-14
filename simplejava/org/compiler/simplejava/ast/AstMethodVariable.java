package org.compiler.simplejava.ast;

import java.util.ArrayList;
import java.util.List;

public class AstMethodVariable extends AstVariable {

  private AstVariable base;
  private String methodName;
  private int line;
  private List<AstExpression> expressions;

  public AstMethodVariable(AstVariable base, String methodName, int line) {
    this.expressions = new ArrayList<AstExpression>();
    this.base = base;
    this.methodName = methodName;
    this.line = line;
  }

  public AstVariable getBase() {
    return base;
  }

  public void setBase(AstVariable base) {
    this.base = base;
  }

  public String getMethodName() {
    return methodName;
  }

  public void setMethodName(String methodName) {
    this.methodName = methodName;
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

  public List<AstExpression> getExpressions() {
    return expressions;
  }
  
  public void setExpressions(List<AstExpression> expressions) {
    this.expressions = expressions;
  }

  @Override
  public Object accept(AstVisitor visitor) {
    return visitor.visitMethodVariable(this);
  }

}
