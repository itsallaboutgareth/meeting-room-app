package MeetingRoomApp;

import java.sql.*;

public class DatabaseConnector {

  Connection conn = null;

  public DatabaseConnector() {

    try {
      //create MySQL connection

      String myDriver = "com.mysql.cj.jdbc.Driver";
      String myUrl = "jdbc:mysql://localhost/RoomDB";
      Class.forName(myDriver);
      conn = DriverManager.getConnection(myUrl, "root", "NW#vg0iMj2x^");

      //SQL SELECT QUERY
//      String query = "SELECT * FROM new_table";
//
//      //create the Java statement
//      Statement st = conn.createStatement();
//
//      //execute the query and get a java resultset
//      ResultSet rs = st.executeQuery(query);
//
//      //iterate through the java resultset
//      while (rs.next()) {
//        int id = rs.getInt("id");
//        System.out.println(id);
//        int meeting_room = rs.getInt("meeting_room");
//        System.out.println(meeting_room);
//        String user_name = rs.getString("user_name");
//        System.out.println(user_name);
//        Date date_time_from = rs.getDate("date_time_from");
//        System.out.println(date_time_from);

//      }
//
    } catch (ClassNotFoundException e) {
      e.printStackTrace();
    } catch (SQLException e) {
      e.printStackTrace();
  }
  }

  public ResultSet selectAllForRoom(String roomName) throws SQLException {
    String query = "SELECT * FROM new_table WHERE meeting_room=" + roomName;
    Statement st = conn.createStatement();
    ResultSet rs = st.executeQuery(query);

    return rs;
  }

  public ResultSet loginUser(String username, String password) throws SQLException {
    String query = "SELECT email FROM accounts WHERE email='" + username + "' AND password='" + password + "'";
    Statement st = conn.createStatement();
    ResultSet rs = st.executeQuery(query);
    return rs;
  }

  public ResultSet getTimesForRoom(String roomName, String bookingDate) throws SQLException {
    String query = "SELECT time_from, time_to FROM new_table WHERE meeting_room='" + roomName + "'" + " AND date_for='" + bookingDate + "'";
    Statement st = conn.createStatement();
    ResultSet rs = st.executeQuery(query);
    return rs;
  }

  public boolean putBookingInTable(String timeFrom, String timeTo, String username, String date, String roomName) throws SQLException {
    String query = "INSERT INTO `RoomDB`.`new_table` (`meeting_room`, `user_name`, `time_from`, `time_to`, `date_for`) VALUES ('" +
            roomName + "', '" +
            username + "', '" +
            timeFrom + "', '" +
            timeTo + "', '" +
            date + "')";
    System.out.println(query);
    Statement st = conn.createStatement();
    int rs = st.executeUpdate(query);

    if (rs == 1){
      return true;
    } else {
      return false;
    }
  }
}


