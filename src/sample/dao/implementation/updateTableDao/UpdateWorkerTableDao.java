package sample.dao.implementation.updateTableDao;

import javafx.scene.control.ComboBox;
import sample.dao.interfacesForDao.updateDataTable.UpdateDataWorkerTableInterface;
import sample.databaseConnection.DatabaseConnectionImpl;
import sample.model.modelsForSingleTables.Worker;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class UpdateWorkerTableDao implements UpdateDataWorkerTableInterface {

    @Override
    public String updateDataWorkerTable(Worker worker, ComboBox<Worker> comboBox) {

        String nameWorker = worker.getFirstNameWorker();
        String lastNameWorker = worker.getLastNameWorker();
        String positionWorker = worker.getPositionWorker();

        int idWorker = comboBox.getValue().getId_Worker();

        Connection connection = null;
        Statement statement = null;

        connection = DatabaseConnectionImpl.createStaticConnection();

        String query = "Update worker set worker.Name_Worker='"+nameWorker+"'," +
                "worker.Last_Name_Worker='"+lastNameWorker+"',worker.Position='"+positionWorker+"'" +
                "where worker.id_worker = '"+idWorker+"'";

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
