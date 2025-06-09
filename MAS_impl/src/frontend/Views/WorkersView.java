package frontend.Views;

import backend.pracownik.Dowodca;
import backend.pracownik.Pracownik;
import frontend.Models.WorkerTableModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

public class WorkersView extends JPanel {
    public WorkersView(Dowodca dowodca) {
        setLayout(new BorderLayout());

        List<Pracownik> pracownicy = dowodca.getZaloga().getPracownicy();
        System.out.println(pracownicy);

        WorkerTableModel model = new WorkerTableModel(pracownicy);
        JTable table = new JTable(model);

        table.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent evt) {
                if(evt.getClickCount() == 2) {
                    int selectedRow = table.getSelectedRow();
                    if(selectedRow >= 0) {
                        Pracownik pracownik = model.getPracownikAt(selectedRow);
                        System.out.println("Wybrano pracownika: " + pracownik.getClass());

                        new WorkerView(pracownik);
                    }
                }
            }
        });

        JScrollPane scrollPane = new JScrollPane(table);
        add(scrollPane, BorderLayout.CENTER);
        setVisible(true);
    }
}
