import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class Frame extends JFrame {

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
        setTitle("ReflectionProgram");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(450, 100, 700, 600);

        JPanel contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        contentPane.setLayout(new BorderLayout(0, 0));
        setContentPane(contentPane);

        JPanel panelData = new JPanel();
        contentPane.add(panelData, BorderLayout.NORTH);

        JPanel panelButtons = new JPanel();
        contentPane.add(panelButtons, BorderLayout.SOUTH);

        JLabel ClassLabel = new JLabel("Class name:");
        panelData.add(ClassLabel);

        JTextField textField = new JTextField();
        panelData.add(textField);
        textField.setColumns(40);

        JTextArea infoField = new JTextArea();
        JScrollPane scrollPane = new JScrollPane(infoField);
        contentPane.add(scrollPane, BorderLayout.CENTER);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);

        JButton buttonAnalysis = new JButton("Analysis");
        buttonAnalysis.addActionListener(e -> {
            try {
                String info = ReflectionProgram.getDescription(textField.getText());
                infoField.setText(info);
            } catch (ClassNotFoundException ex) {
                ex.printStackTrace();
            }
        });

        panelButtons.add(buttonAnalysis);

        JButton buttonClear = new JButton("Clear");
        buttonClear.addActionListener(e -> {
            infoField.setText("");
            textField.setText("");
        });
        panelButtons.add(buttonClear);

        JButton buttonExit = new JButton("Exit");
        buttonExit.addActionListener(e -> System.exit(0));
        panelButtons.add(buttonExit);

    }
}