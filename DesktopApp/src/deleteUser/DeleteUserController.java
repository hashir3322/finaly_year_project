package deleteUser;
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
import register.Register;
import register.RegisterJson;
import register.RegisterResponse;
import requestHelper.GetRequest;
import requestHelper.PostRequest;
import validationHelper.ValidationHelper;

import java.io.IOException;
import java.net.http.HttpResponse;

public class DeleteUserController {
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
    private TextField idField;

    @FXML
    private Label warningLabel;

    @FXML
    private Label responseLabel;


    Register register = null;

    ObservableList<Register> registerList = FXCollections.observableArrayList();


    public void initialize() throws IOException, InterruptedException {

        dataLoader();
    }

    private void dataLoader() throws IOException, InterruptedException {
        refreshTable();

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
    void deleteUser(ActionEvent event) throws IOException, InterruptedException {
        String id = idField.getText();
        ValidationHelper vh = new ValidationHelper();
        boolean isIdValid = vh.validateNumber(id);
        if(isIdValid == true){
            sendData(id);
        }else{
            warningLabel.setText("Invalid Input");
        }
    }

    public void sendData(String id)throws IOException, InterruptedException{
        Gson gson = new Gson();
        DeleteToJson deleteToJson = new DeleteToJson(id);
        String jsonStr = gson.toJson(deleteToJson);
        PostRequest postRequest = new PostRequest();
        HttpResponse<String> response = postRequest.requestSender("http://localhost/MIS/API/delete-user.php",jsonStr);
        Gson gson1 = new Gson();
        RegisterResponse registerResponse = gson1.fromJson(response.body(), RegisterResponse.class);

        if(registerResponse.isStatus() == true){
            responseLabel.setText(registerResponse.getMessage());
            idField.setText("");
            dataLoader();
        }
        else if(registerResponse.isStatus() == false){
            responseLabel.setText(registerResponse.getMessage());
        }

    }

    @FXML
    void getSelectedId(KeyEvent event) {
        Register r = pharmaciesTable.getSelectionModel().getSelectedItem();
        if(r!=null){
            if(event.getCode().equals(KeyCode.ENTER)){
                idField.setText(r.getUser_id());
                idField.requestFocus();
            }
        }
    }

}
