// class decls
// (should print 1 2)
class B extends A {
  int i;
}  

class A {
  int i;

  public int foo(int i) {
    int y;
    return i+1;
  }
}  

class test {
  public int foo(int i) {
    int x;
    return i;
  }

  public static void main(String[] a) {
    B b = new B();
    int i = foo(1);
    int j = b.foo(1);
    System.out.println(i);
    System.out.println(j);
  }
}
