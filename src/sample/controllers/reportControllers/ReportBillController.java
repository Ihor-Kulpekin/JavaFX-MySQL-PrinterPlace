package sample.controllers.reportControllers;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.util.StringConverter;
import org.controlsfx.control.textfield.TextFields;
import sample.dao.implementation.reportDao.ReportBillDao;
import sample.dao.interfacesForDao.reportInterfaces.ReportBillInterface;
import sample.databaseConnection.DatabaseConnectionImpl;
import sample.model.modelsForSingleTables.NameService;
import sample.model.modelsForSingleTables.Ordering;

import java.sql.SQLException;

public class ReportBillController {

    @FXML
    private ComboBox<Ordering> boxCodeOrdering;

    @FXML
    private Button buttonGetCodeOrdering;

    @FXML
    private Label labelNameClient;

    @FXML
    private Label labelLastNameClient;

    @FXML
    private TableView<NameService> tableView;

    @FXML
    private TableColumn<NameService,String> colGood;

    @FXML
    private Label labelGeneralSum;

    @FXML
    private Label labelNumberGoods;

    @FXML
    private Label labelPrice;

    @FXML
    private Button buttonMakeReport;

    @FXML
    public void initialize(){
        colGood.setCellValueFactory(new PropertyValueFactory<>("kindService"));

        DatabaseConnectionImpl.createStaticConnection();

        clickButtonMakeReport();

    }

    @FXML
    public void clickButtonMakeReport(){
        buttonMakeReport.setOnAction(event -> {
            ReportBillInterface RBI = new ReportBillDao();

            NameService nameService = new NameService();

            RBI.getNameClient(labelNameClient,boxCodeOrdering);
            RBI.getLastNameClient(labelLastNameClient,boxCodeOrdering);
            RBI.priceGood(labelPrice);
            RBI.numberGood(labelNumberGoods,boxCodeOrdering);
            ObservableList<NameService> innerList;

            try {
                innerList = RBI.getGoods(nameService,boxCodeOrdering);
                tableView.setItems(innerList);
            } catch (SQLException e) {
                e.printStackTrace();
            }
            RBI.getSumaOrdering(labelGeneralSum,boxCodeOrdering);

        });
    }


    @FXML
    public void clickButtonGetCodeOrdering(){
        buttonGetCodeOrdering.setOnAction(event -> {
            ObservableList<Ordering> innerList;

            ReportBillInterface RBI = new ReportBillDao();

            try {
                innerList = RBI.getCodeOrdering();

                boxCodeOrdering.setItems(innerList);

                TextFields.bindAutoCompletion(boxCodeOrdering.getEditor(),boxCodeOrdering.getItems());

                boxCodeOrdering.setConverter(new StringConverter<Ordering>() {
                    @Override
                    public String toString(Ordering object) {
                        if(object==null)return null;
                        return String.valueOf(object.getCodeOrdering());
                    }

                    @Override
                    public Ordering fromString(String string) {
                        return boxCodeOrdering.getItems().stream()
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
