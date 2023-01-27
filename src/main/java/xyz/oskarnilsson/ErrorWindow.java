package xyz.oskarnilsson;

import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class ErrorWindow extends JFrame {
    private JButton close;
    private JTextArea errorText;
    private JPanel ErrorWindow;

    public ErrorWindow(String text) {
        setContentPane(ErrorWindow);
        setTitle("Error");
        setSize(250, 250);
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setVisible(true);
        setLocationRelativeTo(null);
        setResizable(false);
        errorText.setText(text);
        close.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                dispose();
            }
        });
    }
}
