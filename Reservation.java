public class Reservation { //our Reservation class
  private String name; // declaring a String attribute to represent a name
  private Room roomReserved; // declaring a Room attribute to represent a room

  public Reservation (Room roomWanted, String guestName) { //constructor that initializes the attributes
    this.name = guestName; //uses the String input to initialize the String attribute
    this.roomReserved = roomWanted; //uses the Room input to initialize the Room attribute
  }
  
  public String getName() { //method to get the private attribute representing a name
   return this.name; //returning the String attribute to retrieve it when this method is called
  }
  
  public Room getRoom() {  //method to get the private attribute representing a Room
   return this.roomReserved; //returning the Room attribute to retrieve it when this method is called
  }
}

