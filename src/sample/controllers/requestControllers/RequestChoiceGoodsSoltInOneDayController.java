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
import sample.dao.implementation.insertingIntoDao.OrderingDao;
import sample.dao.implementation.selectingByDataTablesDao.RequestChoiceGoodSoltInDayDao;
import sample.dao.interfacesForDao.insertingInto.OrderingInterface;
import sample.dao.interfacesForDao.selectingDataTables.RequestChoiceSoltGoodInDay;
import sample.databaseConnection.DatabaseConnectionImpl;
import sample.model.modelsForComplexTables.NameServiceAndOrdering;
import sample.model.modelsForSingleTables.Ordering;

import java.sql.SQLException;

public class RequestChoiceGoodsSoltInOneDayController {

    @FXML
    private TableView<NameServiceAndOrdering> tableView;

    @FXML
    private TableColumn<NameServiceAndOrdering,String> colKindService;

    @FXML
    private TableColumn<NameServiceAndOrdering,String> colDateOrdering;

    @FXML
    private TableColumn<NameServiceAndOrdering,Integer> colNumberOrdering;

    @FXML
    private TableColumn<NameServiceAndOrdering,Float> colSumOrdering;

    @FXML
    private ComboBox<Ordering> boxDateOrdering;

    @FXML
    private Button getDateOrdering;

    @FXML
    private Button doRequest;

    @FXML
    public void initialize(){
        colKindService.setCellValueFactory(new PropertyValueFactory<>("kindService"));
        colDateOrdering.setCellValueFactory(new PropertyValueFactory<>("dateOrdering"));
        colNumberOrdering.setCellValueFactory(new PropertyValueFactory<>("sumNumberGoods"));
        colSumOrdering.setCellValueFactory(new PropertyValueFactory<>("sumAllOrdering"));

        DatabaseConnectionImpl.createStaticConnection();

        getDateOrdering();
        doRequest();

    }

    @FXML
    public void doRequest(){
        doRequest.setOnAction(event -> {
            NameServiceAndOrdering nameServiceAndOrdering = new NameServiceAndOrdering();

            RequestChoiceSoltGoodInDay choice = new RequestChoiceGoodSoltInDayDao();


            try {
                tableView.setItems(choice.selectSoltGoodsInChoiceDay(nameServiceAndOrdering,boxDateOrdering));
            } catch (SQLException e) {
                e.printStackTrace();
            }
        });
    }

    @FXML
    public void getDateOrdering(){
        getDateOrdering.setOnAction(event->{
            ObservableList<Ordering> innerList;
            OrderingInterface OI = new OrderingDao();

            try {
                innerList = OI.selectDataOrderingForOrdering();
                boxDateOrdering.setItems(innerList);
                TextFields.bindAutoCompletion(boxDateOrdering.getEditor(),boxDateOrdering.getItems());
                boxDateOrdering.setConverter(new StringConverter<>() {
                    @Override
                    public String toString(Ordering object) {
                        if(object==null)return null;
                        return object.getDataOrderingService().toString();
                    }

                    @Override
                    public Ordering fromString(String string) {
                        return boxDateOrdering.getItems().stream()
                                .filter(ap->ap.toString().equals(string))
                                .findFirst()
                                .orElse(null);
                    }
                });
            } catch (SQLException e) {
                e.printStackTrace();
            }
        });
    }


}
