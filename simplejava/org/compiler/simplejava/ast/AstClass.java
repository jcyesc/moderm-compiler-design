package org.compiler.simplejava.ast;

public class AstClass {

  private String name;
  private AstInstanceVariableDefs instanceVariableDefs;
  private AstFunction constructorDefinition;
  private AstFunctionDefinitions methodDefinitions;
  private int line;

  public AstClass(String name, AstInstanceVariableDefs instanceVariableDefs,
      AstFunction constructorDefinition,
      AstFunctionDefinitions methodDefinitions, int line) {
    this.name = name;
    this.instanceVariableDefs = instanceVariableDefs;
    this.constructorDefinition = constructorDefinition;
    this.methodDefinitions = methodDefinitions;
    this.line = line;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public AstInstanceVariableDefs getInstanceVariableDefs() {
    return instanceVariableDefs;
  }

  public void setInstanceVariableDefs(AstInstanceVariableDefs instanceVariableDefs) {
    this.instanceVariableDefs = instanceVariableDefs;
  }

  public AstFunctionDefinitions getMethodDefinitions() {
    return methodDefinitions;
  }

  public void setMethodDefinitions(AstFunctionDefinitions methodDefinitions) {
    this.methodDefinitions = methodDefinitions;
  }

  public AstFunction getConstructorDefinition() {
    return constructorDefinition;
  }

  public void setConstructorDefinition(AstFunction constructorDefinition) {
    this.constructorDefinition = constructorDefinition;
  }

  public int getLine() {
    return line;
  }

  public void setLine(int line) {
    this.line = line;
  }

  public Object accept(AstVisitor visitor) {
    return visitor.visitClass(this);
  }
}
