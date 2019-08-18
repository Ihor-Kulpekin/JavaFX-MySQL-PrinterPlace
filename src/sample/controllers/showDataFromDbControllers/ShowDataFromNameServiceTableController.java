package sample.controllers.showDataFromDbControllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javafx.util.StringConverter;
import org.controlsfx.control.textfield.TextFields;
import sample.dao.implementation.deleteDataFromTableDao.DeleteDataNameServiceDao;
import sample.dao.implementation.insertingIntoDao.NameServiceDao;
import sample.dao.interfacesForDao.deleteDataFromTable.DeleteNameServiceInterface;
import sample.dao.interfacesForDao.insertingInto.NameServiceInterface;
import sample.databaseConnection.DatabaseConnectionImpl;
import sample.model.modelsForSingleTables.NameService;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ShowDataFromNameServiceTableController {

    NameService nameServiceModel;

    @FXML
    private TableView<NameService> tableNameService;

    @FXML
    private TableColumn<NameService,Integer> id_Name_Service;

    @FXML
    private TableColumn<NameService,String> nameService;

    @FXML
    private TableColumn<NameService,String>kind_Service;

    @FXML
    private TableColumn <NameService,Float> priceService;

    @FXML
    private Button buttonShowTable;

    @FXML
    private Button buttonDeleteData;



    @FXML
    private ComboBox<NameService> boxChoiceNameService;

    @FXML
    private Button buttonGetNameService;

    @FXML
    public void initialize() throws SQLException, ClassNotFoundException {
        id_Name_Service.setCellValueFactory(new PropertyValueFactory<>("Id_NameService"));
        nameService.setCellValueFactory(new PropertyValueFactory<>("name"));
        kind_Service.setCellValueFactory(new PropertyValueFactory<>("kindService"));
        priceService.setCellValueFactory(new PropertyValueFactory<>("priceService"));

        DatabaseConnectionImpl.createStaticConnection();

        showTableNameService();

    }

    public void openUpdateWindow(){
    }

    @FXML
    public void clickButtonDeleteData(){
        buttonDeleteData.setOnAction(event -> {


            DeleteNameServiceInterface DNSI = new DeleteDataNameServiceDao();

            DNSI.deleteDataNameServiceTable(boxChoiceNameService);

        });
    }

    @FXML
    public void clickButtonGetNameService(){
        buttonGetNameService.setOnAction(event -> {
            ObservableList<NameService> innerList;

            NameServiceInterface NSI = new NameServiceDao();

            try {
                innerList = NSI.getKindService();
                boxChoiceNameService.setItems(innerList);

                TextFields.bindAutoCompletion(boxChoiceNameService.getEditor(),boxChoiceNameService.getItems());

                boxChoiceNameService.setConverter(new StringConverter<>() {
                    @Override
                    public String toString(NameService object) {
                        if(object==null)return null;
                        return object.toString();
                    }

                    @Override
                    public NameService fromString(String string) {
                        return boxChoiceNameService.getItems().stream()
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

    private ObservableList<NameService> data;

    public void buildData() throws SQLException {
        data = FXCollections.observableArrayList();

        String query = "Select * from nameservice";

        ResultSet resultSet = DatabaseConnectionImpl.createStaticConnection().createStatement()
                .executeQuery(query);

        while(resultSet.next()){
            NameService nameService = new NameService();
            nameService.setId_NameService(resultSet.getInt(1));
            nameService.setName(resultSet.getString(2));
            nameService.setKindService(resultSet.getString(3));
            nameService.setPriceService(resultSet.getFloat(4));
            data.add(nameService);
        }
        tableNameService.setItems(data);

    }

    @FXML
    public void showTableNameService(){
        buttonShowTable.setOnAction(event -> {
            try {
                buildData();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        });
    }

    @FXML
    public void showNameServiceDetails() throws Exception {
        Stage primaryStage = new Stage();
        try {
            Parent root = FXMLLoader.load(getClass().getClassLoader()
                    .getResource("sample/fxmlUI/showTableFromDbOnUI/detailsUi/NameServiceUi.fxml"));
            Scene scene = new Scene(root,1100,600);
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public TableColumn<NameService, String> getNameService() {
        return nameService;
    }

    public void setNameService(TableColumn<NameService, String> nameService) {
        this.nameService = nameService;
    }

    public TableColumn<NameService, String> getKind_Service() {
        return kind_Service;
    }

    public void setKind_Service(TableColumn<NameService, String> kind_Service) {
        this.kind_Service = kind_Service;
    }

    public TableColumn<NameService, Float> getPriceService() {
        return priceService;
    }

    public void setPriceService(TableColumn<NameService, Float> priceService) {
        this.priceService = priceService;
    }


}
