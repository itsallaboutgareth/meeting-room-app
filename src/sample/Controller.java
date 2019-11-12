package sample;

import MeetingRoomApp.DatabaseConnector;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Controller {

  DatabaseConnector dbC = new DatabaseConnector();

  @FXML
  private void handleButtonAction(ActionEvent event) {
    ResultSet rs = null;
    try {
      rs = dbC.selectAllForRoom("1");
    } catch (SQLException e) {
      e.printStackTrace();
    }
    try {
      while (rs.next()) {
        System.out.println(rs.getInt("id"));
      }
    } catch (SQLException e) {
      e.printStackTrace();
    }
  }

}


