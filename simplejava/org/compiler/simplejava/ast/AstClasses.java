package org.compiler.simplejava.ast;

import java.util.ArrayList;
import java.util.List;

public class AstClasses {
  private List<AstClass> classes;
  
  public AstClasses() {
    this.classes = new ArrayList<AstClass>();
  }

  public AstClasses(AstClass clazz) {
    this();
    this.classes.add(clazz);
  }

  public void addAstClass(AstClass clazz) {
    this.classes.add(clazz);
  }

  public AstClass getAstClass(int index) {
    return (AstClass) this.classes.get(index);
  }

  public int getSize() {
    return this.classes.size();
  }

  public Object accept(AstVisitor visitor) {
    return visitor.visitClasses(this);
  }
}
