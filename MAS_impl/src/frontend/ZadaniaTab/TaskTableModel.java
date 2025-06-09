package frontend.ZadaniaTab;

import backend.Raport;
import backend.zadanie.Zadanie;

import javax.swing.table.AbstractTableModel;
import java.util.List;

public class TaskTableModel extends AbstractTableModel {
    private List<Zadanie> zadania;
    private final String[] columnNames = {"Status", "Typ", "Pracownik"};

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

    public Zadanie getZadanieAt(int selectedRow) {
        return zadania.get(selectedRow);
    }
}
