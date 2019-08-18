package sample.dao.interfacesForDao.selectingDataTables;

import javafx.collections.ObservableList;
import sample.model.modelsForComplexTables.PopularityNameServiceOrdering;

import java.sql.SQLException;

public interface PopularityGoodInterface {

    ObservableList<PopularityNameServiceOrdering> selectPopularityGoodFromOrder
            (PopularityNameServiceOrdering popularityGood) throws SQLException;

}
