class typeErr {
  public int f() { return 1; }

  public static void main(String[] a) {
    int x;
    x = this.f();  // OK
    x = this.f(1); // wrong number of args
  }
}
