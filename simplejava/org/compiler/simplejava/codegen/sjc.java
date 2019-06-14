package org.compiler.simplejava.codegen;

import org.compiler.simplejava.ParseException;
import org.compiler.simplejava.SimpleJava;
import org.compiler.simplejava.aat.AatPrintTree;
import org.compiler.simplejava.aat.AatStatement;
import org.compiler.simplejava.ast.AstPrintTree;
import org.compiler.simplejava.ast.AstProgram;
import org.compiler.simplejava.semantic.CompilerError;
import org.compiler.simplejava.semantic.SemanticAnalyzer;

public class sjc {

  public static void main(String args[]) {
    SimpleJava parser;
    if (args.length < 1) {
      System.out.print("Usage: java sjc <filename>");
      return;
    }

    String path = args[0];
    try {
      parser = new SimpleJava(new java.io.FileInputStream(path));
    } catch (java.io.FileNotFoundException e) {
      System.out.println("File " + path + " not found.");
      return;
    }
    try {
      AstProgram prog = parser.program();
      AstPrintTree pt = new AstPrintTree();
      AatPrintTree pat = new AatPrintTree();
      CompilerError compError = new CompilerError();
      SemanticAnalyzer sa = new SemanticAnalyzer(compError);
      SimpleTilingCodeGenerator cg = new SimpleTilingCodeGenerator(path + ".s");
      //AcumulatorCodeGenerator cg = new AcumulatorCodeGenerator(path + ".s");
      //LargeTilesCodeGenerator cg = new LargeTilesCodeGenerator(path + ".s");
      System.out.println("Parsing Successful");
      prog.accept(pt);
      AatStatement assem = (AatStatement) prog.accept(sa);
      if (!compError.anyErrors()) {
        assem.accept(pat);
        assem.accept(cg);
        cg.generateLibrary();
      }
    } catch (ParseException e) {
      System.out.println(e.getMessage());
      System.out.println("Parsing Failed");
    }
  }

}
