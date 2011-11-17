class A { }
class B extends A { }
class C extends B { }

class typeErr {
  public void foo(A x) { }
  public void bar(B x) { }

  public static void main(String[] a) {
    A a;
    B b;
    C c;
    foo(b);  // OK
    foo(c);  // OK
    bar(c);  // OK
    bar(a);  // error
  }
}
