package sample.dao.interfacesForDao.selectingDataTables;

import javafx.collections.ObservableList;
import javafx.scene.control.ComboBox;
import sample.model.modelsForSingleTables.Client;
import sample.model.modelsForSingleTables.NameService;
import sample.model.modelsForComplexTables.NameServiceAndClient;

public interface RequestClientThatBuyInterface {

    ObservableList<NameServiceAndClient> selectDataClientThatBuy(NameServiceAndClient nameService,
                                                                        ComboBox<NameService> comboBox,
                                                                        ComboBox<Client> clientComboBox);


}
