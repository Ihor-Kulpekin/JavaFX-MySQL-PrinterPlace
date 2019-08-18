package sample.dao.implementation.selectingByDataTablesDao;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.ComboBox;
import sample.dao.interfacesForDao.selectingDataTables.AverageNumberOrderingInterface;
import sample.databaseConnection.DatabaseConnectionImpl;
import sample.model.modelsForSingleTables.NameService;
import sample.model.modelsForComplexTables.NameServiceAndOrdering;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class RequestAvgNumberOrderingDao implements AverageNumberOrderingInterface {

    @Override
    public ObservableList<NameServiceAndOrdering> selectAvgNumberOrderingGood
            (NameServiceAndOrdering nameServiceAndOrdering, ComboBox<NameService> comboBox) throws SQLException {

        ObservableList<NameServiceAndOrdering> data = FXCollections.observableArrayList();


        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;

        connection = DatabaseConnectionImpl.createStaticConnection();

        statement = connection.createStatement();

        String serviceKind = comboBox.getValue().toString();

        String query = "Select nameservice.Kind_Service, avg(ordering.Number_Service),\n" +
                "count(ordering.id_ordering)\n" +
                "from nameservice Inner Join ordering On \n" +
                "nameservice.Kind_Service = ordering.Kind_Service\n" +
                "where(nameservice.Kind_Service = '"+serviceKind+"');";

        resultSet = statement.executeQuery(query);

        while(resultSet.next()){
            nameServiceAndOrdering.setKindService(resultSet.getString(1));
            nameServiceAndOrdering.setAverageNumberOrdering(resultSet.getFloat(2));
            nameServiceAndOrdering.setCounter(resultSet.getInt(3));
            data.add(nameServiceAndOrdering);
        }


        return data;
    }

}
