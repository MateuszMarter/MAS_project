package frontend.Models;

import backend.Raport;
import backend.zadanie.Zadanie;

import javax.swing.table.AbstractTableModel;
import java.util.List;

public class TaskTableModel extends AbstractTableModel {

    private final List<Raport> raporty;
    private final String[] columnNames = {"Status", "Typ", "Pracownik"};

    public TaskTableModel(List<Raport> raporty) {
        this.raporty = raporty;
    }

    @Override
    public int getRowCount() {
        return raporty.size();
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
    public Object getValueAt(int rowIndex, int columnIndex) {
        Raport raport = raporty.get(rowIndex);
        return switch (columnIndex) {
            case 0 -> raport.getZadanie().getStatus();
            case 1 -> raport.getZadanie().getTypZadania();
            case 2 -> raport.getPracownik().getImie() + " " + raport.getPracownik().getNazwisko();
            default -> null;
        };
    }

    public Raport getRaportAt(int selectedRow) {
        return raporty.get(selectedRow);
    }
}
