package sample.controllers.showDataFromDbControllers.deleteDataFromTableControllers;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import sample.dao.implementation.deleteDataFromTableDao.DeleteDataOrderingTableDao;
import sample.dao.interfacesForDao.deleteDataFromTable.DeleteOrderingInterface;
import sample.model.modelsForSingleTables.Ordering;

public class DeleteDataOrderingController {

    @FXML
    private TextField textField;

    @FXML
    private Button buttonDeleteData;

    @FXML
    public void initialize(){

    }

    @FXML
    public void clickButtonDeleteData(){
        buttonDeleteData.setOnAction(event -> {
            Ordering ordering = new Ordering();

            int codeOrdering = Integer.parseInt(textField.getText());

            ordering.setCodeOrdering(codeOrdering);

            DeleteOrderingInterface DOI = new DeleteDataOrderingTableDao();

            String deletedData = DOI.deleteOrderingById(ordering);

            if(deletedData.equals("SUCCESS")){
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Information");
                String s = "Дані видалилися";
                alert.setContentText(s);
                alert.show();
            }else {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Information");
                String s = "Помилка!!! Спробуйте ще раз!!!";
                alert.setContentText(s);
                alert.show();
            }

        });
    }

}
