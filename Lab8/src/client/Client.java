package client;

import interfaces.Conference;

import javax.swing.*;
import java.awt.*;
import java.rmi.ConnectException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class Client extends JFrame {
    private static final long serialVersionUID = 1L;
    private JTextField hostTextField;
    private JTextField portTextField;
    private JTextField participantsTextField;
    private JTextField nameTextField;
    private JTextField surnameTextField;
    private JTextField organizationTextField;
    private JTextField reportTextField;
    private JTextField emailTextField;
    private Conference stub;

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            Client client = new Client();
            client.setVisible(true);
            client.setLocation(Toolkit.getDefaultToolkit().getScreenSize().width - client.getWidth(), Toolkit.getDefaultToolkit().getScreenSize().height / 2 - client.getHeight() / 2);
        });
    }

    private Client() {
        this.initComponents();
    }

    private void initComponents() {
        this.setDefaultCloseOperation(3);
        JPanel buttonPanel = new JPanel();
        JButton registerButton = new JButton();
        JButton clearButton = new JButton();
        JButton getInfoButton = new JButton();
        JButton exitButton = new JButton();
        JPanel mainPanel = new JPanel();
        JLabel labelHost = new JLabel();
        JLabel labelPort = new JLabel();
        JLabel labelParticipants = new JLabel();
        JLabel labelName = new JLabel();
        JLabel labelSurname = new JLabel();
        JLabel labelOrganization = new JLabel();
        JLabel labelReport = new JLabel();
        JLabel labelEmail = new JLabel();
        this.hostTextField = new JTextField();
        this.portTextField = new JTextField();
        this.nameTextField = new JTextField();
        this.surnameTextField = new JTextField();
        this.organizationTextField = new JTextField();
        this.reportTextField = new JTextField();
        this.emailTextField = new JTextField();
        this.participantsTextField = new JTextField();
        labelHost.setText("Host:");
        this.hostTextField.setText("127.0.0.1");
        labelPort.setText("Port:");
        this.portTextField.setText("10100");
        labelParticipants.setText("Participants:");
        this.participantsTextField.setEditable(false);
        this.participantsTextField.setText("0");
        labelName.setText("Name:");
        labelSurname.setText("Surname:");
        labelOrganization.setText("Organization:");
        labelReport.setText("Report:");
        labelEmail.setText("Email:");
        registerButton.setText("Register");
        registerButton.addActionListener((event) -> {
            try {
                this.registerParticipant();
            } catch (NotBoundException | RemoteException var3) {
                var3.printStackTrace();
            }

        });
        clearButton.setText("Clear");
        clearButton.addActionListener((event) -> {
            this.clear();
        });
        getInfoButton.setText("Get info");
        getInfoButton.addActionListener((event) -> {
            try {
                this.getInfo();
            } catch (NotBoundException | RemoteException var3) {
                JOptionPane.showMessageDialog(this, "Failed to get info", "Error", 0);
            }

        });
        exitButton.setText("Finish");
        exitButton.addActionListener((event) -> {
            System.exit(0);
        });
        buttonPanel.setLayout(new FlowLayout());
        buttonPanel.add(registerButton);
        buttonPanel.add(clearButton);
        buttonPanel.add(getInfoButton);
        buttonPanel.add(exitButton);
        GroupLayout MainPanelLayout = new GroupLayout(mainPanel);
        mainPanel.setLayout(MainPanelLayout);
        MainPanelLayout.setHorizontalGroup(MainPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(MainPanelLayout.createSequentialGroup().addContainerGap().addGroup(MainPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(MainPanelLayout.createSequentialGroup().addComponent(labelHost).addGap(18, 18, 18).addComponent(this.hostTextField, -2, 75, -2).addGap(18, 18, 18).addComponent(labelPort).addGap(18, 18, 18).addComponent(this.portTextField, -2, 57, -2).addGap(18, 18, 18).addComponent(labelParticipants).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addComponent(this.participantsTextField, -2, 34, -2)).addGroup(MainPanelLayout.createSequentialGroup().addGroup(MainPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING).addComponent(labelSurname).addComponent(labelName).addComponent(labelOrganization).addComponent(labelReport).addComponent(labelEmail)).addGap(29, 29, 29).addGroup(MainPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING, false).addComponent(this.nameTextField).addComponent(this.surnameTextField).addComponent(this.organizationTextField).addComponent(this.reportTextField, -1, 248, 32767).addComponent(this.emailTextField)))).addContainerGap(-1, 32767)));
        MainPanelLayout.setVerticalGroup(MainPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(MainPanelLayout.createSequentialGroup().addGap(21, 21, 21).addGroup(MainPanelLayout.createParallelGroup(GroupLayout.Alignment.BASELINE).addComponent(labelHost).addComponent(this.hostTextField, -2, -1, -2).addComponent(labelPort).addComponent(this.portTextField, -2, -1, -2).addComponent(labelParticipants).addComponent(this.participantsTextField, -2, -1, -2)).addGap(26, 26, 26).addGroup(MainPanelLayout.createParallelGroup(GroupLayout.Alignment.BASELINE).addComponent(labelName).addComponent(this.nameTextField, -2, -1, -2)).addGap(18, 18, 18).addGroup(MainPanelLayout.createParallelGroup(GroupLayout.Alignment.BASELINE).addComponent(labelSurname).addComponent(this.surnameTextField, -2, -1, -2)).addGap(18, 18, 18).addGroup(MainPanelLayout.createParallelGroup(GroupLayout.Alignment.BASELINE).addComponent(labelOrganization).addComponent(this.organizationTextField, -2, -1, -2)).addGap(18, 18, 18).addGroup(MainPanelLayout.createParallelGroup(GroupLayout.Alignment.BASELINE).addComponent(labelReport).addComponent(this.reportTextField, -2, -1, -2)).addGap(18, 18, 18).addGroup(MainPanelLayout.createParallelGroup(GroupLayout.Alignment.BASELINE).addComponent(labelEmail).addComponent(this.emailTextField, -2, -1, -2)).addContainerGap(-1, 32767)));
        GroupLayout layout = new GroupLayout(this.getContentPane());
        this.getContentPane().setLayout(layout);
        layout.setHorizontalGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING).addComponent(buttonPanel, -1, -1, 32767).addComponent(mainPanel, -1, -1, 32767));
        layout.setVerticalGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(GroupLayout.Alignment.TRAILING, layout.createSequentialGroup().addComponent(mainPanel, -1, -1, 32767).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addComponent(buttonPanel, -2, -1, -2)));
        this.setResizable(false);
        this.pack();
    }

    private void registerParticipant() throws NumberFormatException, RemoteException, NotBoundException {
        try {
            String findName = "Registrable";
            Registry registry = LocateRegistry.getRegistry(this.hostTextField.getText(), Integer.parseInt(this.portTextField.getText()));
            this.stub = (Conference)registry.lookup(findName);
            this.participantsTextField.setText(String.valueOf(this.stub.register(this.getParticipantInfo())));
            JOptionPane.showMessageDialog(this, "Thank you for registering", "Successful", 1);
        } catch (ConnectException var3) {
            JOptionPane.showMessageDialog(this, "Failed to register", "Error", 0);
        }

    }

    private Participant getParticipantInfo() {
        String name = this.nameTextField.getText();
        String surname = this.surnameTextField.getText();
        String organization = this.organizationTextField.getText();
        String report = this.reportTextField.getText();
        String email = this.emailTextField.getText();
        return new Participant(name, surname, organization, report, email);
    }

    private void getInfo() throws NumberFormatException, RemoteException, NotBoundException {
        if (this.stub == null) {
            String findName = "Registrable";
            Registry registry = LocateRegistry.getRegistry(this.hostTextField.getText(), Integer.parseInt(this.portTextField.getText()));
            this.stub = (Conference)registry.lookup(findName);
        }

        this.participantsTextField.setText(String.valueOf(this.stub.getSize()));
        (new TextClient(this.stub.getInfo())).setVisible(true);
    }

    private void clear() {
        this.nameTextField.setText("");
        this.surnameTextField.setText("");
        this.organizationTextField.setText("");
        this.reportTextField.setText("");
        this.emailTextField.setText("");
    }
}
