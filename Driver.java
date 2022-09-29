import java.io.File;
import java.io.IOException;

public class Driver {
	public static void main(String [] args) { 
		  Polynomial p = new Polynomial(); 
		  System.out.println(p.evaluate(3)); 
		  double [] c1 = {6, 5};
		  int [] e1 = {0, 3};
		  Polynomial p1 = new Polynomial(c1, e1); 
		  double [] c2 = {-2,-9}; 
		  int [] e2 = {1, 4};
		  Polynomial p2 = new Polynomial(c2, e2); 
		  Polynomial s = p1.add(p2); 
		  System.out.println("s(0.1) = " + s.evaluate(0.1)); 
		  if(s.hasRoot(1)) 
		   System.out.println("1 is a root of s"); 
		  else 
		   System.out.println("1 is not a root of s"); 
		  Polynomial m = p1.multiply(p2);
		  System.out.println("m(0) = " + m.evaluate(0));
		  System.out.println("m(1) = " + m.evaluate(1));
		  
		  try {
		      File myObj = new File("myFile.txt");
		      if (myObj.createNewFile()) {
		    	  System.out.println("File created: " + myObj.getName());
		      } else {
		    	  System.out.println("Already exists.");
		      }
		    } catch (IOException e) {
		      System.out.println("Error!");
		      e.printStackTrace();
		  }
		  
		  s.saveToFile("myFile.txt");
		  File myObj = new File("myFile.txt");
		  Polynomial fs = new Polynomial(myObj);
		  System.out.println("fs(0.1) = " + fs.evaluate(0.1)); 
		  
		  p2.saveToFile("myFile.txt");
		  File myObj2 = new File("myFile.txt");
		  Polynomial fp2 = new Polynomial(myObj2);
		  System.out.println("fp2(-1) = " + fp2.evaluate(-1)); 
		 } 
}
