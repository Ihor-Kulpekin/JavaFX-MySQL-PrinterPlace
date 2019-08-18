package sample.dao.implementation.insertingIntoDao;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import sample.dao.interfacesForDao.insertingInto.NameServiceInterface;
import sample.databaseConnection.DatabaseConnectionImpl;
import sample.model.modelsForSingleTables.NameService;
import sample.model.modelsForSingleTables.Service;

import java.sql.*;

public class NameServiceDao implements NameServiceInterface {


    @Override
    public String selectDataFromService(Service service) {

        String nameService = service.getName();

        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;

        String nameServiceDB = "";

        connection = DatabaseConnectionImpl.createStaticConnection();
        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery("select Name from service");


            while(resultSet.next()){
                nameServiceDB = resultSet.getString("Name");

                if(nameService.equals(nameServiceDB)){
                    return "SUCCESS";
                }

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return "Invalid data";
    }

    @Override
    public String addService(NameService nameService) {
        String name = nameService.getName();
        String kind_service = nameService.getKindService();
        Float priceService = nameService.getPriceService();
        Connection connection;

        try {
            connection = DatabaseConnectionImpl.createStaticConnection();
            String query = "insert into nameservice(Name_Service, Kind_Service, Price) values(?,?,?)";

            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, name);
            preparedStatement.setString(2,kind_service);
            preparedStatement.setFloat(3,priceService);
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
    public ObservableList<NameService> getKindService() throws SQLException, ClassNotFoundException {

        String selectStatement = "SELECT Kind_Service FROM nameservice";
        ResultSet resultSet = DatabaseConnectionImpl.
                createStaticConnection().
                createStatement().
                executeQuery(selectStatement);
        ObservableList<NameService> serviceList = FXCollections.observableArrayList();
        while (resultSet.next()) {
            NameService model = new NameService();
            model.setKindService(resultSet.getString("Kind_Service"));
            serviceList.add(model);
        }
        return serviceList;
    }



}
