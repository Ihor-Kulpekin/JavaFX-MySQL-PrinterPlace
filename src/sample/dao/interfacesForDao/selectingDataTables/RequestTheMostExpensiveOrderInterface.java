package sample.dao.interfacesForDao.selectingDataTables;

import javafx.collections.ObservableList;
import sample.model.modelsForComplexTables.ClientAndOrdering;

public interface RequestTheMostExpensiveOrderInterface {
    public ObservableList<ClientAndOrdering> selectDataTheMostExpensiveOrder(ClientAndOrdering clientAndOrdering);
}
