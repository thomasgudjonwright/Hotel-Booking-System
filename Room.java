    //Student Name: Thomas Wright
    //Student Number: 260769898
public class Room { //our Room class
  private String type; //declaring a String attribute to represent a room type
  private double price; //declaring a double attribute to represent a room price
  private boolean availability; //declaring a boolean attribute to represent a room's availability
  
  public Room(String roomType) { //constructor that initializes the attributes
    this.availability = true; //initialzie the room availability to true, as all rooms start available
    if (roomType.equalsIgnoreCase("double")) {  //if the room type is a queen
      this.type = "double"; //initialize the room type to double
      this.price = 90.00;  //intialize the room price in accordance to the room type
    }
    else if (roomType.equalsIgnoreCase("queen")) {  //is this allowed or bad formatting??****
      this.type = "queen"; //initialize the room type to queen
      this.price = 110.00;  //intialize the room price in accordance to the room type 
    }
    else if (roomType.equalsIgnoreCase("king")) { //is this allowed or bad formatting??****
      this.type = "king"; //initialize the room type to king
      this.price = 150.00;  //intialize the room price in accordance to the room type 
    }
    else { //if the room type inputted is not one of our three types of room available, we throw the following exception
      throw new IllegalArgumentException("No room of such type exists and therefore cannot be created. We apologize for the inconvenience."); 
    }
  }
  
  public String getType() { //method to get the private attribute representing the type of a room
    return this.type; //returning the String attribute to retrieve it when this method is called 
  }
  
  public double getPrice() { //method to get the private attribute representing the price of a room
    return this.price; //returning the double attribute to retrieve it when this method is called
  }
  
  public boolean getAvailability() { //method to get the private attribute representing the availability of a room
    return this.availability; //returning the boolean attribute to retrieve it when this method is called
  }
  
   public void changeAvailability() { //a method to change the availability of a room
     if (this.availability==true) { //if the availability before the change is true...
       this.availability = false; //we change it to false
     }
     else { //otherwise (ie: if the availability before the change is false)...
       this.availability = true; //we change it to true
     }
   }
   
   public Room findAvailableRoom(Room[] roomList, String roomType) { //a method to check to see if a specific type of room is available
     for (int i = 0; i<roomList.length; i++) { //a loop to iterate through all rooms of a room array
       if (roomList[i].getType().equalsIgnoreCase(roomType) && roomList[i].getAvailability()==true) { //if the room type matches the inputted room type and the room's availability is true...
        return roomList[i]; // we know that the room is available and we return the room at this index
       }
     }
     return null; // return null if no rooms in the room array of a specific type are available
   }
}