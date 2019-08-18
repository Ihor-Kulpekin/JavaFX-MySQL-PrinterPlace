package sample.dao.interfacesForDao.updateDataTable;

import javafx.scene.control.ComboBox;
import sample.model.modelsForSingleTables.Client;
import sample.model.modelsForSingleTables.NameService;
import sample.model.modelsForSingleTables.Ordering;
import sample.model.modelsForSingleTables.Worker;

public interface UpdateOrderingTableInterface {

    String updateOrderingTable(Ordering ordering, ComboBox<Ordering> comboBox, ComboBox<Client> clientComboBox,
                               ComboBox<Worker> workerComboBox, ComboBox<NameService> nameServiceComboBox);

}
