package frontend.ZadaniaTab;

import backend.Raport;
import backend.pracownik.Pracownik;
import backend.pracownik.Dowodca;
import backend.zadanie.Zadanie;
import frontend.Refreshable;

import javax.swing.*;
import java.awt.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

/**
 * The type Task new raport window.
 */
public class TaskNewRaportWindow extends JFrame {
    /**
     * Instantiates a new Task new raport window.
     *
     * @param dowodca the dowodca
     * @param zadanie the zadanie
     * @param view    the view
     */
    public TaskNewRaportWindow(Dowodca dowodca, Zadanie zadanie, Refreshable view) {
        super("Nowy raport dla zadania: " + zadanie.getId());

        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setSize(500, 300);
        setLocationRelativeTo(null);

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));

        // Wybór pracownika
        JLabel pracownikLabel = new JLabel("Pracownik:");
        JComboBox<Pracownik> pracownikComboBox = new JComboBox<>(
                dowodca.wyswietlListePracownikow().toArray(new Pracownik[0])
        );

        // Nazwa raportu
        JLabel nazwaLabel = new JLabel("Nazwa raportu:");
        JTextField nazwaTextField = new JTextField();

        // Opis
        JLabel opisLabel = new JLabel("Opis:");
        JTextField opisTextField = new JTextField();

        // Deadline
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

            for (int y = 2023; y <= 2035; y++) yearBox.addItem(y);
            for (int m = 1; m <= 12; m++) monthBox.addItem(m);
            for (int d = 1; d <= 31; d++) dayBox.addItem(d);
            for (int h = 0; h < 24; h++) hourBox.addItem(h);
            for (int m = 0; m < 60; m += 5) minuteBox.addItem(m);

            LocalDateTime now = selectedDeadline[0];
            yearBox.setSelectedItem(now.getYear());
            monthBox.setSelectedItem(now.getMonthValue());
            dayBox.setSelectedItem(now.getDayOfMonth());
            hourBox.setSelectedItem(now.getHour());
            minuteBox.setSelectedItem((now.getMinute() / 5) * 5);

            JPanel picker = new JPanel();
            picker.add(new JLabel("Rok:")); picker.add(yearBox);
            picker.add(new JLabel("Miesiąc:")); picker.add(monthBox);
            picker.add(new JLabel("Dzień:")); picker.add(dayBox);
            picker.add(new JLabel("Godzina:")); picker.add(hourBox);
            picker.add(new JLabel("Minuta:")); picker.add(minuteBox);

            int result = JOptionPane.showConfirmDialog(
                    this, picker, "Wybierz datę i godzinę", JOptionPane.OK_CANCEL_OPTION
            );

            if (result == JOptionPane.OK_OPTION) {
                int y = (Integer) yearBox.getSelectedItem();
                int mo = (Integer) monthBox.getSelectedItem();
                int d = (Integer) dayBox.getSelectedItem();
                int h = (Integer) hourBox.getSelectedItem();
                int mi = (Integer) minuteBox.getSelectedItem();
                selectedDeadline[0] = LocalDateTime.of(
                        LocalDate.of(y, mo, d), LocalTime.of(h, mi)
                );
                currentDeadlineLabel.setText(formatDeadline(selectedDeadline[0]));
            }
        });

        JPanel deadlinePanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        deadlinePanel.add(deadlineButton);
        deadlinePanel.add(currentDeadlineLabel);

        // Przyciski akcji
        JButton saveButton = new JButton("Zapisz");
        saveButton.addActionListener(e -> {
            Pracownik pracownik = (Pracownik) pracownikComboBox.getSelectedItem();

            if(pracownik == null) {
                JOptionPane.showMessageDialog(this, "Wybor pracownika jest wymagany", "Brak informacji", JOptionPane.ERROR_MESSAGE);
                return;
            }

            if(nazwaTextField.getText() == null || nazwaTextField.getText().trim().isEmpty()) {
                JOptionPane.showMessageDialog(this, "Nazwa jest wymagana", "Brak informacji", JOptionPane.ERROR_MESSAGE);
                return;
            }
            Raport raport = new Raport(
                    nazwaTextField.getText(),
                    pracownik,
                    zadanie,
                    selectedDeadline[0]
            );

            if(!opisTextField.getText().trim().isEmpty()) {
                raport.setOpis(opisTextField.getText());
            }

            view.refresh();
            dispose();
        });

        JButton cancelButton = new JButton("Anuluj");
        cancelButton.addActionListener(e -> dispose());

        JPanel actionPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        actionPanel.add(saveButton);
        actionPanel.add(cancelButton);

        // Dodawanie komponentów do panelu głównego
        for (JComponent[] row : new JComponent[][]{
                {pracownikLabel, pracownikComboBox},
                {nazwaLabel, nazwaTextField},
                {opisLabel, opisTextField},
                {deadlineLabel},
                {deadlinePanel}
        }) {
            for (JComponent c : row) {
                c.setAlignmentX(Component.LEFT_ALIGNMENT);
                mainPanel.add(c);
            }
            mainPanel.add(Box.createVerticalStrut(8));
        }
        mainPanel.add(actionPanel);

        add(mainPanel);
        setVisible(true);
    }

    private String formatDeadline(LocalDateTime dt) {
        return dt.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
    }
}
