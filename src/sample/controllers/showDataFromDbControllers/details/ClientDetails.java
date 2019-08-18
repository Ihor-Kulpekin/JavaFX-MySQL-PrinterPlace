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
import sample.dao.implementation.updateTableDao.UpdateClientTableDao;
import sample.dao.interfacesForDao.insertingInto.ClientInterface;
import sample.dao.interfacesForDao.updateDataTable.UpdateClientTableInterface;
import sample.model.modelsForSingleTables.Client;

import java.sql.SQLException;

public class ClientDetails {

    @FXML
    private TextField firstName;

    @FXML
    private TextField lastName;

    @FXML
    private TextField codeClient;

    @FXML
    private TextField phoneNumber;

    @FXML
    private TextField email;

    @FXML
    private ComboBox<Client> boxIdClient;

    @FXML
    private Button buttonChangeData;

    @FXML
    private Button buttonGetIdClient;

    @FXML
    public void initialize(){

    }

    @FXML
    public void clickButtonUpdateData(){
        buttonChangeData.setOnAction(event -> {
            Client client = new Client();

            String firstNameClient = firstName.getText();
            String lastNameClient = lastName.getText();
            int code = Integer.parseInt(codeClient.getText());
            String numberPhone = phoneNumber.getText();
            String emailClient = email.getText();

            client.setFirstNameClient(firstNameClient);
            client.setLastNameClient(lastNameClient);
            client.setCodeClient(code);
            client.setNumberPhone(numberPhone);
            client.setElectronicPostOffice(emailClient);

            UpdateClientTableInterface UCTI = new UpdateClientTableDao();
            String updatedData = UCTI.updateClientTable(client,boxIdClient);

            if(updatedData.equals("SUCCESS")){
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Information");
                String s = "Дані обновилися";
                alert.setContentText(s);
                alert.show();
            }else {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Information");
                String s = "Помилка!!! Дані не обновилися";
                alert.setContentText(s);
                alert.show();
            }
        });
    }

    @FXML
    public void clickButtonGetClientId(){

        buttonGetIdClient.setOnAction(event -> {

                ObservableList<Client> innerList;



                ClientInterface CI = new AddClientDao();

                try {
                    innerList = CI.getClients();
                    boxIdClient.setItems(innerList);

                    TextFields.bindAutoCompletion(boxIdClient.getEditor(),boxIdClient.getItems());

                    boxIdClient.setConverter(new StringConverter<Client>() {
                        @Override
                        public String toString(Client object) {
                            if(object==null)return null;
                            return object.toString();
                        }

                        @Override
                        public Client fromString(String string) {
                            return boxIdClient.getItems().stream()
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
