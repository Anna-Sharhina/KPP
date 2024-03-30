import java.io.*;
import java.util.Scanner;


public class FileListInterpolation extends ListInterpolation{

    public static void main(String[] args) {
        FileListInterpolation fun = new FileListInterpolation();

        int num;
        double x;
        Scanner scanner = new Scanner(System.in);

        do {
            System.out.print("Points count:");
            num = scanner.nextInt();
        } while (num <= 0);

        for (int i = 0; i < num; i++){
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

        System.out.println("Write to file");
        try {
            fun.writeToFile("dataList.csv");
        }
        catch (IOException ex){
            ex.printStackTrace();
            System.exit(-1);
        }

        System.out.println("Read from file");
        fun.clear();
        try {
            fun.readFromFile("dataList.csv");
        }
        catch (IOException ex){
            ex.printStackTrace();
            System.exit(-1);
        }

        System.out.println("Data form file:");
        fun.sort();
        for (int i = 0; i < fun.numPoints(); i++)
            System.out.println("Point " + (i+1) + ": " + fun.getPoint(i));

        System.out.println("Min value x: " + fun.getPoint(0).getX());
        System.out.println("Min value x: " + fun.getPoint(fun.numPoints() - 1).getX());

        x = 0.5 * (fun.getPoint(0).getX() + fun.getPoint(fun.numPoints() - 1).getX());
        System.out.println("Interpolation value fun(" + x + ") = " + fun.evalf(x));
        System.out.println("Exact value of sin(" + x + ") = " + Math.sin(x));
        System.out.println("Absolute error = " + Math.abs(fun.evalf(x) - Math.sin(x)));

        System.out.println("Preparing data for calculations");
        fun.clear();
        for ( x = 1.0; x <= 7.0; x += 0.1){
            fun.addPoint(new Point2D(x, Math.sin(x)));
        }
        try {
            fun.writeToFile("TblFunList.dat");
        }
        catch (IOException ex){
            ex.printStackTrace();
            System.exit(-1);
        }
    }

    public FileListInterpolation(){
        super();
    }

    public void readFromFile(String fileName) throws IOException{
        BufferedReader reader = new BufferedReader(new FileReader(fileName));
        String line = reader.readLine();
        clear();
        while ((line = reader.readLine()) != null){
            String[] data = line.split(";");
            double x = Double.parseDouble(data[0]);
            double y = Double.parseDouble(data[1]);
            addPoint(new Point2D(x, y));
        }
        reader.close();
    }

    public void writeToFile(String fileName) throws  IOException{
        PrintWriter writer = new PrintWriter(new FileWriter((fileName)));
        writer.println("x"+ ";" + "y");
        for (int i = 0; i < numPoints(); i++){
            writer.println(getPoint(i).getX() + ";" + getPoint(i).getY());
        }
        writer.close();
    }

}
