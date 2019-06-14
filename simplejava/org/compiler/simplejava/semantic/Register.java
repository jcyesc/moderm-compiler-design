package org.compiler.simplejava.semantic;

public class Register {

  static private Register FP = null;
  static private Register SP = null;
  static private Register ESP = null;
  static private Register ACC = null;
  static private Register Result = null;
  static private Register ReturnAddr = null;
  static private Register Zero = null;
  static private Register Tmp1 = null;
  static private Register Tmp2 = null;
  static private Register Tmp3 = null;
  static private Register Tmp4 = null;
  static private Register Heap = null;

  private String name;

  private Register(String name) {
    this.name = name;
  }

  public static Register FP() {
    if (FP == null) {
      FP = new Register("$fp");
    }
    return FP;
  }

  public static Register SP() {
    if (SP == null) {
      SP = new Register("$sp");
    }
    return SP;
  }

  public static Register ESP() {
    if (ESP == null) {
      ESP = new Register("$t1");
    }
    return ESP;
  }

  public static Register ACC() {
    if (ACC == null) {
      ACC = new Register("$t0");
    }
    return ACC;
  }

  public static Register Result() {
    if (Result == null) {
      Result = new Register("$v0");
    }
    return Result;
  }

  public static Register ReturnAddr() {
    if (ReturnAddr == null) {
      ReturnAddr = new Register("$ra");
    }
    return ReturnAddr;
  }

  public static Register Zero() {
    if (Zero == null) {
      Zero = new Register("$zero");
    }
    return Zero;
  }

  public static Register Tmp1() {
    if (Tmp1 == null) {
      Tmp1 = new Register("$t2");
    }
    return Tmp1;
  }

  public static Register Tmp2() {
    if (Tmp2 == null) {
      Tmp2 = new Register("$t3");
    }
    return Tmp2;
  }

  public static Register Tmp3() {
    if (Tmp3 == null) {
      Tmp3 = new Register("$t4");
    }
    return Tmp3;
  }

  public String toString() {
    return name;
  }
}
