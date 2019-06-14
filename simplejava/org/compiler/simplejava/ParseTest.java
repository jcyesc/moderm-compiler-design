package org.compiler.simplejava;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

import org.compiler.simplejava.ast.AstPrintTree;
import org.compiler.simplejava.ast.AstProgram;

public class ParseTest {

	public static void main(String args[]) throws IOException, ParseException {

//		if (args.length < 1) {
//			System.out.println("Please, provide name of the file!!!");
//			return;
//		}

		InputStream stream = null;
		String path =
		    "/Users/jcyescas/development/computing/workspaces/compilers/compilers-usf/src/test/simplejava/org/compiler/simplejava/";
		String fileName = path + "test1.sjava";
		//String fileName = args[0];

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
			System.out.println("Parsing Successful");
			
			// Printing the AST tree.
      AstPrintTree printer = new  AstPrintTree();
      program.accept(printer);
		} finally {
		  if (stream != null)
			  stream.close();
		}
	}
}
