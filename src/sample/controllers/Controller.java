package sample.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.MalformedURLException;


public class Controller  {

    @FXML
    private Button buttonAddWorker;

    @FXML
    private Button buttonAddClient;

    @FXML
    private  Button buttonAddPosluga;

    @FXML
    private Button buttonAddNameService;

    @FXML
    private Button buttonAddOrdering;

    @FXML
    private Button buttonShowService;

    @FXML
    private Button buttonShowClient;

    @FXML
    private Button buttonShowWorker;

    @FXML
    private Button buttonShowNameService;

    @FXML
    private Button buttonShowOrdering;

    @FXML
    private Button buttonDoRequestClientBuy;

    @FXML
    private Button buttonTheMostExpensiveOrder;

    @FXML
    private Button button;

    @FXML
    private Button buttonAvgOrderedGood;

    @FXML
    private Button buttonPriceGreaterThanMiddle;

    @FXML
    private Button buttonPopularityGoods;

    @FXML
    private Button buttonOpenFormRequest;

    @FXML
    private Button buttonOpenFormRequestGood;

    @FXML
    private Button buttonOpenReportBill;

    @FXML
    private Button buttonOpenReportWorker;

    @FXML
    private Button buttonReportAllTime;

    @FXML
    public void clickButtonReportAllTime(){
        buttonReportAllTime.setOnAction(event -> {
            Stage primaryStage = new Stage();
            try {
                Parent root = FXMLLoader.load(getClass().getClassLoader()
                        .getResource("sample/fxmlUI/report/reportAllTime.fxml"));
                Scene scene = new Scene(root,1100,600);
                primaryStage.setScene(scene);
                primaryStage.show();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }

    @FXML
    public void openFormReportWorker(){
        buttonOpenReportWorker.setOnAction(event -> {
            Stage primaryStage = new Stage();
            try {
                Parent root = FXMLLoader.load(getClass().getClassLoader()
                        .getResource("sample/fxmlUI/report/reportWorker.fxml"));
                Scene scene = new Scene(root,1100,600);
                primaryStage.setScene(scene);
                primaryStage.show();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }

    @FXML
    public void openFormReportBill(){
        buttonOpenReportBill.setOnAction(event -> {
            Stage primaryStage = new Stage();
            try {
                Parent root = FXMLLoader.load(getClass().getClassLoader()
                        .getResource("sample/fxmlUI/report/reportBill.fxml"));
                Scene scene = new Scene(root,1100,600);
                primaryStage.setScene(scene);
                primaryStage.show();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }

    @FXML
    public void openFormRequestAboutGood(){
        buttonOpenFormRequestGood.setOnAction(event -> {
            Stage primaryStage = new Stage();
            try {
                Parent root = FXMLLoader.load(getClass().getClassLoader()
                        .getResource("sample/fxmlUI/requestsUi/requestDataGoodForAllTime.fxml"));
                Scene scene = new Scene(root,1100,600);
                primaryStage.setScene(scene);
                primaryStage.show();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }

    @FXML
    public void openFormRequest(){
        buttonOpenFormRequest.setOnAction(event->{
            Stage primaryStage = new Stage();
            try {
                Parent root = FXMLLoader.load(getClass().getClassLoader()
                        .getResource("sample/fxmlUI/requestsUi/requestChoiceGoodsSoltInDayUi.fxml"));
                Scene scene = new Scene(root,1100,600);
                primaryStage.setScene(scene);
                primaryStage.show();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }

    @FXML
    public void openFormPopularityGoods(){
        buttonPopularityGoods.setOnAction(event -> {
            Stage primaryStage = new Stage();
            try {
                Parent root = FXMLLoader.load(getClass().getClassLoader()
                        .getResource("sample/fxmlUI/requestsUi/requestPopularityGoods.fxml"));
                Scene scene = new Scene(root,1100,600);
                primaryStage.setScene(scene);
                primaryStage.show();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }

    @FXML
    public void openFormRequestPriceGreaterThanMiddle(){
        buttonPriceGreaterThanMiddle.setOnAction(event -> {
            Stage primaryStage = new Stage();
            try {
                Parent root = FXMLLoader.load(getClass().getClassLoader()
                        .getResource("sample/fxmlUI/requestsUi/requestPriceGreaterThanMiddle.fxml"));
                Scene scene = new Scene(root,1100,600);
                primaryStage.setScene(scene);
                primaryStage.show();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }

    @FXML
    public void openFormRequestAvgNumberOrderedGood(){
        buttonAvgOrderedGood.setOnAction(event -> {
            Stage primaryStage = new Stage();
            try {
                Parent root = FXMLLoader.load(getClass().getClassLoader()
                        .getResource("sample/fxmlUI/requestsUi/requestAvgNumberOrderedGoodUi.fxml"));
                Scene scene = new Scene(root,1100,600);
                primaryStage.setScene(scene);
                primaryStage.show();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }

    public void openFormRequestWorkerWhoIsWorked(){
        button.setOnAction(event->{
            Stage primaryStage = new Stage();
            try {
                Parent root = FXMLLoader.load(getClass().getClassLoader()
                        .getResource("sample/fxmlUI/requestsUi/requestWorkerWhoIsOrdered.fxml"));
                Scene scene = new Scene(root,1100,600);
                primaryStage.setScene(scene);
                primaryStage.show();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }

    public void openFormRequestTheMostExpensiveOrder(){
        buttonTheMostExpensiveOrder.setOnAction(event -> {
            Stage primaryStage = new Stage();
            try {
                Parent root = FXMLLoader.load(getClass().getClassLoader()
                        .getResource("sample/fxmlUI/requestsUi/requestTheMostExpensiveOrder.fxml"));
                Scene scene = new Scene(root,1100,600);
                primaryStage.setScene(scene);
                primaryStage.show();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }

    public void doRequestClinetBuy(){
        buttonDoRequestClientBuy.setOnAction(event -> {
            Stage primaryStage = new Stage();
            try {
                Parent root = FXMLLoader.load(getClass().getClassLoader()
                        .getResource("sample/fxmlUI/requestsUi/requestClientBuyUi.fxml"));
                Scene scene = new Scene(root,1100,600);
                primaryStage.setScene(scene);
                primaryStage.show();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }

    @FXML
    public void buttonShowOrdering(){
        buttonShowOrdering.setOnAction(event -> {
            Stage primaryStage = new Stage();
            try {
                Parent root = FXMLLoader.load(getClass().getClassLoader()
                        .getResource("sample/fxmlUI/showTableFromDbOnUI/showDataFromOrderingTable.fxml"));
                Scene scene = new Scene(root,1100,600);
                primaryStage.setScene(scene);
                primaryStage.show();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }


    @FXML
    public void buttonShowNameService(){
        buttonShowNameService.setOnAction(event -> {
            Stage primaryStage = new Stage();
            try {
                Parent root = FXMLLoader.load(getClass().getClassLoader()
                        .getResource("sample/fxmlUI/showTableFromDbOnUI/showDataFromNameServiceTable.fxml"));
                Scene scene = new Scene(root,1100,600);
                primaryStage.setScene(scene);
                primaryStage.show();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }

    @FXML
    public void buttonsShowDataFromDbWorker(){
        buttonShowWorker.setOnAction(event -> {
            Stage primaryStage = new Stage();
            try {
                Parent root = FXMLLoader.load(getClass().getClassLoader()
                        .getResource("sample/fxmlUI/showTableFromDbOnUI/showDataFromWorkerTable.fxml"));
                Scene scene = new Scene(root,1100,600);
                primaryStage.setScene(scene);
                primaryStage.show();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }

    @FXML
    public void buttonShowDataFromDbClient(){
        buttonShowClient.setOnAction(event -> {
            Stage primaryStage = new Stage();
            try {
                Parent root = FXMLLoader.load(getClass().getClassLoader()
                        .getResource("sample/fxmlUI/showTableFromDbOnUI/showDataFromClientTable.fxml"));
                Scene scene = new Scene(root,1100,600);
                primaryStage.setScene(scene);
                primaryStage.show();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }

    @FXML
    public void buttonShowDataFromDb(){
        buttonShowService.setOnAction(event -> {
            Stage primaryStage = new Stage();
            try {
                Parent root = FXMLLoader.load(getClass().getClassLoader()
                        .getResource("sample/fxmlUI/showTableFromDbOnUI/showDataFromServiceTable.fxml"));
                Scene scene = new Scene(root,1100,600);
                primaryStage.setScene(scene);
                primaryStage.show();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }


    @FXML
    public void clickButtonAddOrdering(){
        buttonAddOrdering.setOnAction(event -> {
            Stage primaryStage = new Stage();
            try {
                Parent root = FXMLLoader.load(getClass().getClassLoader()
                        .getResource("sample/fxmlUI/addDataToDB/addOrdering.fxml"));
                Scene scene = new Scene(root,1100,600);
                primaryStage.setScene(scene);
                primaryStage.show();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }


    @FXML
    public void clickButtonAddNameService(){
        buttonAddNameService.setOnAction(event -> {
            Stage primaryStage = new Stage();
            try {
                Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("sample/fxmlUI/addDataToDB/addNameService.fxml"));
                Scene scene = new Scene(root,1100,600);
                primaryStage.setScene(scene);
                primaryStage.show();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }


    @FXML
    public void clickButtonAddPosluga(ActionEvent event) throws MalformedURLException {
        buttonAddPosluga.setOnAction(event1 -> {
            Stage primaryStage = new Stage();
            try {
                Parent root1 = FXMLLoader.load(getClass().getClassLoader().getResource("sample/fxmlUI/addDataToDB/addPosluga.fxml"));
                Scene scene = new Scene(root1,1199,594);
                primaryStage.setScene(scene);
                primaryStage.show();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }

    public void clickButtonAddClient(ActionEvent event){
        buttonAddClient.setOnAction(event1 ->{
            Stage primaryStage = new Stage();
            try {
                Parent root1 = FXMLLoader.load(getClass().getClassLoader().getResource("sample/fxmlUI/addDataToDB/addClient.fxml"));
                Scene scene = new Scene(root1,1199,594);
                primaryStage.setScene(scene);
                primaryStage.show();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }
    public void clickButtonAddWorker(){
        buttonAddWorker.setOnAction(event -> {
            Stage primaryStage = new Stage();
            try {
                Parent root1 = FXMLLoader.load(getClass().getClassLoader().getResource("sample/fxmlUI/addDataToDB/addWorker.fxml"));
                Scene scene = new Scene(root1,1199,594);
                primaryStage.setScene(scene);
                primaryStage.show();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }


    @FXML
    public void initialize() {

    }
}
