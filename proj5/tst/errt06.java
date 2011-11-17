class typeErr {
  public static void main(String[] a) {
    boolean b;
    boolean c;
    b = c && true; // OK
    b = 1 && true; // error
  }
}
