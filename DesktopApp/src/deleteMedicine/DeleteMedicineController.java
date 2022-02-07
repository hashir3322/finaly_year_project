package deleteMedicine;

import bill.SearchToJson;
import com.google.gson.Gson;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.event.ActionEvent;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import requestHelper.PostRequest;
import showAll.Medicine;
import showAll.MedicineJson;
import updateMedicine.UpdateResponse;
import updateMedicine.UpdateToJson;
import validationHelper.ValidationHelper;

import java.io.IOException;
import java.net.http.HttpResponse;

public class DeleteMedicineController {

    @FXML
    private TextField nameField;

    @FXML
    private TextField idField;

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

    public boolean validateMedName(String medName){
        ValidationHelper vh = new ValidationHelper();
        if(vh.validateMedName(medName) == true){
            return true;
        }else{
            return false;
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
    public void delete(ActionEvent event) throws IOException, InterruptedException {
        String id = idField.getText();
        ValidationHelper vh = new ValidationHelper();
        boolean isIdValid = vh.validateNumber(id);
        if(isIdValid == true){
            sendData(id);
        }else{
            warningLabel.setText("Make sure to enter correct data in fields");
        }
    }

    public void sendData(String id) throws IOException, InterruptedException {
        Gson gson = new Gson();

        DeleteToJson deleteToJson = new DeleteToJson(id);
        String jsonStr = gson.toJson(deleteToJson);
        PostRequest postRequest = new PostRequest();
        HttpResponse<String> response = postRequest.requestSender("http://localhost/MIS/API/delete-medicine.php",jsonStr);

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

    public void resetFields(){
        nameField.setText("");
        idField.setText("");
        nameField.requestFocus();
    }

}
