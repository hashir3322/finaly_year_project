package insert;


import com.google.gson.Gson;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import javafx.scene.input.KeyEvent;
import requestHelper.PostRequest;
import validationHelper.ValidationHelper;

import java.io.IOException;
import java.net.http.HttpResponse;

public class InsertController {

    @FXML
    private TextField nameField;

    @FXML
    private Label nameWarning;

    @FXML
    private TextField priceField;

    @FXML
    private Label priceWarning;

    @FXML
    private TextField netPriceField;

    @FXML
    private Label netPriceWarning;

    @FXML
    private TextField manufacturerField;

    @FXML
    private Label manufacturerWarning;

    @FXML
    private TextField quantityField;

    @FXML
    private Label quantityWarning;

    @FXML
    private Label statusLabel;



    @FXML
    public void addMedicine(ActionEvent event) throws IOException, InterruptedException {
        String name = nameField.getText();
        String price = priceField.getText();
        String netPrice = netPriceField.getText();
        String manufacturer = manufacturerField.getText();
        String quantity = quantityField.getText();
        boolean isAllValid = validateAll(name,price,netPrice,manufacturer,quantity);
        if(isAllValid == true){
            sendData(name,price,netPrice,manufacturer,quantity);
            resetAllInputs();
        }else{
            setWarnings();
        }
    }

    private void sendData(String name,String price,String netPrice,String manufacturer,String quantity) throws IOException, InterruptedException {
        Gson gson = new Gson();

        InsertToJson insertToJson = new InsertToJson(name,price,netPrice,manufacturer,quantity);
        String jsonStr = gson.toJson(insertToJson);
        PostRequest postRequest = new PostRequest();
        HttpResponse<String> response = postRequest.requestSender("http://localhost/MIS/API/insert-medicine.php",jsonStr);

        Gson gson1 = new Gson();
        InsertResponse insertResponse = gson1.fromJson(response.body(), InsertResponse.class);
        if(insertResponse.isStatus() == true){
            statusLabel.setText(insertResponse.getMessage());
            resetAllInputs();
            resetAllWarnings();
        }else{
            statusLabel.setText(insertResponse.getMessage());
            resetAllInputs();
        }
    }
    private void setWarnings(){
            nameWarning.setText("Must be alpha numeric 1 to 30 characters(No space)");
            priceWarning.setText("Must be number (No space)");
            netPriceWarning.setText("Must be number (No space)");
            manufacturerWarning.setText("Must be alpha numeric 1 to 30 characters(No space)");
            quantityWarning.setText("Must be number (No space)");
            nameField.requestFocus();
    }
    private boolean validateAll(String name,String price,String netPrice,String manufacturer,String quantity){
        ValidationHelper vh = new ValidationHelper();
        boolean isNameValid = vh.validateMedName(name);
        boolean isPriceValid = vh.validateNumber(price);
        boolean isNetPriceValid = vh.validateMedName(netPrice);
        boolean isManufacturerValid = vh.validateMedName(manufacturer);
        boolean isQuantityValid = vh.validateMedName(quantity);
        if(isNameValid == true && isPriceValid == true && isNetPriceValid == true && isManufacturerValid == true && isQuantityValid == true){
            return true;
        }else{
            return false;
        }
    }

    private void resetAllInputs(){
        nameField.setText("");
        priceField.setText("");
        netPriceField.setText("");
        manufacturerField.setText("");
        quantityField.setText("");
        nameField.requestFocus();
    }

    private void resetAllWarnings(){
        nameWarning.setText("");
        priceWarning.setText("");
        netPriceWarning.setText("");
        manufacturerWarning.setText("");
        quantityWarning.setText("");
    }

    @FXML
    public void clearStatus(KeyEvent event) {
        statusLabel.setText("");
    }

}


