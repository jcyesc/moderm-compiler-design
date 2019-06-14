package org.compiler.simplejava.semantic;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

import org.compiler.simplejava.ParseException;
import org.compiler.simplejava.SimpleJava;
import org.compiler.simplejava.ast.AstPrintTree;
import org.compiler.simplejava.ast.AstProgram;

public class TestSemantic {

  public static void main(String args[]) throws IOException, ParseException {

    // if (args.length < 1) {
    // System.out.println("Please, provide name of the file!!!");
    // return;
    // }

    InputStream stream = null;
    String path = "/Users/jcyescas/development/computing/workspaces/compilers/compilers-usf/src/test/simplejava/org/compiler/simplejava/";
    String fileName = path + "test1.sjava";
    // String fileName = args[0];

    try {
      stream = new FileInputStream(fileName);
    } catch (FileNotFoundException ex) {
      System.err.println("File Not fount " + ex.getMessage());
      return;
    }

    try {
      // Parsing the file.
      SimpleJava simpleJava = new SimpleJava(stream);
      AstProgram program = simpleJava.program();
      AstPrintTree pt = new AstPrintTree();
      CompilerError compilerError = new CompilerError();
      SemanticAnalyzer sa = new SemanticAnalyzer(compilerError);
      System.out.println("Parsing Successful");
      program.accept(pt);
      program.accept(sa);

      if (!compilerError.anyErrors()) {
        System.out.println("No Semantic Errors");
      }

    } catch (ParseException e) {
      System.out.println(e.getMessage());
      System.out.println("Parsing Failed");
    }
  }

}
