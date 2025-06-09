package frontend;

import backend.pracownik.Dowodca;
import util.Ext;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
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

        JComboBox<Dowodca> comboBox = new JComboBox<>();
        comboBox.setMaximumSize(new Dimension(200, 20));
        comboBox.setAlignmentX(Component.CENTER_ALIGNMENT);

        List<Dowodca> dowodcy = Ext.getExt(Dowodca.class);
        for(Dowodca dowodca : dowodcy) {
            comboBox.addItem(dowodca);
        }

        JButton loginButton = new JButton("Zaloguj");
        loginButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        loginButton.setPreferredSize(new Dimension(220, 50));
        loginButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        loginButton.setBackground(Color.LIGHT_GRAY);

        loginButton.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(Color.GRAY, 1),
                BorderFactory.createEmptyBorder(10, 20, 10, 20)
        ));
        loginButton.setMargin(new Insets(10, 10, 10, 10));

        loginButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                loginButton.setBackground(Color.GRAY);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                loginButton.setBackground(Color.LIGHT_GRAY);
            }
        });

        loginButton.addActionListener(_ -> {
            Dowodca dowodca = (Dowodca) comboBox.getSelectedItem();

            dispose();

            new DowodcaWindow(dowodca);
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
