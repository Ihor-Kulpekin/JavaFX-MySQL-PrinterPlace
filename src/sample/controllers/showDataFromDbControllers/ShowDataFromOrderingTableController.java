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
import sample.model.modelsForSingleTables.Ordering;

import java.io.IOException;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ShowDataFromOrderingTableController {

    @FXML
    private TableView<Ordering> tableView;

    @FXML
    private TableColumn<Ordering,Integer> colIdOrdering;

    @FXML
    private TableColumn<Ordering,Integer> colGeneralPrice;

    @FXML
    private TableColumn<Ordering,Integer> colCodeOrdering;

    @FXML
    private TableColumn<Ordering, Date> colDateOrdering;

    @FXML
    private TableColumn<Ordering,Integer> colNumberOrdering;

    @FXML
    private TableColumn<Ordering,Integer> colClient_id;

    @FXML
    private TableColumn<Ordering,Integer> colWorker_id;

    @FXML
    private TableColumn<Ordering,Integer> colKindService;

    @FXML
    private Button buttonDeleteData;

    @FXML
    private Button buttonChangingData;

    @FXML
    private Button buttonShowTable;

    public void initialize(){

        colIdOrdering.setCellValueFactory(new PropertyValueFactory<>("id_Ordering"));
        colGeneralPrice.setCellValueFactory(new PropertyValueFactory<>("generalSumOrdering"));
        colCodeOrdering.setCellValueFactory(new PropertyValueFactory<>("codeOrdering"));
        colDateOrdering.setCellValueFactory(new PropertyValueFactory<>("dataOrderingService"));
        colNumberOrdering.setCellValueFactory(new PropertyValueFactory<>("numberService"));
        colClient_id.setCellValueFactory(new PropertyValueFactory<>("clientId"));
        colWorker_id.setCellValueFactory(new PropertyValueFactory<>("workerId"));
        colKindService.setCellValueFactory(new PropertyValueFactory<>("nameKindService"));

        DatabaseConnectionImpl.createStaticConnection();

    }

    @FXML
    public void clickButtonDeleteData(){
        buttonDeleteData.setOnAction(event -> {
            Stage primaryStage = new Stage();
            try {
                Parent root = FXMLLoader.load(getClass().getClassLoader()
                        .getResource("sample/fxmlUI/deleteDataFromTableDB/deleteDataFromOrderingTable.fxml"));
                Scene scene = new Scene(root,700,600);
                primaryStage.setScene(scene);
                primaryStage.show();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }

    public void buildData() throws SQLException {
     ObservableList<Ordering> data = FXCollections.observableArrayList();

        String query = "Select * from ordering";

        ResultSet resultSet = DatabaseConnectionImpl.createStaticConnection().createStatement()
                .executeQuery(query);

        while(resultSet.next()){
            Ordering ordering = new Ordering();

            ordering.setId_Ordering(resultSet.getInt(1));
            ordering.setGeneralSumOrdering(resultSet.getInt(2));
            ordering.setCodeOrdering(resultSet.getInt(3));
            ordering.setDataOrderingService(resultSet.getDate(4));
            ordering.setNumberService(resultSet.getInt(5));
            ordering.setClientId(resultSet.getInt(6));
            ordering.setWorkerId(resultSet.getInt(7));
            ordering.setNameKindService(resultSet.getString(8));

            data.add(ordering);
        }

        tableView.setItems(data);

    }

    @FXML
    public void clickButtonChangingData(){
        buttonChangingData.setOnAction(event -> {
            Stage primaryStage = new Stage();
            try {
                Parent root = FXMLLoader.load(getClass().getClassLoader()
                        .getResource("sample/fxmlUI/showTableFromDbOnUI/detailsUi/OrderingDetails.fxml"));
                Scene scene = new Scene(root,1100,600);
                primaryStage.setScene(scene);
                primaryStage.show();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }

    public void clickButtonShowTable(){
        buttonShowTable.setOnAction(event -> {
            try {
                buildData();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        });
    }

}
