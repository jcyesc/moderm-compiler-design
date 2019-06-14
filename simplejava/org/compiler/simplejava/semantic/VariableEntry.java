package org.compiler.simplejava.semantic;

public class VariableEntry {

  /** Type of the variable. */
  private Type type;
  private int offset;
  private ScopeType scopeType = ScopeType.LOCAL;

  public VariableEntry(Type type) {
    this.type = type;
  }

  public VariableEntry(Type type, int offset) {
    this.type = type;
    this.offset = offset;
  }

  public Type getType() {
    return type;
  }

  public void setType(Type type) {
    this.type = type;
  }

  public int getOffset() {
    return offset;
  }

  public void setOffset(int offset) {
    this.offset = offset;
  }

  public ScopeType getScopeType() {
    return scopeType;
  }

  public void setScopeType(ScopeType scopeType) {
    this.scopeType = scopeType;
  }
}
