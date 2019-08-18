package sample.dao.implementation.selectingByDataTablesDao;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import sample.dao.interfacesForDao.selectingDataTables.RequestTheMostExpensiveOrderInterface;
import sample.databaseConnection.DatabaseConnectionImpl;
import sample.model.modelsForComplexTables.ClientAndOrdering;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class RequestTheMostExpensiveOrderDao implements RequestTheMostExpensiveOrderInterface {

    @Override
    public ObservableList<ClientAndOrdering> selectDataTheMostExpensiveOrder(ClientAndOrdering clientAndOrdering) {

        ObservableList<ClientAndOrdering> data = FXCollections.observableArrayList();


        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;

        connection = DatabaseConnectionImpl.createStaticConnection();

        try {
            statement = connection.createStatement();

            String query = "Select client.Name_Client, client.Last_Name_Client, ordering.Kind_Service,\n" +
                    " max(ordering.General_Price)\n" +
                    " from client\n" +
                    "Inner Join ordering On client.id_Client = ordering.Client_id;";

            resultSet = statement.executeQuery(query);

            while (resultSet.next()){
                clientAndOrdering.setFirstNameClient(resultSet.getString("Name_Client"));
                clientAndOrdering.setLastNameClient(resultSet.getString("Last_Name_Client"));
                clientAndOrdering.setBuyGood(resultSet.getString("Kind_Service"));
                clientAndOrdering.setGeneralSum(resultSet.getFloat(4));
                data.add(clientAndOrdering);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return data;
    }
}
