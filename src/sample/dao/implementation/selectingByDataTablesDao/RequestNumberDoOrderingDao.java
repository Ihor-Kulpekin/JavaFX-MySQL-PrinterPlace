package sample.dao.implementation.selectingByDataTablesDao;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.ComboBox;
import sample.dao.interfacesForDao.selectingDataTables.RequestNumberDoOrderingByWorker;
import sample.databaseConnection.DatabaseConnectionImpl;
import sample.model.modelsForSingleTables.Worker;
import sample.model.modelsForComplexTables.WorkerAndOrdering;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class RequestNumberDoOrderingDao implements RequestNumberDoOrderingByWorker {

    @Override
    public ObservableList<WorkerAndOrdering> selectDataWorkerWhoIsOrdered
            (WorkerAndOrdering workerAndOrdering, ComboBox<Worker> boxId_Worker)
            throws SQLException {

        ObservableList<WorkerAndOrdering> data = FXCollections.observableArrayList();

        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;

        connection = DatabaseConnectionImpl.createStaticConnection();

        statement = connection.createStatement();

        String id_Client = String.valueOf(boxId_Worker.getValue().getId_Worker());

        String query = "Select  worker.Name_Worker, worker.Last_Name_Worker,\n" +
                "count(worker.id_worker),sum(ordering.General_Price) from\n" +
                "worker Inner join ordering On\n" +
                "worker.id_worker = ordering.Worker_id\n" +
                "where((worker.id_worker) = '"+id_Client+"');";

        resultSet = statement.executeQuery(query);

        while(resultSet.next()){
            workerAndOrdering.setFirstNameWorker(resultSet.getString(1));
            workerAndOrdering.setLastNameWorker(resultSet.getString(2));
            workerAndOrdering.setCounter(resultSet.getInt(3));
            workerAndOrdering.setGeneralSum(resultSet.getFloat(4));
            data.add(workerAndOrdering);
        }

        return data;
    }
}
