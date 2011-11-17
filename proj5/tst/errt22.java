class A { }
class B extends A { }
class C extends B { }
class D extends A { }

class typeErr {
  public static void main(String[] a) {
    A a;
    B b;
    C c;
    D d;
    a = b;  // OK
    a = c;  // OK
    b = c;  // OK
    b = a;  // error
    b = d;  // error
  }
}
