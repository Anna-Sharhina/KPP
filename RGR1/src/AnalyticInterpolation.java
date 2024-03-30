import edu.hws.jcm.data.Expression;
import edu.hws.jcm.data.Parser;
import edu.hws.jcm.data.Variable;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;


public class AnalyticInterpolation {

    public static final BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

    public static void main(String[] args) {

        String func;
        double start, stop, step;
        int choice;

        while (true) {

            System.out.println("\n1. Function with parameter");
            System.out.println("2. Function without parameter");
            System.out.println("3. Exit");
            choice = scanInteger();

            if(choice == 3)
                return;

            System.out.println("Function: ");
            func = scanLine();
            do{
                System.out.println("Start:");
                start = scanDouble();
                System.out.println("Stop:");
                stop = scanDouble();
            } while (start >= stop);
            System.out.println("Step: ");
            step = scanDouble();

            switch (choice) {
                case 1:
                    withParam(func, start, stop, step);
                    break;
                case 2:
                    withoutParam(func, start, stop, step);
                    break;
                default:
                    System.out.println("Error");
            }
        }
    }

    public static void withParam(String funStr, double start, double stop, double step) {
        Parser parser = new Parser(Parser.STANDARD_FUNCTIONS);
        System.out.println("1. Function with parameter");
        Variable par = new Variable("a");
        Variable var = new Variable("x");
        parser.add(var);
        parser.add(par);
        Expression fun = parser.parse(funStr);
        Expression der = fun.derivative(var);
        System.out.println("f(x) = " + fun.toString());
        System.out.println("f'(x) = " + der.toString());
        par.setVal(1.0);
        for (double x = start; x <= stop; x += step) {
            var.setVal(x);
            System.out.println(x + "\t" + fun.getVal() + "\t" + der.getVal());
        }
    }

    public static void withoutParam(String funStr, double start, double stop, double step) {
        Parser parser = new Parser(Parser.STANDARD_FUNCTIONS);
        System.out.println("2. Function without parameter");
        Variable var = new Variable("x");
        parser.add(var);
        var funs = parser.parse(funStr);
        Expression ders = funs.derivative(var);
        System.out.println("f(x) = " + funs.toString());
        System.out.println("f'(x) = " + ders.toString());
        for (double x = start; x <= stop; x += step) {
            var.setVal(x);
            System.out.println(x + "\t" + funs.getVal() + "\t" + ders.getVal());
        }
    }

    public static int scanInteger(){
        int num = 0;
        boolean check = true;
        while (check){
            check = false;
            try {
                num = Integer.parseInt(in.readLine());
            } catch (NumberFormatException | IOException e){
                check = true;
                System.err.println("Incorrect number format");
            }
        }
        return num;
    }

    public static double scanDouble(){
        double num = 0;
        boolean check = true;
        while (check){
            check = false;
            try {
                num = Double.parseDouble(in.readLine());
            } catch (NumberFormatException | IOException e){
                check = true;
                System.err.println("Incorrect number format");
            }
        }
        return num;
    }

    public static String scanLine(){
        String check = "";
        try {
            check = in.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return check;
    }

}


