package mybeans;

import javax.swing.*;
import java.awt.*;

public class DataSheetTable extends JPanel {
    private JPanel panel;
    private JPanel buttonPanel;
    private JButton add;
    private JButton delete;
    private JScrollPane scrollPanel;
    private JTable table;
    private DataSheetTableModel tableModel;

    public DataSheetTable(DataSheet dataSheet) {
        createTableBean();
        createTable(dataSheet);
        createScrollPanel();
        createButtonPanel();
    }

    private void createTable(DataSheet dataSheet){
        table = new JTable();
        tableModel = new DataSheetTableModel(dataSheet);
        table.setModel(tableModel);
    }

    private void createAddButton(){
        add.addActionListener(e -> {
            Data data = new Data();

            if (tableModel.isEmpty()) {
                tableModel.getDataSheet().getDataItem(0).setDate(data.getDate());
                tableModel.getDataSheet().getDataItem(0).setX(data.getX());
                tableModel.getDataSheet().getDataItem(0).setY(data.getY());
            } else
                tableModel.getDataSheet().addData(data);

            table.repaint();
            tableModel.setRowCount(tableModel.getDataSheet().size());
            revalidate();
            tableModel.fireDataSheetChange();
        });
    }

    private void createDeleteButton(){
        delete.addActionListener(e -> {
            if (tableModel.getRowCount() > 1){
                tableModel.setRowCount(tableModel.getRowCount() - 1);
                tableModel.getDataSheet().removeDataItem(tableModel.getDataSheet().size() - 1);

            } else {
                tableModel.getDataSheet().getDataItem(0).setDate("");
                tableModel.getDataSheet().getDataItem(0).setX(0);
                tableModel.getDataSheet().getDataItem(0).setY(0);
            }

            table.revalidate();
            table.repaint();
            tableModel.fireDataSheetChange();
            repaint();
        });
    }
    private void createButtonPanel(){
        add(buttonPanel, BorderLayout.SOUTH);

        createAddButton();
        createDeleteButton();
    }

    private void createScrollPanel(){
        scrollPanel = new JScrollPane(table);
        scrollPanel.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scrollPanel.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
        add(scrollPanel);
    }

    private void createTableBean(){
        setLayout(new BorderLayout());
    }

    public DataSheetTableModel getTableModel() {
        return tableModel;
    }
    public void setTableModel(DataSheetTableModel tableModel) {
        this.tableModel = tableModel;
        table.revalidate();
    }
    public void revalidate() {
        if (table != null) table.revalidate();
    }

}
