import java.util.*;

public class ListInterpolation extends Interpolator {

    private List<Point2D> data = null;

    public static void main(String[] args) {
        ListInterpolation fun = new ListInterpolation();
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
        System.out.println("Unsorted:");
        for(int i = 0; i < fun.numPoints(); i++)
            System.out.println("Point: " + (i+1) + ": " + fun.getPoint(i));

        System.out.println("Sorted:");
        fun.sort();
        for(int i = 0; i < fun.numPoints(); i++)
            System.out.println("Point: " + (i+1) + ": " + fun.getPoint(i));

        System.out.println("Min value x: " + fun.getPoint(0).getX());
        System.out.println("Min value x: " + fun.getPoint(fun.numPoints() - 1).getX());

        x = 0.5 * (fun.getPoint(0).getX() + fun.getPoint(fun.numPoints() - 1).getX());
        System.out.println("Interpolation value fun(" + x + ") = " + fun.evalf(x));
        System.out.println("Exact value of sin(" + x + ") = " + Math.sin(x));
        System.out.println("Absolute error = " + Math.abs(fun.evalf(x) - Math.sin(x)));

    }

    public ListInterpolation(List<Point2D> data){
        this.data = data;
    }

    public ListInterpolation(){
        data = new ArrayList<Point2D>();
    }

    public ListInterpolation(Point2D[] data){
        this();
        for(Point2D pt : data)
            this.data.add(pt);
    }

    @Override
    public void clear() {
        data.clear();
    }

    @Override
    public int numPoints() {
        return data.size();
    }

    @Override
    public void addPoint(Point2D pt) {
        data.add(pt);
    }

    @Override
    public Point2D getPoint(int i) {
        return data.get(i);
    }

    @Override
    public void setPoint(int i, Point2D pt) {
        data.set(i, pt);
    }

    @Override
    public void removeLastPoint() {
        data.remove(data.size() - 1);
    }

    @Override
    public void sort() {
        Collections.sort(data);
    }
}
