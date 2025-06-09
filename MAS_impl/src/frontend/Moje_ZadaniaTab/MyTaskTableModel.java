package frontend.Moje_ZadaniaTab;

import backend.Raport;

import javax.swing.table.AbstractTableModel;
import java.util.List;

public class MyTaskTableModel extends AbstractTableModel {

    private List<Raport> raporty;
    private final String[] columnNames = {"Status", "Typ", "Pracownik"};

    public MyTaskTableModel(List<Raport> raporty) {
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
    public Class<?> getColumnClass(int columnIndex) {
        return switch (columnIndex) {
            case 2 -> List.class;
            default -> String.class;
        };
    }


    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Raport raport = raporty.get(rowIndex);
        return switch (columnIndex) {
            case 0 -> raport.getZadanie().getStatus();
            case 1 -> raport.getZadanie().getTypZadania();
            case 2 -> raport.getAllPracownicy();
            default -> null;
        };
    }

    public Raport getRaportAt(int selectedRow) {
        return raporty.get(selectedRow);
    }

    public void setRaporty(List<Raport> raporty) {
        this.raporty = raporty;
    }
}
