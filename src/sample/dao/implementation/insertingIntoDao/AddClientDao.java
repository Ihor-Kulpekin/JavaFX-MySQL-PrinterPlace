package sample.dao.implementation.insertingIntoDao;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import sample.dao.interfacesForDao.insertingInto.ClientInterface;
import sample.databaseConnection.DatabaseConnectionImpl;
import sample.model.modelsForSingleTables.Client;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AddClientDao implements ClientInterface {

    @Override
    public String addClient(Client client) {

        String firstNameClient = client.getFirstNameClient();
        String lastNameClient = client.getLastNameClient();
        int codeClient = client.getCodeClient();
        String emailClient = client.getElectronicPostOffice();
        String phoneNumberClient = client.getNumberPhone();



        Connection connection = DatabaseConnectionImpl.createStaticConnection();

        String query = "Insert into client(Name_Client,Last_Name_Client,Code_Client,Mobile_Number,email)" +
                "Values(?,?,?,?,?)";

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1,firstNameClient);
            preparedStatement.setString(2,lastNameClient);
            preparedStatement.setInt(3,codeClient);
            preparedStatement.setString(4,emailClient);
            preparedStatement.setString(5,phoneNumberClient);

            int executeUpdate = preparedStatement.executeUpdate();

            if (executeUpdate != 0) {
                return "SUCCESS";
            }



        } catch (SQLException e) {
            try {
                connection.close();
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
        }


        return "Oops.. Something went wrong there..!";
    }

    @Override
    public ObservableList<Client> getClients() throws SQLException, ClassNotFoundException {

        String selectStatement = "SELECT id_Client FROM client";
        ResultSet resultSet = DatabaseConnectionImpl.
                createStaticConnection().
                createStatement().
                executeQuery(selectStatement);
        ObservableList<Client> serviceList = FXCollections.observableArrayList();
        while(resultSet.next()){
            Client model = new Client();
            model.setId_Client(resultSet.getInt("id_Client"));
            serviceList.add(model);
        }
        return serviceList;

    }
}
