package org.compiler.simplejava.aat;

public class TestAatBuildTree {

  public static void main(String args[]) {
    AatBuildTree bt = new AatBuildTree();
    AatPrintTree pt = new AatPrintTree();
    AatExpression exptree;
    AatStatement stmtree;
    System.out.println("=================== ");
    System.out.println("Testing Expressions ");
    System.out.println("=================== ");
    System.out.println("----------------------------");
    System.out.println("Testing literal constat : 15 ");
    System.out.println("----------------------------");
    exptree = bt.constantExpression(15);
    exptree.accept(pt);
    System.out.println("--------------------------");
    System.out.println("Testing expression : 4 + 5 ");
    System.out.println("--------------------------");
    exptree = bt.operatorExpression(bt.constantExpression(4),
        bt.constantExpression(5), AatOperator.PLUS);
    exptree.accept(pt);
    System.out.println("================= ");
    System.out.println("Testing Variables ");
    System.out.println("================= ");
    System.out.println("-------------------------------");
    System.out.println("Testing base variable, offset 4 ");
    System.out.println("-------------------------------");
    exptree = bt.baseVariable(4);
    exptree.accept(pt);
    System.out.println("----------------------------------------");
    System.out.println("Assingment:  x := 3 (x local, offset = 4)");
    System.out.println("----------------------------------------");
    stmtree = bt.assignmentStatement(bt.baseVariable(4),
        bt.constantExpression(3));
    stmtree.accept(pt);
    System.out
        .println("-----------------------------------------------------------");
    System.out
        .println("For: for(i=0; i < 10; i++) x := 0;   x offset 4, i offset 8 ");
    System.out
        .println("------------------------------------------------------------");
    stmtree = bt.forStatement(bt.assignmentStatement(bt.baseVariable(8),
        bt.constantExpression(0)), bt.operatorExpression(bt.baseVariable(8),
        bt.constantExpression(10), AatOperator.LESS_THAN), bt
        .assignmentStatement(bt.baseVariable(8), bt.operatorExpression(
            bt.baseVariable(8), bt.constantExpression(1), AatOperator.PLUS)),
        bt.assignmentStatement(bt.baseVariable(4), bt.constantExpression(0)));
    stmtree.accept(pt);
    /* Add more tests !!! */

  }
}
