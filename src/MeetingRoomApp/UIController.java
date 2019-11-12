package MeetingRoomApp;


import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;
import javafx.util.Pair;
import javafx.util.StringConverter;

import java.net.URL;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class UIController implements Initializable {

  @FXML // fx:id="MeetingRoomComboBox"
  private ComboBox<String> MeetingRoomComboBox;

  @FXML // fx:id="ComboBoxTimeFrom"
  private ComboBox<String> ComboBoxTimeFrom;

  @FXML // fx:id="ComboBoxTimeTo"
  private ComboBox<String> ComboBoxTimeTo;

  @FXML // fx:id="datePicker"
  private DatePicker datePicker;

  @FXML // fx:id="bookButton"
  private Button bookButton;

  @FXML // fx:id="circle800"
  private Circle circle800;
  @FXML // fx:id="circle815"
  private Circle circle815;
  @FXML // fx:id="circle830"
  private Circle circle830;
  @FXML // fx:id="circle845"
  private Circle circle845;
  @FXML // fx:id="circle900"
  private Circle circle900;
  @FXML // fx:id="circle915"
  private Circle circle915;
  @FXML // fx:id="circle930"
  private Circle circle930;
  @FXML // fx:id="circle945"
  private Circle circle945;
  @FXML // fx:id="circle1000"
  private Circle circle1000;
  @FXML // fx:id="circle1015"
  private Circle circle1015;
  @FXML // fx:id="circle1030"
  private Circle circle1030;
  @FXML // fx:id="circle1045"
  private Circle circle1045;
  @FXML // fx:id="circle1100"
  private Circle circle1100;
  @FXML // fx:id="circle1115"
  private Circle circle1115;
  @FXML // fx:id="circle1130"
  private Circle circle1130;
  @FXML // fx:id="circle1145"
  private Circle circle1145;
  @FXML // fx:id="circle1200"
  private Circle circle1200;
  @FXML // fx:id="circle1215"
  private Circle circle1215;
  @FXML // fx:id="circle1230"
  private Circle circle1230;
  @FXML // fx:id="circle1245"
  private Circle circle1245;
  @FXML // fx:id="circle1300"
  private Circle circle1300;
  @FXML // fx:id="circle1315"
  private Circle circle1315;
  @FXML // fx:id="circle1330"
  private Circle circle1330;
  @FXML // fx:id="circle1345"
  private Circle circle1345;
  @FXML // fx:id="circle1400"
  private Circle circle1400;
  @FXML // fx:id="circle1415"
  private Circle circle1415;
  @FXML // fx:id="circle1430"
  private Circle circle1430;
  @FXML // fx:id="circle1445"
  private Circle circle1445;
  @FXML // fx:id="circle1500"
  private Circle circle1500;
  @FXML // fx:id="circle1515"
  private Circle circle1515;
  @FXML // fx:id="circle1530"
  private Circle circle1530;
  @FXML // fx:id="circle1545"
  private Circle circle1545;
  @FXML // fx:id="circle1600"
  private Circle circle1600;
  @FXML // fx:id="circle1615"
  private Circle circle1615;
  @FXML // fx:id="circle1630"
  private Circle circle1630;
  @FXML // fx:id="circle1645"
  private Circle circle1645;
  @FXML // fx:id="circle1700"
  private Circle circle1700;
  @FXML // fx:id="circle1715"
  private Circle circle1715;
  @FXML // fx:id="circle1730"
  private Circle circle1730;
  @FXML // fx:id="circle1745"
  private Circle circle1745;

  @FXML // fx:id="TimePane"
  private Pane TimePane;

  List<String> allTimes = new ArrayList<>();
  List<Pair<String, Circle>> allTimesLinked = new ArrayList<>();
  LocalDate lDC = LocalDate.now();
  DatabaseConnector dBC = new DatabaseConnector();

  @Override
  public void initialize(URL location, ResourceBundle resources) {
    populateTimesLists();
    datePicker.setValue(lDC);
    MeetingRoomComboBox.setValue("1");
    ComboBoxTimeFrom.setValue("8:00");
    ComboBoxTimeTo.setValue("9:00");
    reloadTimeDisplay();

    if (MeetingRoomComboBox != null) {
      MeetingRoomComboBox.getItems().setAll("1", "2", "3");
    }

    bookButton.setOnAction((event -> {
      String fromValue = ComboBoxTimeFrom.getValue().replace(":", "");
      String toValue = ComboBoxTimeTo.getValue().replace(":", "");

      boolean timeAvailable = true;

      if (Integer.valueOf(toValue) > Integer.valueOf(fromValue)) {
        for (Pair p : allTimesLinked) {
          if ((Integer.valueOf(p.getKey().toString()) >= Integer.valueOf(fromValue))
                  && (Integer.valueOf(p.getKey().toString()) < Integer.valueOf(toValue))) {
            Circle toCheckColour = (Circle) p.getValue();
            if (toCheckColour.getFill() == Paint.valueOf("RED")) {
              timeAvailable = false;
            }
          }
        }
      } else {
        System.out.println("To value must be higher than from value");
        TimePane.setStyle("-fx-background-color: #c86969");
        timeAvailable = false;
      }
      if (timeAvailable){
        try {
          dBC.putBookingInTable(ComboBoxTimeFrom.getValue().replace(":", ""), ComboBoxTimeTo.getValue().replace(":", ""),
                  Context.getLoggedInUser(), datePicker.getValue().toString(), MeetingRoomComboBox.getValue());
          reloadTimeDisplay();
          TimePane.setStyle("-fx-background-color: #78c86b");
        } catch (SQLException e) {
          System.out.println("SQL Exception");
        }
      } else {
        TimePane.setStyle("-fx-background-color: #c86969");
      }

    }));

    MeetingRoomComboBox.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
      @Override
      public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
        if (newValue != null){
          reloadTimeDisplay();
        }
      }
    });

    if (ComboBoxTimeFrom != null) {
      ComboBoxTimeFrom.getItems().setAll(allTimes);
    }

    ComboBoxTimeFrom.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
      @Override
      public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
        if (newValue != null){
        }
      }
    });

    if (ComboBoxTimeTo != null) {
      ComboBoxTimeTo.getItems().setAll(allTimes);
    }

    ComboBoxTimeTo.valueProperty().addListener(new ChangeListener<String>() {
      @Override
      public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
      }
    });

    datePicker.setOnAction(new EventHandler<ActionEvent>()
    {
      @Override
      public void handle(ActionEvent event)
      {
        LocalDate date = datePicker.getValue();
        reloadTimeDisplay();
      }
    });
  }

  // Reloads the time display line by fetching data from the database. This can then be used any time a different
  // time, date or room is selected to keep the page up-to-date.
  private void reloadTimeDisplay(){
    Circle tempCircle1 = null;
    for (Pair p : allTimesLinked){
      tempCircle1 = (Circle)p.getValue();
      tempCircle1.setFill(Paint.valueOf("GREEN"));
    }
    try {
      ResultSet bookingTimes = dBC.getTimesForRoom(MeetingRoomComboBox.getValue(), datePicker.getValue().toString());
      Circle tempCircle = null;
      while (bookingTimes.next()){
        for (Pair p : allTimesLinked){
          if ((Integer.valueOf(p.getKey().toString()) >= Integer.valueOf(bookingTimes.getString(1)))
                  && (Integer.valueOf(p.getKey().toString())< Integer.valueOf(bookingTimes.getString(2)))) {
            tempCircle = (Circle)p.getValue();
            tempCircle.setFill(Paint.valueOf("RED"));
          }
        }
      }
    } catch (SQLException e) {
      e.printStackTrace();
    }
  }

  private void populateTimesLists(){
    allTimes.add("8:00");
    allTimes.add("8:15");
    allTimes.add("8:30");
    allTimes.add("8:45");
    allTimes.add("9:00");
    allTimes.add("9:15");
    allTimes.add("9:30");
    allTimes.add("9:45");
    allTimes.add("10:00");
    allTimes.add("10:15");
    allTimes.add("10:30");
    allTimes.add("10:45");
    allTimes.add("11:00");
    allTimes.add("11:15");
    allTimes.add("11:30");
    allTimes.add("11:45");
    allTimes.add("12:00");
    allTimes.add("12:15");
    allTimes.add("12:30");
    allTimes.add("12:45");
    allTimes.add("13:00");
    allTimes.add("13:15");
    allTimes.add("13:30");
    allTimes.add("13:45");
    allTimes.add("14:00");
    allTimes.add("14:15");
    allTimes.add("14:30");
    allTimes.add("14:45");
    allTimes.add("15:00");
    allTimes.add("15:15");
    allTimes.add("15:30");
    allTimes.add("15:45");
    allTimes.add("16:00");
    allTimes.add("16:15");
    allTimes.add("16:30");
    allTimes.add("16:45");
    allTimes.add("17:00");
    allTimes.add("17:15");
    allTimes.add("17:30");
    allTimes.add("17:45");

    Pair temp = new Pair("800", circle800);
    allTimesLinked.add(temp);
    temp = new Pair("815", circle815);
    allTimesLinked.add(temp);
     temp = new Pair("830", circle830);
    allTimesLinked.add(temp);
     temp = new Pair("845", circle845);
    allTimesLinked.add(temp);
     temp = new Pair("900", circle900);
    allTimesLinked.add(temp);
     temp = new Pair("915", circle915);
    allTimesLinked.add(temp);
     temp = new Pair("930", circle930);
    allTimesLinked.add(temp);
     temp = new Pair("945", circle945);
    allTimesLinked.add(temp);
     temp = new Pair("1000", circle1000);
    allTimesLinked.add(temp);
     temp = new Pair("1015", circle1015);
    allTimesLinked.add(temp);
     temp = new Pair("1030", circle1030);
    allTimesLinked.add(temp);
     temp = new Pair("1045", circle1045);
    allTimesLinked.add(temp);
     temp = new Pair("1100", circle1100);
    allTimesLinked.add(temp);
     temp = new Pair("1115", circle1115);
    allTimesLinked.add(temp);
     temp = new Pair("1130", circle1130);
    allTimesLinked.add(temp);
     temp = new Pair("1145", circle1145);
    allTimesLinked.add(temp);
     temp = new Pair("1200", circle1200);
    allTimesLinked.add(temp);
     temp = new Pair("1215", circle1215);
    allTimesLinked.add(temp);
     temp = new Pair("1230", circle1230);
    allTimesLinked.add(temp);
     temp = new Pair("1245", circle1245);
    allTimesLinked.add(temp);
     temp = new Pair("1300", circle1300);
    allTimesLinked.add(temp);
     temp = new Pair("1315", circle1315);
    allTimesLinked.add(temp);
     temp = new Pair("1330", circle1330);
    allTimesLinked.add(temp);
     temp = new Pair("1345", circle1345);
    allTimesLinked.add(temp);
     temp = new Pair("1400", circle1400);
    allTimesLinked.add(temp);
     temp = new Pair("1415", circle1415);
    allTimesLinked.add(temp);
     temp = new Pair("1430", circle1430);
    allTimesLinked.add(temp);
     temp = new Pair("1445", circle1445);
    allTimesLinked.add(temp);
     temp = new Pair("1500", circle1500);
    allTimesLinked.add(temp);
     temp = new Pair("1515", circle1515);
    allTimesLinked.add(temp);
     temp = new Pair("1530", circle1530);
    allTimesLinked.add(temp);
     temp = new Pair("1545", circle1545);
    allTimesLinked.add(temp);
     temp = new Pair("1600", circle1600);
    allTimesLinked.add(temp);
     temp = new Pair("1615", circle1615);
    allTimesLinked.add(temp);
     temp = new Pair("1630", circle1630);
    allTimesLinked.add(temp);
     temp = new Pair("1645", circle1645);
    allTimesLinked.add(temp);
     temp = new Pair("1700", circle1700);
    allTimesLinked.add(temp);
     temp = new Pair("1715", circle1715);
    allTimesLinked.add(temp);
     temp = new Pair("1730", circle1730);
    allTimesLinked.add(temp);
     temp = new Pair("1745", circle1745);
    allTimesLinked.add(temp);
  }


}

