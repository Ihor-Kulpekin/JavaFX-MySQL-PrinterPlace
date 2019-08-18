package sample.dao.interfacesForDao.insertingInto;

import javafx.collections.ObservableList;
import sample.model.modelsForSingleTables.NameService;
import sample.model.modelsForSingleTables.Service;

import java.sql.SQLException;

public interface NameServiceInterface {
    String addService(NameService nameService);
    String selectDataFromService(Service service);
    ObservableList<NameService> getKindService() throws SQLException,ClassNotFoundException;
}
