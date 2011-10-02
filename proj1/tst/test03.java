// Driver program for testing MINI lexer v1.5. (Jingke Li)
//
package lexer;
import java.io.*;

public class TestLexer {
  public static void main(String [] args) {
    try {
      if (args.length == 1) {
	FileInputStream stream = new FileInputStream(args[0]);
	miniLexer lexer = new miniLexer(stream);
	int cnt = 0;
	Token t;
	System.out.println("Code Token-image");
	System.out.println("----------------");
	while ((t = lexer.getNextToken()) != null) { 
	  cnt++;
	  System.out.println("[" + t.kind + "] " + t.toString()); 
	  if (t.kind == 0) break;
	}
	System.out.println("Lexical analysis successful, total " + cnt + " tokens");	
	stream.close();
      } else {
	System.out.println("You must provide an input file name.");
      }
    }
    catch (Exception e) {
      System.err.println(e.toString());
    }
  }
}

