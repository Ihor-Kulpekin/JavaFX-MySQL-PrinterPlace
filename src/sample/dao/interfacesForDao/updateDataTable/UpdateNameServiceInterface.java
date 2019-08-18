package sample.dao.interfacesForDao.updateDataTable;

import javafx.collections.ObservableList;
import javafx.scene.control.ComboBox;
import sample.model.modelsForSingleTables.NameService;

public interface UpdateNameServiceInterface {

    String updateData(NameService nameService, ComboBox<NameService> comboBox);

    ObservableList<NameService> getNameServiceId();

}
