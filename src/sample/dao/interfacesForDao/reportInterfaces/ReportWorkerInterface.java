package sample.dao.interfacesForDao.reportInterfaces;

import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import sample.model.modelsForSingleTables.Worker;

public interface ReportWorkerInterface {

    String getNameWorker(Label label, ComboBox<Worker> workerComboBox);
    String getLastNameWorker(Label label,ComboBox<Worker> workerComboBox);
    String getGeneralSum(Label label,ComboBox<Worker> workerComboBox);
    String getCounterClients(Label label,ComboBox<Worker> workerComboBox);

}
