// Symbol error
class typeErr {
  public int foo(int i) {
    int i = 1;  // conflicting param and var
    return i;
  }

  public static void main(String[] a) {
    boolean b;
    int i = foo(1);
    System.out.println(b);
    System.out.println(i);
  }
}
