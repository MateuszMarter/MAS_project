package frontend.PracownicyTab;

import backend.Raport;
import backend.pracownik.Dowodca;
import backend.pracownik.Pracownik;
import backend.zadanie.Zadanie;
import frontend.Moje_ZadaniaTab.MyTaskTableModel;
import frontend.Moje_ZadaniaTab.MyTaskWindow;
import frontend.Refreshable;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class WorkerView extends JFrame implements Refreshable {
    private final MyTaskTableModel model;
    private final Pracownik pracownik;
    private final WorkersView workersView;

    public WorkerView(Dowodca dowodca, Pracownik pracownik, WorkersView workersView) {
        super(pracownik.getImie() + ' ' + pracownik.getNazwisko());

        this.pracownik = pracownik;
        this.workersView = workersView;

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

        /*TaskTableModel model = new TaskTableModel();
        JTable table = new JTable(model);

        WorkerView thisView = this;

        table.addMouseListener(new MouseAdapter() {
           @Override
           public void mouseClicked(MouseEvent e) {
               if (e.getClickCount() == 2) {
                   int selectedRow = table.getSelectedRow();

                   if (selectedRow >= 0) {
                       Zadanie zadanie = model.getZadanieAt(selectedRow);

                       new WorkerTaskWindow(zadanie)
                   }
               }
           }
        });*/

        model = new MyTaskTableModel(pracownik.getRaporty());
        JTable table = new JTable(model);

        WorkerView thisView = this;

        table.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent evt) {
                if (evt.getClickCount() == 2) {
                    int selectedRow = table.getSelectedRow();

                    if(selectedRow >= 0) {
                        Raport raport = model.getRaportAt(selectedRow);
                        Zadanie zadanie = raport.getZadanie();

                        new MyTaskWindow(dowodca, raport, zadanie, thisView);
                    }
                }
            }
        });

        add(new JScrollPane(table));

        setVisible(true);
    }

    public void refresh() {
        revalidate();
        repaint();
        workersView.refresh();
    }
}
