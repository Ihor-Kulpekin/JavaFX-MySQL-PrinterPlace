package sample.controllers.showDataFromDbControllers.deleteDataFromTableControllers;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import sample.dao.implementation.deleteDataFromTableDao.DeleteWorkerDao;
import sample.model.modelsForSingleTables.Worker;

public class DeleteDataWorkerController {

    @FXML
    private TextField textField;

    @FXML
    private Button buttonDeleteData;

    @FXML
    public void initialize(){

    }

    @FXML
    public void clickButtonDeleteData(){
        buttonDeleteData.setOnAction(event -> {

            Worker worker = new Worker();

            int worker_Id = Integer.parseInt(textField.getText());

            worker.setId_Worker(worker_Id);

            DeleteWorkerDao DWD = new DeleteWorkerDao();

            String deletedData = DWD.deleteWorkerById(worker);

            if(deletedData.equals("SUCCESS")){
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Information");
                String s = "Дані видалилися";
                alert.setContentText(s);
                alert.show();
            }else {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Information");
                String s = "Помилка!!! Спробуйте ще раз!!!";
                alert.setContentText(s);
                alert.show();
            }

        });
    }

}
