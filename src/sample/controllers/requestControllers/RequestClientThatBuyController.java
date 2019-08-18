package sample.controllers.requestControllers;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.util.StringConverter;
import org.controlsfx.control.spreadsheet.StringConverterWithFormat;
import org.controlsfx.control.textfield.TextFields;
import sample.dao.interfacesForDao.insertingInto.ClientInterface;
import sample.dao.interfacesForDao.insertingInto.NameServiceInterface;
import sample.dao.interfacesForDao.selectingDataTables.RequestClientThatBuyInterface;
import sample.dao.implementation.insertingIntoDao.AddClientDao;
import sample.dao.implementation.insertingIntoDao.NameServiceDao;
import sample.dao.implementation.selectingByDataTablesDao.RequestClientThatBuyDao;
import sample.databaseConnection.DatabaseConnectionImpl;
import sample.model.modelsForSingleTables.Client;
import sample.model.modelsForSingleTables.NameService;
import sample.model.modelsForComplexTables.NameServiceAndClient;

import java.sql.SQLException;

public class RequestClientThatBuyController {

    @FXML
    private TableView<NameServiceAndClient> tableReuqest;

    @FXML
    private TableColumn<NameServiceAndClient,String> colName;

    @FXML
    private TableColumn<NameServiceAndClient,String> colSurname;

    @FXML
    private TableColumn<NameServiceAndClient,String> buyingGood;

    @FXML
    private TableColumn<NameServiceAndClient,Integer> numberOrdering;

    @FXML
    private TableColumn<NameServiceAndClient,Float> colGeneralSum;

    @FXML
    private Button doRequest;

    @FXML
    private Button buttonGetAllKindServices;

    @FXML
    private Button buttonGetAllIdClients;

    @FXML
    private ComboBox<NameService> boxKindService;

    @FXML
    private ComboBox<Client> boxIdClient;

    @FXML
    public void initialize(){
        colName.setCellValueFactory(new PropertyValueFactory<>("firstName"));
        colSurname.setCellValueFactory(new PropertyValueFactory<>("lastName"));
        buyingGood.setCellValueFactory(new PropertyValueFactory<>("serviceName"));
        numberOrdering.setCellValueFactory(new PropertyValueFactory<>("counter"));
        colGeneralSum.setCellValueFactory(new PropertyValueFactory<>("generalSum"));


        DatabaseConnectionImpl.createStaticConnection();

        doRequest();
    }


    @FXML
    public void getAllIdClient(){
        buttonGetAllIdClients.setOnAction(event -> {
            ObservableList<Client> innerList;
            ClientInterface CI = new AddClientDao();
            try {
                innerList = CI.getClients();
                boxIdClient.setItems(innerList);
                TextFields.bindAutoCompletion(boxIdClient.getEditor(),boxIdClient.getItems());
                boxIdClient.setConverter(new StringConverter<>() {
                    @Override
                    public String toString(Client object) {
                        if(object==null)return null;
                        return object.toString();
                    }

                    @Override
                    public Client fromString(String string) {
                        return boxIdClient.getItems().stream()
                                .filter(ap->ap.toString().equals(string))
                                .findFirst()
                                .orElse(null);
                    }
                });

            } catch (SQLException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        });
    }

    @FXML
    public void getAllKindServices(){
        buttonGetAllKindServices.setOnAction(event -> {
            ObservableList<NameService> innerList;
            NameServiceInterface NSI = new NameServiceDao();
            try {
                innerList = NSI.getKindService();
                boxKindService.setItems(innerList);
                TextFields.bindAutoCompletion(boxKindService.getEditor(),boxKindService.getItems());
                boxKindService.setConverter(new StringConverterWithFormat<NameService>() {
                    @Override
                    public String toString(NameService object) {
                        if(object==null)return null;
                        return object.toString();
                    }

                    @Override
                    public NameService fromString(String string) {
                        return boxKindService.getItems().stream()
                                .filter(ap->ap.toString().equals(string))
                                .findFirst()
                                .orElse(null);
                    }
                });

            } catch (SQLException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        });
        }


    public void doRequest(){
        doRequest.setOnAction(event -> {
            NameServiceAndClient nameServiceAndClient = new NameServiceAndClient();

            RequestClientThatBuyInterface RCBI = new RequestClientThatBuyDao();


            tableReuqest.setItems(RCBI.selectDataClientThatBuy(nameServiceAndClient,boxKindService,boxIdClient));

        });
    }
}
