package sample.controllers.requestControllers;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import sample.dao.implementation.selectingByDataTablesDao.RequestPopularityGoodDao;
import sample.dao.interfacesForDao.selectingDataTables.PopularityGoodInterface;
import sample.databaseConnection.DatabaseConnectionImpl;
import sample.model.modelsForComplexTables.PopularityNameServiceOrdering;

import java.sql.SQLException;

public class RequestPopularityGoodsController {

    @FXML
    private TableView<PopularityNameServiceOrdering> tableView;

    @FXML
    private TableColumn<PopularityNameServiceOrdering,String>colKindService;

    @FXML
    private TableColumn<PopularityNameServiceOrdering,Integer> colNumberOrdering;

    @FXML
    private Button buttonDoRequest;

    @FXML
    public void initialize(){

        colKindService.setCellValueFactory(new PropertyValueFactory<>("kindService"));
        colNumberOrdering.setCellValueFactory(new PropertyValueFactory<>("numberGood"));

        DatabaseConnectionImpl.createStaticConnection();


        doRequest();

    }

    @FXML
    public void doRequest(){
        buttonDoRequest.setOnAction(event -> {

            PopularityNameServiceOrdering popularityNameServiceOrdering = new PopularityNameServiceOrdering();

            PopularityGoodInterface PGI = new RequestPopularityGoodDao();

            ObservableList<PopularityNameServiceOrdering> innerlist;

            try {
                innerlist = PGI.selectPopularityGoodFromOrder(popularityNameServiceOrdering);
                tableView.setItems(innerlist);
            } catch (SQLException e) {
                e.printStackTrace();
            }


        });
    }


}
