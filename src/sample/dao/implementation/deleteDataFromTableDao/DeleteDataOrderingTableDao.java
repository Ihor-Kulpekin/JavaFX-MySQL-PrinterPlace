package sample.dao.implementation.deleteDataFromTableDao;

import sample.dao.interfacesForDao.deleteDataFromTable.DeleteOrderingInterface;
import sample.databaseConnection.DatabaseConnectionImpl;
import sample.model.modelsForSingleTables.Ordering;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DeleteDataOrderingTableDao implements DeleteOrderingInterface {

    @Override
    public String deleteOrderingById(Ordering ordering) {

        int codeOrdering = ordering.getCodeOrdering();

        Connection connection = null;
        PreparedStatement preparedStatement = null;

        String query = "Delete from ordering where ordering.Code_Ordering ='"+codeOrdering+"'";

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
