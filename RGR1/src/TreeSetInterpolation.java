import java.util.Iterator;
import java.util.Scanner;
import java.util.TreeSet;

public class TreeSetInterpolation extends Interpolator {

    private TreeSet<Point2D> data = new TreeSet<>();

    public static void main(String[] args) {
        TreeSetInterpolation fun = new TreeSetInterpolation();
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

    public TreeSetInterpolation(TreeSet<Point2D> data) {
        this.data = data;
    }

    public TreeSetInterpolation() {
    }

    public void clear() {
        data.clear();
    }

    public int numPoints() {
        return data.size();
    }

    public void addPoint(Point2D pt) {
        data.add(pt);
    }

    public Point2D getPoint(int i) {
        if (i == 0) {
            return data.first();
        }
        int n = 0;
        Iterator<Point2D> it = data.iterator();
        Point2D curr = null;
        while (it.hasNext() && n <= i) {
            curr = it.next();
            n++;
        }
        return curr;
    }

    public void setPoint(int i, Point2D pt) {
        data.add(pt);
    }

    public void removeLastPoint() {
        data.remove(data.size() - 1);
    }

    public void sort() {
    }

}
