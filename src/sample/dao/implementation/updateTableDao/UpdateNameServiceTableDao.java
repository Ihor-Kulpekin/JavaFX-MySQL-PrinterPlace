package sample.dao.implementation.updateTableDao;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.ComboBox;
import sample.dao.interfacesForDao.updateDataTable.UpdateNameServiceInterface;
import sample.databaseConnection.DatabaseConnectionImpl;
import sample.model.modelsForSingleTables.NameService;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class UpdateNameServiceTableDao implements UpdateNameServiceInterface {

    @Override
    public ObservableList<NameService> getNameServiceId() {

        ObservableList<NameService> innerList = FXCollections.observableArrayList();
        String selectStatemet = "Select id_name_Service from nameservice";

        ResultSet resultSet = null;
        try {
            resultSet = DatabaseConnectionImpl.createStaticConnection().createStatement()
                    .executeQuery(selectStatemet);


            while(resultSet.next()){
                NameService nameService = new NameService();
                nameService.setId_NameService(resultSet.getInt(1));
                innerList.add(nameService);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return innerList;
    }

    @Override
    public String updateData(NameService nameService, ComboBox<NameService> comboBox) {
        int id = comboBox.getValue().getId_NameService();
        String kindService = nameService.getKindService();
        float price = nameService.getPriceService();

        String query = "Update nameservice set " +
                "nameservice.Kind_Service='"+kindService+"', nameservice.Price='"+price+"'" +
                "where nameservice.id_Name_Service = '"+id+"';";

        Statement statement = null;
        Connection connection = null;

        connection = DatabaseConnectionImpl.createStaticConnection();
        try {
            statement = connection.createStatement();

            int executeUpdate= statement.executeUpdate(query);

            if(executeUpdate!=0){
                return "SUCCESS";
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return "Ooops, We have a problem";
        }

    }

