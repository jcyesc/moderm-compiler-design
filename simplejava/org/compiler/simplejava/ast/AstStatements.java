package org.compiler.simplejava.ast;

import java.util.ArrayList;
import java.util.List;

public class AstStatements extends AstStatement {

  private List<AstStatement> stmts;

  public AstStatements() {
    stmts = new  ArrayList<AstStatement>();
  }

  public AstStatements(AstStatement stmt) {
    this();
    stmts.add(stmt);
  }

  public void addAstStatement(AstStatement stmt) {
    stmts.add(stmt);
  }

  public AstStatement getAstStatment(int index) {
    return stmts.get(index);
  }

  public int getSize() {
    return stmts.size();
  }

  public Object accept(AstVisitor visitor) {
    return visitor.visitStatements(this);
  }
}
