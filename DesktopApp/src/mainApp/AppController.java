package mainApp;

import javafx.fxml.FXML;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.layout.BorderPane;

import java.io.IOException;


public class AppController {
    @FXML
    private BorderPane mainBorderPane;


    @FXML
    public void showAll(ActionEvent event){
        Parent root = null;
        try{
            root = FXMLLoader.load(getClass().getResource("../showAll/showAll.fxml"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        mainBorderPane.setCenter(root);
    }

    @FXML
    public void orders(ActionEvent event){
        Parent root = null;
        try{
            root = FXMLLoader.load(getClass().getResource("../orders/orders.fxml"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        mainBorderPane.setCenter(root);
    }

    @FXML
    public void bills(ActionEvent event){
        Parent root = null;
        try{
            root = FXMLLoader.load(getClass().getResource("../bill/bill.fxml"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        mainBorderPane.setCenter(root);
    }

    @FXML
    public void insert(ActionEvent event){
        Parent root = null;
        try{
            root = FXMLLoader.load(getClass().getResource("../insert/insert.fxml"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        mainBorderPane.setCenter(root);
    }

    @FXML
    void register(ActionEvent event) {
        Parent root = null;
        try{
            root = FXMLLoader.load(getClass().getResource("../register/register.fxml"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        mainBorderPane.setCenter(root);
    }

    @FXML
    void updateMedicine(ActionEvent event) {
        Parent root = null;
        try{
            root = FXMLLoader.load(getClass().getResource("../updateMedicine/updateMedicine.fxml"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        mainBorderPane.setCenter(root);
    }

    @FXML
    void deleteMedicine(ActionEvent event) {
        Parent root = null;
        try{
            root = FXMLLoader.load(getClass().getResource("../deleteMedicine/deleteMedicine.fxml"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        mainBorderPane.setCenter(root);
    }

    @FXML
    void deleteUser(ActionEvent event) {
        Parent root = null;
        try{
            root = FXMLLoader.load(getClass().getResource("../deleteUser/deleteUser.fxml"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        mainBorderPane.setCenter(root);
    }

    @FXML
    void updateUser(ActionEvent event) {
        Parent root = null;
        try{
            root = FXMLLoader.load(getClass().getResource("../updateUser/updateUser.fxml"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        mainBorderPane.setCenter(root);
    }

    @FXML
    void manageOrder(ActionEvent event) {
        Parent root = null;
        try{
            root = FXMLLoader.load(getClass().getResource("../manageOrders/manageOrders.fxml"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        mainBorderPane.setCenter(root);
    }

}
