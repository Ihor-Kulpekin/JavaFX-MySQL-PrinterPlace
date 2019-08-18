package sample.controllers.addDataToDbControllers;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.util.StringConverter;
import org.controlsfx.control.textfield.TextFields;
import sample.dao.interfacesForDao.insertingInto.ServiceInterface;
import sample.dao.implementation.insertingIntoDao.NameServiceDao;
import sample.dao.implementation.insertingIntoDao.ServiceDao;
import sample.model.modelsForSingleTables.NameService;
import sample.model.modelsForSingleTables.Service;

import java.sql.SQLException;

public class AddNamePoslugaController {

    @FXML
    private TextField kindService;

    @FXML
    private TextField priceService;

    @FXML
    private ComboBox<Service> dataFromService;


    @FXML
    private javafx.scene.control.Button addNameService;



    @FXML
    public void initialize(){

    }

    public void addNameService(){
        addNameService.setOnAction(event -> {

            NameService nameService = new NameService();

            String nameKindService = String.valueOf(dataFromService.getValue());
            String kindNameService = kindService.getText();
            float price = Float.valueOf(priceService.getText());

            nameService.setName(nameKindService);
            nameService.setKindService(kindNameService);
            nameService.setPriceService(price);
            NameServiceDao addNameServiceDao = new NameServiceDao();
            String nameServiceAdded = addNameServiceDao.addService(nameService);

            if(nameServiceAdded.equals("SUCCESS")){
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Information");
                String s ="Запис додано до таблиці nameservice";
                alert.setContentText(s);
                alert.show();
            }
            else {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Information");
                String s ="Помилка!!!";
                alert.setContentText(s);
                alert.show();
            }

        });
    }

    public void valuesComboBox() throws SQLException, ClassNotFoundException {
        ObservableList<Service> innerList;
        ServiceInterface ASI = new ServiceDao();
        innerList = ASI.getServices();

        dataFromService.setItems(innerList);
        TextFields.bindAutoCompletion(dataFromService.getEditor(),dataFromService.getItems());
        dataFromService.setConverter(new StringConverter<>() {
            @Override
            public String toString(Service object) {
                if(object == null)return null;
                return object.toString();
            }

            @Override
            public Service fromString(String string) {
                return dataFromService.getItems().stream()
                        .filter(ap->ap.getName().equals(string))
                        .findFirst()
                        .orElse(null);
            }
        });
    }

}
