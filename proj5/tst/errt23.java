class A { }

class typeErr {
  public static void main(String[] a) {
    A a = new A();
    A b = new A();
    int[] x = new int[5];
    int[] y = new int[4];
    int[] z = x;
    System.out.println(a==b); // OK
    System.out.println(x==y); // OK
    System.out.println(a==x); // not valid
    System.out.println(a<b);  // not valid
    System.out.println(x<z);  // not valid
    
  }
}
