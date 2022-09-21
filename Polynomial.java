public class Polynomial{
	double [] arr;
    
    public Polynomial(){
    }
    
    public Polynomial (double[] a){
    	arr = a;
    }
    
    public Polynomial add(Polynomial b){
    	int al = arr.length;
    	Polynomial copy = new Polynomial(arr);
    	int bl = (b.arr).length;
    	if (al >= bl){
    		for(int i=0; i<bl; i++){
    			(copy.arr)[i] += (b.arr)[i];
    		}
    		return copy;
        }
    	else {
    		for(int j=0; j<al; j++){
    			(b.arr)[j] += (copy.arr)[j];
    		}
    		return b;
    	}
    }
    
    public double evaluate(double x) {
    	if (arr == null) {
    		return 0;
    	}
    	
    	double sum = 0;
    	for(int i=0; i<arr.length; i++) {
    		sum = sum + arr[i] * Math.pow(x, i);
    	}
    	return sum;
    }
    
    public boolean hasRoot(double r) {
    	double result = this.evaluate(r);
    	return (result == 0);
    }
}