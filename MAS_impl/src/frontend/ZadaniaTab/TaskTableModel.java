package frontend.ZadaniaTab;

import backend.Raport;
import backend.zadanie.Zadanie;

import javax.swing.table.AbstractTableModel;
import java.util.List;

/**
 * The type Task table model.
 */
public class TaskTableModel extends AbstractTableModel {
    private List<Zadanie> zadania;
    private final String[] columnNames = {"Status", "Typ", "Pracownik"};

    /**
     * Instantiates a new Task table model.
     */
    public TaskTableModel() {
        zadania = Raport.getZadania();
        System.out.println(Raport.getZadania());
    }

    @Override
    public int getRowCount() {
        return zadania.size();
    }

    @Override
    public int getColumnCount() {
        return columnNames.length;
    }

    @Override
    public String getColumnName(int column) {
        return columnNames[column];
    }

    @Override
    public Class<?> getColumnClass(int columnIndex) {
        return switch (columnIndex) {
            case 2 -> List.class;
            default -> String.class;
        };
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Zadanie zadanie = zadania.get(rowIndex);
         switch (columnIndex) {
            case 0 -> {
                return zadanie.getStatus();
            }
            case 1 -> {
                return zadanie.getTypZadania();
            }
            case 2 -> {
                return zadanie.getAllPracownicy();
            }
            default -> {
                 return null;
             }
        }
    }

    /**
     * Gets zadanie at.
     *
     * @param selectedRow the selected row
     * @return the zadanie at
     */
    public Zadanie getZadanieAt(int selectedRow) {
        return zadania.get(selectedRow);
    }
}
