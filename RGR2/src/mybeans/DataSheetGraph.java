package mybeans;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Line2D;
import java.awt.geom.Rectangle2D;
import java.io.Serial;

public class DataSheetGraph  extends JPanel {
    @Serial
    private static final long serialVersionUID = 1L;
    private DataSheet dataSheet;
    private boolean isConnected;
    private int deltaX;
    private int deltaY;
    transient private Color color;

    public DataSheetGraph (DataSheet dataSheet) {
        super();
        initialize();
        this.dataSheet = dataSheet;
    }
    private void initialize() {
        isConnected = false;
        deltaX = 5;
        deltaY = 5;
        color = Color.red;
        this.setSize(300, 400);
    }
    private double minX(){
        double result = 100000;
        for (Data data : dataSheet.getDataArrayList()){
            if (data.getX() < result)
                result = data.getX();
        }

        return result;
    }

    private double maxX(){
        double result = 0;
        for (Data data : dataSheet.getDataArrayList()){
            if (data.getX() > result)
                result = data.getX();
        }

        return result;
    }

    private double minY(){
        double result = 100000;
        for (Data data : dataSheet.getDataArrayList()){
            if (data.getY() < result)
                result = data.getY();
        }

        return result;
    }

    private double maxY(){
        double result = 0;
        for (Data data : dataSheet.getDataArrayList()){
            if (data.getY() > result)
                result = data.getY();
        }

        return result;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        showGraph(g2);
    }
    public void showGraph(Graphics2D graphics2D){
        double xMin = minX() - deltaX;
        double xMax = maxX() + deltaX;
        double width = getWidth();
        double height = getHeight();
        double xScale = 2*width / (xMax - xMin);
        double x0 = 20;
        double y0 = height - 20;

        Paint oldColor = graphics2D.getPaint();
        graphics2D.setPaint(Color.WHITE);
        graphics2D.fill(new Rectangle2D.Double(0.0, 0.0, width, height));

        Stroke oldStroke = graphics2D.getStroke();
        Font oldFont = graphics2D.getFont();

        float[] dashPattern = {10, 10};
        graphics2D.setStroke(new BasicStroke(1.0f, BasicStroke.CAP_BUTT, BasicStroke.JOIN_MITER, 10.0f, dashPattern, 0));
        graphics2D.setFont(new Font("Serif", Font.BOLD, 14));

        double xStep = 1;
        for (double dx = xStep; dx < width; dx += xStep){
            double x = x0 + dx*xScale;
            graphics2D.setPaint(Color.LIGHT_GRAY);
            graphics2D.draw(new Line2D.Double(x, 0, x, height));
            graphics2D.setPaint(Color.BLACK);
            graphics2D.drawString(String.valueOf((int) (Math.round(dx / xStep) * xStep)), (int) x + 2, 15);
        }


        double yStep = 1;
        for (double dy = yStep; dy < height; dy += yStep){
            double y = y0 - dy*xScale;
            graphics2D.setPaint(Color.LIGHT_GRAY);
            graphics2D.draw(new Line2D.Double(0, y, width, y));
            graphics2D.setPaint(Color.BLACK);
            graphics2D.drawString(String.valueOf((int) (Math.round(dy / yStep) * yStep)), 2, (int)y - 2);
        }


        graphics2D.setPaint(Color.BLACK);
        graphics2D.setStroke(new BasicStroke(3.0f));
        graphics2D.draw(new Line2D.Double(x0, 0, x0, height));
        graphics2D.draw(new Line2D.Double(0, y0, width, y0));
        graphics2D.drawString("X", (int)width - 10, (int)y0 - 2);
        graphics2D.drawString("Y", (int)x0 + 2, 10);

        if (dataSheet != null){
            if (!isConnected){
                for (Data data : dataSheet.getDataArrayList()){
                    double x = x0 + data.getX()*xScale;
                    double y = y0 - data.getY()*xScale;
                    graphics2D.setColor(Color.WHITE);
                    graphics2D.fillOval((int)(x - 5 / 2), (int) (y - 5 / 2), 5, 5); //??? x - 5 / 2
                    graphics2D.setColor(color);
                    graphics2D.fillOval((int) (x - 5 / 2), (int) (y - 5 / 2), 5, 5);
                }
            } else {
                graphics2D.setPaint(color);
                graphics2D.setStroke(new BasicStroke(2.0f));
                double xOld = x0 + dataSheet.getDataItem(0).getX() * xScale;
                double yOld = y0 - dataSheet.getDataItem(0).getY() * xScale;

                for (Data data : dataSheet.getDataArrayList()){
                    double x = x0 + data.getX()*xScale;
                    double y = y0 - data.getY()*xScale;
                    graphics2D.draw(new Line2D.Double(xOld, yOld, (double)x, y));
                    xOld = x;
                    yOld = y;
                }
            }

            graphics2D.setPaint(oldColor);
            graphics2D.setStroke(oldStroke);
            graphics2D.setFont(oldFont);
        }
    }
    public DataSheet getDataSheet() {
        return dataSheet;
    }
    public void setDataSheet(DataSheet dataSheet) {
        this.dataSheet = dataSheet;
    }
    public boolean isConnected() {
        return isConnected;
    }
    public void setConnected(boolean isConnected) {
        this.isConnected = isConnected;
        repaint();
    }
    public int getDeltaX() {
        return deltaX;
    }
    public int getDeltaY() {
        return deltaY;
    }
    public void setDeltaX(int deltaX) {
        this.deltaX = deltaX;
        repaint();
    }
    public void setDeltaY(int deltaY) {
        this.deltaY = deltaY;
        repaint();
    }
    public Color getColor() {
        return color;
    }
    public void setColor(Color color) {
        this.color = color;
        repaint();
    }

}
