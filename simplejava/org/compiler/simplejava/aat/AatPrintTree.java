package org.compiler.simplejava.aat;

public class AatPrintTree implements AatVisitor {

  static final int indentstep = 3;

  int indentlevel = 0;

  void Print(String word) {
    int i;
    for (i = 0; i < indentstep * indentlevel; i++)
      System.out.print(" ");
    System.out.println(word);
  }

  public Object visitCallExpression(AatCallExpression call) {
    int i;
    Print("Call Expression: " + call.getLabel());
    indentlevel++;
    for (i = 0; i < call.getActuals().size(); i++)
      ((AatExpression) call.getActuals().get(i)).accept(this);
    indentlevel--;
    return null;
  }

  public Object visitCallStatement(AatCallStatement call) {
    Print("Call Statement: " + call.getLabel());
    indentlevel++;
    for (int i = 0; i < call.getActuals().size(); i++)
      ((AatExpression) call.getActuals().get(i)).accept(this);
    indentlevel--;
    return null;
  }

  public Object visitConditionalJump(AatConditionalJump cjump) {
    Print("Conditional Jump: " + cjump.getLabel());
    indentlevel++;
    cjump.getTest().accept(this);
    indentlevel--;
    return null;
  }

  public Object visitConstant(AatConstant constant) {
    Print(Integer.toString(constant.getValue()));
    return null;
  }

  public Object visitEmpty(AatEmpty empty) {
    Print("Empty");
    return null;
  }

  public Object visitHalt(AatHalt halt) {
    Print("Halt");
    return null;
  }

  public Object visitJump(AatJump jump) {
    Print("Jump: " + jump.getLabel());
    return null;
  }

  public Object visitLabel(AatLabel label) {
    Print("Label: " + label.getLabel());
    return null;
  }

  public Object visitMemory(AatMemory mem) {
    Print("Memory");
    indentlevel++;
    mem.getMemory().accept(this);
    indentlevel--;
    return null;
  }

  public Object visitMove(AatMove move) {
    Print("Move");
    indentlevel++;
    move.getLhs().accept(this);
    move.getRhs().accept(this);
    indentlevel--;
    return null;
  }

  public Object visitOperator(AatOperator oper) {
    Print(AatOperator.names[oper.getOperator()]);
    indentlevel++;
    oper.getLeft().accept(this);
    oper.getRight().accept(this);
    indentlevel--;
    return null;
  }

  public Object visitRegister(AatRegister reg) {
    Print("Register: " + reg.getRegister());
    return null;
  }

  public Object visitReturn(AatReturn ret) {
    Print("Return");
    return null;
  }

  public Object visitSequential(AatSequential seq) {
    try {
    seq.getLeft().accept(this);
    seq.getRight().accept(this);
    return null;
    } catch(NullPointerException ex) {
      System.out.println("EXCEPTION SEQ " + seq);
      System.out.println("EXCEPTION SEQ LEFT " + seq.getLeft());
      System.out.println("EXCEPTION SEQ RIGHT " + seq.getRight());

      throw ex;
    }
  }

}
