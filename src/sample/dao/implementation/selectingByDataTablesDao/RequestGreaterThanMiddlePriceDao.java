package sample.dao.implementation.selectingByDataTablesDao;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.ComboBox;
import sample.dao.interfacesForDao.selectingDataTables.RequestGreaterThanMiddlePriceInterface;
import sample.databaseConnection.DatabaseConnectionImpl;
import sample.model.modelsForComplexTables.ServiceAndNameService;
import sample.model.modelsForSingleTables.Service;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class RequestGreaterThanMiddlePriceDao implements RequestGreaterThanMiddlePriceInterface {

    @Override
    public ObservableList<ServiceAndNameService> selectGreaterThanMiddlePrice(ServiceAndNameService serviceAndNameService, ComboBox<Service> comboBox) {

        ObservableList<ServiceAndNameService> data = FXCollections.observableArrayList();

        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;

        String nameService = comboBox.getValue().getName();

        connection = DatabaseConnectionImpl.createStaticConnection();

        try {
            statement = connection.createStatement();

            String query = "Select nameservice.Kind_Service, service.Name, nameservice.Price \n" +
                    "from service Inner Join nameservice On  nameservice.Name_Service = service.Name\n" +
                    "where((nameservice.Price)<(select avg(nameservice.Price) from nameservice)) \n" +
                    "Group By (nameservice.Price) \n" +
                    "Having((service.Name)='"+nameService+"');";

            resultSet = statement.executeQuery(query);

            while (resultSet.next()){

                serviceAndNameService.setNamesService(resultSet.getString("Kind_Service"));
                serviceAndNameService.setKindNameService(resultSet.getString("Name"));
                serviceAndNameService.setPriceService(resultSet.getFloat("Price"));

                data.add(serviceAndNameService);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return data;
    }

    @Override
    public ObservableList<ServiceAndNameService> selectAverageValueByPrice(ServiceAndNameService serviceAndNameService, ComboBox<Service> comboBox) {


        ObservableList<ServiceAndNameService> data = FXCollections.observableArrayList();
        String nameService = comboBox.getValue().getName();


        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;


        connection = DatabaseConnectionImpl.createStaticConnection();

        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery("select service.Name,avg(nameservice.Price)\n" +
                    "from service Inner Join nameservice On\n" +
                    "service.Name = nameservice.Name_Service\n" +
                    "where((service.Name)='"+nameService+"');");


            while (resultSet.next()){
                serviceAndNameService.setNamesService(resultSet.getString(1));
                serviceAndNameService.setAveragePriceGood(resultSet.getFloat(2));
                data.add(serviceAndNameService);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }


        return data;

    }
}
