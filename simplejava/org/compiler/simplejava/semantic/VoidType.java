package org.compiler.simplejava.semantic;

public class VoidType implements Type {

  static private VoidType instance;

  private VoidType() {
  }

  public static VoidType getInstance() {
    if (instance == null) {
      instance = new VoidType();
    }

    return instance;
  }

  @Override
  public String toString() {
    return "void";
  }
}
