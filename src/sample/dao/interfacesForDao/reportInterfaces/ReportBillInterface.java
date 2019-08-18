package sample.dao.interfacesForDao.reportInterfaces;

import javafx.collections.ObservableList;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import sample.model.modelsForSingleTables.NameService;
import sample.model.modelsForSingleTables.Ordering;

import java.sql.SQLException;

public interface ReportBillInterface {

    ObservableList<Ordering> getCodeOrdering() throws SQLException;
    String getNameClient(Label label, ComboBox<Ordering> clientComboBox);
    String getLastNameClient(Label label,ComboBox<Ordering> clientComboBox);
    ObservableList<NameService> getGoods(NameService nameService, ComboBox<Ordering> clientComboBox) throws SQLException;
    String getSumaOrdering(Label label,ComboBox<Ordering> clientComboBox);
    String priceGood(Label label);
    String numberGood(Label label,ComboBox<Ordering> clientComboBox);

}
