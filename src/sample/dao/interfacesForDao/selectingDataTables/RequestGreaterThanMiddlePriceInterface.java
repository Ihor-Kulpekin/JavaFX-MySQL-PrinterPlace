package sample.dao.interfacesForDao.selectingDataTables;

import javafx.collections.ObservableList;
import javafx.scene.control.ComboBox;
import sample.model.modelsForComplexTables.ServiceAndNameService;
import sample.model.modelsForSingleTables.Service;

public interface RequestGreaterThanMiddlePriceInterface {

    ObservableList<ServiceAndNameService> selectGreaterThanMiddlePrice
            (ServiceAndNameService serviceAndNameService, ComboBox<Service> comboBox);

    ObservableList<ServiceAndNameService> selectAverageValueByPrice(ServiceAndNameService serviceAndNameService, ComboBox<Service> comboBox);

}
