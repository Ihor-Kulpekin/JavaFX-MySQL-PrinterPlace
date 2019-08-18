package sample.controllers.requestControllers;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.util.StringConverter;
import org.controlsfx.control.textfield.TextFields;
import sample.dao.implementation.selectingByDataTablesDao.RequestNumberDoOrderingDao;
import sample.dao.implementation.insertingIntoDao.WorkerDao;
import sample.dao.interfacesForDao.selectingDataTables.RequestNumberDoOrderingByWorker;
import sample.dao.interfacesForDao.insertingInto.WorkerInterface;
import sample.databaseConnection.DatabaseConnectionImpl;
import sample.model.modelsForSingleTables.Worker;
import sample.model.modelsForComplexTables.WorkerAndOrdering;

import java.sql.SQLException;


public class RequestWorkerWhoIsOrderedController {

    @FXML
    private TableView<WorkerAndOrdering> tableView;

    @FXML
    private TableColumn<WorkerAndOrdering, String> firstName;

    @FXML
    private TableColumn<WorkerAndOrdering, String> lastName;

    @FXML
    private TableColumn<WorkerAndOrdering, Integer> obsluzhivClients;

    @FXML
    private TableColumn<WorkerAndOrdering, Integer> generalSumAllOrdering;

    @FXML
    private Button buttonDoRequest;

    @FXML
    private ComboBox<Worker> allWorkers;

    @FXML
    private Button buttonGetWorkers;

    @FXML
    public void initialize() {

        firstName.setCellValueFactory(new PropertyValueFactory<>("firstNameWorker"));
        lastName.setCellValueFactory(new PropertyValueFactory<>("lastNameWorker"));
        obsluzhivClients.setCellValueFactory(new PropertyValueFactory<>("counter"));
        generalSumAllOrdering.setCellValueFactory(new PropertyValueFactory<>("generalSum"));

        DatabaseConnectionImpl.createStaticConnection();

        getWorker();
        doRequest();
    }


    public void getWorker() {
        buttonGetWorkers.setOnAction(event -> {
            ObservableList<Worker> innerList;
            WorkerInterface WI = new WorkerDao();
            try {
                innerList = WI.getWorker();
                allWorkers.setItems(innerList);
            } catch (SQLException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }

            TextFields.bindAutoCompletion(allWorkers.getEditor(),allWorkers.getItems());
            allWorkers.setConverter(new StringConverter<Worker>() {
                @Override
                public String toString(Worker object) {
                    if(object == null)return null;
                    return object.toString();
                }

                @Override
                public Worker fromString(String string) {
                    return allWorkers.getItems().stream()
                            .filter(ap->ap.toString().equals(string))
                            .findFirst()
                            .orElse(null);
                }
            });
        });
    }


    public void doRequest(){
        buttonDoRequest.setOnAction(event -> {
            WorkerAndOrdering workerAndOrdering = new WorkerAndOrdering();

            RequestNumberDoOrderingByWorker request = new RequestNumberDoOrderingDao();

            try {
                tableView.setItems(request.selectDataWorkerWhoIsOrdered(workerAndOrdering,allWorkers));
            } catch (SQLException e) {
                e.printStackTrace();
            }

        });
    }

}


