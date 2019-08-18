package sample.dao.implementation.insertingIntoDao;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import sample.dao.interfacesForDao.insertingInto.OrderingInterface;
import sample.databaseConnection.DatabaseConnectionImpl;
import sample.model.modelsForSingleTables.Client;
import sample.model.modelsForSingleTables.NameService;
import sample.model.modelsForSingleTables.Ordering;
import sample.model.modelsForSingleTables.Worker;

import java.sql.*;

public class OrderingDao implements OrderingInterface {

    @Override
    public String addOrdering(Ordering ordering) {
        int generalPrice = ordering.getGeneralSumOrdering();
        int codeOrdering = ordering.getCodeOrdering();
        Date date = ordering.getDataOrderingService();
        int number = ordering.getNumberService();
        int id_Client = ordering.getClientId();
        int id_Worker = ordering.getWorkerId();
        String nameKindService = ordering.getNameKindService();


        Connection connection;

        try {
            connection = DatabaseConnectionImpl.createStaticConnection();
            String query = "insert into ordering(General_Price, Code_Ordering,Date_Ordering,Number_Service,Client_id," +
                    "Worker_id,Kind_Service) values(?,?,?,?,?,?,?)";

            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, generalPrice);
            preparedStatement.setInt(2,codeOrdering);
            preparedStatement.setDate(3,date);
            preparedStatement.setInt(4,number);
            preparedStatement.setInt(5,id_Client);
            preparedStatement.setInt(6,id_Worker);
            preparedStatement.setString(7,nameKindService);
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
    public String selectDataFromNameService(NameService nameService) {


        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;

        String nameServiceDB = "";

        connection = DatabaseConnectionImpl.createStaticConnection();

        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery("select Kind_Service from nameservice");


            while (resultSet.next()){

                nameServiceDB = resultSet.getString("Kind_Service");

                if(nameService.equals(nameServiceDB)){
                    return "SUCCESS";
                }

            }

        } catch (SQLException e) {
            e.printStackTrace();
        }


        return "ERROR!!!";
    }

    @Override
    public String selectDataFromClient(Client client) {
        String id_Client = String.valueOf(client.getId_Client());

        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;

        String nameServiceDB = "";

        connection = DatabaseConnectionImpl.createStaticConnection();

        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery("select id_Client from client");


            while (resultSet.next()){

                nameServiceDB = resultSet.getString("id_Client");

                if(id_Client.equals(nameServiceDB)){
                    return "SUCCESS";
                }

            }

        } catch (SQLException e) {
            e.printStackTrace();
        }


        return "ERROR!!!";
    }

    @Override
    public String selectDataFromWorker(Worker worker) {
        String id_Worker = String.valueOf(worker.getId_Worker());

        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;

        String nameServiceDB = "";

        connection = DatabaseConnectionImpl.createStaticConnection();

        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery("select id_Worker from worker");


            while (resultSet.next()){

                nameServiceDB = resultSet.getString("id_Client");

                if(id_Worker.equals(nameServiceDB)){
                    return "SUCCESS";
                }

            }

        } catch (SQLException e) {
            e.printStackTrace();
        }


        return "ERROR!!!";
    }



    @Override
    public ObservableList<Ordering> selectDataOrderingForOrdering() throws SQLException {


        String selectStatemet = "Select Date_Ordering from ordering";

        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;


        connection = DatabaseConnectionImpl.createStaticConnection();

        statement = connection.createStatement();

        resultSet = statement.executeQuery(selectStatemet);

        ObservableList<Ordering> serviceList = FXCollections.observableArrayList();
        while(resultSet.next()){
            Ordering model = new Ordering();

            model.setDataOrderingService(resultSet.getDate(1));
            serviceList.add(model);
        }

        return serviceList;
    }

    @Override
    public ObservableList<Ordering> getOrderingById() {

        ObservableList<Ordering> serviceList = FXCollections.observableArrayList();

        String selectStatement = "SELECT id_ordering FROM ordering";
        ResultSet resultSet = null;
        try {
            resultSet = DatabaseConnectionImpl.
                    createStaticConnection().
                    createStatement().
                    executeQuery(selectStatement);

            while(resultSet.next()){
                Ordering model = new Ordering();
                model.setId_Ordering(resultSet.getInt(1));
                serviceList.add(model);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return serviceList;
    }
}
