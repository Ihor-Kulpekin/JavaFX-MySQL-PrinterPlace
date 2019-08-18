package sample.dao.implementation.updateTableDao;

import javafx.scene.control.ComboBox;
import sample.dao.interfacesForDao.updateDataTable.UpdateOrderingTableInterface;
import sample.databaseConnection.DatabaseConnectionImpl;
import sample.model.modelsForSingleTables.Client;
import sample.model.modelsForSingleTables.NameService;
import sample.model.modelsForSingleTables.Ordering;
import sample.model.modelsForSingleTables.Worker;

import java.sql.Connection;
import java.sql.Date;
import java.sql.SQLException;
import java.sql.Statement;

public class UpdateOrderingTableDao implements UpdateOrderingTableInterface {

    @Override
    public String updateOrderingTable(Ordering ordering, ComboBox<Ordering> comboBox, ComboBox<Client> clientComboBox,
                                      ComboBox<Worker> workerComboBox, ComboBox<NameService> nameServiceComboBox) {

          float generalPrice = ordering.getGeneralSumOrdering();
          int codeOrdering = ordering.getCodeOrdering();
          Date dateOrdering = ordering.getDataOrderingService();
          int numberService = ordering.getNumberService();
          int clientId = clientComboBox.getValue().getId_Client();
          int workerId = workerComboBox.getValue().getId_Worker();
          String kindService = nameServiceComboBox.getValue().getKindService();

          int idOrdering = comboBox.getValue().getId_Ordering();








        String query = "Update ordering set ordering.General_Price ='"+generalPrice+"'," +
                "ordering.Code_Ordering = '"+codeOrdering+"',ordering.Date_Ordering='"+dateOrdering+"'," +
                "ordering.Number_Service = '"+numberService+"',ordering.Client_id ='"+clientId+"'," +
                "ordering.Worker_id='"+workerId+"',ordering.Kind_Service='"+kindService+"'" +
                "where ordering.id_ordering = '"+idOrdering+"'";
//
        Connection connection = null;
        Statement statement = null;

        connection = DatabaseConnectionImpl.createStaticConnection();
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
