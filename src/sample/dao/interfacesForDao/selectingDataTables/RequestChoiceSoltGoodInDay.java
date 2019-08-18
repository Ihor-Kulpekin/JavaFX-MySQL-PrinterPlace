package sample.dao.interfacesForDao.selectingDataTables;

import javafx.collections.ObservableList;
import javafx.scene.control.ComboBox;
import sample.model.modelsForComplexTables.NameServiceAndOrdering;
import sample.model.modelsForSingleTables.Ordering;

import java.sql.SQLException;

public interface RequestChoiceSoltGoodInDay {

    ObservableList<NameServiceAndOrdering> selectSoltGoodsInChoiceDay
            (NameServiceAndOrdering nameServiceAndOrdering, ComboBox<Ordering> comboBox) throws SQLException;

}
