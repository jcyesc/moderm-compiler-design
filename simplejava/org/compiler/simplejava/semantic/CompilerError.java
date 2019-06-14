package org.compiler.simplejava.semantic;

/**
 * To handle printing out errors, we will use this class, which ensures that all our
 * error messages have roughtly the same format, and allows us to easily keep track of the
 * number of semantic erros in the program.
 * 
 * @author jcyescas
 *
 */
public class CompilerError {

  private int numberOfErrors = 0;

  public void addMessage(int lineNum, String errMsg) {
    numberOfErrors++;
    System.err.println("Error in line " + lineNum + ": " + errMsg);
  }

  public boolean anyErrors() {
    return numberOfErrors > 0;
  }

  public int getNumberOfErrors() {
    return numberOfErrors;
  }
}
