package sample.controllers.addDataToDbControllers;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import sample.dao.implementation.insertingIntoDao.ServiceDao;
import sample.model.modelsForSingleTables.Service;

public class AddPoslugaController {

    @FXML
    private Button addPosluga;


    @FXML
    private TextField nameServiceField;

    @FXML
    public void initialize(){

    }

    @FXML
    public void addPosluga(){
        addPosluga.setOnAction(event -> {
            Service service = new Service();
            String name = nameServiceField.getText();
            service.setName(name);

            ServiceDao addServiceDao = new ServiceDao();


                String serviceAdded = addServiceDao.addService(service);

                if(serviceAdded.equals("SUCCESS")){
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Information");
                    String s ="Запис додано до таблиці service";
                    alert.setContentText(s);
                    alert.show();
                }

                else if(!serviceAdded.equals("SUCCESS")){
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Information");
                    String s ="Помилка!!!";
                    alert.setContentText(s);
                    alert.show();
                }
        });
    }


}
