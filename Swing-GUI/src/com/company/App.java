package com.company;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class App extends JFrame {
    VehicleTableModel vehicleTableModel;
    CarshowroomTableModel carshowroomTableModel;
    private JPanel panel_main;
    private JTable salonTable;
    private JTable vehicleTable;
    private JButton deleteCarShowroomButton;
    private JButton deleteVehicleButton;
    private JButton addSalonButton;
    private JButton addVehicleButton;
    private JButton sortByCapacityButton;

    public App(String title, CarShowroomContainer container) throws HeadlessException {
        super(title);

        carshowroomTableModel = new CarshowroomTableModel(container);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setPreferredSize(new Dimension(1200, 800));
        setContentPane(panel_main);
        pack();
        setVisible(true);
        salonTable.setModel(carshowroomTableModel);

        ListSelectionModel vehicleTableSelectionModel = vehicleTable.getSelectionModel();
        ListSelectionModel salonTableSelectionModel = salonTable.getSelectionModel();

        salonTableSelectionModel.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if (!salonTableSelectionModel.isSelectionEmpty()) {
                    int selectedRow = salonTableSelectionModel.getMinSelectionIndex();
                    String[] keys = carshowroomTableModel.getNamesTab();
                    vehicleTableModel = new VehicleTableModel(container.getSalony().get(keys[selectedRow]));
                    vehicleTable.setModel(vehicleTableModel);
                }
            }
        });

        deleteCarShowroomButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!salonTableSelectionModel.isSelectionEmpty()) {
                    int selectedRow = salonTableSelectionModel.getMinSelectionIndex();
                    carshowroomTableModel.removeRow(selectedRow);
                    carshowroomTableModel.fireTableDataChanged();
                } else {
                    JOptionPane.showMessageDialog(null, "Select row");
                }
            }
        });

        deleteVehicleButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!vehicleTableSelectionModel.isSelectionEmpty()) {
                    int selectedRow = vehicleTableSelectionModel.getMinSelectionIndex();
                    vehicleTableModel.removeRow(selectedRow);
                    vehicleTableModel.fireTableDataChanged();
                } else {
                    JOptionPane.showMessageDialog(null, "Select row");
                }
            }
        });

        addSalonButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                carshowroomTableModel.addRow(JOptionPane.showInputDialog("Name:"), Integer.parseInt(JOptionPane.showInputDialog("Max capacity:")));
                carshowroomTableModel.fireTableDataChanged();
            }
        });
        addVehicleButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!salonTableSelectionModel.isSelectionEmpty()) {
                    int selectedRow = salonTableSelectionModel.getMinSelectionIndex();
                    vehicleTableModel.addRow(JOptionPane.showInputDialog("Brand:"),
                            JOptionPane.showInputDialog("Model:"),
                            itemCondition.valueOf(JOptionPane.showInputDialog("Condition(NEW, USED, DAMAGED):")),
                            Double.parseDouble(JOptionPane.showInputDialog("Price:")),
                            Integer.parseInt(JOptionPane.showInputDialog("Year of production:")),
                            Double.parseDouble(JOptionPane.showInputDialog("Mileage:")),
                            Double.parseDouble(JOptionPane.showInputDialog("Engine capacity:")),
                            Integer.parseInt(JOptionPane.showInputDialog("Amount:")));
                    vehicleTableModel.fireTableDataChanged();
                } else {
                    JOptionPane.showMessageDialog(null, "Select row");
                }
            }
        });
        sortByCapacityButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                carshowroomTableModel.sortByVolume();
                carshowroomTableModel.fireTableDataChanged();
            }
        });
    }


}




