package org.compiler.simplejava.semantic;

public class ClassType implements Type {

  private VariableEnvironment variableEnvironment;
  private FunctionEnvironment methodEnvironment;

  private String name;

  public ClassType(VariableEnvironment variableEnvironment, FunctionEnvironment methodEnv) {
    this.variableEnvironment = variableEnvironment;
    this.methodEnvironment = methodEnv;
  }

  public VariableEnvironment getVariableEnvironment() {
    return variableEnvironment;
  }

  public void setVariableEnvironment(VariableEnvironment variableEnvironment) {
    this.variableEnvironment = variableEnvironment;
  }

  public FunctionEnvironment getMethodEnvironment() {
    return methodEnvironment;
  }

  public void setMethodEnvironment(FunctionEnvironment methodEnvironment) {
    this.methodEnvironment = methodEnvironment;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  @Override
  public String toString() {
    return name; 
  }
}
