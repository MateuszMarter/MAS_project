package frontend.ZadaniaTab;

import backend.zadanie.Zadanie;
import backend.zadanie.TypZadania;
import backend.pracownik.Dowodca;
import frontend.Refreshable;

import javax.swing.*;
import java.awt.*;

/**
 * The type Task window.
 */
public class TaskWindow extends JFrame {
    /**
     * Instantiates a new Task window.
     *
     * @param dowodca the dowodca
     * @param zadanie the zadanie
     * @param view    the view
     */
    public TaskWindow(Dowodca dowodca, Zadanie zadanie, Refreshable view) {
        super("Ustawienia zadania: " + zadanie.getId());

        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setSize(500, 250);
        setLocationRelativeTo(null);

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));

        JLabel typLabel = new JLabel("Typ zadania:");
        JComboBox<TypZadania> typComboBox = new JComboBox<>(TypZadania.values());
        typComboBox.setSelectedItem(zadanie.getTypZadania());

        JButton saveTaskButton = new JButton("Zapisz zadanie");
        saveTaskButton.addActionListener(e -> {
            zadanie.setTypZadania((TypZadania) typComboBox.getSelectedItem());
            view.refresh();
            dispose();
        });

        JButton newRaportButton = new JButton("Dodaj raport");
        newRaportButton.addActionListener(e -> new TaskNewRaportWindow(dowodca, zadanie, view));

        JPanel actionPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        actionPanel.add(saveTaskButton);
        actionPanel.add(newRaportButton);

        for (JComponent[] row : new JComponent[][]{
                {typLabel, typComboBox},
        }) {
            for (JComponent c : row) {
                c.setAlignmentX(Component.LEFT_ALIGNMENT);
                mainPanel.add(c);
            }
            mainPanel.add(Box.createVerticalStrut(10));
        }
        mainPanel.add(actionPanel);

        add(mainPanel);
        setVisible(true);
    }
}
