package sample.dao.implementation.reportDao;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import sample.dao.interfacesForDao.reportInterfaces.ReportBillInterface;
import sample.databaseConnection.DatabaseConnectionImpl;
import sample.model.modelsForSingleTables.NameService;
import sample.model.modelsForSingleTables.Ordering;

import java.sql.*;

public class ReportBillDao  implements ReportBillInterface {

    @Override
    public String numberGood(Label label,ComboBox<Ordering> clientComboBox) {

        int id = clientComboBox.getValue().getClientId();

        String selectStatement = "Select ordering.Number_Service from ordering " +
                "where ordering.Client_id= '"+id+"'";

        ResultSet resultSet = null;

        try {
            resultSet =DatabaseConnectionImpl
                    .createStaticConnection()
                    .createStatement()
                    .executeQuery(selectStatement);

            while (resultSet.next()){
                label.setText(resultSet.getString(1));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return label.getText();
    }

    @Override
    public String priceGood(Label label) {

        String selectStatement = "Select nameservice.Price from nameservice " +
                "where nameservice.Kind_Service = 'Bukletu A4'";


        ResultSet resultSet = null;

        try {
            resultSet =DatabaseConnectionImpl
                    .createStaticConnection()
                    .createStatement()
                    .executeQuery(selectStatement);

            while (resultSet.next()){
                label.setText(resultSet.getString(1));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return label.getText();
    }

    @Override
    public ObservableList<Ordering> getCodeOrdering() throws SQLException {

        String selectStatement = "SELECT Code_Ordering FROM ordering";
        ResultSet resultSet = DatabaseConnectionImpl.
                createStaticConnection().
                createStatement().
                executeQuery(selectStatement);
        ObservableList<Ordering> serviceList = FXCollections.observableArrayList();
        while(resultSet.next()){
            Ordering model = new Ordering();

            model.setCodeOrdering(resultSet.getInt(1));

            serviceList.add(model);
        }
        return serviceList;

    }

    @Override
    public String getNameClient(Label label, ComboBox<Ordering> clientComboBox) {


        int codeOrdering = clientComboBox.getValue().getCodeOrdering();

        String selectStatement = "Select client.Name_Client from \n" +
                "client Inner Join ordering On\n" +
                "client.id_Client = ordering.Client_id\n" +
                "where ordering.Code_Ordering = '"+codeOrdering+"';\n";
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
    public String getLastNameClient(Label label,ComboBox<Ordering> clientComboBox) {

        int codeOrdering = clientComboBox.getValue().getCodeOrdering();

        String selectStatement = "Select client.Last_Name_Client from \n" +
                "client Inner Join ordering On\n" +
                "client.id_Client = ordering.Client_id\n" +
                "where ordering.Code_Ordering = '"+codeOrdering+"';\n";
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
    public ObservableList<NameService> getGoods(NameService nameService, ComboBox<Ordering> clientComboBox) throws SQLException {

        ObservableList<NameService> data = FXCollections.observableArrayList();

        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;

        int codeORdering = clientComboBox.getValue().getCodeOrdering();

        connection = DatabaseConnectionImpl.createStaticConnection();

        statement = connection.createStatement();

        resultSet = statement.executeQuery("Select nameservice.Kind_Service from \n" +
                "nameservice Inner Join ordering On\n" +
                "nameservice.Kind_Service = ordering.Kind_Service\n" +
                "where ordering.Code_Ordering = '"+codeORdering+"';");

        while(resultSet.next()){
            nameService.setKindService(resultSet.getString(1));
            data.add(nameService);
        }

        return data;

    }

    @Override
    public String getSumaOrdering(Label label,ComboBox<Ordering> clientComboBox) {

        int codeOrdering = clientComboBox.getValue().getCodeOrdering();

        String selectStatement = "Select sum(ordering.General_Price) from \n" +
                "ordering \n" +
                "where ordering.Code_Ordering = '"+codeOrdering+"';";
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
