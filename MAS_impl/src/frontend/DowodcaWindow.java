package frontend;

import backend.pracownik.Dowodca;
import frontend.Views.MyTasksView;
import frontend.Views.WorkersView;
import util.Ext;

import javax.swing.*;
import java.awt.*;

public class DowodcaWindow extends JFrame {
    private Dowodca dowodca;
    private JPanel mainPanel;

    public DowodcaWindow(Dowodca dowodca) {
        super("Menu Dowodcy");
        this.dowodca = dowodca;

        setSize(800, 600);
        setLocationRelativeTo(null);
        addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosing(java.awt.event.WindowEvent e) {
                //Ext.save();
                dispose();
                System.exit(0);
            }
        });

        mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());
        mainPanel.setBackground(Color.LIGHT_GRAY);

        JToolBar toolbar = new JToolBar();
        toolbar.setFloatable(false);

        JButton zadaniaButton = new JButton("Zadania");
        JButton pracownicyButton = new JButton("Pracownicy");
        JButton mojeZadaniaButton = new JButton("Moje Zadania");
        JButton wylogujButton = new JButton("Wyloguj");

        zadaniaButton.addActionListener(e -> showTasks());
        pracownicyButton.addActionListener(e -> showWorkers());
        mojeZadaniaButton.addActionListener(e -> showMyTasks());
        wylogujButton.addActionListener(e -> {
            SwingUtilities.invokeLater(LoginWindow::new);
            dispose();
        });

        toolbar.add(zadaniaButton);
        toolbar.add(pracownicyButton);
        toolbar.add(mojeZadaniaButton);
        toolbar.add(wylogujButton);

        setLayout(new BorderLayout());
        add(toolbar, BorderLayout.NORTH);
        add(mainPanel, BorderLayout.CENTER);

        setVisible(true);
    }

    private void showMyTasks() {
        mainPanel.removeAll();
        mainPanel.add(new MyTasksView(dowodca) , BorderLayout.CENTER);
        mainPanel.revalidate();
        mainPanel.repaint();
    }

    private void showWorkers() {
        mainPanel.removeAll();
        mainPanel.add(new WorkersView(dowodca), BorderLayout.CENTER);
        mainPanel.revalidate();
        mainPanel.repaint();
    }

    private void showTasks() {
        mainPanel.removeAll();
        mainPanel.add(new TasksWindow(dowodca), BorderLayout.CENTER);
        mainPanel.revalidate();
        mainPanel.repaint();
    }
}
