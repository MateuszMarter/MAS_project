package frontend.PracownicyTab;

import backend.pracownik.Pracownik;

import javax.swing.table.AbstractTableModel;
import java.util.List;

public class WorkerTableModel extends AbstractTableModel {
    private List<Pracownik> pracownicy;
    private final String[] columnNames = {"ID","Imie", "Nazwisko", "Liczba zadan"};

    public WorkerTableModel(List<Pracownik> pracownicy) {
        this.pracownicy = pracownicy;
    }


    @Override
    public int getRowCount() {
        return this.pracownicy.size();
    }

    @Override
    public int getColumnCount() {
        return this.columnNames.length;
    }

    @Override
    public String getColumnName(int column) {
        return columnNames[column];
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Pracownik pracownik = pracownicy.get(rowIndex);
        return switch (columnIndex) {
            case 0 -> pracownik.getId();
            case 1 -> pracownik.getImie();
            case 2 -> pracownik.getNazwisko();
            case 3 -> pracownik.getRaporty().size();
            default -> null;
        };
    }

    public Pracownik getPracownikAt(int selectedRow) {
        return pracownicy.get(selectedRow);
    }
}
