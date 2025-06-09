package frontend.Views;

import backend.Raport;
import backend.pracownik.Dowodca;
import backend.zadanie.Zadanie;
import frontend.Models.TaskTableModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

public class MyTasksView extends JPanel {
    public MyTasksView(Dowodca dowodca) {
        setLayout(new BorderLayout());

        List<Raport> raportList = dowodca.getRaporty();
        /*List<Zadanie> zadania = new ArrayList<>();
        for (Raport raport : raportList) {
            zadania.add(raport.getZadanie());
        }*/

        TaskTableModel model = new TaskTableModel(raportList);
        JTable table = new JTable(model);

        table.getSelectionModel().addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
                int selectedRow = table.getSelectedRow();

                if (selectedRow >= 0) {
                    Raport wybranyRaport = model.getRaportAt(selectedRow);
                    Zadanie wybraneZadanie = wybranyRaport.getZadanie();

                    System.out.println("Wybrano zadanie: " + wybraneZadanie);
                }
            }
        });

        MyTasksView thisView = this;

        table.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent evt) {
                if (evt.getClickCount() == 2) {
                    int selectedRow = table.getSelectedRow();
                    if (selectedRow >= 0) {
                        Raport wybranyRaport = model.getRaportAt(selectedRow);
                        Zadanie wybraneZadanie = wybranyRaport.getZadanie();

                        new TaskView(dowodca, wybranyRaport, wybraneZadanie, thisView);
                    }
                }
            }
        });

        JScrollPane scrollPane = new JScrollPane(table);
        add(scrollPane, BorderLayout.CENTER);
        setVisible(true);
    }

    public void refresh() {
        revalidate();
        repaint();
    }
}
