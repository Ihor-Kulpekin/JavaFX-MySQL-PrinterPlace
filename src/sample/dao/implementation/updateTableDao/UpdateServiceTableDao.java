package sample.dao.implementation.updateTableDao;

import javafx.scene.control.ComboBox;
import sample.dao.interfacesForDao.updateDataTable.UpdateServiceInterface;
import sample.databaseConnection.DatabaseConnectionImpl;
import sample.model.modelsForSingleTables.Service;

import java.sql.*;

public class UpdateServiceTableDao implements UpdateServiceInterface {

    @Override
    public String updateDataFromServiceTable(Service service, ComboBox<Service> comboBox) {

        String nameService = service.getName();

        String choiceService = comboBox.getValue().toString();

        Connection connection = null;
        Statement statement = null;


        connection = DatabaseConnectionImpl.createStaticConnection();

        String query = "Update service set service.Name = '"+nameService+"'" +
                "where service.Name = '"+choiceService+"';";

        try {
            statement = connection.createStatement();

            int executeUpdate = statement.executeUpdate(query);

            if(executeUpdate!=0){
                return "SUCCESS";
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return "Oopps we have a problem";
    }
}
