package sample.dao.implementation.deleteDataFromTableDao;

import sample.dao.interfacesForDao.deleteDataFromTable.DeleteWorkerInterface;
import sample.databaseConnection.DatabaseConnectionImpl;
import sample.model.modelsForSingleTables.Worker;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DeleteWorkerDao implements DeleteWorkerInterface {

    @Override
    public String deleteWorkerById(Worker worker) {

        int worker_Id = worker.getId_Worker();

        Connection connection = null;
        PreparedStatement preparedStatement = null;

        String query = "Delete from worker where worker.id_worker ='"+worker_Id+"'";

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
