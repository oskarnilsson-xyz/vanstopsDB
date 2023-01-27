package xyz.oskarnilsson;

import javax.swing.*;
import javax.swing.table.TableModel;
import java.awt.event.*;

public class VanStopsDB extends JFrame {
    public DBConnect dbconnect = new DBConnect();
    public DataValidate validate = new DataValidate();
    private JTable table;
    private JPanel VanStopsDB;
    private JButton btnFetchData;
    private JPanel fetchData;
    private JButton updateStopButton;
    private JButton deleteStopButton;
    private JTextField nameField;
    private JTextField tagsField;
    private JTextField latField;
    private JTextField overnightField;
    private JTextField longField;
    private JTextField nationField;
    private JTextField euField;
    private JButton addStopButton;
    private JButton searchNameButton;
    private JButton searchNationButton;
    private JButton manageTagsButton;
    private int selectedRow;

    public VanStopsDB() {
        setContentPane(VanStopsDB);
        setTitle("VanStops");
        setSize(1260, 600);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setVisible(true);
        setLocationRelativeTo(null);
        setResizable(true);
        euField.setEditable(false);
        overnightField.setEditable(false);
        tagsField.setEditable(false);
        table.setDefaultEditor(Object.class, null); //locks the table from user input


        btnFetchData.addActionListener(new ActionListener() { //Fetches data
            @Override
            public void actionPerformed(ActionEvent e) {

                table.setModel(dbconnect.DBConnectAllview());


            }
        });


        table.addMouseListener(new MouseAdapter() { //Actionlistener that lets the program know what row was selected and the populates the text fields
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                selectedRow = table.getSelectedRow();
                TableModel model = table.getModel();
                nameField.setText(model.getValueAt(selectedRow, 0).toString());
                latField.setText(model.getValueAt(selectedRow, 1).toString());
                longField.setText(model.getValueAt(selectedRow, 2).toString());
                nationField.setText(model.getValueAt(selectedRow, 3).toString());
                euField.setText(model.getValueAt(selectedRow, 4).toString());
                overnightField.setText(model.getValueAt(selectedRow, 5).toString());
                tagsField.setText(model.getValueAt(selectedRow, 6).toString());
            }
        });
        addStopButton.addMouseListener(new MouseAdapter() {  //Validates data before adding to DB
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                if (validate.Validate(nameField.getText(), latField.getText(), longField.getText(), nationField.getText())) {
                    dbconnect.DBConnectNewStop(nameField.getText(), latField.getText(), longField.getText(), nationField.getText());
                }


            }
        });
        updateStopButton.addMouseListener(new MouseAdapter() { //Validates data before adding to DB
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                if (validate.Validate(nameField.getText(), latField.getText(), longField.getText(), nationField.getText())) {
                    nameField.getText();
                    TableModel model = table.getModel();
                    String rowToUpdate = model.getValueAt(selectedRow, 0).toString();
                    dbconnect.DBConnectUpdate(nameField.getText(), latField.getText(), longField.getText(), nationField.getText(), rowToUpdate);
                }


            }
        });
        deleteStopButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                dbconnect.DBConnectDelete(nameField.getText());
            }
        });
        searchNameButton.addMouseListener(new MouseAdapter() { //Populates table based on the name textfield contents
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                table.setModel(dbconnect.DBConnectAllviewNameSearch(nameField.getText()));
            }
        });
        searchNationButton.addMouseListener(new MouseAdapter() { //Populates table based on the nation textfield contents
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                table.setModel(dbconnect.DBConnectAllviewNationSearch(nationField.getText()));
            }
        });
        manageTagsButton.addMouseListener(new MouseAdapter() { //Opens a window to modify tags based on name textfield contents
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                AddTag tagwindow = new AddTag(nameField.getText());
            }
        });
    }


}
