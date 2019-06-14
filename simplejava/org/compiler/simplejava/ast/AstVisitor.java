
package org.compiler.simplejava.ast;

public interface AstVisitor {

    public Object visitArrayVariable(AstArrayVariable array);

    public Object visitAssignmentStatement(AstAssignmentStatement assign);

    public Object visitBaseVariable(AstBaseVariable base);

    public Object visitMethodVariable(AstMethodVariable methodVariable);

    public Object visitMethodCallStatement(AstMethodCallStatement methodCallStatement);

    public Object visitMethodCallExpression(AstMethodCallExpression methodCallExpression);

    public Object visitBooleanLiteral(AstBooleanLiteral boolliteral);

    public Object visitClass(AstClass classs);

    public Object visitClasses(AstClasses classes);

    public Object visitClassVariable(AstClassVariable classvariable);

    public Object visitDoWhileStatement(AstDoWhileStatement dowhile);

    public Object visitEmptyStatement(AstEmptyStatement empty);

    public Object visitForStatement(AstForStatement forstmt);

    public Object visitFormal(AstFormal formal);

    public Object visitFormals(AstFormals formals);

    public Object visitFunction(AstFunction function);

    public Object visitFunctionCallExpression(AstFunctionCallExpression functioncall);

    public Object visitFunctionCallStatement(AstFunctionCallStatement functioncall);

    public Object visitIfStatement(AstIfStatement ifsmt);

    public Object visitIntegerLiteral(AstIntegerLiteral literal);

    public Object visitInstanceVariableDef(AstInstanceVariableDef variabledef);

    public Object visitInstanceVariableDefs(AstInstanceVariableDefs variabledefs);

    public Object visitNewArrayExpression(AstNewArrayExpression newarray);

    public Object visitNewClassExpression(AstNewClassExpression newclass);

    public Object visitOperatorExpression(AstOperatorExpression opexpr);

    public Object visitProgram(AstProgram program);

    public Object visitFunctionDefinitions(AstFunctionDefinitions functionDefinitions);

    public Object visitPrototype(AstPrototype prototype);

    public Object visitReturnStatement(AstReturnStatement ret);

    public Object visitStatements(AstStatements statements);

    public Object visitUnaryOperatorExpression(AstUnaryOperatorExpression operator);

    public Object visitVariableDefStatement(AstVariableDefStatement vardef);

    public Object visitVariableExpression(AstVariableExpression variableexpression);
 
    public Object visitWhileStatement(AstWhileStatement whilestatement);
}
