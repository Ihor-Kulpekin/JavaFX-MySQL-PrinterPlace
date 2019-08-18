package sample.dao.interfacesForDao.selectingDataTables;

import javafx.collections.ObservableList;
import javafx.scene.control.ComboBox;
import sample.model.modelsForSingleTables.NameService;
import sample.model.modelsForComplexTables.NameServiceAndOrdering;

import java.sql.SQLException;

public interface AverageNumberOrderingInterface {

    ObservableList<NameServiceAndOrdering> selectAvgNumberOrderingGood
            (NameServiceAndOrdering nameServiceAndOrdering, ComboBox<NameService> comboBox) throws SQLException;



}
