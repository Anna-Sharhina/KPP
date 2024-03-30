import java.lang.Comparable;
import java.util.List;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Collections;


public class Point2D extends Point implements Comparable<Point2D>{

    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args){
        List<Point2D> data = new ArrayList<Point2D>();
        int num;
        double x;

        do {
           System.out.println("Points count:");
           num = scanner.nextInt();
        } while( num <= 0);

        for(int i = 0; i < num; i++){
            x = 1.0 + (5.0 - 1.0) * Math.random();
            data.add(new Point2D(x, Math.sin(x)));
        }

        System.out.println("Unsorted data:");
        for(Point2D pt : data){
            System.out.println(pt);
        }

        System.out.println("Sorted data:");
        Collections.sort(data);
        for(Point2D pt : data){
            System.out.println("x = " + pt.getX() + "\ty = " + pt.getY());
        }
    }

    public Point2D(double x, double y) {
        super(2);
        setCoords(1, x); setCoords(2, y);
    }

    public Point2D(){
        this(0,0);
    }

    public double getX(){
        return getCoords(1);
    }

    public void setX(double x){
        setCoords(1, x);
    }

    public double getY(){
        return getCoords(2);
    }

    public void setY(double y){
        setCoords(2, y);
    }

    @Override
    public int compareTo(Point2D pt) {
        return Double.compare(getX(), pt.getX());
    }

}
