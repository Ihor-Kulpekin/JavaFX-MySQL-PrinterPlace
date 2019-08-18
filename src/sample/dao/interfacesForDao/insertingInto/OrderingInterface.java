package sample.dao.interfacesForDao.insertingInto;

import javafx.collections.ObservableList;
import sample.model.modelsForSingleTables.Client;
import sample.model.modelsForSingleTables.NameService;
import sample.model.modelsForSingleTables.Ordering;
import sample.model.modelsForSingleTables.Worker;

import java.sql.SQLException;

public interface OrderingInterface {

     String addOrdering(Ordering ordering);
     String selectDataFromNameService(NameService nameService);
     String selectDataFromClient(Client client);
     String selectDataFromWorker(Worker worker);
     ObservableList<Ordering> selectDataOrderingForOrdering() throws SQLException;
     ObservableList<Ordering> getOrderingById();


}
