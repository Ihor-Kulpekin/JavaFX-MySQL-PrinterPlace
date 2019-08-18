package sample.dao.implementation.deleteDataFromTableDao;

import javafx.scene.control.ComboBox;
import sample.dao.interfacesForDao.deleteDataFromTable.DeleteNameServiceInterface;
import sample.databaseConnection.DatabaseConnectionImpl;
import sample.model.modelsForSingleTables.NameService;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DeleteDataNameServiceDao implements DeleteNameServiceInterface {

    @Override
    public String deleteDataNameServiceTable(ComboBox<NameService> comboBox) {
        String kindService = comboBox.getValue().toString();

        Connection connection = null;
        PreparedStatement preparedStatement = null;

        String query = "Delete from nameservice where nameservice.Kind_Service ='"+kindService+"'";

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
