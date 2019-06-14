
package org.compiler.simplejava.semantic;

/**
 * Stores the data type for the program. It also defines the three data
 * types supported by default for SimpleJava: int, boolean and void.
 * 
 * @author jcyescas
 *
 */
public class TypeEnvironment {

  static final int TABLESIZE = 503;

  public TypeEnvironment() {
    htable = new HashTable(TABLESIZE);
    htable.insert("int", IntegerType.getInstance());
    htable.insert("boolean", BooleanType.getInstance());
    htable.insert("void", VoidType.getInstance());
  }

  public Type find(String key) {
    return (Type) htable.find(key);
  }

  public int size() {
    return htable.numelements();
  }

  public void insert(String key, Type type) {
    htable.insert(key, type);
  }

  public void beginScope() {
    htable.beginScope();
  }

  public void endScope() {
    htable.endScope();
  }

  private HashTable htable;

}
