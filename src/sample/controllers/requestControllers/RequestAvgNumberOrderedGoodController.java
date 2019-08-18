package sample.controllers.requestControllers;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.util.StringConverter;
import org.controlsfx.control.textfield.TextFields;
import sample.dao.implementation.insertingIntoDao.NameServiceDao;
import sample.dao.implementation.selectingByDataTablesDao.RequestAvgNumberOrderingDao;
import sample.dao.interfacesForDao.insertingInto.NameServiceInterface;
import sample.dao.interfacesForDao.selectingDataTables.AverageNumberOrderingInterface;
import sample.databaseConnection.DatabaseConnectionImpl;
import sample.model.modelsForSingleTables.NameService;
import sample.model.modelsForComplexTables.NameServiceAndOrdering;

import java.sql.SQLException;

public class RequestAvgNumberOrderedGoodController {

    @FXML
    private TableView<NameServiceAndOrdering> tableView;

    @FXML
    private TableColumn<NameServiceAndOrdering,String> colKindService;

    @FXML
    private TableColumn<NameServiceAndOrdering,Float> colAvgNumberOrder;

    @FXML
    private TableColumn<NameServiceAndOrdering,Integer> colOrdered;

    @FXML
    private ComboBox<NameService> boxKindService;

    @FXML
    private Button buttonDoRequest;

    @FXML
    private Button buttonGetKindServices;

    @FXML
    public void initialize(){
        colKindService.setCellValueFactory(new PropertyValueFactory<>("kindService"));
        colAvgNumberOrder.setCellValueFactory(new PropertyValueFactory<>("averageNumberOrdering"));
        colOrdered.setCellValueFactory(new PropertyValueFactory<>("counter"));

        DatabaseConnectionImpl.createStaticConnection();

        getAllKindServices();
        doRequest();

    }

    @FXML
    public void doRequest(){
        buttonDoRequest.setOnAction(event -> {
            NameServiceAndOrdering nameServiceAndOrdering = new NameServiceAndOrdering();

            AverageNumberOrderingInterface ANOI = new RequestAvgNumberOrderingDao();

            try {
                tableView.setItems(ANOI.selectAvgNumberOrderingGood(nameServiceAndOrdering,boxKindService));
            } catch (SQLException e) {
                e.printStackTrace();
            }

        });
    }

    @FXML
    public void getAllKindServices(){
        buttonGetKindServices.setOnAction(event -> {
            ObservableList<NameService> innerList;
            NameServiceInterface NSI = new NameServiceDao();

            try {
                innerList = NSI.getKindService();
                boxKindService.setItems(innerList);

                TextFields.bindAutoCompletion(boxKindService.getEditor(),boxKindService.getItems());
                boxKindService.setConverter(new StringConverter<>() {
                    @Override
                    public String toString(NameService object) {
                        if(object==null)return null;
                        return object.toString();
                    }

                    @Override
                    public NameService fromString(String string) {
                        return boxKindService.getItems().stream()
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
