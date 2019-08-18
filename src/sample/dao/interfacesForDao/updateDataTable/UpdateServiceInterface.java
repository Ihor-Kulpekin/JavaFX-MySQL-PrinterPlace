package sample.dao.interfacesForDao.updateDataTable;

import javafx.scene.control.ComboBox;
import sample.model.modelsForSingleTables.Service;

public interface UpdateServiceInterface {

    String updateDataFromServiceTable(Service service, ComboBox<Service> comboBox);

}
