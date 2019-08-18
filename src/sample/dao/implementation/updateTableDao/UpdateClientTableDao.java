package sample.dao.implementation.updateTableDao;

import javafx.scene.control.ComboBox;
import sample.dao.interfacesForDao.updateDataTable.UpdateClientTableInterface;
import sample.databaseConnection.DatabaseConnectionImpl;
import sample.model.modelsForSingleTables.Client;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class UpdateClientTableDao implements UpdateClientTableInterface {

    @Override
    public String updateClientTable(Client client, ComboBox<Client> comboBox) {


        String nameClient = client.getFirstNameClient();
        String lastNameClient = client.getLastNameClient();
        int codeClient = client.getCodeClient();
        String mobileNumber = client.getNumberPhone();
        String emailClient = client.getElectronicPostOffice();

        int idClient = comboBox.getValue().getId_Client();


        Connection connection = null;
        Statement statement = null;


        connection = DatabaseConnectionImpl.createStaticConnection();

        String query = "Update client set client.Name_Client = '"+nameClient+"'\n" +
                ",client.Last_Name_Client = '"+lastNameClient+"',client.Code_Client='"+codeClient+"'" +
                ",client.Mobile_Number='"+mobileNumber+"',client.Email = '"+emailClient+"'" +
                "where client.id_Client = '"+idClient+"';";

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
