package sample.dao.interfacesForDao.insertingInto;

import javafx.collections.ObservableList;
import sample.model.modelsForSingleTables.Worker;

import java.sql.SQLException;

public interface WorkerInterface {

    public String addWorker(Worker worker);

    ObservableList<Worker> getWorker() throws SQLException,ClassNotFoundException;

}
