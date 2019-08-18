package sample.controllers.showDataFromDbControllers.deleteDataFromTableControllers;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import sample.dao.implementation.deleteDataFromTableDao.DeleteDataClientDao;
import sample.dao.interfacesForDao.deleteDataFromTable.DeleteClientByCodeInterface;
import sample.model.modelsForSingleTables.Client;

public class DeleteDataClientController {

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
            Client client = new Client();

            int codeClient = Integer.parseInt(textField.getText());

            client.setCodeClient(codeClient);

            DeleteClientByCodeInterface DCBCI = new DeleteDataClientDao();

            String deletedData = DCBCI.deleteClientByCode(client);

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
