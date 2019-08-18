package sample.controllers.requestControllers;

import com.mysql.cj.xdevapi.Client;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import sample.dao.implementation.selectingByDataTablesDao.RequestTheMostExpensiveOrderDao;
import sample.dao.interfacesForDao.selectingDataTables.RequestTheMostExpensiveOrderInterface;
import sample.databaseConnection.DatabaseConnectionImpl;
import sample.model.modelsForComplexTables.ClientAndOrdering;

public class RequestTheMostExpensiveOrderController {

    @FXML
    private TableView<ClientAndOrdering> tableTheMostExpensiveOrder;

    @FXML
    private TableColumn<ClientAndOrdering,String> firstNameClient;

    @FXML
    private TableColumn<ClientAndOrdering,String> lastNameClient;

    @FXML
    private TableColumn<ClientAndOrdering,String> boughtGood;

    @FXML
    private TableColumn<Client,Float> generalSum;

    @FXML
    private Button buttonDoRequest;

    @FXML
    public void initialize(){
        firstNameClient.setCellValueFactory(new PropertyValueFactory<>("firstNameClient"));
        lastNameClient.setCellValueFactory(new PropertyValueFactory<>("lastNameClient"));
        boughtGood.setCellValueFactory(new PropertyValueFactory<>("buyGood"));
        generalSum.setCellValueFactory(new PropertyValueFactory<>("generalSum"));

        DatabaseConnectionImpl.createStaticConnection();

        doRequest();
    }


    @FXML
    public void doRequest(){
        buttonDoRequest.setOnAction(event -> {
            ClientAndOrdering clientAndOrdering = new ClientAndOrdering();

            RequestTheMostExpensiveOrderInterface request = new RequestTheMostExpensiveOrderDao();

            tableTheMostExpensiveOrder.setItems(request.selectDataTheMostExpensiveOrder(clientAndOrdering));

        });
    }

}
