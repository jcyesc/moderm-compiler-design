package org.compiler.simplejava.aat;

import java.io.BufferedReader;
import java.io.InputStreamReader;

import org.compiler.simplejava.ast.AstPrintTree;
import org.compiler.simplejava.ast.AstProgram;
import org.compiler.simplejava.semantic.CompilerError;
import org.compiler.simplejava.semantic.SemanticAnalyzer;
import org.compiler.simplejava.ParseException;
import org.compiler.simplejava.SimpleJava;

public class TestAssembly {

  public static void main(String args[]) {
    SimpleJava parser;
    if (args.length < 1) {
      System.out.print("Usage: java TestAssembly <filename>");
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
      AatPrintTree pat = new AatPrintTree();
      CompilerError compilerError = new CompilerError();
      SemanticAnalyzer sa = new SemanticAnalyzer(compilerError);
      AatInterpreter intr;
      System.out.println("Parsing Successful");
      System.out.println("=================");
      System.out.println("Printing AST ...");
      System.out.println("=================");
      prog.accept(pt);
      AatStatement assem = (AatStatement) prog.accept(sa);
      if (!compilerError.anyErrors()) {
        System.out.println("=================");
        System.out.println("Printing AAT ...");
        System.out.println("=================");
        assem.accept(pat);
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        intr = new AatInterpreter(assem, reader, System.out);
        System.out.println("========================");
        System.out.println("Starting Interpreter ...");
        System.out.println("========================");
        intr.run();
        System.out.println("========================");
        System.out.println("Interpreter Done!");
        System.out.println("========================");

      } else {
        System.out.println("Semantic Errors Detected.  No AAT Created");
      }
    } catch (ParseException e) {
      System.out.println(e.getMessage());
      System.out.println("Parsing Failed");
    }
  }

}
