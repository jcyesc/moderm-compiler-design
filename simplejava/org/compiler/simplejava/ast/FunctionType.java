package org.compiler.simplejava.ast;

public enum FunctionType {
  CONSTRUCTOR("Constructor"),
  FUNCTION("Function"),
  METHOD("Method");
  
  String description;
  FunctionType(String description) {
    this.description = description;
  }
  
  public String toString() {
    return this.description;
  }
}
