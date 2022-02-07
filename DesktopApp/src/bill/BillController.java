package bill;

import com.google.gson.Gson;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
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
import validationHelper.ValidationHelper;

import java.io.IOException;
import java.net.http.HttpResponse;
import java.util.ArrayList;


public class BillController {

    @FXML
    private TextField nameField;

    @FXML
    private TextField quantityField;

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
    private TableView<BillMedicine> billTable;

    @FXML
    private TableColumn<BillMedicine, String> billIdCol;

    @FXML
    private TableColumn<BillMedicine, String> billNameCol;

    @FXML
    private TableColumn<BillMedicine, String> billPriceCol;

    @FXML
    private TableColumn<BillMedicine, String> billQtyCol;

    @FXML
    private TableColumn<BillMedicine, String> billNetCol;


    @FXML
    private Label warningLabel;

    @FXML
    private Label statusLabel;

    Medicine medicine = null;
    BillMedicine billMedicine = null;

    ObservableList<BillMedicine> billMedicineList = FXCollections.observableArrayList();

    ObservableList<Medicine> medicineList = FXCollections.observableArrayList();

    MedicineJson[] medJsonArray = null;
    ArrayList<BillMedicine> billMedicineArrayList = new ArrayList<BillMedicine>();


    @FXML
    public void searchMedicine(KeyEvent event) throws IOException, InterruptedException {
        statusLabel.setText("");
        boolean isValid = validateMedName(nameField.getText());
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

        medJsonArray = gson.fromJson(response.body(),MedicineJson[].class);


        if(medJsonArray[0]!= null){
            medicineList.clear();
            for(MedicineJson med : medJsonArray) {
                medicineList.add(new Medicine(med.getMed_id(),med.getMed_name(),med.getMed_price(),med.getMed_net_price(),med.getMed_manufacturer(),med.getMed_quantity()));
                medicineTable.setItems(medicineList);
            }
        }else{
            statusLabel.setText("No record Found");
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

    @FXML
    void getSelectedId(KeyEvent event) {
        Medicine m = medicineTable.getSelectionModel().getSelectedItem();

        if(event.getCode().equals(KeyCode.ENTER)){
            nameField.setText(m.getMed_name());
            idField.setText(String.valueOf(m.getMed_id()));
            quantityField.requestFocus();
        }
    }

    @FXML
    public void addToBill(ActionEvent event) {
        String name = nameField.getText();
        String qty = quantityField.getText();
        String id = idField.getText();
       boolean isValid = validateAll(name,qty,id);
       if(isValid == true){

//            billMedicineList.clear();
           int nId = Integer.parseInt(id);
           int nQty = Integer.parseInt(qty);
           for(MedicineJson med : medJsonArray) {
               if(nId == med.getMed_id()){
                   int deductedQty = med.getMed_quantity() - nQty;
                   if(deductedQty >= 0){
                       int nNet = nQty*med.getMed_net_price();
                       billMedicineList.add(new BillMedicine(med.getMed_id(),med.getMed_name(),med.getMed_net_price(),nNet,nQty));
                       billTable.setItems(billMedicineList);
                       billDataLoader();
                       resetAllFields();
                   }else{
                       statusLabel.setText("Entered Quantity Exceeds stock");
                   }
               }
//              billMedicineList.add(new BillMedicine());
//              medicineTable.setItems(medicineList);
           }
       }
    }
    public void billDataLoader(){
        billIdCol.setCellValueFactory(new PropertyValueFactory<>("billId"));
        billNameCol.setCellValueFactory(new PropertyValueFactory<>("billMedName"));
        billPriceCol.setCellValueFactory(new PropertyValueFactory<>("billPrice"));
        billQtyCol.setCellValueFactory(new PropertyValueFactory<>("billQty"));
        billNetCol.setCellValueFactory(new PropertyValueFactory<>("billNetPrice"));


    }
    public boolean validateAll(String name,String qty,String id){
        ValidationHelper vh = new ValidationHelper();
        if(vh.validateMedName(name)==true && vh.validateNumber(qty) == true && vh.validateNumber(id) == true){
            return true;
        }else{
            return false;
        }
    }

    public void totalCalculator(ActionEvent event)throws IOException, InterruptedException{
        if(billMedicineList!=null){
            for(BillMedicine billMedicine : billMedicineList){
                billMedicineArrayList.add(new BillMedicine(billMedicine.getBillId(),billMedicine.getBillMedName(),billMedicine.getBillPrice(),billMedicine.getBillNetPrice(),billMedicine.getBillQty()));
            }
            BillMedicineToJson[] billMedicineToJson = new BillMedicineToJson[billMedicineArrayList.size()];
            for(int i=0;i<billMedicineArrayList.size();i++){
                billMedicineToJson[i] = new BillMedicineToJson(billMedicineArrayList.get(i).getBillId(),billMedicineArrayList.get(i).getBillMedName(),billMedicineArrayList.get(i).getBillPrice(),billMedicineArrayList.get(i).getBillNetPrice(),billMedicineArrayList.get(i).getBillQty());
            }

            Gson gson = new Gson();
            String jsonStr = gson.toJson(billMedicineArrayList);
            PostRequest postRequest = new PostRequest();
            HttpResponse<String> response = postRequest.requestSender("http://localhost/MIS/API/bill.php",jsonStr);
            Gson gson1 = new Gson();
            BillResponse bR = gson1.fromJson(response.body(),BillResponse.class);
            if(bR.isStatus()==true){
                statusLabel.setText(bR.getMessage());
                resetAllFields();
                medicineList.clear();
                billMedicineList.clear();
                billMedicineArrayList.clear();
            }

        }
    }
    public void resetAllFields(){
        nameField.setText("");
        idField.setText("");
        quantityField.setText("");

        nameField.requestFocus();
    }

}
