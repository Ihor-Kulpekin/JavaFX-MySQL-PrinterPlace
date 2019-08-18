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
import sample.model.modelsForSingleTables.Worker;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ShowDataFromWorkerTableController {

    @FXML
    private TableView<Worker> tableView;

    @FXML
    private TableColumn<Worker,Integer> id_Worker;

    @FXML
    private TableColumn<Worker,String> Name_Worker;

    @FXML
    private TableColumn<Worker,String> Last_Name_Worker;

    @FXML
    private TableColumn<Worker,String> Position;

    @FXML
    private Button buttonOpenWindowChanging;

    @FXML
    private Button buttonOpenWindowDeletingData;

    @FXML
    private Button buttonUpdateTable;

    @FXML
    public void initialize(){
        id_Worker.setCellValueFactory(new PropertyValueFactory<>("id_Worker"));
        Name_Worker.setCellValueFactory(new PropertyValueFactory<>("firstNameWorker"));
        Last_Name_Worker.setCellValueFactory(new PropertyValueFactory<>("lastNameWorker"));
        Position.setCellValueFactory(new PropertyValueFactory<>("positionWorker"));

        DatabaseConnectionImpl.createStaticConnection();

        showWorkerTable();

    }

    @FXML
    public void clickButton(){
        buttonOpenWindowDeletingData.setOnAction(event -> {
            Stage primaryStage = new Stage();
            try {
                Parent root = FXMLLoader.load(getClass().getClassLoader()
                        .getResource("sample/fxmlUI/deleteDataFromTableDB/deleteDataFromWorkerTable.fxml"));
                Scene scene = new Scene(root,750,600);
                primaryStage.setScene(scene);
                primaryStage.show();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }

    @FXML
    public void openWindowChanging(){
        buttonOpenWindowChanging.setOnAction(event -> {
            Stage primaryStage = new Stage();
            try {
                Parent root = FXMLLoader.load(getClass().getClassLoader()
                        .getResource("sample/fxmlUI/showTableFromDbOnUI/detailsUi/WorkerDetails.fxml"));
                Scene scene = new Scene(root,750,600);
                primaryStage.setScene(scene);
                primaryStage.show();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }

    private ObservableList<Worker> data;

    public void buildData() throws SQLException {
        data = FXCollections.observableArrayList();

        String query = "Select * from worker";

        ResultSet resultSet = DatabaseConnectionImpl.createStaticConnection().createStatement()
                .executeQuery(query);

        while(resultSet.next()){
            Worker worker = new Worker();
            worker.setId_Worker(resultSet.getInt(1));
            worker.setFirstNameWorker(resultSet.getString(2));
            worker.setLastNameWorker(resultSet.getString(3));
            worker.setPositionWorker(resultSet.getString(4));
            data.add(worker);
        }
        tableView.setItems(data);

    }

    @FXML
    public void showWorkerTable(){
        buttonUpdateTable.setOnAction(event -> {
            try {
                buildData();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        });
    }


}
