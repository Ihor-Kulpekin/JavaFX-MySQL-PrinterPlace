package sample.controllers.addDataToDbControllers;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import sample.dao.implementation.insertingIntoDao.WorkerDao;
import sample.model.modelsForSingleTables.Worker;

public class AddWorkerController {

    @FXML
    private TextField nameWorker;

    @FXML
    private TextField lastNameWorker;

    @FXML
    private TextField position;

    @FXML
    private Button addWorkerBtn;

    @FXML
    public void initialize(){
        System.setProperty("file.encoding","UTF-8");
    }


    @FXML
    public void addWorker(){
        addWorkerBtn.setOnAction(event -> {
            Worker worker = new Worker();

            String firstName = nameWorker.getText();
            String lastName = lastNameWorker.getText();
            String positionWorker = position.getText();

            worker.setFirstNameWorker(firstName);
            worker.setLastNameWorker(lastName);
            worker.setPositionWorker(positionWorker);


            WorkerDao addWorkerDao = new WorkerDao();

            String workerAdded = addWorkerDao.addWorker(worker);

            if(workerAdded.equals("SUCCESS")){
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Information");
                String s ="Запис додано до таблиці 'послуга'";
                alert.setContentText(s);
                alert.show();
            }

            else{
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Information");
                String s ="Помилка!!!";
                alert.setContentText(s);
                alert.show();
            }

        });
    }

}
