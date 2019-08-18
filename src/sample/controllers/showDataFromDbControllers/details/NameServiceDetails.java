package sample.controllers.showDataFromDbControllers.details;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.util.StringConverter;
import org.controlsfx.control.textfield.TextFields;
import sample.dao.implementation.updateTableDao.UpdateNameServiceTableDao;
import sample.dao.interfacesForDao.updateDataTable.UpdateNameServiceInterface;
import sample.model.modelsForSingleTables.NameService;

public class NameServiceDetails {

    @FXML
    private ComboBox<NameService> boxIdNameService;

    @FXML
    private TextField textAreaKindService;

    @FXML
    private TextField textAreaPriceService;

    @FXML
    private Button buttonClickChangeData;

    @FXML
    private Button buttonGetId;

    @FXML
    public void initialize() {
    }

    @FXML
    public void clickGetId() {
        buttonGetId.setOnAction(event -> {
            ObservableList<NameService> innerList;
            UpdateNameServiceInterface UNSI = new UpdateNameServiceTableDao();

                innerList = UNSI.getNameServiceId();
                boxIdNameService.setItems(innerList);
                TextFields.bindAutoCompletion(boxIdNameService.getEditor(),boxIdNameService.getItems());
                boxIdNameService.setConverter(new StringConverter<NameService>() {
                    @Override
                    public String toString(NameService object) {
                        if(object==null)return null;
                        return String.valueOf(object.getId_NameService());
                    }

                    @Override
                    public NameService fromString(String string) {
                        return boxIdNameService.getItems().stream()
                                .filter(ap->ap.toString().equals(string))
                                .findFirst()
                                .orElse(null);
                    }
                });
        });
    }

    @FXML
    public void updateNameService() {
        buttonClickChangeData.setOnAction(event -> {
            NameService nameService = new NameService();

            String kindService = textAreaKindService.getText();
            float priceService = Float.parseFloat(textAreaPriceService.getText());

            nameService.setKindService(kindService);
            nameService.setPriceService(priceService);

            UpdateNameServiceInterface UNSI = new UpdateNameServiceTableDao();
            String updatedData = UNSI.updateData(nameService, boxIdNameService);

            if (updatedData.equals("SUCCESS")) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Information");
                String s = "Дані обновилися";
                alert.setContentText(s);
                alert.show();
            } else {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Information");
                String s = "Помилка!!! Дані не обновилися!!!";
                alert.setContentText(s);
                alert.show();
            }

        });

    }
}
