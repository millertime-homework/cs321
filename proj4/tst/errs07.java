// Symbol error
class symErr {
  public int foo(int i, int i) {  // duplicated params
    int j = 1;
    return i;
  }

  public static void main(String[] a) {
    boolean b;
    int i;
    b = true;
    i = foo(1,2);
    System.out.println(b);
    System.out.println(i);
  }
}
