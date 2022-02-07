package manageOrders;

import com.google.gson.Gson;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import orders.Order;
import orders.OrderJson;
import requestHelper.GetRequest;
import requestHelper.PostRequest;
import validationHelper.ValidationHelper;

import java.io.IOException;
import java.net.http.HttpResponse;

public class manageOrdersController {

    @FXML
    private TextField idField;


    @FXML
    private TableView<Order> orderTable;

    @FXML
    private TableColumn<Order, String> idCol;

    @FXML
    private TableColumn<Order, String> nameCol;

    @FXML
    private TableColumn<Order, String> priceCol;

    @FXML
    private TableColumn<Order, String> quantityCol;

    @FXML
    private TableColumn<Order, String> netPriceCol;

    @FXML
    private TableColumn<Order, String> pharmacyCol;

    @FXML
    private TableColumn<Order, String> pharmacyNameCol;


    @FXML
    private TableColumn<Order, String> statusCol;

    @FXML
    private ComboBox<String> combobox;


    @FXML
    private Label warningLabel;

    @FXML
    private Label statusLabel;

    Order order = null;

    ObservableList<String> list = FXCollections.observableArrayList("pending","processing","complete");

    ObservableList<Order> orderList = FXCollections.observableArrayList();

    public void initialize() throws IOException, InterruptedException {
        dataLoader();
    }

    public void dataLoader() throws IOException, InterruptedException {
        refreshTable();
        combobox.setItems(list);

        idCol.setCellValueFactory(new PropertyValueFactory<>("order_id"));
        nameCol.setCellValueFactory(new PropertyValueFactory<>("ordered_med_name"));
        priceCol.setCellValueFactory(new PropertyValueFactory<>("ordered_med_price"));
        quantityCol.setCellValueFactory(new PropertyValueFactory<>("ordered_med_qty"));
        netPriceCol.setCellValueFactory(new PropertyValueFactory<>("ordered_med_net"));
        pharmacyCol.setCellValueFactory(new PropertyValueFactory<>("ordering_pharmacy"));
        pharmacyNameCol.setCellValueFactory(new PropertyValueFactory<>("pharmacy_name"));
        statusCol.setCellValueFactory(new PropertyValueFactory<>("order_status"));
    }


    public void refreshTable() throws IOException, InterruptedException {
        GetRequest getRequest = new GetRequest();
        HttpResponse<String> response = getRequest.requestSender("http://localhost/MIS/API/all-orders.php");

        Gson gson = new Gson();

        OrderJson[] orderJsonArray = gson.fromJson(response.body(),OrderJson[].class);
        orderList.clear();
        for(OrderJson ord : orderJsonArray) {
            orderList.add(new Order(ord.getOrder_id(),ord.getOrdered_med_name(),ord.getOrdered_med_price(),ord.getOrdered_med_qty(),ord.getOrdered_med_net(),ord.getOrdering_pharmacy(),ord.getPharmacy_name(),ord.getOrder_status()));
            orderTable.setItems(orderList);
        }
    }

    @FXML
    public void updateOrder(ActionEvent event) throws IOException, InterruptedException {
        boolean isValid = validateAll(idField.getText(),combobox.getValue());
        if(isValid == true){

            Gson gson = new Gson();
            ManageToJson manageToJson = new ManageToJson(idField.getText(),combobox.getValue());
            String jsonStr = gson.toJson(manageToJson);

            PostRequest postRequest = new PostRequest();
            HttpResponse<String> response = postRequest.requestSender("http://localhost/MIS/API/update-order.php",jsonStr);
            Gson gson1 = new Gson();
            ManageResponse manageResponse = gson1.fromJson(response.body(),ManageResponse.class);

            if(manageResponse.isStatus()==true){
                statusLabel.setText(manageResponse.getMessage());
                resetAllFields();
                dataLoader();
            }else if(manageResponse.isStatus()==false){
                statusLabel.setText(manageResponse.getMessage());
                resetAllFields();
            }

        }else{
            warningLabel.setText("Ni sai");
        }
    }

    @FXML
    public void getSelectedId(KeyEvent event) {
        Order o = orderTable.getSelectionModel().getSelectedItem();
        if(event.getCode().equals(KeyCode.ENTER)){
            idField.setText(String.valueOf(o.getOrder_id()));
            combobox.setValue(o.getOrder_status());
            idField.requestFocus();
        }
    }

    private void resetAllFields(){
        idField.setText("");
    }

    public boolean validateAll(String id,String status){
        ValidationHelper vh = new ValidationHelper();
        if(vh.validateNumber(id) == true && status!=null){
            return true;
        }else{
            return false;
        }
    }

}
