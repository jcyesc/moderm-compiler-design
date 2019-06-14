package org.compiler.simplejava.aat;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import org.compiler.simplejava.semantic.Label;

public class TestAaaInterpreter {

  public static void main(String[] args) {
    System.out.println("--------- Program 1 ---------");
    interpretersExampleProgram1();

    System.out.println("\n--------- Program 2 ---------");
    interpretersExampleProgram2();

    System.out.println("\n--------- Program 3 ---------");
    interpretersExampleProgram3();
  }
  
  /**
   * Interpreters the following program:
   *
   * void testFunc() {
   *   int x = 3 + 4;
   *   int y = x + 9;
   *   
   *   print(y);
   * }
   * 
   * int main() {
   *   testFunc();
   *   Println();
   * }
   */
  public static void interpretersExampleProgram1() {
    AatBuildTree bt = new AatBuildTree();
    
    // DEFINITION OF THE testFunc() function
    // int x = 3 + 4
    AatExpression firstExp = bt.operatorExpression(new AatConstant(4), new AatConstant(3), AatOperator.PLUS);
    AatExpression xVariable = bt.baseVariable(0);
    AatStatement assigment1 = bt.assignmentStatement(xVariable, firstExp);

    // int y = x + 9
    AatExpression secondExp = bt.operatorExpression(xVariable, new AatConstant(9), AatOperator.PLUS);
    AatExpression yVariable = bt.baseVariable(MachineDependent.WORDSIZE);
    AatStatement assigment2 = bt.assignmentStatement(yVariable, secondExp);

    // Putting the two previous statements together.
    AatStatement body = bt.sequentialStatement(assigment1, assigment2);

    // Print(y)
    List<AatExpression> parameters = new ArrayList<AatExpression>();
    parameters.add(yVariable);
    AatCallStatement callStmt = new AatCallStatement(Label.absLabel("Print"), parameters);

    // Putting the three previous statements together.
    body = bt.sequentialStatement(body, callStmt);

    int framesize = MachineDependent.WORDSIZE * 2; // 2 variables x and y.
    Label startTestFunction = new Label("testFunc");
    Label endTestFunction = new Label("testFuncEnd");
    AatStatement testFunction = bt.functionDefinition(body, framesize, startTestFunction, endTestFunction);

    // DEFINITION OF THE main() function
    AatStatement mainBody = new AatCallStatement(startTestFunction, new ArrayList<AatExpression>());
    // Println();
    mainBody = bt.sequentialStatement(mainBody, new AatCallStatement(Label.absLabel("Println"), new ArrayList<AatExpression>()));
    
    AatStatement mainFunction = bt.functionDefinition(mainBody, 0, Label.absLabel("main1"), new Label("mainEnd"));

    AatStatement program = bt.sequentialStatement(testFunction, mainFunction);
    
    AatInterpreter interpreter = new AatInterpreter(program, new BufferedReader(new InputStreamReader(System.in)), System.out);
    interpreter.run();
  }

  /**
   * Interpreters the following program:
   *
   * int testFunc() {
   *   int x = 3 + 4;
   *   int y = x + 9;
   *   
   *   Print(y);
   *   
   *   return y;
   * }
   * 
   * int main() {
   *   int a = testFunc();
   *   a = a + 1;
   *
   *   Println();
   *   Print(a);
   *   Println();
   * }
   */
  public static void interpretersExampleProgram2() {
    AatBuildTree bt = new AatBuildTree();
    
    // DEFINITION OF THE testFunc() function
    Label startTestFunction = new Label("testFunc");
    Label endTestFunction = new Label("testFuncEnd");

    // int x = 3 + 4
    AatExpression firstExp = bt.operatorExpression(new AatConstant(4), new AatConstant(3), AatOperator.PLUS);
    AatExpression xVariable = bt.baseVariable(0);
    AatStatement assigment1 = bt.assignmentStatement(xVariable, firstExp);
 
    // int y = x + 9
    AatExpression secondExp = bt.operatorExpression(xVariable, new AatConstant(9), AatOperator.PLUS);
    AatExpression yVariable = bt.baseVariable(MachineDependent.WORDSIZE);
    AatStatement assigment2 = bt.assignmentStatement(yVariable, secondExp);

    // Putting the two previous statements together.
    AatStatement body = bt.sequentialStatement(assigment1, assigment2);

    // Print(y)
    List<AatExpression> parameters = new ArrayList<AatExpression>();
    parameters.add(yVariable);
    AatCallStatement callStmt = new AatCallStatement(Label.absLabel("Print"), parameters);

    // Putting the three previous statements together.
    body = bt.sequentialStatement(body, callStmt);
    
    // return y;
    body = bt.sequentialStatement(body, bt.returnStatement(yVariable, endTestFunction));

    int framesize = MachineDependent.WORDSIZE * 2; // 2 variables x and y.
    AatStatement testFunction = bt.functionDefinition(body, framesize, startTestFunction, endTestFunction);

    // DEFINITION OF THE main() function
    // int a = testFunc();
    AatExpression aVariable = bt.baseVariable(0);
    AatStatement testCallStmt = bt.assignmentStatement(aVariable, new AatCallExpression(startTestFunction, new ArrayList<AatExpression>()));

    // a = a + 1;
    AatStatement stmt1 = bt.assignmentStatement(aVariable, bt.operatorExpression(aVariable, new AatConstant(1), AatOperator.PLUS));
    
    AatStatement mainBody = bt.sequentialStatement(testCallStmt, stmt1);

    // Println();
    mainBody = bt.sequentialStatement(mainBody, new AatCallStatement(Label.absLabel("Println"), new ArrayList<AatExpression>()));

    // Print(a);
    List<AatExpression> printParameters = new ArrayList<AatExpression>();
    printParameters.add(aVariable);    
    mainBody = bt.sequentialStatement(mainBody, new AatCallStatement(Label.absLabel("Print"), printParameters));

    // Println();
    mainBody = bt.sequentialStatement(mainBody, new AatCallStatement(Label.absLabel("Println"), new ArrayList<AatExpression>()));

    AatStatement mainFunction = bt.functionDefinition(mainBody, 0, Label.absLabel("main1"), new Label("mainEnd"));

    // Adding the testFunc() and main() functions to the program.
    AatStatement program = bt.sequentialStatement(testFunction, mainFunction);

    // Interpreting the program.
    AatInterpreter interpreter = new AatInterpreter(program, new BufferedReader(new InputStreamReader(System.in)), System.out);
    interpreter.run();
  }

  /**
   * Interpreters the following program:
   *
   * int addFunc(int a, int b) {
   *   int x = 3 + 4;
   *   int y = x + 9;
   *   
   *   y = y + a + b;
   *
   *   Print(y);
   *   
   *   return y;
   * }
   * 
   * void main() {
   *   int a = addFunc(11, 13);
   *   a = a + 1;
   *
   *   Println();
   *   Print(a);
   *   Println();
   * }
   */
  public static void interpretersExampleProgram3() {
    AatBuildTree bt = new AatBuildTree();
    
    // DEFINITION OF THE testFunc() function
    Label startTestFunction = new Label("addFunc");
    Label endTestFunction = new Label("addFuncEnd");

    // int x = 3 + 4
    AatExpression firstExp = bt.operatorExpression(new AatConstant(4), new AatConstant(3), AatOperator.PLUS);
    AatExpression xVariable = bt.baseVariable(0);
    AatStatement assigment1 = bt.assignmentStatement(xVariable, firstExp);
 
    // int y = x + 9
    AatExpression secondExp = bt.operatorExpression(xVariable, new AatConstant(9), AatOperator.PLUS);
    AatExpression yVariable = bt.baseVariable(MachineDependent.WORDSIZE * -1);
    AatStatement assigment2 = bt.assignmentStatement(yVariable, secondExp);

    // y = y + a + b
    AatExpression aParam = bt.baseVariable(MachineDependent.WORDSIZE);
    AatExpression bParam = bt.baseVariable(MachineDependent.WORDSIZE * 2);
    AatExpression thirdExp = bt.operatorExpression(yVariable, aParam, AatOperator.PLUS);
    AatExpression fourthExp = bt.operatorExpression(thirdExp, bParam, AatOperator.PLUS);
    AatStatement assigment3 = bt.assignmentStatement(yVariable, fourthExp);

    // Putting the three previous statements together.
    AatStatement body = bt.sequentialStatement(assigment2, assigment3);
    body = bt.sequentialStatement(assigment1, body);
 
    // Print(y)
    List<AatExpression> parameters = new ArrayList<AatExpression>();
    parameters.add(yVariable);
    AatCallStatement callStmt = new AatCallStatement(Label.absLabel("Print"), parameters);

    // Putting the three previous statements together.
    body = bt.sequentialStatement(body, callStmt);
    
    // return y;
    body = bt.sequentialStatement(body, bt.returnStatement(yVariable, endTestFunction));

    int framesize = MachineDependent.WORDSIZE * 2; // 2 variables x and y.
    AatStatement testFunction = bt.functionDefinition(body, framesize, startTestFunction, endTestFunction);

    // DEFINITION OF THE main() function
    // int a = addFunc(11, 13);
    AatExpression aVariable = bt.baseVariable(0);
    List<AatExpression> params = new ArrayList<AatExpression>();
    params.add(bt.constantExpression(11));
    params.add(bt.constantExpression(13));
    AatStatement testCallStmt = bt.assignmentStatement(aVariable, new AatCallExpression(startTestFunction, params));

    // a = a + 1;
    AatStatement stmt1 = bt.assignmentStatement(aVariable, bt.operatorExpression(aVariable, new AatConstant(1), AatOperator.PLUS));
    
    AatStatement mainBody = bt.sequentialStatement(testCallStmt, stmt1);

    // Println();
    mainBody = bt.sequentialStatement(mainBody, new AatCallStatement(Label.absLabel("Println"), new ArrayList<AatExpression>()));

    // Print(a);
    List<AatExpression> printParameters = new ArrayList<AatExpression>();
    printParameters.add(aVariable);    
    mainBody = bt.sequentialStatement(mainBody, new AatCallStatement(Label.absLabel("Print"), printParameters));

    // Println();
    mainBody = bt.sequentialStatement(mainBody, new AatCallStatement(Label.absLabel("Println"), new ArrayList<AatExpression>()));

    AatStatement mainFunction = bt.functionDefinition(mainBody, 0, Label.absLabel("main1"), new Label("mainEnd"));

    // Adding the testFunc() and main() functions to the program.
    AatStatement program = bt.sequentialStatement(testFunction, mainFunction);

    // Printing the Aat tree.
    // AatPrintTree pat = new AatPrintTree();
    // program.accept(pat);
    
    // Interpreting the program.
    AatInterpreter interpreter = new AatInterpreter(program, new BufferedReader(new InputStreamReader(System.in)), System.out);
    interpreter.run();
  }
}
