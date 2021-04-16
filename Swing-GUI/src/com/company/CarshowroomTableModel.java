package com.company;

import javax.swing.table.AbstractTableModel;

class CarshowroomTableModel extends AbstractTableModel {
    private final CarShowroomContainer container;
    private String[] namesTab;
    private final String[] columnNames = {"Salon name", "Max capacity"};

    public CarshowroomTableModel(CarShowroomContainer container) {
        this.container = container;
        this.namesTab = container.getSalony().keySet().toArray(new String[0]);

    }

    @Override
    public int getColumnCount() {
        return 2;
    }

    @Override
    public String getColumnName(int index) {
        return columnNames[index];
    }

    @Override
    public int getRowCount() {
        return container.getSalony().size();
    }

    @Override
    public Object getValueAt(int row, int col) {
        switch (col) {
            case 0:
                container.getSalony().keySet();
                return namesTab[row];

            case 1:
                return container.getSalony().get(namesTab[row]).getPojemnosc();
        }
        return "error";
    }

    public int removeRow(int row) {
        String rowToDelete = namesTab[row];
        container.removeCenter(rowToDelete);
        namesTab = container.getSalony().keySet().toArray(new String[0]);
        return 0;
    }

    public int addRow(String name, int vol) {
        container.addCenter(name, vol);
        namesTab = container.getSalony().keySet().toArray(new String[0]);
        return 0;
    }

    public void sortByVolume() {
        container.sortByAmount();
        this.namesTab = container.getSalony().keySet().toArray(new String[0]);
    }

    public String[] getNamesTab() {
        return namesTab;
    }

    public void setNamesTab(String[] namesTab) {
        this.namesTab = namesTab;
    }
}

