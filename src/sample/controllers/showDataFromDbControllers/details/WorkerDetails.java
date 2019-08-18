package sample.controllers.showDataFromDbControllers.details;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.util.StringConverter;
import org.controlsfx.control.textfield.TextFields;
import sample.dao.implementation.insertingIntoDao.WorkerDao;
import sample.dao.implementation.updateTableDao.UpdateWorkerTableDao;
import sample.dao.interfacesForDao.insertingInto.WorkerInterface;
import sample.dao.interfacesForDao.updateDataTable.UpdateDataWorkerTableInterface;
import sample.model.modelsForSingleTables.Worker;

import java.sql.SQLException;

public class WorkerDetails {

    @FXML
    private TextField firstNameWorker;

    @FXML
    private TextField lastNameWorker;

    @FXML
    private TextField positionWorker;

    @FXML
    private ComboBox<Worker> boxIdWorker;

    @FXML
    private Button buttonChangeData;

    @FXML
    private Button buttonGetIdWorker;

    @FXML
    public void initialize(){

    }

    @FXML
    public void clickButtonChangeData(){
        buttonChangeData.setOnAction(event -> {
            Worker worker = new Worker();
            String firstName = firstNameWorker.getText();
            String latName = lastNameWorker.getText();
            String position = positionWorker.getText();

            worker.setFirstNameWorker(firstName);
            worker.setLastNameWorker(latName);
            worker.setPositionWorker(position);

            UpdateDataWorkerTableInterface UDWTI = new UpdateWorkerTableDao();

            String updatedData = UDWTI.updateDataWorkerTable(worker,boxIdWorker);

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
    public void clickGetIdWorker(){
        buttonGetIdWorker.setOnAction(event -> {
            ObservableList<Worker> innerList;

            WorkerInterface WI = new WorkerDao();

            try {
                innerList=WI.getWorker();
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
