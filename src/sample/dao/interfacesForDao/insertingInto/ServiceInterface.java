package sample.dao.interfacesForDao.insertingInto;

import javafx.collections.ObservableList;
import sample.model.modelsForSingleTables.Service;

import java.sql.SQLException;

public interface ServiceInterface {

    public String addService(Service service);

    ObservableList<Service> getServices() throws SQLException,ClassNotFoundException;

}
