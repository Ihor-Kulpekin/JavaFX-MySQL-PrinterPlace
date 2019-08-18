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
import sample.dao.implementation.selectingByDataTablesDao.RequestDataGoodForAllTime;
import sample.dao.interfacesForDao.insertingInto.NameServiceInterface;
import sample.dao.interfacesForDao.selectingDataTables.RequestDataGoodBoughtForAllTime;
import sample.databaseConnection.DatabaseConnectionImpl;
import sample.model.modelsForComplexTables.NameServiceAndOrdering;
import sample.model.modelsForSingleTables.NameService;

import java.sql.SQLException;

public class RequestDataGoodForAllTimeController {

    @FXML
    TableView<NameServiceAndOrdering> tableView;

    @FXML
    TableColumn<NameServiceAndOrdering,String> colNameService;

    @FXML
    TableColumn<NameServiceAndOrdering,Integer> colOrderedNumber;

    @FXML
    TableColumn<NameServiceAndOrdering,Float> colGeneralSum;

    @FXML
    TableColumn<NameServiceAndOrdering,Integer> colNumberOrdering;

    @FXML
    Button buttonDoRequest;

    @FXML
    Button buttonGetNameService;

    @FXML
    ComboBox<NameService> boxNameService;

    @FXML
    public void initialize(){
        colNameService.setCellValueFactory(new PropertyValueFactory<>("kindService"));
        colOrderedNumber.setCellValueFactory(new PropertyValueFactory<>("sumNumberGoods"));
        colGeneralSum.setCellValueFactory(new PropertyValueFactory<>("sumAllOrdering"));
        colNumberOrdering.setCellValueFactory(new PropertyValueFactory<>("counter"));

        DatabaseConnectionImpl.createStaticConnection();

    }

    @FXML
    public void clickButtonDoRequest(){
        buttonDoRequest.setOnAction(event->{

            NameServiceAndOrdering nameServiceAndOrdering = new NameServiceAndOrdering();

            RequestDataGoodBoughtForAllTime request = new RequestDataGoodForAllTime();

            tableView.setItems(request.selectDataGoodForAllTime(nameServiceAndOrdering,boxNameService));
        });
    }

    @FXML
    public void clickButtonGetNameService(){
        buttonGetNameService.setOnAction(event->{

            ObservableList<NameService> innerList;


            NameServiceInterface NSI = new NameServiceDao();

            try {
                innerList = NSI.getKindService();

                boxNameService.setItems(innerList);

                TextFields.bindAutoCompletion(boxNameService.getEditor(),boxNameService.getItems());

                boxNameService.setConverter(new StringConverter<NameService>() {
                    @Override
                    public String toString(NameService object) {
                        if(object==null)return null;
                        return object.toString();
                    }

                    @Override
                    public NameService fromString(String string) {
                        return boxNameService.getItems().stream()
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
