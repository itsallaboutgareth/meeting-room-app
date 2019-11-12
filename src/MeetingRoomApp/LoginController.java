package MeetingRoomApp;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class LoginController extends Application {

  /** Controls the login screen */
    @FXML private TextField InputEmail;
    @FXML private TextField InputPassword;
    @FXML private Button ButtonLogin;

    DatabaseConnector dbC = new DatabaseConnector();

  public void initialize() {
    System.out.println("Initialise.");

    // Handle Button event.
    ButtonLogin.setOnAction((event) -> {
//      System.out.println(InputEmail.getText());
      try {
        if (dbC.loginUser(InputEmail.getText(), InputPassword.getText()).next()){

          Context.setLoggedInUser(InputEmail.getText());
          Parent root;
          try {
            root = FXMLLoader.load(getClass().getClassLoader().getResource("MeetingRoomApp/UI.fxml"));
            Stage stage = new Stage();
            stage.setTitle("My New Stage Title");
            stage.setScene(new Scene(root, 640, 480));
            stage.show();
            // Hide this current window (if this is what you want)
            ((Node)(event.getSource())).getScene().getWindow().hide();
          }
          catch (IOException e) {
            e.printStackTrace();
          }
        };
      } catch (SQLException e) {
        e.printStackTrace();
      }
    });
  }






  @Override
  public void start(Stage primaryStage) throws Exception{
    Parent root = FXMLLoader.load(getClass().getResource("login.fxml"));
    primaryStage.setTitle("Login");
    primaryStage.setScene(new Scene(root, 640, 480));
    primaryStage.show();
    System.out.println("Begin login.");
  }







  public static void main(String[] args) {
    launch(args);
  }

  }

