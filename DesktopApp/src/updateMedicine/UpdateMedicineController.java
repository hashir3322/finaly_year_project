package updateMedicine;


import bill.SearchToJson;
import com.google.gson.Gson;
import javafx.scene.control.Label;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import requestHelper.PostRequest;
import showAll.Medicine;
import showAll.MedicineJson;
import validationHelper.ValidationHelper;

import java.io.IOException;
import java.net.http.HttpResponse;

public class UpdateMedicineController {
    @FXML
    private TextField nameField;

    @FXML
    private TextField idField;


    @FXML
    private TextField priceField;

    @FXML
    private TextField netPriceField;

    @FXML
    private TextField manufacturerField;

    @FXML
    private TextField quantityField;

    @FXML
    private TableView<Medicine> medicineTable;

    @FXML
    private TableColumn<Medicine, String> medIdCol;

    @FXML
    private TableColumn<Medicine, String> medNameCol;

    @FXML
    private TableColumn<Medicine, String> medPriceCol;

    @FXML
    private TableColumn<Medicine, String> medNetPriceCol;

    @FXML
    private TableColumn<Medicine, String> medManufacturerCol;

    @FXML
    private TableColumn<Medicine, String> medQtyCol;

    @FXML
    private Label responseLabel;

    @FXML
    private Label warningLabel;


    Medicine medicine = null;

    ObservableList<Medicine> medicineList = FXCollections.observableArrayList();


    @FXML
    void getSelectedId(KeyEvent event) {
        Medicine m = medicineTable.getSelectionModel().getSelectedItem();
        if(m!=null){
            if(event.getCode().equals(KeyCode.ENTER)){
                nameField.setText(m.getMed_name());
                idField.setText(String.valueOf(m.getMed_id()));
                priceField.setText(String.valueOf(m.getMed_price()));
                netPriceField.setText(String.valueOf(m.getMed_net_price()));
                manufacturerField.setText(m.getMed_manufacturer());
                quantityField.setText(String.valueOf(m.getMed_quantity()));
                nameField.requestFocus();
            }
        }
    }

    @FXML
    void searchMedicine(KeyEvent event) throws IOException, InterruptedException {
        boolean isValid = validateMedName(nameField.getText());
        responseLabel.setText("");
        if(isValid == true){
            dataLoader();
            warningLabel.setText("");
        }else{
            warningLabel.setText("Not a valid format for Name");
        }
    }

    public void dataLoader() throws IOException, InterruptedException {

        refreshTable();

        medIdCol.setCellValueFactory(new PropertyValueFactory<>("med_id"));
        medNameCol.setCellValueFactory(new PropertyValueFactory<>("med_name"));
        medPriceCol.setCellValueFactory(new PropertyValueFactory<>("med_price"));
        medNetPriceCol.setCellValueFactory(new PropertyValueFactory<>("med_net_price"));
        medManufacturerCol.setCellValueFactory(new PropertyValueFactory<>("med_manufacturer"));
        medQtyCol.setCellValueFactory(new PropertyValueFactory<>("med_quantity"));
    }

    public void refreshTable() throws IOException, InterruptedException {
        Gson gson = new Gson();
        SearchToJson searchToJson = new SearchToJson(nameField.getText());
        String jsonStr = gson.toJson(searchToJson);

        PostRequest postRequest = new PostRequest();
        HttpResponse<String> response = postRequest.requestSender("http://localhost/MIS/API/fetch-all-live.php",jsonStr);

        Gson gson1 = new Gson();

        MedicineJson[] medJsonArray = gson.fromJson(response.body(),MedicineJson[].class);


        if(medJsonArray[0]!= null){
            medicineList.clear();
            for(MedicineJson med : medJsonArray) {
                medicineList.add(new Medicine(med.getMed_id(),med.getMed_name(),med.getMed_price(),med.getMed_net_price(),med.getMed_manufacturer(),med.getMed_quantity()));
                medicineTable.setItems(medicineList);
            }
        }else{
            responseLabel.setText("No record Found");
        }
    }

    @FXML
   public void update(ActionEvent event) throws IOException, InterruptedException {
        String name = nameField.getText();
        String id = idField.getText();
        String price = priceField.getText();
        String netPrice = netPriceField.getText();
        String manufacturer = manufacturerField.getText();
        String quantity = quantityField.getText();
        boolean isAllValid = validateAll(name,id,price,netPrice,manufacturer,quantity);
        if(isAllValid == true){
            sendData(name,id,price,netPrice,manufacturer,quantity);
        }else{
            warningLabel.setText("Make sure to enter correct data in fields");
        }
    }
    public void sendData(String name,String id,String price,String netPrice,String manufacturer,String quantity) throws IOException, InterruptedException {
        Gson gson = new Gson();

        UpdateToJson updateToJson = new UpdateToJson(name,id,price,netPrice,manufacturer,quantity);
        String jsonStr = gson.toJson(updateToJson);
        PostRequest postRequest = new PostRequest();
        HttpResponse<String> response = postRequest.requestSender("http://localhost/MIS/API/update-medicine.php",jsonStr);

        Gson gson1 = new Gson();
        UpdateResponse updateResponse = gson1.fromJson(response.body(),UpdateResponse.class);
        if(updateResponse.isStatus() == true){
            responseLabel.setText(updateResponse.getMessage());
            resetFields();
        }
        else{
            responseLabel.setText(updateResponse.getMessage());
        }
    }

    public boolean validateMedName(String medName){
        ValidationHelper vh = new ValidationHelper();
        if(vh.validateMedName(medName) == true){
            return true;
        }else{
            return false;
        }

    }


    private boolean validateAll(String name,String id,String price,String netPrice,String manufacturer,String quantity){
        ValidationHelper vh = new ValidationHelper();
        boolean isNameValid = vh.validateMedName(name);
        boolean isIdValid = vh.validateNumber(id);
        boolean isPriceValid = vh.validateNumber(price);
        boolean isNetPriceValid = vh.validateMedName(netPrice);
        boolean isManufacturerValid = vh.validateMedName(manufacturer);
        boolean isQuantityValid = vh.validateMedName(quantity);
        if(isNameValid == true && isIdValid == true && isPriceValid == true && isNetPriceValid == true && isManufacturerValid == true && isQuantityValid == true){
            return true;
        }else{
            return false;
        }
    }

    public void resetFields(){
        nameField.setText("");
        idField.setText("");
        priceField.setText("");
        netPriceField.setText("");
        manufacturerField.setText("");
        quantityField.setText("");
        nameField.requestFocus();
    }
}
