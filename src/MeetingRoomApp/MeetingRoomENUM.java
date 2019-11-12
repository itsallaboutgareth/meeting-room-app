package MeetingRoomApp;

public enum MeetingRoomENUM
{
  ONE("Meeting Room 1"),
  TWO("Meeting Room 2"),
  THREE("Meeting Room 3");


  private String room;

  MeetingRoomENUM(String meetRoom) {
    this.room = meetRoom;
  }

  public String meetRoom() {
    return room;
  }
}
