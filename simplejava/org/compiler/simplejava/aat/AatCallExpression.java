package org.compiler.simplejava.aat;

import java.util.List;

import org.compiler.simplejava.semantic.Label;

public class AatCallExpression extends AatExpression {

  private Label label;
  private List<AatExpression> actuals;

  public AatCallExpression(Label label, List<AatExpression> actuals) {
    this.label = label;
    this.actuals = actuals;
  }

  public Label getLabel() {
    return label;
  }

  public void setLabel(Label label) {
    this.label = label;
  }

  public List<AatExpression> getActuals() {
    return actuals;
  }

  public void setActuals(List<AatExpression> actuals) {
    this.actuals = actuals;
  }

  public Object accept(AatVisitor visitor) {
    return visitor.visitCallExpression(this);
  }
}
