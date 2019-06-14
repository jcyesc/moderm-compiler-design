package org.compiler.simplejava.semantic;

public class ArrayType implements Type {

  private Type type;

  public ArrayType(Type type) {
    this.type = type;
  }

  public Type getType() {
    return type;
  }

  public void setType(Type type) {
    this.type = type;
  }

  @Override
  public String toString() {
    return type.toString() + "[]"; 
  }
}
