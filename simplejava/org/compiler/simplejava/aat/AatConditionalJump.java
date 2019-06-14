package org.compiler.simplejava.aat;

import org.compiler.simplejava.semantic.Label;

public class AatConditionalJump extends AatStatement {

  private Label label;
  private AatExpression test;

  public AatConditionalJump(AatExpression test, Label label) {
    this.label = label;
    this.test = test;
  }

  public Label getLabel() {
    return label;
  }

  public void setLabel(Label label) {
    this.label = label;
  }

  public AatExpression getTest() {
    return test;
  }

  public void setTest(AatExpression test) {
    this.test = test;
  }

  public Object accept(AatVisitor visitor) {
    return visitor.visitConditionalJump(this);
  }

}
