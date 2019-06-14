package org.compiler.simplejava.semantic;

public class VariableEnvironment {
  static final int TABLESIZE = 503;

  private HashTable hashTable;

  public VariableEnvironment() {
    hashTable = new HashTable(TABLESIZE);
  }

  public VariableEntry find(String key) {
    return (VariableEntry) hashTable.find(key);
  }

  public void insert(String key, VariableEntry entry) {
    hashTable.insert(key, entry);
  }

  public int size() {
    return hashTable.numelements();
  }

  public void beginScope() {
    hashTable.beginScope();
  }

  public void endScope() {
    hashTable.endScope();
  }
}
