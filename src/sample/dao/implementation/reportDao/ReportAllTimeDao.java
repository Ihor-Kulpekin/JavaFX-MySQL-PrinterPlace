package sample.dao.implementation.reportDao;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Label;
import sample.dao.interfacesForDao.reportInterfaces.ReportAllTimeInterface;
import sample.databaseConnection.DatabaseConnectionImpl;
import sample.model.modelsForSingleTables.NameService;

import java.sql.*;

public class ReportAllTimeDao implements ReportAllTimeInterface {

    @Override
    public String countAllClients(Label label) {

        String selectStatement = "Select sum(ordering.Client_id) from ordering \n" +
                "Group By ordering.Client_id;";
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
    public String numberSoldGoods(Label label) {

        String selectStatement = "Select sum(ordering.Number_Service) from ordering;";

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
    public String sumSoldGoods(Label label) {

        String selectStatement = "Select sum(ordering.General_Price) from ordering;";

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
    public ObservableList<NameService> getGoods(NameService nameService) throws SQLException {
        ObservableList<NameService> data = FXCollections.observableArrayList();

        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;

        connection = DatabaseConnectionImpl.createStaticConnection();

        statement = connection.prepareStatement("Select ordering.Kind_Service from ordering;");

        resultSet = statement.executeQuery();

        while(resultSet.next()){
            nameService.setKindService(resultSet.getString("Kind_Service"));
            data.add(nameService);
        }

        return data;

    }
}
