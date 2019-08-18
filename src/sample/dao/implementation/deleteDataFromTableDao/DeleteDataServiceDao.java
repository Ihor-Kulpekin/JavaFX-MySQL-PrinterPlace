package sample.dao.implementation.deleteDataFromTableDao;

import javafx.scene.control.ComboBox;
import sample.dao.interfacesForDao.deleteDataFromTable.DeleteServiceInterface;
import sample.databaseConnection.DatabaseConnectionImpl;
import sample.model.modelsForSingleTables.Service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DeleteDataServiceDao implements DeleteServiceInterface {

    @Override
    public String deleteName(ComboBox<Service> comboBox) {

        String serviceName = comboBox.getValue().toString();

        Connection connection = null;
        PreparedStatement preparedStatement = null;


        String query = "Delete  from service\n" +
                "where service.Name = '"+serviceName+"';";

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


        return "Oopes";
    }
}
