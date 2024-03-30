import javax.swing.*;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;

import edu.hws.jcm.data.Expression;
import edu.hws.jcm.data.Parser;

import java.awt.FlowLayout;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import edu.hws.jcm.data.Variable;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;
import org.jfree.ui.RectangleInsets;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;


public class Frame extends JFrame {
    private final JTextField textFieldA;
    private final JTextField textFieldFun;
    private final JTextField textFieldStart;
    private final JTextField textFieldStop;
    private final JTextField textFieldStep;

    private XYSeries funSeries;
    private XYSeries derSeries;

    private double start;
    private double stop;
    private double step;
    private double a;

    Parser parser = new Parser(Parser.STANDARD_FUNCTIONS);
    Variable varS = new Variable("x");
    Variable varA = new Variable("a");

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
                Frame frame = new Frame();
                frame.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    public Frame() {
        setResizable(false);
        setTitle("fFreeChart Test Plot");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 600, 500);

        JPanel contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        contentPane.setLayout(new BorderLayout(0, 0));
        setContentPane(contentPane);

        JPanel panelButtons = new JPanel();
        FlowLayout flowLayout = (FlowLayout) panelButtons.getLayout();
        flowLayout.setHgap(15);

        contentPane.add(panelButtons, BorderLayout.SOUTH);
        JButton btnNewButtonPlot = new JButton("Plot");
        panelButtons.add(btnNewButtonPlot);
        btnNewButtonPlot.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                a = Double.parseDouble(textFieldA.getText());
                start = Double.parseDouble(textFieldStart.getText());
                stop = Double.parseDouble(textFieldStop.getText());
                step = Double.parseDouble(textFieldStep.getText());

                funSeries.clear();
                derSeries.clear();

                String fun = String.valueOf(textFieldFun.getText());

                parser.add(varS);
                parser.add(varA);

                Expression function = parser.parse(fun);
                Expression derFun = function.derivative(varS);
                varA.setVal(a);

                for (double x = start; x < stop; x += step) {
                    varS.setVal(x);
                    funSeries.add(x, function.getVal());
                    derSeries.add(x, derFun.getVal());
                }
            }
        });

        JButton btnNewButtonExit = new JButton("Exit");
        btnNewButtonExit.addActionListener(e -> System.exit(0));
        panelButtons.add(btnNewButtonExit);

        JPanel panelData = new JPanel();
        contentPane.add(panelData, BorderLayout.NORTH);

        JLabel FunNewLabel = new JLabel("fun(x):");
        panelData.add(FunNewLabel);
        textFieldFun = new JTextField();
        textFieldFun.setLocation(1, 1);
        textFieldFun.setText("sin(a * x) / x");
        panelData.add(textFieldFun);
        textFieldFun.setColumns(15);

        JLabel lblNewLabel = new JLabel("a:");
        panelData.add(lblNewLabel);
        textFieldA = new JTextField();
        textFieldA.setText("1.0");
        panelData.add(textFieldA);
        textFieldA.setColumns(5);

        JLabel StartNewLabel = new JLabel("Start:");
        panelButtons.add(StartNewLabel);
        textFieldStart = new JTextField();
        textFieldStart.setText("-9.0");
        panelButtons.add(textFieldStart);
        textFieldStart.setColumns(5);

        JLabel StopNewLabel = new JLabel("Stop:");
        panelButtons.add(StopNewLabel);
        textFieldStop = new JTextField();
        textFieldStop.setText("9.0");
        panelButtons.add(textFieldStop);
        textFieldStop.setColumns(5);

        JLabel StepNewLabel = new JLabel("Step:");
        panelButtons.add(StepNewLabel);
        textFieldStep = new JTextField();
        textFieldStep.setText("0.01");
        panelButtons.add(textFieldStep);
        textFieldStep.setColumns(5);

        JFreeChart chart = createChart();
        ChartPanel chartPanel = new ChartPanel(chart);
        contentPane.add(chartPanel, BorderLayout.CENTER);
    }

    private JFreeChart createChart() {

        funSeries = new XYSeries("Function");
        derSeries = new XYSeries("Derivative");

        a = Double.parseDouble(textFieldA.getText());
        start = Double.parseDouble(textFieldStart.getText());
        stop = Double.parseDouble(textFieldStop.getText());
        step = Double.parseDouble(textFieldStep.getText());

        String fun = String.valueOf(textFieldFun.getText());

        parser.add(varS);
        parser.add(varA);

        Expression function = parser.parse(fun);
        Expression derFun = function.derivative(varS);
        varA.setVal(a);

        for (double x = start; x < stop; x += step) {
            varS.setVal(x);
            funSeries.add(x, function.getVal());
            derSeries.add(x, derFun.getVal());
        }

        XYSeriesCollection dataset = new XYSeriesCollection();
        dataset.addSeries(funSeries);
        dataset.addSeries(derSeries);

        JFreeChart chart = ChartFactory.createXYLineChart(
                null,
                "X",
                "Y",
                dataset,
                PlotOrientation.VERTICAL, true,
                true,
                false
        );

        chart.setBackgroundPaint(Color.white);

        XYPlot plot = (XYPlot) chart.getPlot();
        plot.setBackgroundPaint(Color.lightGray);
        plot.setOutlinePaint(Color.RED);
        plot.setAxisOffset(new RectangleInsets(10.0, 10.0, 10.0, 10.0));
        plot.setDomainGridlinePaint(Color.white);
        plot.setRangeGridlinePaint(Color.white);

        return chart;
    }
}
