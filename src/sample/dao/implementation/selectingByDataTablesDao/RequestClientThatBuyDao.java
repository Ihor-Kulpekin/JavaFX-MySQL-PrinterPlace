package sample.dao.implementation.selectingByDataTablesDao;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.ComboBox;
import sample.dao.interfacesForDao.selectingDataTables.RequestClientThatBuyInterface;
import sample.databaseConnection.DatabaseConnectionImpl;
import sample.model.modelsForSingleTables.Client;
import sample.model.modelsForSingleTables.NameService;
import sample.model.modelsForComplexTables.NameServiceAndClient;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class RequestClientThatBuyDao implements RequestClientThatBuyInterface {


    private ObservableList<NameServiceAndClient> data;


    @Override
    public ObservableList<NameServiceAndClient> selectDataClientThatBuy(NameServiceAndClient nameService, ComboBox<NameService> comboBox, ComboBox<Client> clientComboBox) {
        data = FXCollections.observableArrayList();


        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;

        connection = DatabaseConnectionImpl.createStaticConnection();

        try {
            statement = connection.createStatement();

            String choiceKindService = comboBox.getValue().toString();
            String choiceId_Client = clientComboBox.getValue().toString();

            String query = "Select client.Name_Client,client.Last_Name_Client, nameservice.Kind_Service,count(ordering.id_ordering)," +
                    "sum(ordering.General_Price) from \n" +
                    "nameservice Inner Join(client Inner Join ordering \n" +
                    "On client.id_Client = ordering.Client_id) On\n" +
                    "nameservice.Kind_Service = ordering.Kind_Service\n" +
                    "where((nameservice.Kind_Service)='"+choiceKindService+"'  AND client.id_Client='"+choiceId_Client+"');";

            resultSet = statement.executeQuery(query);

            while (resultSet.next()){
                nameService.setFirstName(resultSet.getString("Name_Client"));
                nameService.setLastName(resultSet.getString("Last_Name_Client"));
                nameService.setServiceName(resultSet.getString("Kind_Service"));
                nameService.setCounter(resultSet.getInt(4));
                nameService.setGenetalSum(resultSet.getFloat(5));
                data.add(nameService);
            }


        } catch (SQLException e) {
            e.printStackTrace();
        }


        return data;
    }
}
