package sample.controllers.addDataToDbControllers;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import sample.dao.implementation.insertingIntoDao.AddClientDao;
import sample.model.modelsForSingleTables.Client;

public class AddClientController {

    @FXML
    private TextField nameClient;

    @FXML
    private TextField lastNameClient;

    @FXML
    private TextField codeClient;

    @FXML
    private TextField mobileNumberClient;

    @FXML
    private TextField emailClient;


    @FXML
    private Button addClient;

    @FXML
    public void initialize(){

    }

    public void addClient(){
        addClient.setOnAction(event -> {
            Client client = new Client();

            String firstName = nameClient.getText();
            String lastName = lastNameClient.getText();
            int clientCode = Integer.parseInt(codeClient.getText());
            String mobileClient = mobileNumberClient.getText();
            String email = emailClient.getText();

            client.setFirstNameClient(firstName);
            client.setLastNameClient(lastName);
            client.setCodeClient(clientCode);
            client.setNumberPhone(mobileClient);
            client.setElectronicPostOffice(email);

            AddClientDao addClientDao = new AddClientDao();

            String clientAdded = addClientDao.addClient(client);

            if(clientAdded.equals("SUCCESS")){
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Information");
                String s ="Запис додано до таблиці client";
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


}
