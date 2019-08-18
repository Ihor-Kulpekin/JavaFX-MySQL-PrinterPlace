package sample.dao.implementation.deleteDataFromTableDao;

import sample.dao.interfacesForDao.deleteDataFromTable.DeleteClientByCodeInterface;
import sample.databaseConnection.DatabaseConnectionImpl;
import sample.model.modelsForSingleTables.Client;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DeleteDataClientDao implements DeleteClientByCodeInterface {

    @Override
    public String deleteClientByCode(Client client) {

        int codeClient = client.getCodeClient();

        Connection connection = null;
        PreparedStatement preparedStatement = null;

        String query = "Delete from client where client.Code_Client ='"+codeClient+"'";

        connection = DatabaseConnectionImpl.createStaticConnection();

        try {
            preparedStatement = connection.prepareStatement(query);

            int executeUpdate = preparedStatement.executeUpdate();

            if(executeUpdate!=0){
                return "SUCCESS";
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }


        return "There is a mistake";
    }
}
