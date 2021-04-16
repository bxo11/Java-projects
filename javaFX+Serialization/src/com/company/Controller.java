package com.company;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.Clipboard;
import javafx.scene.input.ClipboardContent;

import java.io.*;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Controller {
    @FXML
    public TableColumn colBrand;
    @FXML
    public TableColumn colModel;
    @FXML
    public TableColumn colPrice;
    @FXML
    public TableColumn colYearofproduction;
    @FXML
    public TableColumn colSalonname;
    @FXML
    public TextField searchTextField;
    @FXML
    private ComboBox salonsComboBox;
    @FXML
    private TableView<Vehicle> vehicleTable;

    private CarShowroomContainer data = new CarShowroomContainer();
    private final ObservableList<Vehicle> list = FXCollections.observableArrayList();
    private ObservableList<Vehicle> cart_list = FXCollections.observableArrayList();

    public Controller() {
        CarShowroomContainer test = new DataGenerator().generate();
        test.serialization("carshowroomData.ser.clean");
        this.data = this.data.deserialization("carshowroomData.ser.clean");

        if (data == null) {
            new Alert(Alert.AlertType.ERROR, "Deserialisation Error").showAndWait();
        }
    }

    public void salonComboBox_select() {
        if (salonsComboBox.getValue() == null) {
            vehicleTable.setItems(null);
        } else if (data.getSalons().get(salonsComboBox.getValue()) == null) {
            list.clear();
            for (CarShowroom salon : data.getSalons().values()) {
                for (Vehicle v : salon.getLista_samochodow()) {
                    list.add(v);
                }
            }
            vehicleTable.setItems(list);
        } else {
            ObservableList<Vehicle> temp_list = FXCollections.observableArrayList();
            CarShowroom temp_salon = data.getSalons().get(salonsComboBox.getValue());
            for (Vehicle v : temp_salon.getLista_samochodow()) {
                temp_list.add(v);
            }
            vehicleTable.setItems(temp_list);
        }
    }

    public void searchButton() {

        String temp_string = searchTextField.getText();
        ObservableList<Vehicle> temp_list = FXCollections.observableArrayList(vehicleTable.getItems());
        ObservableList<Vehicle> list_toremove = FXCollections.observableArrayList();

        for (Vehicle s : temp_list) {
            String look = s.getBrand() + " " + s.getModel();
            if (!look.contains(temp_string)) {
                list_toremove.add(s);
            }
        }
        for (Vehicle d : list_toremove) {
            temp_list.remove(d);
        }
        vehicleTable.setItems(temp_list);
    }

    public void addToCartButton() {
        Vehicle v = vehicleTable.getSelectionModel().getSelectedItem();
        if (v != null) {
            if (!v.isReserved()) {
                v.setReserved(true);
                cart_list.add(v);
            } else {
                new Alert(Alert.AlertType.ERROR, "Vehicle already reserved").showAndWait();
            }
        } else {
            new Alert(Alert.AlertType.ERROR, "Select vehicle").showAndWait();
        }
    }

    public void setVehicleTableToolKit() {
        Vehicle v = vehicleTable.getSelectionModel().getSelectedItem();
        vehicleTable.setTooltip(new Tooltip(String.valueOf(v)));
    }

    public void showPhoneNumberButton() {
        Vehicle v = vehicleTable.getSelectionModel().getSelectedItem();

        final Clipboard clipboard = Clipboard.getSystemClipboard();
        final ClipboardContent content = new ClipboardContent();
        content.putString(String.valueOf(v.getPhoneNumber()));
        clipboard.setContent(content);

        String info = "Phone number: " + v.getPhoneNumber() + "\nPhone number coppied to clipboard";
        new Alert(Alert.AlertType.INFORMATION, info).showAndWait();
    }

    public void serializeData() {
        this.data.serialization("carshowroomData.ser");

        //cart serialization
        try {
            FileOutputStream file = new FileOutputStream("cart.ser");
            ObjectOutputStream out = new ObjectOutputStream(file);

            out.writeObject((new ArrayList<Vehicle>(cart_list)));
            out.close();
            file.close();

        } catch (IOException e) {
            System.out.println("cart serialization, IOException is caught");
            e.printStackTrace();
        }
    }

    public void deserializeData() {
        this.data = this.data.deserialization("carshowroomData.ser");

        list.clear();
        for (CarShowroom salon : data.getSalons().values()) {
            for (Vehicle v : salon.getLista_samochodow()) {
                list.add(v);
            }
        }
        vehicleTable.setItems(list);

        //cart deserialization
        try {
            FileInputStream file = new FileInputStream("cart.ser");
            ObjectInputStream in = new ObjectInputStream(file);

            List<Vehicle> list = (List<Vehicle>) in.readObject();
            ObservableList<Vehicle> object = FXCollections.observableArrayList(list);
            in.close();
            file.close();

            cart_list = object;
        } catch (IOException e) {
            System.out.println("cart deserialisation ,IOException is caught");
            e.printStackTrace();
        } catch (ClassNotFoundException ex) {
            System.out.println("cart ClassNotFoundException is caught");
            ex.printStackTrace();
        }
    }

    public void showCartButton() {
        salonsComboBox.getSelectionModel().clearSelection();
//        cart_list.clear();
//        for (CarShowroom salon : data.getSalons().values()) {
//            for (Vehicle v : salon.getLista_samochodow()) {
//                if (v.isReserved()) {
//                    cart_list.add(v);
//                }
//            }
//        }
        vehicleTable.setItems(cart_list);
    }

    public void clearCartButton() {
        for (Vehicle v : cart_list) {
            for (Vehicle v2 : list) {
                if (v.equals(v2)) {
                    v2.setReserved(false);
                }
            }
        }
        cart_list.clear();
    }

    private String convertToCSV(String[] data) {
        return Stream.of(data)
                .collect(Collectors.joining(","));
    }

    private List<String> getRecordFromLine(String line) {
        List<String> values = new ArrayList<String>();
        try (Scanner rowScanner = new Scanner(line)) {
            rowScanner.useDelimiter(",");
            while (rowScanner.hasNext()) {
                values.add(rowScanner.next());
            }
        }
        return values;
    }

    public void saveCartButton() {
        List<String[]> dataLines = new ArrayList<>();
        Field[] fields = Vehicle.class.getDeclaredFields();
        List<String> s = new ArrayList<String>();

        for (Vehicle v : cart_list) {
            s.clear();
            for (Field field : fields) {
                if (field.isAnnotationPresent(ToCSV.class)) {
                    try {
                        field.setAccessible(true);
                        s.add(String.valueOf(field.get(v)));
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    }
                }
            }

            String[] itemsArray = new String[s.size()];
            s.toArray(itemsArray);
            dataLines.add(itemsArray);
        }

        File csvOutputFile = new File("cart.csv");
        try (PrintWriter pw = new PrintWriter(csvOutputFile)) {
            dataLines.stream()
                    .map(this::convertToCSV)
                    .forEach(pw::println);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

    }

    public void loadCartButton() {
        List<List<String>> records = new ArrayList<>();
        try (Scanner scanner = new Scanner(new File("cart.csv"));) {
            while (scanner.hasNextLine()) {
                records.add(getRecordFromLine(scanner.nextLine()));
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        cart_list.clear();
        for (List<String> l : records) {
            Vehicle v = new Vehicle(
                    l.get(0),
                    l.get(1),
                    itemCondition.NEW,
                    Double.parseDouble(l.get(2)),
                    Integer.parseInt(l.get(3)),
                    Double.parseDouble(l.get(4)),
                    Double.parseDouble(l.get(5)),
                    Integer.parseInt(l.get(6)),
                    Integer.parseInt(l.get(7)),
                    l.get(8)
            );
            v.setSalonName(l.get(9));

            for (Vehicle v2 : list) {
                if (v.equals(v2)) {
                    cart_list.add(v2);
                    v2.setReserved(true);
                }
            }
        }
    }

    public void exportSalonToCSVButton() {
        CarShowroom salon = data.getSalons().get(salonsComboBox.getValue());
        List<String[]> dataLines = new ArrayList<>();
        Field[] fields = Vehicle.class.getDeclaredFields();
        List<String> rightFields = new ArrayList<String>();

        for (Vehicle v : salon.getLista_samochodow()) {
            rightFields.clear();
            for (Field field : fields) {
                if (field.isAnnotationPresent(ToCSV.class)) {
                    try {
                        field.setAccessible(true);
                        rightFields.add(String.valueOf(field.get(v)));
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    }
                }
            }

            String[] itemsArray = new String[rightFields.size()];
            rightFields.toArray(itemsArray);
            dataLines.add(itemsArray);
        }

        File csvOutputFile = new File("salon.csv");
        try (PrintWriter pw = new PrintWriter(csvOutputFile)) {
            dataLines.stream()
                    .map(this::convertToCSV)
                    .forEach(pw::println);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void importSalonFromCSVButton() {
        CarShowroom salon = data.getSalons().get(salonsComboBox.getValue());
        salon.getLista_samochodow().clear();

        List<List<String>> records = new ArrayList<>();
        try (Scanner scanner = new Scanner(new File("salon.csv"));) {
            while (scanner.hasNextLine()) {
                records.add(getRecordFromLine(scanner.nextLine()));
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        for (List<String> l : records) {
            Vehicle v = new Vehicle(
                    l.get(0),
                    l.get(1),
                    itemCondition.NEW,
                    Double.parseDouble(l.get(2)),
                    Integer.parseInt(l.get(3)),
                    Double.parseDouble(l.get(4)),
                    Double.parseDouble(l.get(5)),
                    Integer.parseInt(l.get(6)),
                    Integer.parseInt(l.get(7)),
                    l.get(8)
            );
            v.setSalonName(String.valueOf(salonsComboBox.getValue()));

            salon.getLista_samochodow().add(v);
        }
        salonComboBox_select();
    }

    public void setColumnNames(String a, String b, String c, String d, String e) {
        colBrand.setText(a);
        colModel.setText(b);
        colPrice.setText(c);
        colYearofproduction.setText(d);
        colSalonname.setText(e);
    }

    @FXML
    public void initialize() {
        deserializeData();

        salonsComboBox.getItems().add("Any salon");
        for (String name : data.getSalons().keySet()) {
            salonsComboBox.getItems().add(name);
        }
        salonsComboBox.getSelectionModel().selectFirst();

        colBrand.setCellValueFactory(new PropertyValueFactory<Vehicle, String>("brand"));
        colModel.setCellValueFactory(new PropertyValueFactory<Vehicle, String>("model"));
        colPrice.setCellValueFactory(new PropertyValueFactory<Vehicle, String>("price"));
        colYearofproduction.setCellValueFactory(new PropertyValueFactory<Vehicle, String>("year_of_production"));
        colSalonname.setCellValueFactory(new PropertyValueFactory<Vehicle, String>("salonName"));

        try {
            Class cls = this.getClass();
            Method m = cls.getDeclaredMethod("setColumnNames", String.class, String.class, String.class, String.class, String.class);

            try {
                m.invoke(this, "a", "b", "c", "d", "e");
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            }
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }

        for (CarShowroom salon : data.getSalons().values()) {
            for (Vehicle v : salon.getLista_samochodow()) {
                list.add(v);
            }
        }

        vehicleTable.setItems(list);
    }

    public void closeWindowEvent() {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmation Dialog");
        alert.setHeaderText("Save data?");

        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK) {
            serializeData();
        } else {
        }
    }


}
