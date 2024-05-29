package client;

import javax.swing.*;

public class TextClient  extends JFrame {
    private static final long serialVersionUID = 1L;

    TextClient(String text) {
        this.initComponents(text);
    }

    private void initComponents(String text) {
        this.setDefaultCloseOperation(3);
        JScrollPane jScrollPane = new JScrollPane();
        JTextArea mainArea = new JTextArea();
        JLabel label = new JLabel();
        mainArea.setColumns(20);
        mainArea.setRows(5);
        jScrollPane.setViewportView(mainArea);
        label.setText("Info:");
        GroupLayout layout = new GroupLayout(this.getContentPane());
        this.getContentPane().setLayout(layout);
        layout.setHorizontalGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING).addComponent(jScrollPane).addGroup(layout.createSequentialGroup().addContainerGap().addComponent(label, -2, 113, -2).addContainerGap(277, 32767)));
        layout.setVerticalGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(GroupLayout.Alignment.TRAILING, layout.createSequentialGroup().addGap(0, 11, 32767).addComponent(label).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addComponent(jScrollPane, -2, 276, -2)));
        mainArea.setText(text);
        this.setDefaultCloseOperation(2);
        this.pack();
    }
}

