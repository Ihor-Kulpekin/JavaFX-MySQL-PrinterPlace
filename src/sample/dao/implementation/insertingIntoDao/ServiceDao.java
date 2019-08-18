package sample.dao.implementation.insertingIntoDao;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import sample.dao.interfacesForDao.insertingInto.ServiceInterface;
import sample.databaseConnection.DatabaseConnectionImpl;
import sample.model.modelsForSingleTables.Service;

import java.sql.*;


/*There is a mistake
* */
public class ServiceDao implements ServiceInterface {

    @Override
    public String addService(Service service) {
        String name = service.getName();
        Connection connection;

        try {
            connection = DatabaseConnectionImpl.createStaticConnection();
            String query = "insert into service(Name) values(?)";

            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1,name);
            int executeUpdate = preparedStatement.executeUpdate();

            if(executeUpdate!=0) {
                return "SUCCESS";
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }


        return "Oops.. Something went wrong there..!";
    }

    @Override
    public ObservableList<Service> getServices() throws SQLException, ClassNotFoundException {
        String selectStatement = "SELECT Name FROM service";
        ResultSet resultSet = DatabaseConnectionImpl.
                createStaticConnection().
                createStatement().
                executeQuery(selectStatement);
        ObservableList<Service> serviceList = FXCollections.observableArrayList();
        while(resultSet.next()){
            Service model = new Service();
            model.setName(resultSet.getString("Name"));
            serviceList.add(model);
        }
        return serviceList;
    }
}
