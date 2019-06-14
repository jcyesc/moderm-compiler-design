package org.compiler.simplejava.ast;

public class AstProgram {
  private AstClasses classes;
  private AstFunctionDefinitions functiondefinitions;
  private int line;

  public AstProgram(AstClasses classes,
      AstFunctionDefinitions functiondefinitions, int line) {
    this.classes = classes;
    this.functiondefinitions = functiondefinitions;
    this.line = line;
  }

  public AstClasses getClasses() {
    return classes;
  }

  public void setClasses(AstClasses classes) {
    this.classes = classes;
  }

  public AstFunctionDefinitions getFunctiondefinitions() {
    return functiondefinitions;
  }

  public void setFunctiondefinitions(AstFunctionDefinitions functiondefinitions) {
    this.functiondefinitions = functiondefinitions;
  }

  public int getLine() {
    return line;
  }

  public void setLine(int line) {
    this.line = line;
  }

  public Object accept(AstVisitor visitor) {
    return visitor.visitProgram(this);
  }
}
