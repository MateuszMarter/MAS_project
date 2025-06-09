package frontend;

import backend.Raport;
import backend.pracownik.Dowodca;
import backend.pracownik.Pracownik;
import backend.zadanie.Zadanie;
import frontend.PracownicyTab.WorkerView;

import javax.swing.*;
import java.awt.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class NewRaportWindow extends JFrame {
    public NewRaportWindow(WorkerView view, Dowodca dowodca, Zadanie zadanie) {
        super("Nowy raport");

        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setSize(500, 400);
        setLocationRelativeTo(null);

        List<Pracownik> pracownicy = dowodca.wyswietlListePracownikow();

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));

        JLabel pracownikLabel = new JLabel("Wybierz pracownika:");
        JComboBox<Pracownik> pracownikComboBox = new JComboBox<>(pracownicy.toArray(new Pracownik[0]));

        JLabel nazwaLabel = new JLabel("Nazwa zadania:");
        JTextField nazwaTextField = new JTextField();

        JLabel opisLabel = new JLabel("Opis:");
        JTextField opisTextField = new JTextField();

        JLabel deadlineLabel = new JLabel("Deadline (kliknij, aby wybrać):");
        JButton deadlineButton = new JButton("Wybierz datę i godzinę");
        final LocalDateTime[] selectedDeadline = {LocalDateTime.now()};
        JLabel currentDeadlineLabel = new JLabel(formatDeadline(selectedDeadline[0]));

        deadlineButton.addActionListener(e -> {
            JComboBox<Integer> yearBox = new JComboBox<>();
            JComboBox<Integer> monthBox = new JComboBox<>();
            JComboBox<Integer> dayBox = new JComboBox<>();
            JComboBox<Integer> hourBox = new JComboBox<>();
            JComboBox<Integer> minuteBox = new JComboBox<>();

            for(int y = 2023; y <= 2035; y++) yearBox.addItem(y);
            for(int m = 1; m <= 12; m++) monthBox.addItem(m);
            for(int d = 1; d <= 31; d++) dayBox.addItem(d);
            for(int h = 0; h < 24; h++) hourBox.addItem(h);
            for(int m = 0; m < 60; m += 5) minuteBox.addItem(m);

            JPanel panel = new JPanel();
            panel.add(new JLabel("Rok:")); panel.add(yearBox);
            panel.add(new JLabel("Miesiąc:")); panel.add(monthBox);
            panel.add(new JLabel("Dzień:")); panel.add(dayBox);
            panel.add(new JLabel("Godzina:")); panel.add(hourBox);
            panel.add(new JLabel("Minuta:")); panel.add(minuteBox);

            int result = JOptionPane.showConfirmDialog(this, panel, "Wybierz datę i godzinę", JOptionPane.OK_CANCEL_OPTION);
            if (result == JOptionPane.OK_OPTION) {
                int year = (int) yearBox.getSelectedItem();
                int month = (int) monthBox.getSelectedItem();
                int day = (int) dayBox.getSelectedItem();
                int hour = (int) hourBox.getSelectedItem();
                int minute = (int) minuteBox.getSelectedItem();
                selectedDeadline[0] = LocalDateTime.of(LocalDate.of(year, month, day), LocalTime.of(hour, minute));
                currentDeadlineLabel.setText(formatDeadline(selectedDeadline[0]));
            }
        });

        JPanel deadlinePanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        deadlinePanel.add(deadlineButton);
        deadlinePanel.add(currentDeadlineLabel);

        JButton saveButton = new JButton("Zapisz");
        saveButton.addActionListener(e -> {
            String nazwa = nazwaTextField.getText();
            String opis = opisTextField.getText();
            Pracownik pracownik = (Pracownik) pracownikComboBox.getSelectedItem();

            if (nazwa == null || nazwa.trim().isEmpty()) {
                JOptionPane.showMessageDialog(this, "Nazwa zadania jest wymagana", "Błąd", JOptionPane.ERROR_MESSAGE);
                return;
            }

            if (pracownik == null) {
                JOptionPane.showMessageDialog(this, "Wybierz pracownika", "Błąd", JOptionPane.ERROR_MESSAGE);
                return;
            }

            Raport raport = new Raport(nazwa, pracownik, zadanie, selectedDeadline[0]);
            raport.setOpis(opis);
            Raport.dodajZadanie(zadanie);

            if (view != null) view.refresh();

            dispose();
        });

        JButton cancelButton = new JButton("Anuluj");
        cancelButton.addActionListener(e -> dispose());

        JPanel actionPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        actionPanel.add(saveButton);
        actionPanel.add(cancelButton);

        for (JComponent[] pair : new JComponent[][]{
                {pracownikLabel, pracownikComboBox},
                {nazwaLabel, nazwaTextField},
                {opisLabel, opisTextField},
                {deadlineLabel},
                {deadlinePanel}
        }) {
            for (JComponent comp : pair) {
                comp.setAlignmentX(Component.LEFT_ALIGNMENT);
                mainPanel.add(comp);
            }
            mainPanel.add(Box.createVerticalStrut(8));
        }

        mainPanel.add(actionPanel);
        add(mainPanel);
        setVisible(true);
    }

    private String formatDeadline(LocalDateTime deadline) {
        return deadline.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
    }
}
