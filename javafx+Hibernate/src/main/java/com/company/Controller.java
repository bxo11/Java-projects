package com.company;

import com.company.modelEntities.CarShowroom.CarshowroomEntity;
import com.company.modelEntities.CarShowroomContainer.CarShowroomContainerService;
import com.company.modelEntities.CarShowroomContainer.CarshowroomcontainerEntity;
import com.company.modelEntities.CarShowroomContainer.ICarShowroomContainerService;
import com.company.modelEntities.Rating.RatingEntity;
import com.company.modelEntities.Vehicle.VehicleEntity;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.Clipboard;
import javafx.scene.input.ClipboardContent;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

import javax.persistence.NoResultException;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
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
    public TextArea ratingTextArea;
    @FXML
    private ComboBox salonsComboBox;
    @FXML
    private TableView<VehicleEntity> vehicleTable;

    private CarshowroomcontainerEntity data;
    private ICarShowroomContainerService dataService;
    private final ObservableList<VehicleEntity> list = FXCollections.observableArrayList();
    private ObservableList<VehicleEntity> cart_list = FXCollections.observableArrayList();

    private SessionFactory ourSessionFactory = null;
    private Session session;

    public Controller() {
        Configuration configuration = new Configuration();
        configuration.configure();

        ourSessionFactory = configuration.buildSessionFactory();
        session = ourSessionFactory.openSession();

        data = (CarshowroomcontainerEntity) session.get(CarshowroomcontainerEntity.class, 1);
        dataService = new CarShowroomContainerService(session, data);
//        starter data
//         CarShowroomContainer test = new DataGenerator().generate();
//       test.serialization("carshowroomData.ser.clean");
//
//        this.data = this.data.deserialization("carshowroomData.ser.clean");
//
//        if (data == null) {
//            new Alert(Alert.AlertType.ERROR, "Deserialisation Error").showAndWait();
//        }
    }

    public void salonComboBox_select() {
        if (salonsComboBox.getValue() == null) {
            vehicleTable.setItems(null);
        } else if (salonsComboBox.getValue().toString().equals("Any salon")) {
            list.clear();
            for (CarshowroomEntity salon : data.getSalons()) {
                for (VehicleEntity v : salon.getVehicles()) {
                    list.add(v);
                }
            }
            vehicleTable.setItems(list);
        } else {
            ObservableList<VehicleEntity> temp_list = FXCollections.observableArrayList();
            System.out.println(salonsComboBox.getValue());
            CarshowroomEntity temp_salon = (CarshowroomEntity) dataService.getSalonByName(salonsComboBox.getValue().toString());
            for (VehicleEntity v : temp_salon.getVehicles()) {
                temp_list.add(v);
            }
            vehicleTable.setItems(temp_list);
        }
    }

    public void searchButton() {

        String temp_string = searchTextField.getText();
        ObservableList<VehicleEntity> temp_list = FXCollections.observableArrayList(vehicleTable.getItems());
        ObservableList<VehicleEntity> list_toremove = FXCollections.observableArrayList();

        for (VehicleEntity s : temp_list) {
            String look = s.getBrand() + " " + s.getModel();
            if (!look.contains(temp_string)) {
                list_toremove.add(s);
            }
        }
        for (VehicleEntity d : list_toremove) {
            temp_list.remove(d);
        }
        vehicleTable.setItems(temp_list);
    }

    public void addToCartButton() {
        VehicleEntity v = vehicleTable.getSelectionModel().getSelectedItem();
        if (v != null) {
            if (v.getReserved() == 0) {
                v.setReserved((byte) 1);
                cart_list.add(v);
            } else {
                new Alert(Alert.AlertType.ERROR, "Vehicle already reserved").showAndWait();
            }
        } else {
            new Alert(Alert.AlertType.ERROR, "Select vehicle").showAndWait();
        }
        session.beginTransaction();
        session.saveOrUpdate(v);
        session.getTransaction().commit();
    }

    public void setVehicleTableToolKit() {
        VehicleEntity v = vehicleTable.getSelectionModel().getSelectedItem();
        vehicleTable.setTooltip(new Tooltip(String.valueOf(v)));

        CriteriaBuilder cb = session.getCriteriaBuilder();
        CriteriaQuery<Double> cr = cb.createQuery(Double.class);
        Root<RatingEntity> root = cr.from(RatingEntity.class);
        cr.select(cb.avg(root.get("value"))).where(cb.equal(root.get("r_salon"), v.getR_salon().getId()));
        Query<Double> query = session.createQuery(cr);

        ratingTextArea.setText("Amount of rates: " + v.getR_salon().getRatings().size()
                + "\nAvegare: " + query.getSingleResult());
    }

    public void showPhoneNumberButton() {
        VehicleEntity v = vehicleTable.getSelectionModel().getSelectedItem();

        final Clipboard clipboard = Clipboard.getSystemClipboard();
        final ClipboardContent content = new ClipboardContent();
        content.putString(String.valueOf(v.getPhoneNumber()));
        clipboard.setContent(content);

        String info = "Phone number: " + v.getPhoneNumber() + "\nPhone number coppied to clipboard";
        new Alert(Alert.AlertType.INFORMATION, info).showAndWait();
    }

    public void serializeData() {
//        this.data.serialization("carshowroomData.ser");
//
//        //cart serialization
//        try {
//            FileOutputStream file = new FileOutputStream("cart.ser");
//            ObjectOutputStream out = new ObjectOutputStream(file);
//
//            out.writeObject((new ArrayList<Vehicle>(cart_list)));
//            out.close();
//            file.close();
//
//        } catch (IOException e) {
//            System.out.println("cart serialization, IOException is caught");
//            e.printStackTrace();
//        }
    }

    public void deserializeData() {
//        this.data = this.data.deserialization("carshowroomData.ser");
//
//        list.clear();
//        for (CarShowroom salon : data.getSalons().values()) {
//            for (Vehicle v : salon.getLista_samochodow()) {
//                list.add(v);
//            }
//        }
//        vehicleTable.setItems(list);
//
//        //cart deserialization
//        try {
//            FileInputStream file = new FileInputStream("cart.ser");
//            ObjectInputStream in = new ObjectInputStream(file);
//
//            List<Vehicle> list = (List<Vehicle>) in.readObject();
//            ObservableList<Vehicle> object = FXCollections.observableArrayList(list);
//            in.close();
//            file.close();
//
//            cart_list = object;
//        } catch (IOException e) {
//            System.out.println("cart deserialisation ,IOException is caught");
//            e.printStackTrace();
//        } catch (ClassNotFoundException ex) {
//            System.out.println("cart ClassNotFoundException is caught");
//            ex.printStackTrace();
//        }
    }

    public void showCartButton() {
        salonsComboBox.getSelectionModel().clearSelection();
        vehicleTable.setItems(cart_list);
    }

    public void clearCartButton() {
        session.beginTransaction();
        for (VehicleEntity v : cart_list) {
            for (VehicleEntity v2 : list) {
                if (v.equals(v2)) {
                    v2.setReserved((byte) 0);
                    session.saveOrUpdate(v);
                }
            }
        }
        cart_list.clear();
        session.getTransaction().commit();
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
        Field[] fields = VehicleEntity.class.getDeclaredFields();
        List<String> s = new ArrayList<String>();

        for (VehicleEntity v : cart_list) {
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
//        List<List<String>> records = new ArrayList<>();
//        try (Scanner scanner = new Scanner(new File("cart.csv"));) {
//            while (scanner.hasNextLine()) {
//                records.add(getRecordFromLine(scanner.nextLine()));
//            }
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//        }
//
//        cart_list.clear();
//        for (List<String> l : records) {
//            Vehicle v = new Vehicle(
//                    l.get(0),
//                    l.get(1),
//                    itemCondi.NEW,
//                    Double.parseDouble(l.get(2)),
//                    Integer.parseInt(l.get(3)),
//                    Double.parseDouble(l.get(4)),
//                    Double.parseDouble(l.get(5)),
//                    Integer.parseInt(l.get(6)),
//                    Integer.parseInt(l.get(7)),
//                    l.get(8)
//            );
//            v.setSalonName(l.get(9));
//
//            for (Vehicle v2 : list) {
//                if (v.equals(v2)) {
//                    cart_list.add(v2);
//                    v2.setReserved(true);
//                }
//            }
//        }
    }

    public void exportSalonToCSVButton() {
        try {
            CarshowroomEntity salon = (CarshowroomEntity) dataService.getSalonByName(salonsComboBox.getValue().toString());

            List<String[]> dataLines = new ArrayList<>();

            Query query = session.createSQLQuery("SELECT * FROM vehicle WHERE id_salon=:id_s ORDER BY model").addEntity(VehicleEntity.class);
            query.setParameter("id_s", salon.getId());
            List<VehicleEntity> a = query.getResultList();

            Field[] fields = VehicleEntity.class.getDeclaredFields();
            List<String> rightFields = new ArrayList<String>();

            for (VehicleEntity v : a) {
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
        } catch (NoResultException ignored) {
        }
    }

    public void importSalonFromCSVButton() {
//        CarShowroom salon = data.getSalons().get(salonsComboBox.getValue());
//        salon.getLista_samochodow().clear();
//
//        List<List<String>> records = new ArrayList<>();
//        try (Scanner scanner = new Scanner(new File("salon.csv"));) {
//            while (scanner.hasNextLine()) {
//                records.add(getRecordFromLine(scanner.nextLine()));
//            }
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//        }
//
//        for (List<String> l : records) {
//            Vehicle v = new Vehicle(
//                    l.get(0),
//                    l.get(1),
//                    itemCondi.NEW,
//                    Double.parseDouble(l.get(2)),
//                    Integer.parseInt(l.get(3)),
//                    Double.parseDouble(l.get(4)),
//                    Double.parseDouble(l.get(5)),
//                    Integer.parseInt(l.get(6)),
//                    Integer.parseInt(l.get(7)),
//                    l.get(8)
//            );
//            v.setSalonName(String.valueOf(salonsComboBox.getValue()));
//
//            salon.getLista_samochodow().add(v);
//        }
//        salonComboBox_select();
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

        salonsComboBox.getItems().add("Any salon");
        for (CarshowroomEntity sal : data.getSalons()) {
            salonsComboBox.getItems().add(sal.getName());
        }
        salonsComboBox.getSelectionModel().selectFirst();

        colBrand.setCellValueFactory(new PropertyValueFactory<VehicleEntity, String>("brand"));
        colModel.setCellValueFactory(new PropertyValueFactory<VehicleEntity, String>("model"));
        colPrice.setCellValueFactory(new PropertyValueFactory<VehicleEntity, String>("price"));
        colYearofproduction.setCellValueFactory(new PropertyValueFactory<VehicleEntity, String>("yearOfProduction"));
        colSalonname.setCellValueFactory(new PropertyValueFactory<VehicleEntity, String>("salonName"));

        //column names by reflection
//        try {
//            Class cls = this.getClass();
//            Method m = cls.getDeclaredMethod("setColumnNames", String.class, String.class, String.class, String.class, String.class);
//
//            try {
//                m.invoke(this, "a", "b", "c", "d", "e");
//            } catch (IllegalAccessException e) {
//                e.printStackTrace();
//            } catch (InvocationTargetException e) {
//                e.printStackTrace();
//            }
//        } catch (NoSuchMethodException e) {
//            e.printStackTrace();
//        }

        for (CarshowroomEntity salon : data.getSalons()) {
            for (VehicleEntity v : salon.getVehicles()) {
                list.add(v);
            }
        }

        vehicleTable.setItems(list);
    }

    public void closeWindowEvent() {
//        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
//        alert.setTitle("Confirmation Dialog");
//        alert.setHeaderText("Save data?");
//
//        Optional<ButtonType> result = alert.showAndWait();
//        if (result.get() == ButtonType.OK) {
//            serializeData();
//        } else {
//        }
    }


}
