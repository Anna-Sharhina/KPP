import java.util.Iterator;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;


public class TreeMapInterpolation extends Interpolator {

    private TreeMap<Double, Double> data = new TreeMap<>();

    public static void main(String[] args) {
        TreeMapInterpolation fun = new TreeMapInterpolation();
        int num;
        double x;
        Scanner scanner = new Scanner(System.in);

        do {
            System.out.println("Points count:");
            num = scanner.nextInt();
        } while(num <= 0);

        for(int i = 0; i < num; i++){
            x = 1.0 + (5.0 - 1.0) * Math.random();
            fun.addPoint(new Point2D(x, Math.sin(x)));
        }

        System.out.println("Interpolation by: " + fun.numPoints() + " точкам");
        for(int i = 0; i < fun.numPoints(); i++)
            System.out.println("Point: " + (i+1) + ": " + fun.getPoint(i));

        System.out.println("Min value x: " + fun.getPoint(0).getX());
        System.out.println("Min value x: " + fun.getPoint(fun.numPoints() - 1).getX());

        x = 0.5 * (fun.getPoint(0).getX() + fun.getPoint(fun.numPoints() - 1).getX());
        System.out.println("Interpolation value fun(" + x + ") = " + fun.evalf(x));
        System.out.println("Exact value of sin(" + x + ") = " + Math.sin(x));
        System.out.println("Absolute error = " + Math.abs(fun.evalf(x) - Math.sin(x)));

    }

    public TreeMapInterpolation(TreeMap<Double, Double> data) {
        this.data = data;
    }

    public TreeMapInterpolation() {
    }

    public void clear() {
        data.clear();
    }

    public int numPoints() {
        return data.size();
    }

    public void addPoint(Point2D pt) {
        data.put(pt.getX(), pt.getY());
    }

    public Point2D getPoint(int i) {
        if (i < 0 || data.size() <= i) {
            throw new IndexOutOfBoundsException("IndexOutOfBoundsException");
        }
        Map.Entry<Double, Double> e = null;
        Iterator<Map.Entry<Double, Double>> it = data.entrySet().iterator();
        while (0 <= i--) {
            e = it.next();
        }
        double x = e.getKey();
        double y = e.getValue();
        return new Point2D(x, y);
    }

    public void setPoint(int i, Point2D pt) {
        data.put(pt.getX(), pt.getY());
    }

    public void removeLastPoint() {
        data.remove(data.size() - 1);
    }

    public void sort() {
    }
}
