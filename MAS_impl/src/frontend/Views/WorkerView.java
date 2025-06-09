package frontend.Views;

import backend.pracownik.Pracownik;

import javax.swing.*;
import java.awt.*;

public class WorkerView extends JFrame {
    public WorkerView(Pracownik pracownik) {
        super(pracownik.getImie() + ' ' + pracownik.getNazwisko());

        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(600, 400);
        setLocationRelativeTo(null);

        JPanel infoPanel = new JPanel(new GridLayout(0, 1));
        infoPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        infoPanel.add(new JLabel("ID: " + pracownik.getId()));
        infoPanel.add(new JLabel("Imię: " + pracownik.getImie()));
        infoPanel.add(new JLabel("Nazwisko: " + pracownik.getNazwisko()));
        infoPanel.add(new JLabel("Zarobki: " + pracownik.obliczPensje() + " zł"));

        add(infoPanel, BorderLayout.NORTH);



        setVisible(true);
    }
}
