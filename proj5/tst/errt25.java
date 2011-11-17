class typeErr {
  public int f(int i) { return i; }

  public static void main(String[] a) {
    int x;
    x = this.f(true);  // wrong type of arg
  }
}
