package org.compiler.simplejava.ast;

import org.compiler.simplejava.ParseException;
import org.compiler.simplejava.SimpleJava;

public class TestParser {

  public static void main(String args[]) {
    SimpleJava parser;
    if (args.length < 1) {
      System.out.print("Usage: java TestParser <filename>");
      return;
    }
    try {
      parser = new SimpleJava(new java.io.FileInputStream(args[0]));
    } catch (java.io.FileNotFoundException e) {
      System.out.println("File " + args[0] + " not found.");
      return;
    }
 
    try {
      AstProgram prog = parser.program();
      AstPrintTree pt = new AstPrintTree();
      System.out.println("Parsing Successful");
      prog.accept(pt);
    } catch (ParseException e) {
      System.out.println(e.getMessage());
      System.out.println("Parsing Failed");
    }
  }

}
