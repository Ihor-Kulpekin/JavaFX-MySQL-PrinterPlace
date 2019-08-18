package sample.dao.interfacesForDao.insertingInto;

import javafx.collections.ObservableList;
import sample.model.modelsForSingleTables.Client;

import java.sql.SQLException;

public interface ClientInterface {
    public String addClient(Client client);

    ObservableList<Client> getClients() throws SQLException,ClassNotFoundException;

}
