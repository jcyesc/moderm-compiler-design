package org.compiler.simplejava.semantic;

public class BooleanType implements Type {

  static private BooleanType instance;

  private BooleanType() {
  }

  public static BooleanType getInstance() {
    if (instance == null) {
      instance = new BooleanType();
    }

    return instance;
  }

  @Override
  public String toString() {
    return "boolean";
  }
}
