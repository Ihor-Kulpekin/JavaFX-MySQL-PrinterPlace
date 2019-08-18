package sample.controllers.addDataToDbControllers;

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
import sample.dao.interfacesForDao.insertingInto.ClientInterface;
import sample.dao.interfacesForDao.insertingInto.NameServiceInterface;
import sample.dao.interfacesForDao.insertingInto.WorkerInterface;
import sample.model.modelsForSingleTables.Client;
import sample.model.modelsForSingleTables.NameService;
import sample.model.modelsForSingleTables.Ordering;
import sample.model.modelsForSingleTables.Worker;

import java.sql.Date;
import java.sql.SQLException;

public class AddOrderingController {

    @FXML
    private TextField dataOrdering;

    @FXML
    private TextField numberOrdering;

    @FXML
    private TextField generalSum;

    @FXML
    private TextField codeOrdering;

    @FXML
    private ComboBox<Client> listClient;

    @FXML
    private ComboBox<Worker> listWorker;

    @FXML
    private ComboBox<NameService> nameKindService;

    @FXML
    private Button addOrdering;



    @FXML
    public void initialize(){

    }

    // need to fix the problem with client_Id, worker_Id, kind_Service;

    //Method for adding ordering to DB
    @FXML
    public void addOrdering(){
        addOrdering.setOnAction(event -> {
            Ordering ordering = new Ordering();

            //Parsing fields from fxml form to variables
            int generalPrice = Integer.parseInt(generalSum.getText());
            int codeOrdering1 = Integer.parseInt(codeOrdering.getText());
            Date date = Date.valueOf(dataOrdering.getText());
            int numberOrderedService = Integer.parseInt(numberOrdering.getText());
            int client_Id = Integer.parseInt(listClient.getValue().toString());
            int worker_Id = Integer.parseInt(listWorker.getValue().toString());
            String kind_Service = nameKindService.getValue().toString();


            ordering.setGeneralSumOrdering(generalPrice);
            ordering.setCodeOrdering(codeOrdering1);
            ordering.setDataOrderingService(date);
            ordering.setNumberService(numberOrderedService);
            ordering.setClientId(client_Id);
            ordering.setWorkerId(worker_Id);
            ordering.setNameKindService(kind_Service);

            //Make instance of OrderingDao and then add ordering to DB
            OrderingDao orderingDao = new OrderingDao();
            String nameOrderingAdded = orderingDao.addOrdering(ordering);

            if(nameOrderingAdded.equals("SUCCESS")){
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Information");
                String s ="Запис додано до таблиці ordering";
                alert.setContentText(s);
                alert.show();
            }
            else {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Information");
                String s ="Помилка!!!";
                alert.setContentText(s);
                alert.show();
            }
        });
    }


    //Next three methods are made to set values to combobox
    public void valuesForComboBoxClient() throws SQLException, ClassNotFoundException {
        ObservableList<Client> innerList;
        ClientInterface ASI = new AddClientDao();
        innerList = ASI.getClients();

        listClient.setItems(innerList);
        TextFields.bindAutoCompletion(listClient.getEditor(),listClient.getItems());
        listClient.setConverter(new StringConverter<Client>() {
            @Override
            public String toString(Client object) {
                if(object == null)return null;
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
    }

    public void valuesForComboBoxWorker() throws SQLException, ClassNotFoundException {
        ObservableList<Worker> innerList;
        WorkerInterface WI = new WorkerDao();
        innerList = WI.getWorker();

        listWorker.setItems(innerList);
        TextFields.bindAutoCompletion(listWorker.getEditor(),listWorker.getItems());
        listWorker.setConverter(new StringConverter<Worker>() {

            @Override
            public String toString(Worker object) {
                if(object == null)return null;
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
    }


    public void valuesForComboBoxKindService() throws SQLException, ClassNotFoundException {
        ObservableList<NameService> innerList;
        NameServiceInterface NSI = new NameServiceDao();
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
    }

}
