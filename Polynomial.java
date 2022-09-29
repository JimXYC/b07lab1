import java.io.File;
import java.util.Scanner;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;

public class Polynomial {
	double [] co;
	int [] ex;
    
    public Polynomial(){
    }
    
    public Polynomial (double[] a, int[] b){
    	if (a.length == b.length) {
        	co = a;
        	ex = b;
    	}
    	else {
    		System.out.println("Invalid input");
    	}
    }
    
    public Polynomial(File f){
    	try {
    		Scanner input = new Scanner(f);
        	while(input.hasNextLine()) {
        		String line = input.nextLine();
        		String [] str = line.split("X");
        		int a = str.length;
        	    double [] strco = new double[a];
        	    int [] strex = new int[a];
        	    int bo = 1;
        	    	
        	    if(str[0].indexOf('+')!=-1) {
        	    	String [] str1 = str[0].split("+");
        	    	strco[0] = Double.parseDouble(str1[0]);
        	    	strex[0] = 0;
        	    	strco[1] = Double.parseDouble(str1[1]);
        	    }
        	    else if((str[0].indexOf('-')!=-1)&&(str[0].indexOf('-')!=0)) {
        	    	String [] str1 = str[0].split("-");
        	    	strco[0] = Double.parseDouble(str1[0]);
        	    	strex[0] = 0;
        	    	strco[1] = Double.parseDouble("-"+str1[1]);
        	    }
        	    else {
        	    	strco[0] = Double.parseDouble(str[0]);
        	    	bo = 0;
        	    }
        	    if(bo==1) {
        	    	for(int i=1; i<a-1; i++) {
        	    		if(str[i].indexOf('+')!=-1) {
        	    			String [] strr = str[i].split("\\+");
        	    			strex[i] = Integer.parseInt(strr[0]);
        	    			strco[i+1] = Double.parseDouble(strr[1]);
        	    		}
        	    		else{
        	    			String [] strr = str[i].split("-");
        	    			strex[i] = Integer.parseInt(strr[0]);
        	    			strco[i+1] = Double.parseDouble("-"+strr[1]);
        	    		}
        	    	}
        	    	strex[a-1] = Integer.parseInt(str[a-1]);
        	    }
        	    else {
        	    	for(int i=1; i<a-1; i++) {
        	    		if(str[i].indexOf('+')!=-1) {
        	    			String [] strr = str[i].split("\\+");
        	    			strex[i-1] = Integer.parseInt(strr[0]);
        	    			strco[i] = Double.parseDouble(strr[1]);
        	    		}
        	    		else{
        	    			String [] strr = str[i].split("-");
        	    			strex[i-1] = Integer.parseInt(strr[0]);
        	    			strco[i] = Double.parseDouble("-"+strr[1]);
        	    		}

        	    	}
        	    	strex[a-2] = Integer.parseInt(str[a-1]);
        	    }
        	    co = strco;
        	    ex = strex;
        	}
        	input.close();
        	System.out.println("Successfully convey!");
    	}
    	catch(FileNotFoundException e) {
    		System.out.println("Error!");
    		e.printStackTrace();
    	}
    }

    public Polynomial add(Polynomial b){
    	int al = ex.length;
    	int bl = (b.ex).length;
    	int cs;
    	if(ex[al-1] >= b.ex[bl-1]) {
    		cs = ex[al-1]+1;
    	}
    	else {
    		cs = b.ex[bl-1]+1;
    	}
    	
    	double [] copy = new double[cs];
    	
    	for(int i=0; i<al; i++){
    		copy[ex[i]] += co[i];
    	}

    	for(int j=0; j<bl; j++){
    		copy[b.ex[j]] += b.co[j];
    	}
    	
    	int count=0;
    	for(int k = 0; k<cs; k++) {
    		if(copy[k]!=0) count++;
    	}
    	double [] copyco = new double[count];
    	int [] copyex = new int[count];
    	
    	int n = 0;
    	for(int m=0; m<cs;m++) {
    		if(copy[m]!=0) {
    			copyco[n] = copy[m];
    			copyex[n] = m;
    			n++;
    		}
    	}
    	
    	Polynomial result = new Polynomial(copyco, copyex);
    	return result;
    }
    
    public double evaluate(double x) {
    	if (ex == null) {
    		return 0;
    	}
    	
    	double sum = 0;
    	for(int i=0; i<ex.length; i++) {
    		sum = sum + co[i] * Math.pow(x, ex[i]);
    	}
    	return sum;
    }
    
    public boolean hasRoot(double r) {
    	double result = this.evaluate(r);
    	return (result == 0);
    }
    
    public Polynomial multiply(Polynomial b) {
    	int al = ex.length;
    	int bl = (b.ex).length;
    	int cs;
    	cs = ex[al-1]+b.ex[bl-1]+1;
    	
    	double [] copy = new double[cs];
    	for(int i=0; i<al; i++) {
    		for(int j=0; j<bl; j++) {
    			copy[ex[i]+b.ex[j]] += co[i] * b.co[j];
    		}
    	}
    	
    	int count=0;
    	for(int k = 0; k<cs; k++) {
    		if(copy[k]!=0) count++;
    	}
    	double [] copyco = new double[count];
    	int [] copyex = new int[count];
    	
    	int n = 0;
    	for(int m=0; m<cs;m++) {
    		if(copy[m]!=0) {
    			copyco[n] = copy[m];
    			copyex[n] = m;
    			n++;
    		}
    	}
    	
    	Polynomial result = new Polynomial(copyco, copyex);
    	return result;
    	
    }
    public void saveToFile(String ad) {
    	String s;
    	if(ex[0]==0) {
    		s = Double.toString(co[0]);
    	}
    	else {
    		s = Double.toString(co[0]) + "X" + Integer.toString(ex[0]);
    	}
    	for (int i=1; i<ex.length; i++) {
    		if(co[i]>0) {
    			s = s + "+" + Double.toString(co[i]) + "X" + Integer.toString(ex[i]);
    		}
    		else {
    			s = s + Double.toString(co[i]) + "X" + Integer.toString(ex[i]);
    		}
    	}
    	try {
    		FileWriter mywriter = new FileWriter(ad);
    		mywriter.write(s);
    		mywriter.close();
    		System.out.println("Successfully write!");
    	}
    	catch(IOException e) {
    		System.out.println("Error!");
    		e.printStackTrace();
    	}
    }
    public void display() {
    	for(int i=0; i<ex.length; i++) {
    		System.out.println(co[i]);
    	}
    }
}
