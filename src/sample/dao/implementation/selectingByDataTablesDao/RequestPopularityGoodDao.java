package sample.dao.implementation.selectingByDataTablesDao;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import sample.dao.interfacesForDao.selectingDataTables.PopularityGoodInterface;
import sample.databaseConnection.DatabaseConnectionImpl;
import sample.model.modelsForComplexTables.PopularityNameServiceOrdering;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class RequestPopularityGoodDao implements PopularityGoodInterface {

    @Override
    public ObservableList<PopularityNameServiceOrdering> selectPopularityGoodFromOrder
            (PopularityNameServiceOrdering popularityGood) throws SQLException {

        ObservableList<PopularityNameServiceOrdering> data = FXCollections.observableArrayList();

        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;

        connection = DatabaseConnectionImpl.createStaticConnection();

        statement = connection.createStatement();

        resultSet = statement.executeQuery("Select nameservice.Kind_Service,sum(ordering.Number_Service) from\n" +
                "nameservice  Inner Join ordering \n" +
                "On  ordering.Kind_Service=nameservice.Kind_Service\n" +
                "Group By(nameservice.Kind_Service)desc\n" +
                "Having(sum(ordering.Number_Service));");

        while(resultSet.next()){
            popularityGood.setKindService(resultSet.getString(1));
            popularityGood.setNumberGood(resultSet.getInt(2));

            data.add(popularityGood);
        }

        return data;
    }
}
