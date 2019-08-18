package sample.dao.interfacesForDao.selectingDataTables;

import javafx.collections.ObservableList;
import javafx.scene.control.ComboBox;
import sample.model.modelsForSingleTables.Worker;
import sample.model.modelsForComplexTables.WorkerAndOrdering;

import java.sql.SQLException;

public interface RequestNumberDoOrderingByWorker {

    ObservableList<WorkerAndOrdering> selectDataWorkerWhoIsOrdered
            (WorkerAndOrdering workerAndOrdering, ComboBox<Worker> boxId_Worker )
            throws SQLException;

}
