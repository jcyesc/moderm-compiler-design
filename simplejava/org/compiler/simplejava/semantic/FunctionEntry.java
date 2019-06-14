package org.compiler.simplejava.semantic;

import java.util.List;

import org.compiler.simplejava.ast.FunctionType;

public class FunctionEntry {

  private Type returnType;
  
  private List<Type> formals;
  private Label startLabel;
  private Label endLabel;
  private FunctionType functionType = FunctionType.FUNCTION;

  public FunctionEntry(Type returnType, List<Type> formals) {
    this.returnType = returnType;
    this.formals = formals;
    this.startLabel = null;
    this.endLabel = null;
  }

  public FunctionEntry(Type returnType, List<Type> formals, Label startLabel, Label endLabel) {
    this.returnType = returnType;
    this.formals = formals;
    this.startLabel = startLabel;
    this.endLabel = endLabel;
  }

  public Type getReturnType() {
    return returnType;
  }

  public void setReturnType(Type returnType) {
    this.returnType = returnType;
  }

  public List<Type> getFormals() {
    return formals;
  }

  public void setFormals(List<Type> formals) {
    this.formals = formals;
  }

  public Label getStartLabel() {
    return startLabel;
  }

  public void setStartLabel(Label startLabel) {
    this.startLabel = startLabel;
  }

  public Label getEndLabel() {
    return endLabel;
  }

  public void setEndLabel(Label endLabel) {
    this.endLabel = endLabel;
  }

  public FunctionType getFunctionType() {
    return functionType;
  }

  public void setFunctionType(FunctionType functionType) {
    this.functionType = functionType;
  }
}
