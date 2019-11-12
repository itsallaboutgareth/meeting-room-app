package MeetingRoomApp;

import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.stage.Stage;

public class Main extends Application {



    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("UI.fxml"));
        primaryStage.setTitle("Meeting Room Booking App");
        primaryStage.setScene(new Scene(root, 640, 480));
        primaryStage.show();

    }






    public static void main(String[] args) {
        launch(args);
    }
}
