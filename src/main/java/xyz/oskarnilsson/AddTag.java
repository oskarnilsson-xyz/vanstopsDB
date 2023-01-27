package xyz.oskarnilsson;

import javax.swing.*;
import javax.swing.table.TableModel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class AddTag extends JFrame {
    DBConnect dbConnect = new DBConnect();
    private JPanel addTag;
    private JButton addTagButton;
    private JButton closeButton;
    private JTable table;
    private JButton removeTagButton;
    private JTextField selectedTag;
    private int selectedRow;

    public AddTag(String selectedStop) {
        setContentPane(addTag);
        setTitle("Add tag");
        setSize(500, 500);
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setVisible(true);
        setLocationRelativeTo(null);
        setResizable(true);
        table.setModel(dbConnect.DBConnectTagTable());
        table.setDefaultEditor(Object.class, null);
        selectedTag.setEditable(false);


        addTagButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                dbConnect.DBConnectAddTag(selectedStop, selectedTag.getText());
            }
        });
        closeButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                dispose();
            }
        });
        table.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                selectedRow = table.getSelectedRow();
                TableModel model = table.getModel();
                selectedTag.setText(model.getValueAt(selectedRow, 0).toString());
            }
        });
        removeTagButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                dbConnect.DBConnectRemoveTag(selectedStop, selectedTag.getText());
            }
        });
    }


}
