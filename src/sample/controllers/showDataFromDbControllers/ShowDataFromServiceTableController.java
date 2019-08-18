package sample.controllers.showDataFromDbControllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.util.StringConverter;
import org.controlsfx.control.textfield.TextFields;
import sample.dao.implementation.deleteDataFromTableDao.DeleteDataServiceDao;
import sample.dao.implementation.insertingIntoDao.ServiceDao;
import sample.dao.implementation.updateTableDao.UpdateServiceTableDao;
import sample.dao.interfacesForDao.deleteDataFromTable.DeleteServiceInterface;
import sample.dao.interfacesForDao.insertingInto.ServiceInterface;
import sample.dao.interfacesForDao.updateDataTable.UpdateServiceInterface;
import sample.databaseConnection.DatabaseConnectionImpl;
import sample.model.modelsForSingleTables.Service;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ShowDataFromServiceTableController {

    @FXML
    private TableView<Service> tableService;

    @FXML
    private TableColumn<Service,Integer> id_Service;

    @FXML
    private TableColumn<Service,String> nameService;

    @FXML
    private Button updateData;

    @FXML
    private Button buttonDeleteData;

    @FXML
    private ComboBox<Service> boxDeleteData;

    @FXML
    private ComboBox<Service> boxDataForUpdating;

    @FXML
    private Button buttonGetNameService;

    @FXML
    private Button buttonGetName;

    @FXML
    private Button buttonUpdateData;

    @FXML
    private TextField textField;

    @FXML
    public void initialize(){

        id_Service.setCellValueFactory(new PropertyValueFactory<>("id_Service"));
        nameService.setCellValueFactory(new PropertyValueFactory<>("Name"));

        DatabaseConnectionImpl.createStaticConnection();
        updateData();
    }

    @FXML
    public void clickButtonUpdateData(){
        buttonUpdateData.setOnAction(event -> {
            Service service = new Service();

            String name = textField.getText();

            service.setName(name);

            UpdateServiceInterface USI = new UpdateServiceTableDao();

            String updatedData = USI.updateDataFromServiceTable(service,boxDataForUpdating);

            if(updatedData.equals("SUCCESS")){
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Information");
                String s ="Дані обновилися";
                alert.setContentText(s);
                alert.show();
            }else {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Information");
                String s ="Помилка!!! Дані не обновилися!!!";
                alert.setContentText(s);
                alert.show();
            }

        });
    }

    @FXML
    public void clickGetNameUpdate(){
        buttonGetNameService.setOnAction(event -> {
            ObservableList<Service> innerList;
            ServiceInterface SI = new ServiceDao();

            try {
                innerList = SI.getServices();

                boxDataForUpdating.setItems(innerList);

                TextFields.bindAutoCompletion(boxDataForUpdating.getEditor(),boxDataForUpdating.getItems());

                boxDataForUpdating.setConverter(new StringConverter<Service>() {
                    @Override
                    public String toString(Service object) {
                        if(object==null)return null;
                        return object.toString();
                    }

                    @Override
                    public Service fromString(String string) {
                        return boxDataForUpdating.getItems().stream()
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

    @FXML
    public void clickDeleteData(){
        buttonDeleteData.setOnAction(event -> {
            DeleteServiceInterface DSI = new DeleteDataServiceDao();
            DSI.deleteName(boxDeleteData);
        });
    }

    @FXML
    public void clickGetName(){
        buttonGetName.setOnAction(event -> {
            ObservableList<Service> innerList;
            ServiceInterface SI = new ServiceDao();

            try {
                innerList = SI.getServices();
                boxDeleteData.setItems(innerList);

                TextFields.bindAutoCompletion(boxDeleteData.getEditor(),boxDeleteData.getItems());

                boxDeleteData.setConverter(new StringConverter<Service>() {
                    @Override
                    public String toString(Service object) {
                        if(object==null)return null;
                        return object.toString();
                    }

                    @Override
                    public Service fromString(String string) {
                        return boxDeleteData.getItems().stream()
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

    private ObservableList<Service> data;

    public void buildData() throws SQLException {
        data = FXCollections.observableArrayList();

        String query = "Select * from service";
        ResultSet rs = DatabaseConnectionImpl.createStaticConnection().createStatement()
                .executeQuery(query);

        while(rs.next()){
            Service service = new Service();
            service.setId_Service(rs.getInt("id_Service"));
            service.setName(rs.getString("Name"));
            data.add(service);
        }
        tableService.setItems(data);
    }

    @FXML
    public void updateData(){
        updateData.setOnAction(event -> {
            try {
                buildData();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        });
    }



}
