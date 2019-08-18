package sample.controllers.reportControllers;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import sample.dao.implementation.reportDao.ReportAllTimeDao;
import sample.dao.interfacesForDao.reportInterfaces.ReportAllTimeInterface;
import sample.databaseConnection.DatabaseConnectionImpl;
import sample.model.modelsForSingleTables.NameService;

import java.sql.SQLException;

public class ReportAllTimeController {

    @FXML
    private TableView<NameService> tableView;

    @FXML
    private TableColumn<NameService,String> colKindService;

    @FXML
    private Label labelNumberSoldGoods;

    @FXML
    private Label labelGeneralSum;

    @FXML
    private Label labelServeClients;

    @FXML
    private Button buttonMakeReport;

    @FXML
    public void initialize(){
        colKindService.setCellValueFactory(new PropertyValueFactory<>("kindService"));

        DatabaseConnectionImpl.createStaticConnection();

    }

    @FXML
    public void clickButtonMakeReport(){
        buttonMakeReport.setOnAction(event -> {

            NameService nameService = new NameService();

            ReportAllTimeInterface RATI = new ReportAllTimeDao();

            ObservableList<NameService> innerList;

            try {
                innerList=RATI.getGoods(nameService);
                tableView.setItems(innerList);

                RATI.countAllClients(labelServeClients);
                RATI.numberSoldGoods(labelNumberSoldGoods);
                RATI.sumSoldGoods(labelGeneralSum);

            } catch (SQLException e) {
                e.printStackTrace();
            }


        });
    }


}
