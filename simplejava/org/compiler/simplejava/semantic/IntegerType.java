package org.compiler.simplejava.semantic;

public class IntegerType implements Type {

  static private IntegerType instance;
  
  private IntegerType() {
  }

  public static IntegerType getInstance() {
    if (instance == null) {
      instance = new IntegerType();
    }

    return instance;
  }

  @Override
  public String toString() {
    return "int";
  }
}
