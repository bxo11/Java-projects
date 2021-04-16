package com.company;

import javax.swing.table.AbstractTableModel;
import java.util.List;

public class VehicleTableModel extends AbstractTableModel {
    CarShowroom salon;
    List<Vehicle> list;
    private final String[] columnNames = {"Brand", "Model", "Condition", "Year of production", "Price", "Mileage", "Engine capacity", "Amount"};

    public VehicleTableModel(CarShowroom showroom) {
        this.list = showroom.getLista_samochodow();
        this.salon = showroom;
    }

    @Override
    public int getRowCount() {
        return list.size();
    }

    @Override
    public int getColumnCount() {
        return 8;
    }

    @Override
    public String getColumnName(int index) {
        return columnNames[index];
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        switch (columnIndex) {
            case 0:
                return list.get(rowIndex).getMarka();
            case 1:
                return list.get(rowIndex).getModel();
            case 2:
                return list.get(rowIndex).getStan();
            case 3:
                return list.get(rowIndex).getCena();
            case 4:
                return list.get(rowIndex).getRok_produkcji();
            case 5:
                return list.get(rowIndex).getPrzebieg();
            case 6:
                return list.get(rowIndex).getPojemnosc_silnika();
            case 7:
                return list.get(rowIndex).getIlosc();
        }
        return "Error";
    }

    public int removeRow(int row) {
        salon.removeProduct(salon.getLista_samochodow().get(row));
        return 0;
    }

    public int addRow(String marka, String model, itemCondition stan, double cena, int rp, double p, double ps, int i) {
        salon.addProduct(new Vehicle(marka, model, stan, cena, rp, p, ps, i));
        return 0;
    }
}
