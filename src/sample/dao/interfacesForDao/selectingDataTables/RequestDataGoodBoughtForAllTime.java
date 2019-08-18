package sample.dao.interfacesForDao.selectingDataTables;

import javafx.collections.ObservableList;
import javafx.scene.control.ComboBox;
import sample.model.modelsForComplexTables.NameServiceAndOrdering;
import sample.model.modelsForSingleTables.NameService;

public interface RequestDataGoodBoughtForAllTime {

    ObservableList<NameServiceAndOrdering> selectDataGoodForAllTime
            (NameServiceAndOrdering nameServiceAndOrdering, ComboBox<NameService> comboBox);

}
