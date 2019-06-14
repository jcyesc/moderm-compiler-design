package org.compiler.simplejava.ast;

import java.util.ArrayList;
import java.util.List;

public class AstFormals {
  private List<AstFormal> formals;

  public AstFormals() {
    this.formals = new ArrayList<AstFormal>();
  }

  public AstFormals(AstFormal formal) {
    this();
    formals.add(formal);
  }

  public void addAstFormal(AstFormal formal) {
    formals.add(formal);
  }

  public AstFormal getAstFormal(int index) {
    return formals.get(index);
  }

  public int getSize() {
    return formals.size();
  }

  public Object accept(AstVisitor visitor) {
    return visitor.visitFormals(this);
  }
}
