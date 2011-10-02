// Driver program for testing MINI lexer v1.6. (Jingke Li)
//
package lexer;
import java.io.*;

public class TestLexer {
  public static void main(String [] args) {
    try {
      if ((args.length < 1) || ((args.length > 1) && !args[0].equals("-d"))) {
        System.out.println("Usage: java lexer/TestLexer [-d] <file-name>");
      } else {
	Boolean detail = false;
	if (args.length > 1)
	  detail = true;
	FileInputStream stream = new FileInputStream(args[detail ? 1 : 0]);
	miniLexer lexer = new miniLexer(stream);
	System.out.println(detail ? "Code Token-image\n----------------"
			          : "Token-image\n-----------");
	Token t;
	int cnt = 0;
        while ((t = lexer.getNextToken()) != null) { 
	  cnt++;
	  if (detail)
	    System.out.print("[" + t.kind + "] "); 
	  System.out.println(t.toString()); 
	  if (t.kind == 0) break;
        }
        System.out.println("Lexical analysis successful, total " + cnt + " tokens");	
        stream.close();
      }
    }
    catch (Exception e) {
      System.err.println(e.toString());
    }
  }
}

