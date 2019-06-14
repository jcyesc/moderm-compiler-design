package org.compiler.simplejava.ast;

import java.util.ArrayList;
import java.util.List;

public class AstInstanceVariableDefs {
  private List<AstInstanceVariableDef> variableDefs;

  public AstInstanceVariableDefs() {
    this.variableDefs = new ArrayList<AstInstanceVariableDef>();
  }

  public AstInstanceVariableDefs(AstInstanceVariableDef variabledef) {
    this();
    variableDefs.add(variabledef);
  }

  public void addAstInstanceVariableDef(AstInstanceVariableDef variabledef) {
    variableDefs.add(variabledef);
  }

  public AstInstanceVariableDef getAstInstanceVariableDef(int index) {
    return variableDefs.get(index);
  }

  public int getSize() {
    return variableDefs.size();
  }

  public Object accept(AstVisitor visitor) {
    return visitor.visitInstanceVariableDefs(this);
  }
}
