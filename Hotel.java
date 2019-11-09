import java.util.NoSuchElementException; //importing an exception we may encounter to properly catch it later on
public class Hotel {  //our Hotel class
  private String name; //declaring a String attribute to represent the hotel name
  private Room[] rooms; //declaring a Room array attribute to represent the hotel rooms
  private Reservation[] reservations; //declaring a Reservation array attribute to represent the hotel reservations
  
  public Hotel(String hotelName, Room[] roomList) { //constructor to initialize our attributes
    this.name = hotelName; //initializing the hotel name with the String input
    this.rooms = new Room[roomList.length]; //initializing the length of our room array to match the length of the inputted room array
    for (int i = 0; i<this.rooms.length; i++) { // a loop to iterate through the room array
      this.rooms[i] = roomList[i]; // initializing the elements of our room array with the corresponding elements of the inputted room array
    }
    reservations = new Reservation[this.rooms.length]; //initializing a reservation array that starts empty (each element is null) and has the same length as the room array
  }
  
  private void addReservation(Reservation booking) { //a method to add a reservation to our reservation array
    for (int i = 0; i<this.reservations.length; i++) { //a method to loop through the reservation array
      if (this.reservations[i] == null) { //if the element of the reservation array is null...
        this.reservations[i] = booking; //we can fill it with our new reservation
        break; //exiting the loop
      }
      else {
        continue; // if the element of the reservation array isn't null, there is already a reservation there so we continue checking the array for a null element
      }
    }
  }
  
  private void removeReservation(String guestName, String roomType) { //a method to remove a reservation from our reservation array
    boolean roomMatch = true; //a boolean seeing if the inputs match with a room in the reservation array or not
    int arrayIndex = 0; //an integer initialized that will find the position within the array of the matching reservation
    for (int i = 0; i<this.reservations.length; i++) { // a loop to iterate through the elements of the reservation array
      if (this.reservations[i] != null) { //if the element of the reservation array has a reservation stored and is not null...
        if (this.reservations[i].getName().equalsIgnoreCase(guestName) && this.reservations[i].getRoom().getType().equalsIgnoreCase(roomType)) { // and the reservation matches the inputted name and roomtype...
          arrayIndex = i; // we update our arrayIndex with the index of the matching reservation
          roomMatch = true; // update the boolean to confirm that we have found a room that is a match
          break; //exit the loop as we have found a match
        }
        else { // if no matching reservation has been found...
          roomMatch = false; // update the boolean to confirm that we have not found a room that is a match
        }
      }
    }
    if (roomMatch==true) { // if we have found a reservation that matches the inputted name and room type
      this.reservations[arrayIndex].getRoom().changeAvailability(); //change the availability of the room from the reservation to true
      this.reservations[arrayIndex] = null; //empty that element of the reservation array 
    }
    else { // if we found no such matching reservation...
      throw new NoSuchElementException(); //throw a NoSuchElementException
    }
  }
  
  public void createReservation(String guestName, String roomType) { //a method to create a reservation
    Room roomReserved = new Room(roomType); //initializing a new room of the inputted type
    roomReserved = roomReserved.findAvailableRoom(this.rooms, roomType); //checking if this type of room is available or not
      if (roomReserved != null) { //if the specific type of room is available...
        Reservation reserved = new Reservation (roomReserved, guestName); //create a new reservation of the specific room for a specific guest
        addReservation(reserved); //adding our new reservation to our reservation array
        reserved.getRoom().changeAvailability(); //changing the availability of the room in the reservation to false
        System.out.println("Thank you " + guestName + ". You have successfully booked a reservation. We look forward to your visit."); //inform the user of a succesfull booking
      }
      else { //if the specific type of room is not available...
        System.out.println("No rooms of such type are available. We apologize for the inconvenience."); //inform the user that no room of such type is available.
      }
  }
  
  public void cancelReservation(String guestName, String roomType) { // a method to cancel a reservation
    try { // if the following throws no exception, it will execute, if an exception is thrown, the catch is executed instead
      removeReservation(guestName, roomType); // removing the reservation matching the user inputs. *if error is thrown, see catch*
      System.out.println("A reservation under " + guestName + " has been cancelled for a " + roomType + " room."); //informing the user of a successful cancellation
    }
    catch(Exception e) { //if the try command throws an error...
      System.out.println("No reservation under " + guestName + " has been found for a " + roomType + " room."); // informing user that no reservations match the user inputs
    }
  }
  
  public void printInvoice(String name) { // a method to print the invoice of a specific guest
    double amountOwed = 0.00; //initializing the amount owed by a guest to zero
    for (int i = 0; i<reservations.length; i++) { // a loop to iterate through the reservation array
      if (reservations[i] != null) { // if the element at a specific index is not null/empty...
        if (reservations[i].getName().equalsIgnoreCase(name)) { // if the reservation belongs to the inputted guest 
          amountOwed += reservations[i].getRoom().getPrice(); //we add the price of the room the guest booked to their amount owed
        }
      }
    }
    System.out.println(name + "'s invoice is of $" + amountOwed); //printing out the amount the specific guest owes
  }
  
  public String toString() { // a method to convert the hotel information to a string
    String hotelName = this.name; //initializing a variable to be the hotel name
    int availableDoubles = 0; //initializing the amount of available double rooms to zero
    int availableQueens = 0; //initializing the amount of available queen rooms to zero
    int availableKings = 0; //initializing the amount of available king rooms to zero
    for (int i = 0; i<this.rooms.length; i++) { // a loop to iterate through the rooms array
      if (this.rooms[i] != null) {  // this is a double check to see if the room at a specific index is not null... 
        if (this.rooms[i].getAvailability()==true) { // if the room is available...
          if (this.rooms[i].getType().equalsIgnoreCase("double")) { // and the room is a double...
            availableDoubles+=1; // add one available double
          }
          else if (this.rooms[i].getType().equalsIgnoreCase("queen")) { // and the room is a queen...
            availableQueens+=1; // add one available queen
          }
          else if (this.rooms[i].getType().equalsIgnoreCase("king")) { // and the room is a king...
            availableKings+=1; // add one available king
          }
          else { // if the room is not available...
            continue; // continue checking our room array
          }
        }
      }
    } // the next line is concatenating all the information required into one string
    String info = "Here is the hotel info.\nHotel name: " + this.name + "\nAvailable Rooms: " + availableDoubles + " doubles, " + availableQueens + " queens, " + availableKings + " kings";
    return info; //returning our hotel information
  }
}
