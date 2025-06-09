package frontend;

import backend.pracownik.Dowodca;
import util.Ext;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class LoginWindow extends JFrame {
    public LoginWindow() {
        super("Login");

        addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosing(java.awt.event.WindowEvent e) {
                Ext.save();
                System.exit(0);
            }
        });

        setSize(500, 600);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBackground(Color.WHITE);

        JLabel label = new JLabel("Login");
        label.setAlignmentX(Component.CENTER_ALIGNMENT);

        JComboBox<String> comboBox = new JComboBox<>();
        comboBox.setMaximumSize(new Dimension(200, 20));
        comboBox.setAlignmentX(Component.CENTER_ALIGNMENT);

        List<Dowodca> dowodcy = Ext.getExt(Dowodca.class);
        for(Dowodca dowodca : dowodcy) {
            comboBox.addItem(dowodca.getImie() + ' ' + dowodca.getNazwisko() + " id:" + dowodca.getId());
        }

        JButton loginButton = new JButton("Zaloguj");
        loginButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        loginButton.setPreferredSize(new Dimension(200, 20));
        loginButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        loginButton.setBackground(Color.LIGHT_GRAY);

        loginButton.addActionListener(e -> {
            String selectedItem = (String) comboBox.getSelectedItem();
            Ext.getExt();
        });

        panel.add(label);
        panel.add(comboBox);
        panel.add(Box.createVerticalStrut(15));
        panel.add(loginButton);
        panel.add(Box.createVerticalStrut(10));

        add(panel);

        setContentPane(panel);
        setVisible(true);
    }
}
