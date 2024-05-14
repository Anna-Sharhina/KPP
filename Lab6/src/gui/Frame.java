package gui;

import javax.swing.*;
import java.awt.*;
import java.lang.reflect.Proxy;
import java.net.InetAddress;
import java.net.UnknownHostException;

public class Frame extends JFrame {
    private JButton connectButton;
    private JButton disconnectButton;
    private JButton clearButton;
    private JButton exitButton;
    private JButton sendButton;

    private JTextArea textPane1;
    private JTextArea textArea1;
    private JTextArea groupTextArea;
    private JTextArea portTextArea;
    private JTextArea nameArea;

    private JPanel ConnectPanel;
    private JPanel panelText;
    private JPanel contentPanel;

    private JTextField yourNameTextField;
    private Messanger messanger = null;

    public static void main(String[] args) {
        Frame clientFrame = new Frame();
        clientFrame.setVisible(true);
    }

    public Frame(){

        setTitle("Frame");
        setLayout(new BorderLayout());
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setSize(600, 400);
        setResizable(false);
        setLocationRelativeTo(null);

        ConnectPanel = new JPanel(new GridLayout(3, 2));
        ConnectPanel.setSize(250, 300);
        add(ConnectPanel, BorderLayout.CENTER);

        setContentPane(contentPanel);

        connectButton.addActionListener(e -> {
            UITasks ui = (UITasks) Proxy.newProxyInstance(getClass().getClassLoader(),
                    new Class[]{UITasks.class},
                    new EDTInvocationHandler(new UITasksImpl()));
            try {
                String name = nameArea.getText();
                messanger = new MessangerImpl(InetAddress.getByName(groupTextArea.getText()),Integer.parseInt(portTextArea.getText()), name, ui);
            }
            catch (UnknownHostException ex) {
                throw new RuntimeException(ex);
            }
            messanger.start();
            disconnectButton.setEnabled(true);
            connectButton.setEnabled(false);
        });

        sendButton.addActionListener(e -> messanger.send());

        disconnectButton.addActionListener(e -> {
            messanger.stop();
            connectButton.setEnabled(true);
            disconnectButton.setEnabled(false);
        });

        clearButton.addActionListener(e -> textPane1.setText(""));

        exitButton.addActionListener(e -> {
            JFrame frame = (JFrame) SwingUtilities.getWindowAncestor(exitButton);
            frame.dispose();
        });
    }


    private class UITasksImpl implements UITasks {

        @Override
        public String getMessage() {
            String res = textArea1.getText();
            textArea1.setText("");
            return res;
        }

        @Override
        public void setText(String txt) {
            textPane1.append(txt + "\n");
        }
    }
}
