package sample;

import com.google.gson.Gson;
import javafx.fxml.FXML;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import mainApp.AppController;
import requestHelper.PostRequest;
import validationHelper.ValidationHelper;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class Controller {

    @FXML
    private TextField usernameField;

    @FXML
    private PasswordField passwordField;

    @FXML
    private Label errorLabel;

    @FXML
    private Label passwordWarningLabel;

    @FXML
    private Label usernameWarningLabel;

    private Stage stage;
    private Scene scene;
    private Parent root;


    @FXML
    public void login(ActionEvent event) throws IOException, InterruptedException {
        String username = usernameField.getText().toString();
        String password = passwordField.getText().toString();
        ValidationHelper vh = new ValidationHelper();
        boolean isUsernameValid = vh.validateUserName(username);
        boolean isPasswordValid = vh.validatePassword(password);
        if(isUsernameValid == true && isPasswordValid == true){
            boolean isValidServer = validateFromServer(username,password);
            if(isValidServer == true){
                FXMLLoader loader = new FXMLLoader(getClass().getResource("../mainApp/mainApp.fxml"));
                root = loader.load();

                AppController appController = loader.getController();

                stage = (Stage)((Node)event.getSource()).getScene().getWindow();
                scene = new Scene(root);
                stage.setScene(scene);
                stage.show();
            }else{
                errorLabel.setText("Incorrect username or password");
            }
        }else
        {
            usernameWarningLabel.setText("Must be alpha numeric 8 to 30 characters(No space)");
            passwordWarningLabel.setText("Must be alpha numeric 8 to 20 characters(No space)");
        }
    }

    public boolean validateFromServer(String username,String password) throws IOException, InterruptedException {
        Gson gson = new Gson();
        LoginToJson loginToJson = new LoginToJson(username, password);

        String jsonStr = gson.toJson(loginToJson);

        PostRequest postRequest = new PostRequest();
        HttpResponse<String> response = postRequest.requestSender("http://localhost/MIS/API/admin-login.php",jsonStr);
        Gson gson1 = new Gson();
        LoginResponse loginResponse =gson1.fromJson(response.body(),LoginResponse.class);
        if(loginResponse.isStatus() == true){
            return true;
        }else if(loginResponse.isStatus() == false){
            return false;
        }
        return false;
    }
}
