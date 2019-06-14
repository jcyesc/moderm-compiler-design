package org.compiler.simplejava.aat;

public interface AatVisitor {

  public Object visitCallExpression(AatCallExpression call);

  public Object visitCallStatement(AatCallStatement call);

  public Object visitConditionalJump(AatConditionalJump cjump);

  public Object visitConstant(AatConstant constant);

  public Object visitEmpty(AatEmpty empty);

  public Object visitHalt(AatHalt halt);

  public Object visitJump(AatJump jump);

  public Object visitLabel(AatLabel label);

  public Object visitMemory(AatMemory mem);

  public Object visitMove(AatMove move);

  public Object visitOperator(AatOperator oper);

  public Object visitRegister(AatRegister reg);

  public Object visitReturn(AatReturn ret);

  public Object visitSequential(AatSequential seq);

}
