package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;


// main blue        #0598ff
// main orange      #ffa43c

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        try{
            Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
            primaryStage.setTitle("Desktop App");
            primaryStage.setScene(new Scene(root));
            Image image = new Image("/Images/LogoC.png");
            primaryStage.getIcons().add(image);
//        root.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
            primaryStage.show();
        }catch(Exception e){
            e.printStackTrace();
        }
    }


    public static void main(String[] args) {
        launch(args);
    }
}
