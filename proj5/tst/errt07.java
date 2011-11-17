class typeErr {
  public static void main(String[] a) {
    boolean b;
    b = false != true; // OK
    b = false == 0;    // incompatible args
  }
}
