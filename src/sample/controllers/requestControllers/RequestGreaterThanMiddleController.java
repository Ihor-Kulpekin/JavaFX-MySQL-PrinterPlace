package sample.controllers.requestControllers;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.util.StringConverter;
import org.controlsfx.control.textfield.TextFields;
import sample.dao.implementation.insertingIntoDao.ServiceDao;
import sample.dao.implementation.selectingByDataTablesDao.RequestGreaterThanMiddlePriceDao;
import sample.dao.interfacesForDao.insertingInto.ServiceInterface;
import sample.dao.interfacesForDao.selectingDataTables.RequestGreaterThanMiddlePriceInterface;
import sample.databaseConnection.DatabaseConnectionImpl;
import sample.model.modelsForComplexTables.ServiceAndNameService;
import sample.model.modelsForSingleTables.Service;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.sql.SQLException;

public class RequestGreaterThanMiddleController {

    @FXML
    private TableView<ServiceAndNameService> tablePriceGreaterThanMiddle;

    @FXML
    private TableView<ServiceAndNameService> tableMiddlePriceGood;

    @FXML
    private TableColumn<ServiceAndNameService,Float> colMiddlePriceGood;

    @FXML
    private TableColumn<ServiceAndNameService,String> colKindService;

    @FXML
    private TableColumn<ServiceAndNameService,String> colNameService;

    @FXML
    private TableColumn<ServiceAndNameService,Float> colPrice;

    @FXML
    private ComboBox<Service> boxNameService;

    @FXML
    private Button buttonGetServices;

    @FXML
    private Button buttonDoRequest;

    @FXML
    private Button buttonGetMiddlePriceGood;

    @FXML
    private TableColumn<ServiceAndNameService,String> colServiceName;

    @FXML
    public void initialize(){

        colNameService.setCellValueFactory(new PropertyValueFactory<>("namesService"));
        colKindService.setCellValueFactory(new PropertyValueFactory<>("kindNameService"));
        colPrice.setCellValueFactory(new PropertyValueFactory<>("priceService"));

        colServiceName.setCellValueFactory(new PropertyValueFactory<>("namesService"));
        colMiddlePriceGood.setCellValueFactory(new PropertyValueFactory<>("averagePriceGood"));


        try {
            String encodeMess = URLEncoder.encode(tableMiddlePriceGood.toString(),"Windows-1251");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        DatabaseConnectionImpl.createStaticConnection();

        doRequestMiddlePrice();
        getAllNameService();
        doRequest();
    }

    @FXML
    public void doRequestMiddlePrice(){
        buttonGetMiddlePriceGood.setOnAction(event -> {
            ServiceAndNameService serviceAndNameService = new ServiceAndNameService();

            RequestGreaterThanMiddlePriceInterface request = new RequestGreaterThanMiddlePriceDao();

            ObservableList<ServiceAndNameService> innerList;
            innerList = request.selectAverageValueByPrice(serviceAndNameService,boxNameService);
            tableMiddlePriceGood.setItems(innerList);

        });
    }

    @FXML
    public void doRequest(){
        buttonDoRequest.setOnAction(event -> {
            ServiceAndNameService serviceAndNameService = new ServiceAndNameService();

            RequestGreaterThanMiddlePriceInterface request = new RequestGreaterThanMiddlePriceDao();

            tablePriceGreaterThanMiddle.setItems(request.selectGreaterThanMiddlePrice
                    (serviceAndNameService,boxNameService));

        });
    }

    @FXML
    public void getAllNameService(){
        buttonGetServices.setOnAction(event -> {
            ObservableList<Service> innerList;

            ServiceInterface SI = new ServiceDao();

            try {
                innerList = SI.getServices();
                boxNameService.setItems(innerList);
            } catch (SQLException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }

            TextFields.bindAutoCompletion(boxNameService.getEditor(),boxNameService.getItems());
            boxNameService.setConverter(new StringConverter<Service>() {
                @Override
                public String toString(Service object) {
                    if(object==null)return null;
                    return object.toString();
                }

                @Override
                public Service fromString(String string) {
                    return boxNameService.getItems().stream()
                            .filter(ap->ap.toString().equals(string))
                            .findFirst()
                            .orElse(null);
                }
            });
        });
    }


}
