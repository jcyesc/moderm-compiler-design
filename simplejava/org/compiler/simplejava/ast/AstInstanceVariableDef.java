package org.compiler.simplejava.ast;

public class AstInstanceVariableDef {
  private String name;
  private String type;
  private int arrayDimension;
  private int line;

  public AstInstanceVariableDef(String type, String name, int line) {
    this(type, name, 0, line);
  }

  public AstInstanceVariableDef(String type, String name, int arrayDimension, int line) {
    this.type = type;
    this.name = name;
    this.arrayDimension = arrayDimension;
    this.line = line;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getType() {
    return type;
  }

  public void setType(String type) {
    this.type = type;
  }

  public int getArrayDimension() {
    return arrayDimension;
  }

  public void setArrayDimension(int arrayDimension) {
    this.arrayDimension = arrayDimension;
  }

  public int getLine() {
    return line;
  }

  public void setLine(int line) {
    this.line = line;
  }

  public boolean isArray() {
    return arrayDimension > 0;
  }

  public Object accept(AstVisitor visitor) {
    return visitor.visitInstanceVariableDef(this);
  }
}
