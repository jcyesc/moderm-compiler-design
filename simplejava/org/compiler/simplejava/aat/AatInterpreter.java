package org.compiler.simplejava.aat;

import java.util.List;
import java.io.*;

import org.compiler.simplejava.semantic.HashTable;

@SuppressWarnings("rawtypes")
public class AatInterpreter {
  static final int HEAPSIZE = 5000;
  static final int STACKSIZE = 5000;
  static final int ESPSTACKSIZE = 50;

  AatStatement code[];
  int IP;
  int codeLength;

  HashTable labels;

  int ESPStack[];
  int memory[];
  int SP;
  int ESP;
  int FP;
  int ACC;
  int Result;
  int ReturnAddr;
  int temp1;
  int temp2;
  int temp3;
  int temp4;
  int heap;
  BufferedReader stdin;
  PrintStream stdout;
  AatPrintTree pt;

  public AatInterpreter(AatStatement program, BufferedReader input, PrintStream output) {
    codeLength = countStatements(program);
    code = new AatStatement[codeLength];
    labels = new HashTable(127);
    pt = new AatPrintTree();
    flattenCode(program, 0);
    stdin = input;
    stdout = output;
    ESPStack = new int[ESPSTACKSIZE];
    memory = new int[STACKSIZE + HEAPSIZE];
  }

  int countStatements(AatStatement program) {
    if (program instanceof AatSequential) {
      AatSequential s = (AatSequential) program;
      return countStatements(s.getLeft()) + countStatements(s.getRight());
    }
    return 1;
  }

  void setMemory(int index, int value) {
    memory[index / 4] = value;
  }

  void setRegister(AatRegister reg, int value) {
    if (reg.getRegister() == Register.FP()) {
      FP = value;
    } else if (reg.getRegister() == Register.SP()) {
      SP = value;
    } else if (reg.getRegister() == Register.ESP()) {
      ESP = value;
    } else if (reg.getRegister() == Register.ACC()) {
      ACC = value;
    } else if (reg.getRegister() == Register.Result()) {
      Result = value;
    } else if (reg.getRegister() == Register.ReturnAddr()) {
      ReturnAddr = value;
    } else if (reg.getRegister() == Register.Zero()) {
      System.out.println("WARNING:  Setting zero register");
    } else if (reg.getRegister() == Register.Tmp1()) {
      temp1 = value;
    } else if (reg.getRegister() == Register.Tmp2()) {
      temp2 = value;
    } else if (reg.getRegister() == Register.Tmp3()) {
      temp3 = value;
    } else {
      throw new RuntimeException("ERROR:  Can't find register " + reg.getRegister());
    }
  }

  int getRegister(AatRegister reg) {
    if (reg.getRegister() == Register.FP()) {
      return FP;
    } else if (reg.getRegister() == Register.SP()) {
      return SP;
    } else if (reg.getRegister() == Register.ESP()) {
      return ESP;
    } else if (reg.getRegister() == Register.ACC()) {
      return ACC;
    } else if (reg.getRegister() == Register.Result()) {
      return Result;
    } else if (reg.getRegister() == Register.ReturnAddr()) {
      return ReturnAddr;
    } else if (reg.getRegister() == Register.Zero()) {
      return 0;
    } else if (reg.getRegister() == Register.Tmp1()) {
      return temp1;
    } else if (reg.getRegister() == Register.Tmp2()) {
      return temp2;
    } else if (reg.getRegister() == Register.Tmp3()) {
      return temp3;
    } else {
      throw new RuntimeException("ERROR:  Can't find register " + reg.getRegister());
    }
  }

  int flattenCode(AatStatement program, int IP) {
    if (program instanceof AatSequential) {
      IP = flattenCode(((AatSequential) program).getLeft(), IP);
      IP = flattenCode(((AatSequential) program).getRight(), IP);
      return IP;
    }
    code[IP] = program;
    if (program instanceof AatLabel) {
      labels.insert(((AatLabel) program).getLabel().toString(), new Integer(IP));
    }
    return IP + 1;
  }

  boolean evalCallExp(AatCallExpression exp) {
    int i;
    try {
      if (exp.getLabel().toString().compareTo("Read") == 0) {
        String message = stdin.readLine();
        ACC = Integer.parseInt(message);
        return false;
      }
    } catch (Exception e) {
      System.out.println("I/O Exception:" + e);
      return true;
    }

    if (exp.getLabel().toString().compareTo("allocate") == 0) {
      if (evaluateExpression((AatExpression) exp.getActuals().get(0)))
        return true;
      // System.out.println("allocating " + ACC + " bytes");
      temp1 = ACC;
      ACC = heap;
      heap = heap - temp1;
      return false;
    }

    for (i = exp.getActuals().size() - 1; i >= 0; i--) {
      List actuals = exp.getActuals();
      if (evaluateExpression((AatExpression) (actuals.get(i))))
        return true;
      // System.out.println("Parameter:"+ACC);
      setMemory(SP, ACC);
      SP = SP - 4;
    }
    Integer jumpIndex = (Integer) labels.find(exp.getLabel().toString());
    if (jumpIndex == null) {
      System.out.println("ERROR:  CallExpression");
      System.out.println("        Undefined label:" + exp.getLabel());
      return true;
    }
    ReturnAddr = IP;
    IP = jumpIndex.intValue();
    if (runFunction())
      return true;
    SP = SP + 4 * exp.getActuals().size();
    ACC = Result;
    return false;
  }

  int getMemory(int index) {
    return memory[index / 4];
  }

  boolean evaluateExpression(AatExpression exp) {
    if (exp instanceof AatCallExpression) {
      if (evalCallExp((AatCallExpression) exp))
        return true;
    } else if (exp instanceof AatConstant) {
      ACC = ((AatConstant) exp).getValue();
    } else if (exp instanceof AatMemory) {
      if (evaluateExpression(((AatMemory) exp).getMemory()))
        return true;
      ACC = getMemory(ACC);
    } else if (exp instanceof AatOperator) {
      AatOperator opexp = (AatOperator) exp;
      if (evaluateExpression(opexp.getLeft()))
        return true;
      if (opexp.getOperator() == AatOperator.NOT) {
        if (ACC != 0)
          ACC = 0;
        else
          ACC = 1;
        return false;
      }

      ESPStack[ESP++] = ACC;
      if (evaluateExpression(opexp.getRight())) {
        return true;
      }
      ESP--;
      switch (opexp.getOperator()) {
        case AatOperator.PLUS:
          ACC = ESPStack[ESP] + ACC;
          break;
        case AatOperator.MINUS:
          ACC = ESPStack[ESP] - ACC;
          break;
        case AatOperator.MULTIPLY:
          ACC = ESPStack[ESP] * ACC;
          break;
        case AatOperator.DIVIDE:
          ACC = ESPStack[ESP] / ACC;
          break;
        case AatOperator.AND:
          if (ACC != 0 && ESPStack[ESP] != 0)
            ACC = 1;
          else
            ACC = 0;
          break;
        case AatOperator.OR:
          if (ACC != 0 || ESPStack[ESP] != 0)
            ACC = 1;
          else
            ACC = 0;
          break;
        case AatOperator.EQUAL:
          if (ESPStack[ESP] == ACC)
            ACC = 1;
          else
            ACC = 0;
          break;
        case AatOperator.NOT_EQUAL:
          if (ESPStack[ESP] != ACC)
            ACC = 1;
          else
            ACC = 0;
          break;
        case AatOperator.LESS_THAN:
          if (ESPStack[ESP] < ACC)
            ACC = 1;
          else
            ACC = 0;
          break;

        case AatOperator.LESS_THAN_EQUAL:
          if (ESPStack[ESP] <= ACC)
            ACC = 1;
          else
            ACC = 0;
          break;
        case AatOperator.GREATER_THAN:
          if (ESPStack[ESP] > ACC)
            ACC = 1;
          else
            ACC = 0;
          break;

        case AatOperator.GREATER_THAN_EQUAL:
          if (ESPStack[ESP] >= ACC)
            ACC = 1;
          else
            ACC = 0;
          break;
        default:
          System.out.println("ERROR: Bad Operator:" + opexp.getOperator());
          return true;
      }
    } else if (exp instanceof AatRegister) {
      ACC = getRegister(((AatRegister) exp));
    } else {
      throw new RuntimeException("ERROR:  Unknown expression!!");
    }
    return false;
  }

  boolean evalCallStm(AatCallStatement stm) {
    int i;
    if (stm.getLabel().toString().compareTo("Print") == 0) {
      if (evaluateExpression((AatExpression) stm.getActuals().get(0)))
        return true;
      stdout.print(ACC + " ");
      return false;
    }
    if (stm.getLabel().toString().compareTo("Println") == 0) {
      stdout.println("");
      return false;
    }
    for (i = stm.getActuals().size() - 1; i >= 0; i--) {
      if (evaluateExpression((AatExpression) stm.getActuals().get(i)))
        return true;
      // System.out.println("Parameter:"+ACC);
      setMemory(SP, ACC);
      SP = SP - 4;
    }
    Integer jumpIndex = (Integer) labels.find(stm.getLabel().toString());
    if (jumpIndex == null) {
      throw new RuntimeException("ERROR:  CallStatement\n" +
          "        Undefined label" + stm.getLabel());
    }
    ReturnAddr = IP;
    IP = jumpIndex.intValue();
    if (runFunction())
      return true;
    SP = SP + 4 * stm.getActuals().size();
    return false;
  }

  boolean execute(AatStatement s) {
    if (s instanceof AatCallStatement) {
      return evalCallStm((AatCallStatement) s);
    } else if (s instanceof AatConditionalJump) {
      if (evaluateExpression(((AatConditionalJump) s).getTest()))
        return true;
      if (ACC != 0) {
        Integer jumpIndex = (Integer) labels.find(((AatConditionalJump) s).getLabel().toString());
        if (jumpIndex == null) {
          throw new RuntimeException("ERROR: in AATConditionalJump\n" +
              "       Trying to jump to :" +
              ((AatConditionalJump) s).getLabel() + "\n" +
              "       Label does not exist!");
        }
        IP = jumpIndex.intValue();
      }

    } else if (s instanceof AatEmpty) {
      /* do nothing */
    } else if (s instanceof AatHalt) {
      return true;
    } else if (s instanceof AatJump) {
      Integer jumpIndex = (Integer) labels.find(((AatJump) s).getLabel()
          .toString());
      if (jumpIndex == null) {
        throw new RuntimeException("ERROR: in AATJump\n" +
            "       Trying to jump to :" + ((AatJump) s).getLabel() + "\n" +
            "       Label does not exist!");
      }
      IP = jumpIndex.intValue();
    } else if (s instanceof AatLabel) {
      /* do nothing */
    } else if (s instanceof AatMove) {
      if (((AatMove) s).getLhs() instanceof AatMemory) {
        if (evaluateExpression(((AatMemory) ((AatMove) s).getLhs()).getMemory()))
          return true;
        ESPStack[ESP++] = ACC;
        if (evaluateExpression(((AatMove) s).getRhs()))
          return true;
        temp1 = ESPStack[--ESP];
        setMemory(temp1, ACC);
      } else if (((AatMove) s).getLhs() instanceof AatRegister) {
        if (evaluateExpression(((AatMove) s).getRhs()))
          return true;
        setRegister((AatRegister) (((AatMove) s).getLhs()), ACC);
      } else {
        throw new RuntimeException("ERROR: in AATMove\n" +
            "       LHS must be AATMemory or AATRegister!");
      }
    } else if (s instanceof AatReturn) {
      throw new RuntimeException("Interpreter ERROR\n" +
          "   It should not be possible to get here (AATReturn)!\n" +
          "   Please report to Prof. Galles!");
    } else if (s instanceof AatSequential) {
      throw new RuntimeException("Interpreter ERROR\n" +
          "   It should not be possible to get here!\n" +
          "   All AATSequentials should have been removed by flatten!\n" +
          "   Please report to Prof. Galles!");
    } else {
      throw new RuntimeException("ERROR: malformed AAT");
    }
    return false;
  }

  boolean runFunction() {
    while (true) {
      if ((IP >= codeLength) || (IP < 0))
        return true;
      // System.out.println("-- Executing statement " + IP);
      // code[IP].Accept(pt);
      IP++;
      if (code[IP - 1] instanceof AatReturn) {
        IP = ReturnAddr;
        return false;
      }

      if (execute(code[IP - 1]))
        return true;
    }
  }

  public void run() {
    SP = (STACKSIZE - 1) * 4;
    ESP = 0;
    heap = (HEAPSIZE + STACKSIZE - 1) * 4;
    ReturnAddr = -1;
    IP = 0;
    Integer mainLab = (Integer) labels.find("main1");
    if (mainLab == null) {
      throw new RuntimeException("ERROR:  no main!");
    }
    IP = mainLab.intValue();
    if (!runFunction()) {
      System.out.println("Program Completed");
    } else {
      throw new RuntimeException("Error occured -- system aborted");
    }
  }
}
