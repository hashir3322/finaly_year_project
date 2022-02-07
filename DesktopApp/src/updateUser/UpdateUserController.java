package updateUser;

import java.io.IOException;
import java.net.http.HttpResponse;

import com.google.gson.Gson;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import register.Register;
import register.RegisterJson;
import register.RegisterResponse;
import requestHelper.GetRequest;
import requestHelper.PostRequest;
import validationHelper.ValidationHelper;

public class UpdateUserController {

    @FXML
    private TableView<Register> pharmaciesTable;

    @FXML
    private TableColumn<Register, String> idCol;

    @FXML
    private TableColumn<Register, String> nameCol;

    @FXML
    private TableColumn<Register, String> userNameCol;

    @FXML
    private TableColumn<Register, String> passwordCol;

    @FXML
    private TableColumn<Register, String> statusCol;

    @FXML
    private TableColumn<Register, String> licenseCol;


    @FXML
    private ComboBox<String> combobox;

    @FXML
    private TextField nameField;

    @FXML
    private TextField idField;


    @FXML
    private TextField userNameField;

    @FXML
    private TextField passwordField;

    @FXML
    private TextField licenseField;

    @FXML
    private Label warningLabel;

    @FXML
    private Label responseLabel;


    Register register = null;

    ObservableList<String> list = FXCollections.observableArrayList("pending","active");

    ObservableList<Register> registerList = FXCollections.observableArrayList();

    public void initialize() throws IOException, InterruptedException {

        dataLoader();
    }
    private void dataLoader() throws IOException, InterruptedException {
        refreshTable();
        combobox.setItems(list);
        idCol.setCellValueFactory(new PropertyValueFactory<>("user_id"));
        nameCol.setCellValueFactory(new PropertyValueFactory<>("pharmacy_name"));
        userNameCol.setCellValueFactory(new PropertyValueFactory<>("user_name"));
        passwordCol.setCellValueFactory(new PropertyValueFactory<>("user_password"));
        statusCol.setCellValueFactory(new PropertyValueFactory<>("user_status"));
        licenseCol.setCellValueFactory(new PropertyValueFactory<>("user_license"));

    }
    public void refreshTable() throws IOException, InterruptedException {
        GetRequest getRequest = new GetRequest();
        HttpResponse<String> response = getRequest.requestSender("http://localhost/MIS/API/all-registered.php");

        Gson gson = new Gson();
        RegisterJson[] registerJsonArray = gson.fromJson(response.body(),RegisterJson[].class);

        registerList.clear();

        for(RegisterJson reg : registerJsonArray) {
            registerList.add(new Register(reg.getUser_id(),reg.getPharmacy_name(),reg.getUser_name(),reg.getUser_password(),reg.getUser_status(),reg.getUser_license()));
            pharmaciesTable.setItems(registerList);
        }
    }

    @FXML
    public void updateUser(ActionEvent event) throws IOException, InterruptedException {
        boolean isValid = validateAll(nameField.getText(),idField.getText(),userNameField.getText(),passwordField.getText(),licenseField.getText());
        String status;
        if(combobox.getValue()!=null){
            status =  combobox.getValue();
        }else{
            status = "pending";
        }
        if(isValid == true){
            Gson gson = new Gson();

            UpdateToJson updateToJson = new UpdateToJson(idField.getText(),nameField.getText(),userNameField.getText(),passwordField.getText(),status,licenseField.getText());
            String jsonStr = gson.toJson(updateToJson);
            PostRequest postRequest = new PostRequest();
            HttpResponse<String> response = postRequest.requestSender("http://localhost/MIS/API/update-user.php",jsonStr);

            Gson gson1 = new Gson();
            RegisterResponse registerResponse = gson1.fromJson(response.body(), RegisterResponse.class);

            if(registerResponse.isStatus() == true){
                responseLabel.setText(registerResponse.getMessage());
                resetAllFields();
                dataLoader();
            }
            else if(registerResponse.isStatus() == false){
                responseLabel.setText(registerResponse.getMessage());
            }

        }else{
            warningLabel.setText("Invalid Input");
        }
    }
    public boolean validateAll(String name,String id,String username,String password,String license){
        ValidationHelper vh = new ValidationHelper();

        if(vh.validateName(name) == true && vh.validateNumber(id) == true && vh.validateUserName(username) == true && vh.validatePassword(password) && vh.validateLicense(license)){
            return true;
        }else{
            return false;
        }
    }

    @FXML
    void getSelectedId(KeyEvent event) {
        Register r = pharmaciesTable.getSelectionModel().getSelectedItem();
        if(r!=null){
            if(event.getCode().equals(KeyCode.ENTER)){
                combobox.setValue(r.getUser_status());
                nameField.setText(r.getPharmacy_name());
                idField.setText(r.getUser_id());
                userNameField.setText(r.getUser_name());
                passwordField.setText(r.getUser_password());
                licenseField.setText(r.getUser_license());
                nameField.requestFocus();
            }
        }
    }
    public void resetAllFields(){
        nameField.setText("");
        idField.setText("");
        userNameField.setText("");
        passwordField.setText("");
        licenseField.setText("");
        nameField.requestFocus();
    }
}
