package frontend.Moje_ZadaniaTab;

import backend.Raport;
import backend.pracownik.Dowodca;
import backend.zadanie.Zadanie;
import frontend.Refreshable;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

/**
 * The type My tasks view.
 */
public class MyTasksView extends JPanel implements Refreshable {
    private final MyTaskTableModel model;
    private final Dowodca dowodca;

    /**
     * Instantiates a new My tasks view.
     *
     * @param dowodca the dowodca
     */
    public MyTasksView(Dowodca dowodca) {
        setLayout(new BorderLayout());

        this.dowodca = dowodca;
        List<Raport> raportList = this.dowodca.getRaporty();

        model = new MyTaskTableModel(raportList);
        JTable table = new JTable(model);

        MyTasksView thisView = this;

        table.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent evt) {

                if (evt.getClickCount() == 2) {
                    int selectedRow = table.getSelectedRow();
                    if (selectedRow >= 0) {
                        Raport wybranyRaport = model.getRaportAt(selectedRow);
                        Zadanie wybraneZadanie = wybranyRaport.getZadanie();

                        new MyTaskWindow(dowodca, wybranyRaport, wybraneZadanie, thisView);
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
