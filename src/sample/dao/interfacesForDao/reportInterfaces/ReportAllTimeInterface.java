package sample.dao.interfacesForDao.reportInterfaces;

import javafx.collections.ObservableList;
import javafx.scene.control.Label;
import sample.model.modelsForSingleTables.NameService;

import java.sql.SQLException;

public interface ReportAllTimeInterface {

    String countAllClients(Label label);
    String numberSoldGoods(Label label);
    String sumSoldGoods(Label label);
    ObservableList<NameService> getGoods(NameService nameService) throws SQLException;


}
