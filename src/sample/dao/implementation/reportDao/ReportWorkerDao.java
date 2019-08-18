package sample.dao.implementation.reportDao;

import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import sample.dao.interfacesForDao.reportInterfaces.ReportWorkerInterface;
import sample.databaseConnection.DatabaseConnectionImpl;
import sample.model.modelsForSingleTables.Worker;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ReportWorkerDao implements ReportWorkerInterface {



    @Override
    public String getNameWorker(Label label, ComboBox<Worker> workerComboBox) {

        int workerId = workerComboBox.getValue().getId_Worker();

        String selectStatement = "Select worker.Name_Worker from worker \n" +
                "Inner Join ordering On worker.id_worker = ordering.Worker_id \n" +
                "where ordering.Worker_id = '"+workerId+"';";
        ResultSet resultSet = null;
        try {
            resultSet = DatabaseConnectionImpl.
                    createStaticConnection().
                    createStatement().
                    executeQuery(selectStatement);

            while(resultSet.next()){
                label.setText(resultSet.getString(1));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return label.getText();


    }

    @Override
    public String getLastNameWorker(Label label, ComboBox<Worker> workerComboBox) {

        int workerId = workerComboBox.getValue().getId_Worker();

        String selectStatement = "Select worker.Last_Name_Worker from worker \n" +
                "Inner Join ordering On worker.id_worker = ordering.Worker_id \n" +
                "where ordering.Worker_id = '"+workerId+"';";
        ResultSet resultSet = null;
        try {
            resultSet = DatabaseConnectionImpl.
                    createStaticConnection().
                    createStatement().
                    executeQuery(selectStatement);

            while(resultSet.next()){
                label.setText(resultSet.getString(1));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return label.getText();
    }

    @Override
    public String getGeneralSum(Label label, ComboBox<Worker> workerComboBox) {

        int workerId = workerComboBox.getValue().getId_Worker();

        String selectStatement = "Select sum(ordering.General_Price) from worker \n" +
                "Inner Join ordering On worker.id_worker = ordering.Worker_id \n" +
                "where ordering.Worker_id = '"+workerId+"';";
        ResultSet resultSet = null;
        try {
            resultSet = DatabaseConnectionImpl.
                    createStaticConnection().
                    createStatement().
                    executeQuery(selectStatement);

            while(resultSet.next()){
                label.setText(resultSet.getString(1));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return label.getText();
    }



    @Override
    public String getCounterClients(Label label, ComboBox<Worker> workerComboBox) {
        int workerId = workerComboBox.getValue().getId_Worker();

        String selectStatement = "Select count(ordering.Client_id) from client\n" +
                "    Inner Join ordering On client.id_Client = ordering.Client_id\n" +
                "    where ordering.Worker_id = '"+workerId+"';";
        ResultSet resultSet = null;
        try {
            resultSet = DatabaseConnectionImpl.
                    createStaticConnection().
                    createStatement().
                    executeQuery(selectStatement);

            while(resultSet.next()){
                label.setText(resultSet.getString(1));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return label.getText();
    }
}
