package sample.controllers.reportControllers;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.util.StringConverter;
import org.controlsfx.control.textfield.TextFields;
import sample.dao.implementation.insertingIntoDao.WorkerDao;
import sample.dao.implementation.reportDao.ReportWorkerDao;
import sample.dao.interfacesForDao.insertingInto.WorkerInterface;
import sample.dao.interfacesForDao.reportInterfaces.ReportWorkerInterface;
import sample.model.modelsForSingleTables.Worker;

import java.sql.SQLException;

public class ReportWorkerController {

    @FXML
    private ComboBox<Worker> boxIdWorker;

    @FXML
    private Button buttonGetWorkerId;

    @FXML
    private Button buttonMakeReport;

    @FXML
    private Label labelNumberClient;

    @FXML
    private Label labelGeneralSum;

    @FXML
    private Label labelNameWorker;

    @FXML
    private Label labelLastName;

    @FXML
    public void initialize(){

    }

    @FXML
    public void clickButtonMakeReport(){
        buttonMakeReport.setOnAction(event -> {
            ReportWorkerInterface RWI = new ReportWorkerDao();

            RWI.getNameWorker(labelNameWorker,boxIdWorker);
            RWI.getLastNameWorker(labelLastName,boxIdWorker);
            RWI.getCounterClients(labelNumberClient,boxIdWorker);
            RWI.getGeneralSum(labelGeneralSum,boxIdWorker);
        });
    }

    @FXML
    public void clickButtonGetWorker(){
        buttonGetWorkerId.setOnAction(event -> {
            ObservableList<Worker> innerList;

            WorkerInterface WI = new WorkerDao();

            try {
                innerList = WI.getWorker();

                boxIdWorker.setItems(innerList);

                TextFields.bindAutoCompletion(boxIdWorker.getEditor(),boxIdWorker.getItems());

                boxIdWorker.setConverter(new StringConverter<Worker>() {
                    @Override
                    public String toString(Worker object) {
                        if(object==null)return null;
                        return object.toString();
                    }

                    @Override
                    public Worker fromString(String string) {
                        return boxIdWorker.getItems().stream()
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
