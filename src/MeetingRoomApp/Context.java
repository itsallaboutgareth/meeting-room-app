package MeetingRoomApp;

public class Context {

  private static String loggedInUser = null;

  public static String getLoggedInUser() {
    return loggedInUser;
  }

  public static void setLoggedInUser(String loggedInUser) {
    Context.loggedInUser = loggedInUser;
  }

}
