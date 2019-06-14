package org.compiler.simplejava.ast;

public class AstFormal {
  private String name;
  private String type;
  private int arrayDimension;
  int line;
  
  public AstFormal(String type, String name, int line) {
    this.type = type;
    this.name = name;
    this.arrayDimension = 0;
    this.line = line;
  }

  public AstFormal(String type, String name, int arrayDimension, int line) {
    this(type, name, line);
    this.arrayDimension = arrayDimension;
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
    return visitor.visitFormal(this);
  }
}
