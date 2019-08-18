package sample.dao.interfacesForDao.updateDataTable;

import javafx.scene.control.ComboBox;
import sample.model.modelsForSingleTables.Client;

public interface UpdateClientTableInterface {

    String updateClientTable(Client client, ComboBox<Client> comboBox);

}
