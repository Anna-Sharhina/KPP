import java.util.Scanner;


public class FFunction implements Evaluatable{

    private double a;
    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        System.out.println("FFunction:");

        FFunction fun = new FFunction();

        System.out.println("xBeg:");
        double xBeg = scanner.nextDouble();
        System.out.println("xEnd:");
        double xEnd = scanner.nextDouble();
        System.out.println("xStep:");
        double xStep = scanner.nextDouble();

        System.out.println("A = " + fun.getA());
        for(double x = xBeg; x <= xEnd; x += xStep) {
        System.out.printf("x: %6.4f\t: %6.4f\n", x, fun.evalf(x));
        }

        System.out.println("x:");
        double x = scanner.nextDouble();
        System.out.println("aBeg:");
        double aBeg = scanner.nextDouble();
        System.out.println("aEnd:");
        double aEnd = scanner.nextDouble();
        System.out.println("aStep:");
        double aStep = scanner.nextDouble();

        System.out.println("x = " + x);
        for(double a = aBeg; a <= aEnd; a += aStep) {
            System.out.printf("a: %6.4f\t: %6.4f\n", fun.getA(), fun.evalf(x));
        }

    }

    public FFunction(double a){
        this.a = a;
    }

    public FFunction(){
        this(1.0);
    }

    public double getA(){
        return a;
    }

    public void setA(double a){
        this.a = a;
    }

    @Override
    public double evalf(double x) {
        return Math.exp(-a * x * x) * Math.sin(x);
    }
}
