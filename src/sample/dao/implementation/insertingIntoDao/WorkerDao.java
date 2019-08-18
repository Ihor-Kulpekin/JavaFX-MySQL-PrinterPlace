package sample.dao.implementation.insertingIntoDao;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import sample.dao.interfacesForDao.insertingInto.WorkerInterface;
import sample.databaseConnection.DatabaseConnectionImpl;
import sample.model.modelsForSingleTables.Worker;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class WorkerDao implements WorkerInterface {

    @Override
    public String addWorker(Worker worker) {
        String firstName = worker.getFirstNameWorker();
        String lastName = worker.getLastNameWorker();
        String position = worker.getPositionWorker();

        Connection connection;


        try {
            connection = DatabaseConnectionImpl.createStaticConnection();
            String query = "insert into worker(Name_Worker,Last_Name_Worker,Position) values(?,?,?)";

            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, firstName);
            preparedStatement.setString(2, lastName);
            preparedStatement.setString(3, position);
            preparedStatement.executeUpdate();
            int executeUpdate = preparedStatement.executeUpdate();

            if (executeUpdate != 0) {
                return "SUCCESS";
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }


        return "Oops.. Something went wrong there..!";

    }

    @Override
    public ObservableList<Worker> getWorker() throws SQLException, ClassNotFoundException {

        String selectStatement = "SELECT id_worker FROM worker";
        ResultSet resultSet = DatabaseConnectionImpl.
                createStaticConnection().
                createStatement().
                executeQuery(selectStatement);
        ObservableList<Worker> serviceList = FXCollections.observableArrayList();
        while (resultSet.next()) {
            Worker model = new Worker();
            model.setId_Worker(resultSet.getInt("id_worker"));
            serviceList.add(model);
        }
        return serviceList;

    }

}