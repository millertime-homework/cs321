// Driver program for testing MINI parser0 v1.6. (Jingke Li)
//
package parser0;
import java.io.*;

public class TestParser0 {
  public static void main(String [] args) {
    try {
      if (args.length == 1) {
	FileInputStream stream = new FileInputStream(args[0]);
	new miniParser0(stream).Program();
	stream.close();
        System.out.println("Parsing successful");
      } else {
	System.out.println("You must provide an input file name.");
      }
    } catch (Exception e) {
      System.err.println(e.toString());
    }
  }
}
