package org.compiler.simplejava.ast;

import java.util.ArrayList;
import java.util.List;

public class AstFunctionDefinitions {

  private List<AstFunctionDefinition> functionDefinitions;

  public AstFunctionDefinitions() {
    this.functionDefinitions = new ArrayList<AstFunctionDefinition>();
  }

  public AstFunctionDefinitions(AstFunctionDefinition functiondefinition) {
    this();
    this.functionDefinitions.add(functiondefinition);
  }

  public void addAstFunctionDefinition(AstFunctionDefinition functiondefinition) {
    functionDefinitions.add(functiondefinition);
  }

  public AstFunctionDefinition elementAt(int index) {
    return functionDefinitions.get(index);
  }

  public int getSize() {
    return functionDefinitions.size();
  }

  public Object accept(AstVisitor visitor) {
    return visitor.visitFunctionDefinitions(this);
  }
}
