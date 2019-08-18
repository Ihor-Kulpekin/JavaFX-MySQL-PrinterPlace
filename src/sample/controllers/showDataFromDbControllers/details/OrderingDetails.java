package sample.controllers.showDataFromDbControllers.details;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.util.StringConverter;
import org.controlsfx.control.textfield.TextFields;
import sample.dao.implementation.insertingIntoDao.AddClientDao;
import sample.dao.implementation.insertingIntoDao.NameServiceDao;
import sample.dao.implementation.insertingIntoDao.OrderingDao;
import sample.dao.implementation.insertingIntoDao.WorkerDao;
import sample.dao.implementation.updateTableDao.UpdateOrderingTableDao;
import sample.dao.interfacesForDao.insertingInto.ClientInterface;
import sample.dao.interfacesForDao.insertingInto.NameServiceInterface;
import sample.dao.interfacesForDao.insertingInto.OrderingInterface;
import sample.dao.interfacesForDao.insertingInto.WorkerInterface;
import sample.dao.interfacesForDao.updateDataTable.UpdateOrderingTableInterface;
import sample.model.modelsForSingleTables.Client;
import sample.model.modelsForSingleTables.NameService;
import sample.model.modelsForSingleTables.Ordering;
import sample.model.modelsForSingleTables.Worker;

import java.sql.Date;
import java.sql.SQLException;

public class OrderingDetails {


    @FXML
    private TextField generalSum;


    @FXML
    private TextField codeOrdering;

    @FXML
    private TextField dataOrdering;

    @FXML
    private TextField numberOrdering;

    @FXML
    private ComboBox<Client> listClient;

    @FXML
    private ComboBox<Worker> listWorker;

    @FXML
    private ComboBox<NameService> nameKindService;

    @FXML
    private ComboBox<Ordering> boxIdOrdering;

    @FXML
    private Button buttonGetId;

    @FXML
    private Button addOrdering;

    @FXML
    private Button buttonGetClient;

    @FXML
    private Button buttonGetWorker;

    @FXML
    private Button buttonGetKindService;

    @FXML
    private Button buttonChangeDataOrdering;

    @FXML
    public void initialize(){

    }

    @FXML
    public void clickButtonChangeDataOrdering(){
        buttonChangeDataOrdering.setOnAction(event -> {
            Ordering ordering = new Ordering();

            Date date = Date.valueOf(dataOrdering.getText());
            int amountOrdering = Integer.parseInt(numberOrdering.getText());
            int code = Integer.parseInt(codeOrdering.getText());
            int idClient = listClient.getValue().getId_Client();
            int idWorker = listWorker.getValue().getId_Worker();
            int price = Integer.parseInt(generalSum.getText());
            String serviceKind = nameKindService.getValue().toString();

            ordering.setNameKindService(serviceKind);
            ordering.setWorkerId(idWorker);
            ordering.setClientId(idClient);
            ordering.setNumberService(amountOrdering);
            ordering.setCodeOrdering(code);
            ordering.setGeneralSumOrdering(price);
            ordering.setDataOrderingService(date);


            UpdateOrderingTableInterface UDTI = new UpdateOrderingTableDao();

            String updatedData = UDTI.updateOrderingTable(ordering,boxIdOrdering,listClient,listWorker,nameKindService);

            if (updatedData.equals("SUCCESS")) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Information");
                String s = "Дані обновилися";
                alert.setContentText(s);
                alert.show();
            } else {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Information");
                String s = "Помилка!!! Дані не обновилися!!!";
                alert.setContentText(s);
                alert.show();
            }

        });
    }

    @FXML
    public void clickButtonGetIdOrdering(){
        buttonGetId.setOnAction(event -> {
            ObservableList<Ordering> innerList;
            OrderingInterface OI = new OrderingDao();

            innerList = OI.getOrderingById();

            boxIdOrdering.setItems(innerList);

            TextFields.bindAutoCompletion(boxIdOrdering.getEditor(),boxIdOrdering.getItems());

            boxIdOrdering.setConverter(new StringConverter<Ordering>() {
                @Override
                public String toString(Ordering object) {
                    if(object==null)return null;
                    return String.valueOf(object.getId_Ordering());
                }

                @Override
                public Ordering fromString(String string) {
                    return boxIdOrdering.getItems().stream()
                            .filter(ap->ap.toString().equals(string))
                            .findFirst()
                            .orElse(null);
                }
            });

        });
    }

    @FXML
    public void clickGetKindService(){
        buttonGetKindService.setOnAction(event -> {
            ObservableList<NameService> innerList;

            NameServiceInterface NSI = new NameServiceDao();

            try {
                innerList = NSI.getKindService();

                nameKindService.setItems(innerList);

                TextFields.bindAutoCompletion(nameKindService.getEditor(),nameKindService.getItems());

                nameKindService.setConverter(new StringConverter<NameService>() {
                    @Override
                    public String toString(NameService object) {
                        if(object==null)return null;
                        return object.toString();
                    }

                    @Override
                    public NameService fromString(String string) {
                        return nameKindService.getItems().stream()
                                .filter(ap->ap.toString().equals(string))
                                .findFirst()
                                .orElse(null);
                    }
                });

            } catch (SQLException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }

        });
    }

    @FXML
    public void clickGetWorkerId(){
        buttonGetWorker.setOnAction(event -> {
            ObservableList<Worker> innerList;

            WorkerInterface WI = new WorkerDao();

            try {
                innerList = WI.getWorker();

                listWorker.setItems(innerList);

                TextFields.bindAutoCompletion(listWorker.getEditor(), listClient.getItems());

                listWorker.setConverter(new StringConverter<Worker>() {
                    @Override
                    public String toString(Worker object) {
                        if(object==null)return null;
                        return object.toString();
                    }

                    @Override
                    public Worker fromString(String string) {
                        return listWorker.getItems().stream()
                                .filter(ap->ap.toString().equals(string))
                                .findFirst()
                                .orElse(null);
                    }
                });
            } catch (SQLException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }

        });
    }

    @FXML
    public void clickGetClientId(){
        buttonGetClient.setOnAction(event -> {
            ObservableList<Client> innerList;

            ClientInterface CI = new AddClientDao();

            try {
                innerList = CI.getClients();

                listClient.setItems(innerList);

                TextFields.bindAutoCompletion(listClient.getEditor(),listClient.getItems());

                listClient.setConverter(new StringConverter<Client>() {
                    @Override
                    public String toString(Client object) {
                        if(object==null)return null;
                        return object.toString();
                    }

                    @Override
                    public Client fromString(String string) {
                        return listClient.getItems().stream()
                                .filter(ap->ap.toString().equals(string))
                                .findFirst()
                                .orElse(null);
                    }
                });

            } catch (SQLException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }

        });
    }


}
