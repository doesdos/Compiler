package src;

   
import java.io.*;
   
public class Main {
  static public void main(String argv[]) {   
    final long startTime = System.currentTimeMillis();
      
    /* Start the parser */
    try {
      parser p = new parser(new Lexer(new FileReader(argv[0])));
      p.parse();
      //Object result = p.parse().value;      
    } catch (Exception e) {
      /* do cleanup here -- possibly rethrow e */
      e.printStackTrace();
    }
    
    final long duration = System.currentTimeMillis() - startTime;
    //System.out.println(duration);
  }
}


