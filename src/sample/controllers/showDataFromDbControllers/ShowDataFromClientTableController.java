package sample.controllers.showDataFromDbControllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import sample.databaseConnection.DatabaseConnectionImpl;
import sample.model.modelsForSingleTables.Client;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ShowDataFromClientTableController {

    @FXML
    private TableView<Client> tableView;

    @FXML
    private TableColumn<Client,Integer> colId_Client;

    @FXML
    private TableColumn<Client,String> colName_Client;

    @FXML
    private TableColumn<Client,String> colLast_Name;

    @FXML
    private TableColumn<Client,Integer> colCode_Client;

    @FXML
    private TableColumn<Client,String> colMobile_Number;

    @FXML
    private TableColumn<Client,String> colEmail;

    @FXML
    private Button buttonOpenFormChanging;

    @FXML
    private Button buttonShowDataFromClientTable;

    @FXML
    private Button buttonOpenFormDeleting;

    @FXML
    public void initialize(){
        colId_Client.setCellValueFactory(new PropertyValueFactory<>("id_Client"));
        colName_Client.setCellValueFactory(new PropertyValueFactory<>("firstNameClient"));
        colLast_Name.setCellValueFactory(new PropertyValueFactory<>("lastNameClient"));
        colCode_Client.setCellValueFactory(new PropertyValueFactory<>("codeClient"));
        colMobile_Number.setCellValueFactory(new PropertyValueFactory<>("numberPhone"));
        colEmail.setCellValueFactory(new PropertyValueFactory<>("electronicPostOffice"));

        DatabaseConnectionImpl.createStaticConnection();

        showTableClient();

    }

    @FXML
    public void clickButtonOpenFormDeleting(){
        buttonOpenFormDeleting.setOnAction(event -> {
            Stage primaryStage = new Stage();
            try {
                Parent root = FXMLLoader.load(getClass().getClassLoader()
                        .getResource("sample/fxmlUI/deleteDataFromTableDB/deleteDataFromClientTable.fxml"));
                Scene scene = new Scene(root,850,600);
                primaryStage.setScene(scene);
                primaryStage.show();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }

    @FXML
    public void clickButtonOpenFormChanging(){
        buttonOpenFormChanging.setOnAction(event -> {
            Stage primaryStage = new Stage();
            try {
                Parent root = FXMLLoader.load(getClass().getClassLoader()
                        .getResource("sample/fxmlUI/showTableFromDbOnUI/detailsUi/ClientDetails.fxml"));
                Scene scene = new Scene(root,850,600);
                primaryStage.setScene(scene);
                primaryStage.show();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }

    private ObservableList<Client> data;


    public void buildData() throws SQLException {
        data = FXCollections.observableArrayList();

        String query = "Select * from client";

        ResultSet resultSet = DatabaseConnectionImpl.createStaticConnection().createStatement()
                .executeQuery(query);

        while(resultSet.next()){
            Client client = new Client();
            client.setId_Client(resultSet.getInt("id_Client"));
            client.setFirstNameClient(resultSet.getString("Name_Client"));
            client.setLastNameClient(resultSet.getString("Last_Name_Client"));
            client.setCodeClient(resultSet.getInt("Code_Client"));
            client.setNumberPhone(resultSet.getString("Mobile_Number"));
            client.setElectronicPostOffice(resultSet.getString("email"));
            data.add(client);
        }

        tableView.setItems(data);

    }

    @FXML
    public void showTableClient(){
        buttonShowDataFromClientTable.setOnAction(event -> {
            try {
                buildData();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        });
    }

}
