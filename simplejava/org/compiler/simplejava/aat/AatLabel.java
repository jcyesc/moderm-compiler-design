
package org.compiler.simplejava.aat;

import org.compiler.simplejava.semantic.Label;

public class AatLabel extends AatStatement {

  private Label label;

  public AatLabel(Label label) {
    this.label = label;
  }

  public Label getLabel() {
    return label;
  }

  public void setLabel(Label label) {
    this.label = label;
  }

  public Object accept(AatVisitor visitor) {
    return visitor.visitLabel(this);
  }
}
