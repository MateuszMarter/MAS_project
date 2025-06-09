package frontend.ZadaniaTab;

import backend.Raport;
import backend.pracownik.Dowodca;
import backend.zadanie.Zadanie;
import frontend.Refreshable;

import javax.swing.*;
import java.awt.*;
import java.util.List;

/**
 * The type Tasks view.
 */
public class TasksView extends JPanel implements Refreshable {
    private List<Zadanie> zadania;

    /**
     * Instantiates a new Tasks view.
     *
     * @param dowodca the dowodca
     */
    public TasksView(Dowodca dowodca) {
        setLayout(new BorderLayout());

        zadania = Raport.getZadania();

        TaskTableModel model = new TaskTableModel();
        JTable table = new JTable(model);

        TasksView thisView = this;

        table.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                if(evt.getClickCount() == 2) {
                    int selectedRow = table.getSelectedRow();
                    Zadanie zadanie = model.getZadanieAt(selectedRow);

                    new TaskWindow(dowodca, zadanie, thisView);
                }
            }
        });

        add(new JScrollPane(table));
        setVisible(true);
    }

    public void refresh() {

    }
}
