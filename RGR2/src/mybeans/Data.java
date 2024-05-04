package mybeans;

public class Data {
    private double x;
    private double y;
    private String date;
    public Data(){
        this.x = 0;
        this.y = 0;
        this.date = "11.11.2011";
    }

    public Data(double x, double y, String date){
        this.x = x;
        this.y = y;
        this.date = date;

    }

    public String getDate() {
        return date;
    }

    public void setDate(String  date) {
        this.date = date;
    }

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }

    @Override
    public String toString() {
        return "Data{" +
                "x=" + x +
                ", y=" + y +
                ", date=" + date +
                '}';
    }
}

