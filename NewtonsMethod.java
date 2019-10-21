package newtonsmethod;

//can recursion be used to find roots using Newton's Method? 

public class NewtonsMethod {
    final static double nthPlace = 0.0000000000001; //accuracy

    public static void main(String[] args) {
        System.out.println(getNewton(2, 1.5, 0)); //x, start guess, blank
    }
    
    public static double getNewton(double x, double n, double n2) {
        if (n == 0 | Math.abs(n-n2) < nthPlace) return n;
        n2=n;
        n = n-(((n*n)-x)/(x*n));
        return getNewton(x, n, n2);
    }
}
