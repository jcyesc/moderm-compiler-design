
package org.compiler.simplejava.semantic;

import java.util.ArrayList;
import java.util.List;

import org.compiler.simplejava.aat.AatBuildTree;
import org.compiler.simplejava.aat.AatConstant;
import org.compiler.simplejava.aat.AatExpression;
import org.compiler.simplejava.aat.AatOperator;
import org.compiler.simplejava.aat.AatSequential;
import org.compiler.simplejava.aat.AatStatement;
import org.compiler.simplejava.aat.ExpressionRecord;
import org.compiler.simplejava.ast.AstArrayVariable;
import org.compiler.simplejava.ast.AstAssignmentStatement;
import org.compiler.simplejava.ast.AstBaseVariable;
import org.compiler.simplejava.ast.AstBooleanLiteral;
import org.compiler.simplejava.ast.AstClass;
import org.compiler.simplejava.ast.AstClassVariable;
import org.compiler.simplejava.ast.AstClasses;
import org.compiler.simplejava.ast.AstDoWhileStatement;
import org.compiler.simplejava.ast.AstEmptyStatement;
import org.compiler.simplejava.ast.AstExpression;
import org.compiler.simplejava.ast.AstForStatement;
import org.compiler.simplejava.ast.AstFormal;
import org.compiler.simplejava.ast.AstFormals;
import org.compiler.simplejava.ast.AstFunction;
import org.compiler.simplejava.ast.AstFunctionCallExpression;
import org.compiler.simplejava.ast.AstFunctionCallStatement;
import org.compiler.simplejava.ast.AstFunctionDefinition;
import org.compiler.simplejava.ast.AstFunctionDefinitions;
import org.compiler.simplejava.ast.AstIfStatement;
import org.compiler.simplejava.ast.AstInstanceVariableDef;
import org.compiler.simplejava.ast.AstInstanceVariableDefs;
import org.compiler.simplejava.ast.AstIntegerLiteral;
import org.compiler.simplejava.ast.AstMethodCallExpression;
import org.compiler.simplejava.ast.AstMethodCallStatement;
import org.compiler.simplejava.ast.AstMethodVariable;
import org.compiler.simplejava.ast.AstNewArrayExpression;
import org.compiler.simplejava.ast.AstNewClassExpression;
import org.compiler.simplejava.ast.AstOperatorExpression;
import org.compiler.simplejava.ast.AstProgram;
import org.compiler.simplejava.ast.AstPrototype;
import org.compiler.simplejava.ast.AstReturnStatement;
import org.compiler.simplejava.ast.AstStatement;
import org.compiler.simplejava.ast.AstStatements;
import org.compiler.simplejava.ast.AstUnaryOperatorExpression;
import org.compiler.simplejava.ast.AstVariable;
import org.compiler.simplejava.ast.AstVariableDefStatement;
import org.compiler.simplejava.ast.AstVariableExpression;
import org.compiler.simplejava.ast.AstVisitor;
import org.compiler.simplejava.ast.AstWhileStatement;
import org.compiler.simplejava.ast.FunctionType;

/**
 * 
 * The semantic analysis Visit methods can be divided into tree categories, based on the type
 * of the node that they are visiting:
 * 
 * - Definition Nodes
 * - Expression Nodes
 * - Statement Nodes
 * 
 * @author jcyescas
 *
 */
@SuppressWarnings("unchecked")
public class SemanticAnalyzer implements AstVisitor {

  /** Stores the variables defined in the environment. */
  private VariableEnvironment variableEnv;

  /** Stores the functions defined in the program. */
  private FunctionEnvironment functionEnv;

  /** Stores the data types for the program. */
  private TypeEnvironment typeEnv;

  /** Keeps track of the errors. */
  private CompilerError compilerError;

  /** Return type of the function. */
  private Type  currentReturnType;

  /** Aat build tree class that helps to build the Aat Tree. */
  private AatBuildTree aatBuildTree = new AatBuildTree();

  /** Current function or method being processed. */
  private FunctionEntry currentFunctionEntry;

  /** Current class being processed. */
  private VariableEntry currentClassEntry;

  /** Keeps the size of the function framesize. */
  private int functionFramesize;

  /** Keeps track of the offset of the function.*/
  private int functionOffset;

  public SemanticAnalyzer(CompilerError compilerError) {
    this.compilerError = compilerError;
    this.variableEnv = new VariableEnvironment();
    this.functionEnv = new FunctionEnvironment();
    this.typeEnv = new TypeEnvironment();
    this.functionEnv.addBuiltinFunctions();
  }

  /**
   * Analyzes an array variable. To analyze an array variable, the next steps are followed:
   *
   * - Analyze the index, ensuring that it is of type int.
   * - Analyze the base variable. Ensure that the base variable is an array type.
   * - Return the type of an array element, extracted from the base type of the array.
   *
   * @return ExpressionRecord
   */
  @Override
  public Object visitArrayVariable(AstArrayVariable arrayVar) {
    AstVariable baseVar = arrayVar.getBase();
    AstExpression index = arrayVar.getIndex();
    ExpressionRecord baseRecord = (ExpressionRecord) baseVar.accept(this);
    ExpressionRecord indexRecord = (ExpressionRecord) index.accept(this);
    Type baseType = baseRecord.getType();
    Type indexType = indexRecord.getType();

    if (indexType != IntegerType.getInstance()) {
      compilerError.addMessage(arrayVar.getLine(), "The index must be int, found " + indexType);
    }

    if (baseType instanceof ArrayType) {
      AatExpression arrayVarExpr =
          aatBuildTree.arrayVariable(baseRecord.getExpressionTree(), indexRecord.getExpressionTree(),
              MachineDependent.WORDSIZE);
      Type arrayType = ((ArrayType) baseType).getType();
      return new ExpressionRecord(arrayType, arrayVarExpr);
    } else {
      compilerError.addMessage(arrayVar.getLine(),
          "The base type is not an array, Found " + baseType);

      return new ExpressionRecord(IntegerType.getInstance(), (AatExpression) null);
    }
  }

  /**
   * Checks that the left hand side and right hand side of the assignment has the same type.
   *
   * @return AatStatement that contains the assignment.
   */
  @Override
  public Object visitAssignmentStatement(AstAssignmentStatement assignStmt) {
    AstVariable variable = assignStmt.getVariable();
    ExpressionRecord leftRecord = (ExpressionRecord) variable.accept(this); 
    Type leftType = leftRecord.getType();
    AstExpression rightExpr = assignStmt.getValue();
    ExpressionRecord rightRecord = (ExpressionRecord) rightExpr.accept(this); 
    Type rightType = (Type) rightRecord.getType();

    if (leftType != rightType) {
      compilerError.addMessage(assignStmt.getLine(),
          rightType + " can't be assigned to " + leftType + ".");
    }

    return aatBuildTree.assignmentStatement(leftRecord.getExpressionTree(),
        rightRecord.getExpressionTree());
  }

  /**
   *  Analyzes simple variables. For a simple variable like x, all we need to do is look up x
   *  in the variable environment. If x is not in the variable environment, then we can output
   *  an error. If x is in the variable environment, we return the type associated with x in
   *  the variable environment.
   *
   *  @return ExpressionRecord
   */
  @Override
  public Object visitBaseVariable(AstBaseVariable baseVar) {
    Type type = checkVariable(baseVar.getLine(), baseVar.getName());
    VariableEntry varEntry = variableEnv.find(baseVar.getName());
    AatExpression exprTree = null;
    if (varEntry != null) {
      if (varEntry.getScopeType() == ScopeType.INSTANCE) {
        exprTree = aatBuildTree.baseInstanceVariable(varEntry.getOffset());
      } else {
        exprTree = aatBuildTree.baseVariable(varEntry.getOffset());
      }
    }

    return new ExpressionRecord(type, exprTree);
  }

  /**
   * Returns the boolean type.
   *
   * @return ExpressionRecord
   */
  @Override
  public Object visitBooleanLiteral(AstBooleanLiteral boolLiteral) {
    return new ExpressionRecord(BooleanType.getInstance(),
        aatBuildTree.constantBoolExpression(boolLiteral.isValue()));
  }

  /**
   * Analyzes a class definition:
   * 
   * - Creates a new  variable environment.
   * - Adds the instance variables to the new variable environment.
   * - Create a new entry in the type environment with the name of the class, which stores
   *   the definition of the new Class.
   */
  @Override
  public Object visitClass(AstClass classs) {
    AatStatement methods = aatBuildTree.emptyStatement();
    
    //  Begin a new variable scope and function scope
    this.variableEnv.beginScope();
    this.functionEnv.beginScope();
    
    // Getting the variable environment definitions.
    AstInstanceVariableDefs varDefs = classs.getInstanceVariableDefs();

    // Creating a new variable environment and adding the new variables to the environment.
    VariableEnvironment varinstanceEnv = (VariableEnvironment) varDefs.accept(this);

    // Creating a method environment
    FunctionEnvironment methodEnv = new FunctionEnvironment();

    // Creating a new entry in the type environment for the new class.
    ClassType classType = new ClassType(varinstanceEnv, methodEnv);
    classType.setName(classs.getName());
    typeEnv.insert(classs.getName(), classType);

    // Add variable name "this" to the current environment, using the type class type.
    this.currentClassEntry = new VariableEntry(classType);
    this.variableEnv.insert("this", currentClassEntry);

    // Analyze the class methods, adding the prototypes for the methods to the function
    // environment for the current class, as well as to the current function environment.
    // The bodies of methods are analyzed in the same way as the bodies of standard functions.
    AstFunctionDefinitions methodDefs = classs.getMethodDefinitions();
    for (int i = 0; i < methodDefs.getSize(); i++) {
      AstFunctionDefinition function = methodDefs.elementAt(i);
      // Polymorphically visits the AstFunction or AstPrototype.
      AatStatement funcStmt = (AatStatement) function.accept(this);
      methods = new AatSequential(funcStmt, methods);
    }

    // Analyze the constructor like if it were a function.
    AstFunction constructor = classs.getConstructorDefinition();
    if (constructor != null) {
      // Analyzing the constructor definition is essentially the same as analyzing a method definition.
      AatStatement constructorStmt = (AatStatement) constructor.accept(this);
      methods = new AatSequential(constructorStmt, methods);
    }

    // Setting to null the current class entry.
    this.currentClassEntry = null;

    //  Ending a new variable scope and function scope
    this.variableEnv.endScope();
    this.functionEnv.endScope();

    return methods;
  }

  /**
   * Analyzes all the classes in the program. The definition of the class doesn't generate any
   * code.
   */
  @Override
  public Object visitClasses(AstClasses classes) {
    
    AatStatement stmts = aatBuildTree.emptyStatement();
    for (int i = 0; i < classes.getSize(); i++) {
      AatStatement methods = (AatStatement) classes.getAstClass(i).accept(this);
        stmts = new AatSequential(methods, stmts);
    }

    return stmts;
  }

  /**
   * Analyzes instance variables. In order to analyze an instance variable like x.y, it is
   * necessary to:
   * 
   * - Recursively analyze the base of this variable (x), to get the internal representation of
   *   the type of x.
   * - Make sure the internal representation of the type of x is a class type.
   * - Look up the key y in the variable environment contained within the internal representation
   *   of the type of x.
   * - Return the type associated with the key y.
   * 
   * Since the first step in this process is a recursive analysis of the base type, arbitrarily
   * nested structure variables (such as x.y[3].z) can be successfully analyzed using this method.
   *
   * @return ExpressionRecord
   */
  @Override
  public Object visitClassVariable(AstClassVariable classVar) {
    AstVariable base = classVar.getBase();
    ExpressionRecord baseRecord = (ExpressionRecord) base.accept(this);
    Type classType = baseRecord.getType();
    String instanceVar = classVar.getVariable();
    
    if (classType instanceof ClassType) {
      VariableEnvironment env = ((ClassType) classType).getVariableEnvironment();
      VariableEntry entry = env.find(instanceVar);
      if (entry != null) {
        AatExpression classVariable = aatBuildTree.classVariable(baseRecord.getExpressionTree(), entry.getOffset());
        return new ExpressionRecord(entry.getType(), classVariable);
      }

      compilerError.addMessage(classVar.getLine(),
          "The instance variable " + instanceVar + " doesn't belong to the class " + classType);
    } else {
      compilerError.addMessage(classVar.getLine(),
          "The class of the instance variable " + instanceVar + " is undefined.");
    }

    return new ExpressionRecord(IntegerType.getInstance(), (AatExpression) null);
  }

  /**
   * Analyzes the do while statement. First checks the body and then makes sure that the
   * test expression evaluates to boolean.
   *
   * @return AatStatement that contains the AAT tree for the do while statement.
   */
  @Override
  public Object visitDoWhileStatement(AstDoWhileStatement doWhileStmt) {
    // Begin the do while scope.
    variableEnv.beginScope();

    // Evaluate body
    AatStatement bodyStmt = (AatStatement) doWhileStmt.getBody().accept(this);

    // Check test expression
    ExpressionRecord testRecord = checkTestExpression(doWhileStmt.getTestExpression());

    // End of the do while scope.
    variableEnv.endScope();

    // Returning the AAT for the do while statement.
    return aatBuildTree.dowhileStatement(testRecord.getExpressionTree(), bodyStmt);
  }

  /**
   * Analyzes the empty statement. For this statement we do nothing.
   *
   * @return AatStatement that represents the empty statement.
   */
  @Override
  public Object visitEmptyStatement(AstEmptyStatement empty) {
    // Do nothing for a empty statement.
    return aatBuildTree.emptyStatement();
  }

  /**
   * Analyzes the for statement. First, checks the initialization statement, second
   * checks the test expression evaluates to boolean, then, checks the increment statement and
   * finally it checks for the body.
   *
   * @return AatStatement that represents the for statement.
   */
  @Override
  public Object visitForStatement(AstForStatement forStmt) {
    // Begin the for scope.
    variableEnv.beginScope();

    // Analyzing the initialization part.
    AatStatement initStmt = (AatStatement) forStmt.getInitialization().accept(this);

    // Analyzing the testing part.
    ExpressionRecord testRecord = checkTestExpression(forStmt.getTestExpression());

    // Analyzing the increment part.
    AatStatement incrementStmt = (AatStatement) forStmt.getIncrementStmt().accept(this);

    // Analyzing the body part.
    AatStatement bodyStmt = (AatStatement) forStmt.getBodyStmt().accept(this);

    // End of the for scope.
    variableEnv.endScope();

    return aatBuildTree.forStatement(initStmt, testRecord.getExpressionTree(), incrementStmt, bodyStmt);
  }

  /**
   * Analyzes the formal parameter of the function and returns the type of the formal parameter.
   *
   * @return Type of the formal
   */
  @Override
  public Object visitFormal(AstFormal formal) {
    Type type;
    // Array type
    if (formal.isArray()) {
      type = checkArrayDeclaration(formal.getType(), formal.getArrayDimension(), formal.getLine());
    } else {
      // Non-array type.
      String typeName = formal.getType();
      type = typeEnv.find(typeName);
      if (type == null) {
        compilerError.addMessage(formal.getLine(), "Type " + typeName
            + " is not defined.");

        // The default return time is IntegerType.
        type = IntegerType.getInstance();
      }
    }

    return type;
  }

  /**
   * Returns a list of the types of the formal parameters.
   *
   * @return List<Type> that contains the type of the formals.
   */
  @Override
  public Object visitFormals(AstFormals formals) {
    List<Type> types = new ArrayList<Type>();
    for (int i = 0; i < formals.getSize(); i++) {
      AstFormal formal = formals.getAstFormal(i);
      types.add((Type) formal.accept(this));
    }

    return types;
  }

  /**
   * Analyzes a function definition. To do semantic analysis of a function definition, we need
   * to take the following steps:
   *
   * - Analyze the formal parameters, ensuring that the type and number of parameters matches
   *   the prototype.
   *     - If no prototype exists, add the prototype information to the function environment.
   * - Begin a new  scope in the variable environment.
   * - Add the formal parameters to the variable environment.
   * - Analyze the body of the function, using the modified variable environment.
   * - End the current scope in the variable environment, which removes the formal parameters
   *   and any local variables from the variable environment.
   * 
   *  @return AatStatement
   */
  @Override
  public Object visitFunction(AstFunction function) {
    return processFunction(function);
  }
  
  /**
   * Analyzes a function definition (Function, Method and Constructor). To do semantic analysis
   * of a function definition, we need to take the following steps:
   *
   * - Analyze the formal parameters, ensuring that the type and number of parameters matches
   *   the prototype.
   *     - If no prototype exists, add the prototype information to the function environment.
   * - Begin a new  scope in the variable environment.
   * - Add the formal parameters to the variable environment.
   * - Analyze the body of the function, using the modified variable environment.
   * - End the current scope in the variable environment, which removes the formal parameters
   *   and any local variables from the variable environment.
   * 
   *  @return AatStatement
   */
  private AatStatement processFunction(AstFunction function) {
    String returnTypeName = function.getReturnType();
    Type returnType = typeEnv.find(returnTypeName);
    FunctionType typeFunction = function.getTypeFunction();
    AstFormals formals = function.getFormals();
    List<Type> formalsTypes = (List<Type>) formals.accept(this);

    FunctionEntry functionEntry = checkOrAddFunctionEntry(function.getName(), returnTypeName, 
        returnType, formals, formalsTypes, function.getLine());

    // Setting the currentFunctionEntry.
    this.currentFunctionEntry = functionEntry;

    // Beging a scope
    variableEnv.beginScope();

    // Adding the formal parameters to the variable environment.
    int offsetParameters = MachineDependent.WORDSIZE;
    for (int i = 0; i < formals.getSize(); i++) {
      AstFormal formal = formals.getAstFormal(i);
      // Creates a new variable entry for the formal parameter and calculates the offset accordingly.
      VariableEntry variableEntry = new VariableEntry(formalsTypes.get(i), offsetParameters);
      offsetParameters += MachineDependent.WORDSIZE;
      variableEnv.insert(formal.getName(), variableEntry);
    }

    // Setting the default return type.
    if (typeFunction == FunctionType.CONSTRUCTOR) {
      this.currentReturnType = this.currentClassEntry.getType();
    } else {
      this.currentReturnType = VoidType.getInstance();
    }

    // Initializing the function framesize and offset.
    this.functionFramesize = 0;
    this.functionOffset = 0;
    if (typeFunction == FunctionType.METHOD || typeFunction == FunctionType.CONSTRUCTOR) {
      // At the beginning of every method, the local variable offset is set to -WORDSIZE because
      // the first entry in the activation record for a method is the "this" pointer, not the
      // first local variable.
      this.functionOffset -= MachineDependent.WORDSIZE;
    }
    
    // Processing the body.
    AatStatement bodyStmt = (AatStatement) function.getBodyStmt().accept(this);

    // Checking the return type.
    if (returnType != currentReturnType) {
      if (typeFunction == FunctionType.CONSTRUCTOR) {
        // Constructors name have to be the same as the class name
        compilerError.addMessage(function.getLine(),
            "The name of constructor must be the same as the class " +  this.currentClassEntry.getType() +
            ", found " + function.getName());
      } else {
        compilerError.addMessage(function.getLine(),
            "The return type of " + function.getName() +" must be " + returnTypeName +
            ", found " + currentReturnType); 
      }
    }

    // End the current scope and removing the local variables from the variable environment.
    variableEnv.endScope();

    // Cleaning the currentFunctionEntry.
    this.currentFunctionEntry = null;

    switch(typeFunction) {    
      case CONSTRUCTOR:
         // Adding "return this" to the constructor.
         AatStatement returnThis = aatBuildTree.returnThisStatement(functionEntry.getEndLabel());
         bodyStmt = aatBuildTree.sequentialStatement(bodyStmt, returnThis);
         return aatBuildTree.methodDefinition(bodyStmt, functionFramesize,
             functionEntry.getStartLabel(), functionEntry.getEndLabel());
      case METHOD:
        return aatBuildTree.methodDefinition(bodyStmt, functionFramesize,
            functionEntry.getStartLabel(), functionEntry.getEndLabel());
      case FUNCTION:
      default:
        return aatBuildTree.functionDefinition(bodyStmt, functionFramesize,
            functionEntry.getStartLabel(), functionEntry.getEndLabel());
    }
  }

  /**
   * Checks that the function entry matches the prototype, if it hasn't been defined it, it added
   * to the Function environment.
   *
   * @return FunctionEntry
   */
  private FunctionEntry checkOrAddFunctionEntry(String functionName, String returnTypeName,
      Type returnType, AstFormals formals, List<Type> formalsTypes, int line) {
    FunctionEntry functionEntry = functionEnv.find(functionName);
    if (functionEntry == null) {
      // Add Prototype information
      functionEntry = addPrototype(functionName, returnTypeName, formals, line);
    } else {
      // Check if the function matches the prototype
      List<Type> expectedFormalsType = functionEntry.getFormals();
      // Checking if the number of parameters are the same.
      if (formalsTypes.size() != expectedFormalsType.size()) {
        compilerError.addMessage(line, "The number of parameters in " + functionName +
            " are different from the prototype");
      }

      // Checking if the type of parameters are the same.
      for (int i = 0; i < expectedFormalsType.size() && i < formalsTypes.size(); i++) {
        if (formalsTypes.get(i) != expectedFormalsType.get(i)) {
          compilerError.addMessage(line, "The type of parameter in " + functionName +
              " is different from the prototype");
        }
      }

      // Checking if the return type is the same.
      Type expectedReturnType = functionEntry.getReturnType();
      if (returnType != expectedReturnType) {
        compilerError.addMessage(line, "The return type in " + functionName +
            " is different from the prototype");
      }
    }

    return functionEntry;
  }

  /**
   * Validates the the function is already defined, that the type of the parameters match the
   * formal parameters. It returns the return type of the function.
   *
   * @return ExpressionRecord
   */
  @Override
  public Object visitFunctionCallExpression(AstFunctionCallExpression functionCall) {
    ExpressionRecord record = checkFunctionCall(functionCall.getName(), functionCall.getAstExpressions(), 
        functionCall.getLine());

    AatExpression tree = null;
    FunctionEntry functionEntry = functionEnv.find(functionCall.getName());
    if (functionEntry != null) {
      switch(functionEntry.getFunctionType()) {
        case FUNCTION:
          tree = aatBuildTree.callExpression(record.getExpressions(), functionEntry.getStartLabel());
          break;
        case METHOD:
          tree = aatBuildTree.methodExpression(record.getExpressions(), functionEntry.getStartLabel());
          break;
        default:
          new RuntimeException("This code should be unreachable!!!");
      }
    }

    // Return the default values when the program is invalid.
    return new ExpressionRecord(record.getType(), tree);
  }

  /**
   * Analyzes a function call statement. It makes sure that the return type is void and that
   * the parameters matches the definition.
   * 
   * @return AatStatement for the function call.
   */
  @Override
  public Object visitFunctionCallStatement(AstFunctionCallStatement functionCallStmt) {
    ExpressionRecord record = checkFunctionCall(functionCallStmt.getName(),
        functionCallStmt.getAstExpressions(), 
        functionCallStmt.getLine());

    Type returnType = record.getType();

    if (returnType != VoidType.getInstance()) {
      compilerError.addMessage(functionCallStmt.getLine(),
          "A function statement can't return a value, Found " + returnType);
    }

    AatStatement tree = null;
    FunctionEntry functionEntry = functionEnv.find(functionCallStmt.getName());
    if (functionEntry != null) {
      switch(functionEntry.getFunctionType()) {
        case FUNCTION:
          tree = aatBuildTree.callStatement(record.getExpressions(), functionEntry.getStartLabel());
          break;
        case METHOD:
          tree = aatBuildTree.methodStatement(record.getExpressions(), functionEntry.getStartLabel());
          break;
        default:
          new RuntimeException("This code should be unreachable!!!");
      }
    }

    return tree;
  }

  /**
   * Analyzes the if statement. To analyze an if statement, we first analyze the test and ensure
   * that it is a boolean. Then we analyze the "then" and "else" statements.
   *
   * @return AatStatement that represents the if structure.
   */
  @Override
  public Object visitIfStatement(AstIfStatement ifStmt) {
    // Analyzing the test expression.
    ExpressionRecord testExpression = checkTestExpression(ifStmt.getTestExpression());

    // Begin of the if scope.
    variableEnv.beginScope();

    // Analyzing the body.
    AatStatement ifBody = (AatStatement) ifStmt.getThenStmt().accept(this);

    // End of the if scope.
    variableEnv.endScope();

    // Analyzing the else part.
    AatStatement elseBody = null;
    if (ifStmt.getElseStmt() != null) {
      // Begin of the else scope.
      variableEnv.beginScope();

      elseBody = (AatStatement) ifStmt.getElseStmt().accept(this);

      // End of the else scope.
      variableEnv.endScope();
    }

    return aatBuildTree.ifStatement(testExpression.getExpressionTree(), ifBody, elseBody);
  }

  /**
   * Returns the type of the Integer literal which it is Integer Type.
   *
   * @return ExpressionRecord
   */
  @Override
  public Object visitIntegerLiteral(AstIntegerLiteral literal) {
    return new ExpressionRecord(IntegerType.getInstance(), 
        aatBuildTree.constantExpression(literal.getValue()));
  }

  /**
   * Analyzes the variable definitions. When we analyze a variable, we will need to ensure that
   * the variable has been defined in the current scope. We will then determine the type of the
   * variable, which will be used in the rest of the semantic analysis process. The variables
   * could be:
   * 
   * - Simple Variables
   * - Array Variables
   * - Instance Variables
   */
  @Override
  public Object visitInstanceVariableDef(AstInstanceVariableDef varDef) {
    Type type;

    // Array type.
    if (varDef.isArray()) {
      type = checkArrayDeclaration(varDef.getType(), varDef.getArrayDimension(), varDef.getLine());
    } else {
      // Non array type.
      // Checking that the type is defined.
      type = checkType(varDef.getLine(), varDef.getType());
    }

    // The offset is set in visitInstanceVariableDefs()
    return new VariableEntry(type);
  }

  /**
   * Analyzes the variable definitions.
   *
   * @return VariableEnvironment for the class that is being analyzed.
   */
  @Override
  public Object visitInstanceVariableDefs(AstInstanceVariableDefs variableDefs) {
    // Creating a new environment for the class.
    VariableEnvironment varinstanceEnv = new VariableEnvironment();
    int offset = 0;
    for (int i = 0; i < variableDefs.getSize(); i++) {
      AstInstanceVariableDef variableDef = variableDefs.getAstInstanceVariableDef(i);
      VariableEntry varEntry = (VariableEntry) variableDef.accept(this);

      // Setting and updating the offset.
      varEntry.setOffset(offset);
      varEntry.setScopeType(ScopeType.INSTANCE);
      offset -= MachineDependent.WORDSIZE;

      // Inserting the variable in the class environment.
      varinstanceEnv.insert(variableDef.getName(), varEntry);

      // Inserting the variable in the current variable environment.
      this.variableEnv.insert(variableDef.getName(), varEntry);
    }

    return varinstanceEnv;
  }

  /**
   * Analyzes the new array expression. It makes sure that the expression to evaluate the size is
   * integer. It also verifies that the base type has been defined. It returns the array type.
   *
   * @return ExpressionRecord that contains the allocate tree.
   */
  @Override
  public Object visitNewArrayExpression(AstNewArrayExpression newArray) {
    ExpressionRecord record = (ExpressionRecord) newArray.getAstExpression().accept(this); 
    Type sizeType = record.getType();
    if (sizeType != IntegerType.getInstance()) {
      compilerError.addMessage(newArray.getLine(), "Array size must be int, found " + sizeType);

      // Returning the default value when the type doesn't match.
      return new ExpressionRecord(IntegerType.getInstance(), (AatExpression) null);
    }

    Type arrayType =
        checkArrayDeclaration(newArray.getType(), newArray.getArrayDimension(), newArray.getLine());

    return new ExpressionRecord(arrayType, aatBuildTree.allocate(record.getExpressionTree()));
  }

  /**
   * Checks that the type of the new object is in the type environment and returns the type.
   *
   * Note: By the default, if a constructor is not defined, one without any arguments is provided.
   */
  @Override
  public Object visitNewClassExpression(AstNewClassExpression newClass) {
    Type type = checkType(newClass.getLine(), newClass.getType());

    AatExpression constructorTree = null;
    if (type instanceof ClassType) {
      ClassType classType = (ClassType) type;

      // Calculates the size of the class given the number of instance variables.
      int size = classType.getVariableEnvironment().size();
      constructorTree = aatBuildTree.allocate(aatBuildTree.constantExpression(size));
      
      // Invoking the constructor code.
      FunctionEnvironment methodEnv = classType.getMethodEnvironment();

      // Checking if there is defined method, otherwise one is created by default.
      FunctionEntry methodEntry = methodEnv.find(newClass.getType());
      if (methodEntry != null) {      
        ExpressionRecord record = checkFunctionCall(newClass.getType(), newClass.getExpressions(),
            methodEnv, newClass.getLine());

        // Adding the "this" parameter.
        List<AatExpression> parametersAndThis = new ArrayList<AatExpression>();
        parametersAndThis.add(constructorTree);
        if (record.getExpressions() != null) {
          for (AatExpression argument : record.getExpressions()) {
            parametersAndThis.add(argument);
          }
        }

        constructorTree = aatBuildTree.callExpression(parametersAndThis, methodEntry.getStartLabel());
      }
    }

    return new ExpressionRecord(type, constructorTree);
  }

  /**
   * Analyzes the operator expression and returns the type.  When analyzing an expression, we
   * need to both ensure that the expression has no semantic errors and determine the type of
   * the expression.
   *
   * @return ExpressionRecord
   */
  @Override
  public Object visitOperatorExpression(AstOperatorExpression operExpr) {
    AstExpression rightExpression = operExpr.getRightExpression();
    AstExpression leftExpression = operExpr.getLeftExpression();
    ExpressionRecord rightRecord = (ExpressionRecord) rightExpression.accept(this);
    ExpressionRecord leftRecord = (ExpressionRecord) leftExpression.accept(this);
    
    Type rightType = rightRecord.getType();
    Type leftType = leftRecord.getType();

    int lineNumber = operExpr.getLine();
    int operator = operExpr.getOperator();

    Type resultType;
    
    switch (operator) {
      case AstOperatorExpression.PLUS:
      case AstOperatorExpression.MINUS:
      case AstOperatorExpression.MULTIPLY:
      case AstOperatorExpression.DIVIDE:
        checkOperatorExpressionType(IntegerType.getInstance(), rightType, leftType, operator,
            lineNumber);
        resultType = IntegerType.getInstance();
        break;

      case AstOperatorExpression.AND:
      case AstOperatorExpression.OR:
        checkOperatorExpressionType(BooleanType.getInstance(), rightType, leftType, operator,
            lineNumber);
        resultType = BooleanType.getInstance();
        break;

      case AstOperatorExpression.EQUAL:
      case AstOperatorExpression.NOT_EQUAL:
        resultType = BooleanType.getInstance();
        break;

      case AstOperatorExpression.LESS_THAN:
      case AstOperatorExpression.LESS_THAN_EQUAL:
      case AstOperatorExpression.GREATER_THAN:
      case AstOperatorExpression.GREATER_THAN_EQUAL:
        checkOperatorExpressionType(IntegerType.getInstance(), rightType, leftType, operator,
            lineNumber);
        resultType = BooleanType.getInstance();
        break;

      default:
        compilerError.addMessage(lineNumber, "Bad operator!");
        return new ExpressionRecord(IntegerType.getInstance(), (AatExpression) null);
    }

    AatExpression operatorExp = aatBuildTree.operatorExpression(leftRecord.getExpressionTree(),
        rightRecord.getExpressionTree(), operator);
    return new ExpressionRecord(resultType, operatorExp);
  }

  /**
   * Analyzes the program. First, it gets the classes and analyzes them, then it gets all
   * the function definitions and analyzes them.
   *
   * @return AatStatement that contains the function definitions.
   */
  @Override
  public Object visitProgram(AstProgram program) {
    // Adding classes to the type environment.
    AstClasses classes = program.getClasses();
    AatStatement methods = (AatStatement) classes.accept(this);

    // Adding functions to the function environment.
    AstFunctionDefinitions functionDefinitions = program.getFunctiondefinitions();
    AatStatement functions = (AatStatement) functionDefinitions.accept(this);

    return new AatSequential(methods, functions);
  }

  /**
   * Analyzes the function definitions.
   */
  @Override
  public Object visitFunctionDefinitions(AstFunctionDefinitions functionDefinitions) {
    AatStatement stmts = null;
    for (int i = 0; i < functionDefinitions.getSize(); i++) {
      AstFunctionDefinition function = functionDefinitions.elementAt(i);

      // Polymorphically visits the AstFunction or AstPrototype.
      AatStatement funcStmt = (AatStatement) function.accept(this);

      // Adding the function to the AAT tree. The prototypes are not added.
      if (function instanceof AstFunction) {
        if (stmts == null) {
          stmts = new AatSequential(funcStmt, aatBuildTree.emptyStatement());
        } else {
          stmts = new AatSequential(funcStmt, stmts);
        }
      }
    }

    return stmts;
  }

  /**
   * Analyzes the function prototype and add it to the function environment.
   */
  // TODO HANDLE DUPLICATE DEFINITIONS
  @Override
  public Object visitPrototype(AstPrototype prototype) {
    addPrototype(prototype.getName(), prototype.getType(), prototype.getFormals(),
        prototype.getLine());
    return aatBuildTree.emptyStatement();
  }

  /**
   * Checks the return type and sets it up in a variable instance.
   *
   * @return AatStatement.
   */
  @Override
  public Object visitReturnStatement(AstReturnStatement returnStmt) {
    // Setting the return type of the statement.
    AstExpression expr = returnStmt.getValue();
    ExpressionRecord valueRecord = null;
    if (expr != null) {
      valueRecord = (ExpressionRecord) expr.accept(this);
      this.currentReturnType = valueRecord.getType();
    }

    if (valueRecord != null) {
      AatExpression returnValue = valueRecord.getExpressionTree();
      return aatBuildTree.returnStatement(returnValue, this.currentFunctionEntry.getEndLabel());
    } else {
      return aatBuildTree.returnStatement(null, this.currentFunctionEntry.getEndLabel());
    }
    
  }

  /**
   * Analyzes the statements.
   *
   * @return AatStatement.
   */
  @Override
  public Object visitStatements(AstStatements statements) {
    AatStatement stmts = null;
    List<AatStatement> listStmts = new ArrayList<AatStatement>();
    // Processing all the statement list.
    for (int i = 0; i < statements.getSize(); i++) {
      // Calling the accept method polymorphically.
      AstStatement astStmt = statements.getAstStatment(i);
      listStmts.add((AatStatement) astStmt.accept(this));
    }

    // Adding the Statements to the AAT build tree. The tree is built button-up.
    for(int i = listStmts.size() - 1; i >= 0; i--) {
      AatStatement aatStmt = listStmts.get(i);
      if (stmts == null) {
        stmts = aatBuildTree.sequentialStatement(aatStmt, aatBuildTree.emptyStatement());
      } else {
        stmts = aatBuildTree.sequentialStatement(aatStmt, stmts);
      }
    }
 
    return stmts != null ? stmts : aatBuildTree.emptyStatement();
  }

  /**
   * Analyzes the operator expression. It makes sure that the unary operation is applied to a
   * boolean expression.
   *
   * @return ExpressionRecord
   */
  @Override
  public Object visitUnaryOperatorExpression(AstUnaryOperatorExpression unaryOperator) {
    AstExpression expression = unaryOperator.getOperand();
    ExpressionRecord record = (ExpressionRecord) expression.accept(this);
    Type expType = record.getType();
    if (BooleanType.getInstance() != expType) {
      compilerError.addMessage(unaryOperator.getLine(),
          "The operand of the unary operator is not boolean");

      return new ExpressionRecord(IntegerType.getInstance(), (AatExpression) null);
    }

    // NOT x is equivalent to (1 - x).
    AatExpression operatorExpr =
        aatBuildTree.operatorExpression(new AatConstant(1), record.getExpressionTree(), AatOperator.MINUS);

    return new ExpressionRecord(BooleanType.getInstance(), operatorExpr);
  }

  /**
   * Variable and Array Declarations.
   * 
   * When analyzing a non-array variable declaration, such as:
   *
   * int x;
   *
   * we first look up the type (int) in the type environment. If no such type exists, we should
   * report an error. If the type does exist, then we need to add the variable x to the variable
   * environment, with the type that we received from looking up int in the type environment.
   *
   * Declarations of array variables are a little more tricky, since a type and a variable are
   * declared at the same time. For instance, consider the variable declaration:
   * 
   * int a[];
   * 
   * This declaration not only defines a new variable A, it also defines a new type int[]. How
   * should we deal with array varaibles?
   * 
   * - Look up the array type (int this case, int[]) in the type environment. If the type exists,
   * add the variable to the variable environment with this type, just like non-array variables.
   * 
   * - If the array type does not appear in the type environment, check to see if the base type
   * (in this case, int) appears in the type environment. If the base type does not appear in the
   * type environment, report an error.
   * 
   * - If the array type does not appear in the type envrionment, but the base type does appear
   * in the type environment, then create a new array type (int []), and add it to the type
   * environment. Then add the variable to the variable environment using this type.
   * 
   * For multidimensional arrays, several types may need to be added to the type environment. When
   * we encounter the definition:
   * 
   * int a[][][];
   * 
   * we  will need to add the types int[], int[][], and int[][][] to the type environment, before
   * adding a to the variable environment.
   * 
   * @return AatStatement
   */
  @Override
  public Object visitVariableDefStatement(AstVariableDefStatement varDef) {
    Type leftType;

    // Array variable.
    if (varDef.isArray()) {
      leftType = checkArrayDeclaration(varDef.getType(), varDef.getArrayDimension(), varDef.getLine());
    } else {
      // Non- array variable
      leftType = checkType(varDef.getLine(), varDef.getType());
    }

    // If there is a init expression, validate that the left and right side have the same types.
    // All variables are initialized by default to zero.
    AatExpression initialValue = null;
    AstExpression expression = varDef.getInitExpression();
    if (expression != null) {
      ExpressionRecord expRecord = (ExpressionRecord) expression.accept(this);
      initialValue = expRecord.getExpressionTree();
      Type rightType = (Type) expRecord.getType();

      if (leftType != rightType) {
        compilerError.addMessage(varDef.getLine(), rightType + " can't be assigned to " + leftType);
      }
    }

    // Setting the variable in the Variable environment with the right offset.
    variableEnv.insert(varDef.getName(), new VariableEntry(leftType, this.functionOffset));

    // Creating the variable.
    AatExpression baseVar = aatBuildTree.baseVariable(this.functionOffset);

    // Setting the default value.
    initialValue = initialValue != null ? initialValue : aatBuildTree.getDefaultInitValue();

    AatStatement definitionVar = aatBuildTree.assignmentStatement(baseVar, initialValue);
    
    // Increasing the function framesize.
    this.functionFramesize += MachineDependent.WORDSIZE;
    this.functionOffset -= MachineDependent.WORDSIZE;
    
    return definitionVar;
  }

  /**
   * Analyzes the variable expression and returns the type.
   *
   * @return ExpressionRecord
   */
  @Override
  public Object visitVariableExpression(AstVariableExpression varExpression) {
    AstVariable variable = varExpression.getVariable();
    return (ExpressionRecord) variable.accept(this);
  }

  /**
   * Analyzes the while statement. It makes sure that the test expression evaluates to boolean.
   * Then it analyzes the body.
   *
   * @return AatStatement that represents the while statement.
   */
  @Override
  public Object visitWhileStatement(AstWhileStatement whileStmt) {
    ExpressionRecord testExpression = checkTestExpression(whileStmt.getTestExpression());

    // Begin the while scope.
    variableEnv.beginScope();

    AatStatement bodyStmt = (AatStatement) whileStmt.getBodyStmt().accept(this);

    // End of the while scope.
    variableEnv.endScope();

    return aatBuildTree.whileStatement(testExpression.getExpressionTree(), bodyStmt);
  }

  /**
   * Adds the Function prototype.
   *
   * @param functionName of the function.
   * @param returnType of the function.
   * @param formals parameters.
   * @param lineNumber of the prototype.
   * @return FunctionEntry
   */
  private FunctionEntry addPrototype(String functionName, String returnType, AstFormals formals, int lineNumber) {
    // Checking if the return type is valid.
    Type result = checkType(lineNumber, returnType);
    List<Type> formalTypes = (List<Type>) formals.accept(this);
    FunctionEntry functionEntry = new FunctionEntry(result, formalTypes,
        new Label(functionName), new Label(functionName + "End"));
    functionEnv.insert(functionName, functionEntry);

    // If a class is being processed, add the method to the environment.
    if (this.currentClassEntry != null) {
      functionEntry.setFunctionType(FunctionType.METHOD);
      ClassType classType = (ClassType) this.currentClassEntry.getType();
      classType.getMethodEnvironment().insert(functionName, functionEntry);
    }

    return functionEntry;
  }

  /**
   * Checks if the variable is defined in the variable environment. It returns the type of the
   * variable if found, otherwise it returns the default return type and reports the error.
   *
   * @param lineNumber
   * @param varName
   * @return Type of the variable.
   */
  private Type checkVariable(int lineNumber, String varName) {
    VariableEntry varEntry = variableEnv.find(varName);
    if (varEntry == null) {
      compilerError.addMessage(lineNumber, "The variable " + varName + " is not defined.");
      // The default return time is IntegerType.
      return IntegerType.getInstance();
    }

    return varEntry.getType();
  }

  /**
   * Analyzes the array type and declaration. Declarations of array are a little tricky, since
   * a type and a variable are declared at the same time.
   *
   * @return Type of the array, in case the base type is not defined, it reports the error and
   *     returns an Integer Type instance.
   */
  private Type checkArrayDeclaration(String typeName, int arrayDimension, int lineNumber) {
    Type baseType = typeEnv.find(typeName);

    // The base type does not appear, so the error is reported.
    if (baseType == null) {
      compilerError.addMessage(lineNumber, "Type " + typeName + " is not defined.");
      return IntegerType.getInstance();
    } else {
      for (int i = 0; i < arrayDimension; i++) {
        typeName += "[]";
        Type varType = typeEnv.find(typeName);
        if (varType == null) {
          // Creating a new array type, and adding it to the type environment.
          varType = new ArrayType(baseType);
          typeEnv.insert(typeName, varType);
        }
        baseType = varType;
      }
      
      return baseType;
    }
  }

  /**
   * Analyzes the function calls. A function call must be declared before they are called. The
   * number and type of parameters must match the declaration. Unlike C functions, only void
   * functions can be statements in simpleJava.
   *
   * @param functionName
   * @param expressions
   * @param lineNumber
   * @return ExpressionRecord that contains the Type and the a list of AatExpressions.
   */
  private ExpressionRecord checkFunctionCall(String functionName, List<AstExpression> expressions,
      int lineNumber) {
    return checkFunctionCall(functionName, expressions, this.functionEnv, lineNumber);
  }

  /**
   * Analyzes the function calls. A function call must be declared before they are called. The
   * number and type of parameters must match the declaration. Unlike C functions, only void
   * functions can be statements in simpleJava.
   *
   * @param functionName
   * @param expressions
   * @param functionEnvironment to be used in order to analyze this function or method call.
   * @param lineNumber
   * @return ExpressionRecord that contains the Type and the a list of AatExpressions.
   */
  private ExpressionRecord checkFunctionCall(String functionName, List<AstExpression> expressions,
      FunctionEnvironment functionEnvironment, int lineNumber) {
    FunctionEntry functionEntry = functionEnvironment.find(functionName);
    if (functionEntry == null) {
      String errorMessage = "The function or method " + functionName + " is not defined";
      compilerError.addMessage(lineNumber, errorMessage);

      // Returning the default type.
      return new ExpressionRecord(IntegerType.getInstance(), (List<AatExpression>) null);
    }

    // Getting the return type.
    Type returnType = functionEntry.getReturnType();

    // Verifying that the arguments match the formal parameters.
    List<Type> expectedTypes = functionEntry.getFormals();

    // Verifying the number of arguments
    if (expressions.size() != expectedTypes.size()) {
      String errorMessage = "The number of parameters in " + functionName +
          " doesn't match the prototype";
      compilerError.addMessage(lineNumber, errorMessage);

      return new ExpressionRecord(returnType, (List<AatExpression>) null);
    }

    // List of AatExpression parameters.
    List<AatExpression> parameters = new ArrayList<AatExpression>();

    // Verifying the type of the arguments.
    for (int i = 0; i < expectedTypes.size(); i++) {
      AstExpression argExpress = expressions.get(i);
      
      ExpressionRecord recordParam = (ExpressionRecord) argExpress.accept(this);
      parameters.add(recordParam.getExpressionTree());
      Type argType = recordParam.getType();
      if (argType != expectedTypes.get(i)) {
        String errorMessage =
            "Parameter " + (i + 1) + " is " + argType + ", required " + expectedTypes.get(i);
        compilerError.addMessage(lineNumber, errorMessage);
      }
    }

    return new ExpressionRecord(returnType, parameters);
  }
  
  /**
   * Checks that the test expression evalues to boolean.
   *
   * @param testExpr
   * @return ExpressionRecord for the given expression.
   */
  public ExpressionRecord checkTestExpression(AstExpression testExpr) {
    ExpressionRecord expRecord = (ExpressionRecord) testExpr.accept(this);
    Type testType = expRecord.getType();
    if (testType != BooleanType.getInstance()) {
      compilerError.addMessage(testExpr.getLine(),
          "The test expression doesn't evaluate to boolean.");
    }
    
    return expRecord;
  }

  /**
   * Checks that right and left operator have the same type and the type expected by the operator.
   * 
   * @param requiredType
   * @param rightType
   * @param leftType
   * @param operator
   * @param lineNumber
   */
  private void checkOperatorExpressionType(Type requiredType, Type rightType, Type leftType,
      int operator, int lineNumber) {
    if (rightType != requiredType || leftType != requiredType) {
      Type foundType = rightType != requiredType ? rightType : leftType;
      String errorMessage = "(" + AstOperatorExpression.names[operator ] + ") Required type " +
          requiredType + ", Found " + foundType;
      compilerError.addMessage(lineNumber, errorMessage);
    }
  }

  /**
   * Checks that the type is in the Type Environment, if the type is not there, it adds an error
   * message and returns an Integer type, otherwise returns the found type.
   *
   * @param lineNumber of the program.
   * @param typeName to be checked.
   * @return the type of given type name if found, otherwise returns an Integer type.
   */
  private Type checkType(int lineNumber, String typeName) {
    Type type = typeEnv.find(typeName);
    if (type == null) {
      compilerError.addMessage(lineNumber, "Type " + typeName + " is not defined.");
      // The default return time is IntegerType.
      type = IntegerType.getInstance();
    }

    return type;
  }

  /**
   * Analyzing Method calls
   *
   * Method calls are handled in a similar fashion as class instance variables. Consider the method
   * call:
   *
   * x.foo()
   *
   * To analyze this method call, first the variable x is analyzed - which needs to return a class
   * type. This class type will contain a function environment, containing all methods defined in
   * the class. Next, we look up the key "foo" in this function environment, to find the prototype
   * for the method foo. After checking that the method foo is defined for variables of this type,
   * and the function foo takes no input parameters, we return the return type for the method foo. 
   * 
   */
  @Override
  public Object visitMethodVariable(AstMethodVariable methodVariable) {
    return processMethodVariable(methodVariable, true);
  }

  /**
   * Analyzes the method call statements.
   */
  @Override
  public Object visitMethodCallStatement(AstMethodCallStatement methodCallStatement) {
    // Processing the method variable as a call statement.
    ExpressionRecord record = processMethodVariable(methodCallStatement.getMethodVariable(), false);
    
    Type returnType = record.getType();
    if (returnType != VoidType.getInstance()) {
      compilerError.addMessage(methodCallStatement.getLine(),
          "A method statement can't return a value, Found " + returnType);
    }

    return record.getStatementTree();
  }

  /**
   * Analyzes the method call expressions.
   */
  @Override
  public Object visitMethodCallExpression(AstMethodCallExpression methodCallExpression) {
    // Processing the method variable as a call expression.
    return processMethodVariable(methodCallExpression.getMethodVariable(), true);
  }

  /**
   * Analyzes AstMethodVariables. Depending on if it is an expression or statement it will generate
   * the appropriate AAT.
   *
   * @param methodVariable
   * @param expressionCall True if it is an expression call, False if it is an statement call.
   * @return
   */
  private ExpressionRecord processMethodVariable(AstMethodVariable methodVariable, boolean expressionCall) {
    AstVariable base = methodVariable.getBase();
    ExpressionRecord baseRecord = (ExpressionRecord) base.accept(this);
    Type classType = baseRecord.getType();
    String methodName = methodVariable.getMethodName();
    
    if (classType instanceof ClassType) {
      FunctionEnvironment methodEnv = ((ClassType) classType).getMethodEnvironment();
      ExpressionRecord record =
          checkFunctionCall(methodName, methodVariable.getExpressions(), methodEnv, methodVariable.getLine());

      FunctionEntry methodEntry = methodEnv.find(methodName);
      if (methodEntry != null) {
        // Adding the "this" parameter.
        List<AatExpression> parametersAndThis = new ArrayList<AatExpression>();
        parametersAndThis.add(baseRecord.getExpressionTree());
        for (AatExpression parameter : record.getExpressions()) {
          parametersAndThis.add(parameter);
        }

        if (expressionCall) {
          AatExpression exprTree = aatBuildTree.callExpression(parametersAndThis, methodEntry.getStartLabel());
          return new ExpressionRecord(record.getType(), exprTree);
        } else {
          AatStatement stmtTree = aatBuildTree.callStatement(parametersAndThis, methodEntry.getStartLabel()); 
          return new ExpressionRecord(record.getType(), stmtTree);
        }
      }
    } else {
      compilerError.addMessage(methodVariable.getLine(),
          "The type " + classType + " doesn't have a method call " + methodName + ".");
    }

    // Return the default values when the program is invalid.
    return new ExpressionRecord(IntegerType.getInstance(), (AatExpression) null);    
  }
}