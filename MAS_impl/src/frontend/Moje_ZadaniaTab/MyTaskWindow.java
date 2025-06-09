package frontend.Moje_ZadaniaTab;

import backend.Raport;
import backend.pracownik.Dowodca;
import backend.zadanie.TypZadania;
import backend.zadanie.Zadanie;
import frontend.Refreshable;

import javax.swing.*;
import java.awt.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

/**
 * The type My task window.
 */
public class MyTaskWindow extends JFrame {
    /**
     * Instantiates a new My task window.
     *
     * @param dowodca the dowodca
     * @param raport  the raport
     * @param zadanie the zadanie
     * @param view    the view
     */
    public MyTaskWindow(Dowodca dowodca, Raport raport, Zadanie zadanie, Refreshable view) {
        super(raport.getNazwaZadania());

        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setSize(500, 300);
        setLocationRelativeTo(null);

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));

        JLabel nazwaLabel = new JLabel("Nazwa zadania:");
        JTextField nazwaTextField = new JTextField(raport.getNazwaZadania() != null ? raport.getNazwaZadania() : " ");
        System.out.println("Nazwa zadania: " + raport.getNazwaZadania());

        JLabel opisLabel = new JLabel("Opis:");
        JTextField opisTextField = new JTextField(raport.getOpis() != null ? raport.getOpis() : "");
        System.out.println("Opis: " + raport.getOpis());

        JLabel deadlineLabel = new JLabel("Deadline (kliknij, aby wybrać):");
        JButton deadlineButton = new JButton("Wybierz datę i godzinę");
        final LocalDateTime[] selectedDeadline = {raport.getDeadline() != null ? raport.getDeadline() : LocalDateTime.now()};
        JLabel currentDeadlineLabel = new JLabel(formatDeadline(selectedDeadline[0]));

        deadlineButton.addActionListener(e -> {
            JComboBox<Integer> yearBox = new JComboBox<>();
            JComboBox<Integer> monthBox = new JComboBox<>();
            JComboBox<Integer> dayBox = new JComboBox<>();
            JComboBox<Integer> hourBox = new JComboBox<>();
            JComboBox<Integer> minuteBox = new JComboBox<>();

            LocalDateTime current = selectedDeadline[0];

            for(int y = 2025; y <= 2035; y++) {
                yearBox.addItem(y);
            }

            for(int m = 1; m <= 12; m++) {
                monthBox.addItem(m);
            }

            for(int d = 1; d <= 31; d++) {
                dayBox.addItem(d);
            }

            for(int h = 0; h < 24; h++) {
                hourBox.addItem(h);
            }

            for(int m = 0; m < 60; m += 5) {
                minuteBox.addItem(m);
            }

            yearBox.setSelectedItem(current.getYear());
            monthBox.setSelectedItem(current.getMonthValue());
            dayBox.setSelectedItem(current.getDayOfMonth());
            hourBox.setSelectedItem(current.getHour());
            minuteBox.setSelectedItem(current.getMinute());

            JPanel panel = new JPanel();
            panel.add(new JLabel("Rok:"));
            panel.add(yearBox);

            panel.add(new JLabel("Miesiąc:"));
            panel.add(monthBox);

            panel.add(new JLabel("Dzień:"));
            panel.add(dayBox);

            panel.add(new JLabel("Godzina:"));
            panel.add(hourBox);

            panel.add(new JLabel("Minuta:"));
            panel.add(minuteBox);

            int result = JOptionPane.showConfirmDialog(this, panel, "Wybierz datę i godzinę", JOptionPane.OK_CANCEL_OPTION);

            if (result == JOptionPane.OK_OPTION) {
                int year = (Integer) yearBox.getSelectedItem();
                int month = (Integer) monthBox.getSelectedItem();
                int day = (Integer) dayBox.getSelectedItem();
                int hour = (Integer) hourBox.getSelectedItem();
                int minute = (Integer) minuteBox.getSelectedItem();
                selectedDeadline[0] = LocalDateTime.of(LocalDate.of(year, month, day), LocalTime.of(hour, minute));
                currentDeadlineLabel.setText(formatDeadline(selectedDeadline[0]));
            }
        });

        JPanel deadlinePanel = new JPanel();
        deadlinePanel.setLayout(new FlowLayout(FlowLayout.LEFT));
        deadlinePanel.add(deadlineButton);
        deadlinePanel.add(currentDeadlineLabel);

        JLabel typLabel = new JLabel("Typ zadania:");
        JComboBox<TypZadania> typComboBox = new JComboBox<>(TypZadania.values());
        typComboBox.setSelectedItem(zadanie.getTypZadania());

        JButton saveButton = new JButton("Zapisz");
        saveButton.addActionListener(e -> {
            if (nazwaTextField.getText() == null || nazwaTextField.getText().trim().isEmpty()) {
                JOptionPane.showMessageDialog(this, "Nazwa jest wymagana", "Brak informacji", JOptionPane.ERROR_MESSAGE);
                return;
            }

            if (selectedDeadline[0].isBefore(LocalDateTime.now())) {
                JOptionPane.showMessageDialog(this, "Deadline musi być w przyszłości", "Nieprawidłowa data", JOptionPane.ERROR_MESSAGE);
                return;
            }

            raport.setNazwaZadania(nazwaTextField.getText());
            raport.setOpis(opisTextField.getText());
            raport.setDeadline(selectedDeadline[0]);
            zadanie.setTypZadania((TypZadania) typComboBox.getSelectedItem());
            view.refresh();
            dispose();
        });

        JButton deleteButton = new JButton("Usuń");
        deleteButton.addActionListener(e -> {
            raport.getPracownik().porzucZadanie(zadanie);
            view.refresh();
            dispose();
        });

        JButton completeButton = new JButton("Zrealizuj");
        completeButton.addActionListener(e -> {
            dowodca.wykonajZadanie(zadanie);
            view.refresh();
            dispose();
        });

        JPanel actionPanel = new JPanel();
        actionPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));
        actionPanel.add(saveButton);
        actionPanel.add(deleteButton);
        actionPanel.add(completeButton);

        for (JComponent[] pair : new JComponent[][]{
                {nazwaLabel, nazwaTextField},
                {opisLabel, opisTextField},
                {deadlineLabel},
                {deadlinePanel},
                {typLabel, typComboBox}}) {

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
