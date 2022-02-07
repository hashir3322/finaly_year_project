package orders;


import java.io.IOException;
import com.google.gson.Gson;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import requestHelper.GetRequest;
import java.net.http.HttpResponse;
import javafx.scene.control.TextField;
import requestHelper.PostRequest;
import javafx.scene.control.Label;
import validationHelper.ValidationHelper;


public class OrdersController {
    @FXML
    private TextField orderField;

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
    private Label statusLabel;

    Order order = null;
    ObservableList<Order> orderList = FXCollections.observableArrayList();

    public void initialize() throws IOException, InterruptedException {
        dataLoader();
    }
    public void dataLoader() throws IOException, InterruptedException {
        refreshTable();

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
    public void getSelectedId(KeyEvent event) {
        Order o = orderTable.getSelectionModel().getSelectedItem();
        if(event.getCode().equals(KeyCode.ENTER)){
            orderField.setText(String.valueOf(o.getOrder_id()));
            orderField.requestFocus();
        }
    }

    @FXML
    public void deleteOrder(ActionEvent event) throws IOException, InterruptedException {
        ValidationHelper vh = new ValidationHelper();
        if(vh.validateNumber(orderField.getText()) == true){
            Gson gson = new Gson();

            OrderToJson orderToJson = new OrderToJson(orderField.getText());
            String jsonStr = gson.toJson(orderToJson);

            PostRequest postRequest = new PostRequest();
            HttpResponse<String> response = postRequest.requestSender("http://localhost/MIS/API/delete-order.php",jsonStr);
            Gson gson1 = new Gson();
            OrderResponse orderResponse = gson1.fromJson(response.body(), OrderResponse.class);

            if(orderResponse.isStatus() == true){
                statusLabel.setText(orderResponse.getMessage());
                orderField.setText("");
                dataLoader();
            }else if(orderResponse.isStatus() == false){
                statusLabel.setText(orderResponse.getMessage());
            }
        }else{
            statusLabel.setText("Invalid Input");
        }

    }

}
