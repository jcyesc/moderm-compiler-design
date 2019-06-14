package org.compiler.simplejava;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

/**
 * This program prints the token of the file that was given as an Argument.
 * 
 * @author jcyescas
 * 
 */
public class TokenTest {

	public static void main(String args[]) throws IOException {

//		if (args.length < 1) {
//			System.out.println("Please, provide name of the file!!!");
//			return;
//		}
	
		InputStream stream;
		SimpleJavaTokenManager tm;
		String path =
		    "/Users/jcyescas/development/computing/workspaces/compilers/compilers-usf/src/test/simplejava/org/compiler/simple/java/";
		String fileName = path + "test3.sjava";
		//String fileName = args[0];

		try {
			stream = new FileInputStream(fileName);
		} catch (FileNotFoundException ex) {
			System.err.println("File Not fount " + ex.getMessage());
			return;
		}

		try {
			SimpleCharStream simpleStream = new SimpleCharStream(stream);
			tm = new SimpleJavaTokenManager(simpleStream);
			Token token = tm.getNextToken();

			while (token.kind != SimpleJavaConstants.EOF) {
				System.out
						.printf("%20s \t %s\n",
								SimpleJavaConstants.tokenImage[token.kind],
								token.image);
				token = tm.getNextToken();
			}
		} finally {
			stream.close();
		}
	}
}
