package org.compiler.simplejava.semantic;

import java.util.ArrayList;
import java.util.List;

/**
 * Defines the function environment for the semantic analyzer. This class will keep track
 * of all the functions that have been defined.
 *
 * @author jcyescas
 *
 */
@SuppressWarnings({"rawtypes", "unchecked"})
public class FunctionEnvironment {

  private HashTable htable;

  static final int TABLESIZE = 503;

  public FunctionEnvironment() {
    htable = new HashTable(TABLESIZE);
  }

  public FunctionEntry find(String key) {
    return (FunctionEntry) htable.find(key);
  }

  public int size() {
    return htable.numelements();
  }

  public void addBuiltinFunctions() {
    List formals = new ArrayList();
    htable.insert("Read",
         new FunctionEntry(IntegerType.getInstance(), formals,
        Label.absLabel("Read"), Label.absLabel("Readend")));
    htable.insert("Println", new FunctionEntry(VoidType.getInstance(), formals,
        Label.absLabel("Println"), Label.absLabel("Printlnend")));

    formals = new ArrayList();
    formals.add(IntegerType.getInstance());
    htable.insert("Print", new FunctionEntry(VoidType.getInstance(), formals,
        Label.absLabel("Print"), Label.absLabel("Printend")));
  }

  public void insert(String key, FunctionEntry entry) {
    htable.insert(key, entry);
  }

  public void beginScope() {
    htable.beginScope();
  }

  public void endScope() {
    htable.endScope();
  }
}
