package sample.dao.implementation.selectingByDataTablesDao;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.ComboBox;
import sample.dao.interfacesForDao.selectingDataTables.RequestChoiceSoltGoodInDay;
import sample.databaseConnection.DatabaseConnectionImpl;
import sample.model.modelsForComplexTables.NameServiceAndOrdering;
import sample.model.modelsForSingleTables.Ordering;

import java.sql.*;

public class RequestChoiceGoodSoltInDayDao implements RequestChoiceSoltGoodInDay {

    @Override
    public ObservableList<NameServiceAndOrdering> selectSoltGoodsInChoiceDay
            (NameServiceAndOrdering nameServiceAndOrdering, ComboBox<Ordering> comboBox) throws SQLException {

        ObservableList<NameServiceAndOrdering> data = FXCollections.observableArrayList();


        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;

        connection = DatabaseConnectionImpl.createStaticConnection();

        statement = connection.createStatement();

        Date dateOrdering = comboBox.getValue().getDataOrderingService();



        String query = "Select nameservice.Kind_Service,ordering.Date_Ordering,sum(ordering.Number_Service),\n" +
                "sum(ordering.General_Price) from nameservice Inner Join ordering \n" +
                "On nameservice.Kind_Service = ordering.Kind_Service\n" +
                "where((ordering.Date_Ordering)='"+dateOrdering+"')\n" +
                "Group By(nameservice.Name_Service) desc\n" +
                "Having(count(ordering.Number_Service));";

        resultSet = statement.executeQuery(query);

        while(resultSet.next()){
            nameServiceAndOrdering.setKindService(resultSet.getString(1));
            nameServiceAndOrdering.setDateOrdering(resultSet.getDate(2));
            nameServiceAndOrdering.setSumNumberGoods(resultSet.getInt(3));
            nameServiceAndOrdering.setSumAllOrdering(resultSet.getFloat(4));
            data.add(nameServiceAndOrdering);
        }

        return data;
    }


}
