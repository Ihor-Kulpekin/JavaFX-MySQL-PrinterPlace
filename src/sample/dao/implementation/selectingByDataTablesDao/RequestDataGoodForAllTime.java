package sample.dao.implementation.selectingByDataTablesDao;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.ComboBox;
import sample.dao.interfacesForDao.selectingDataTables.RequestDataGoodBoughtForAllTime;
import sample.databaseConnection.DatabaseConnectionImpl;
import sample.model.modelsForComplexTables.NameServiceAndOrdering;
import sample.model.modelsForSingleTables.NameService;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class RequestDataGoodForAllTime implements RequestDataGoodBoughtForAllTime {

    @Override
    public ObservableList<NameServiceAndOrdering> selectDataGoodForAllTime(NameServiceAndOrdering nameServiceAndOrdering, ComboBox<NameService> comboBox) {

        ObservableList<NameServiceAndOrdering> data = FXCollections.observableArrayList();

        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;

        connection = DatabaseConnectionImpl.createStaticConnection();

        try {
            statement = connection.createStatement();

            String nameService = comboBox.getValue().toString();

            String query = "Select  nameservice.Kind_Service, \n" +
                    "sum(ordering.Number_Service),sum(ordering.General_Price),\n" +
                    "count(ordering.id_ordering) from nameservice Inner Join ordering On\n" +
                    "nameservice.Kind_Service = ordering.Kind_service\n" +
                    "where(nameservice.Kind_Service = '"+nameService+"')\n" +
                    "Group By(nameservice.Kind_Service);";

            resultSet = statement.executeQuery(query);

            while(resultSet.next()){
                nameServiceAndOrdering.setKindService(resultSet.getString(1));
                nameServiceAndOrdering.setSumNumberGoods(resultSet.getInt(2));
                nameServiceAndOrdering.setSumAllOrdering(resultSet.getFloat(3));
                nameServiceAndOrdering.setCounter(resultSet.getInt(4));
                data.add(nameServiceAndOrdering);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }


        return data;
    }
}
