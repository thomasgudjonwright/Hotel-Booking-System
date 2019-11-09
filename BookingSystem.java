import java.util.Scanner;
import java.util.Random;
public class BookingSystem {
  
  private static String[] typeOfRooms = {"double","queen","king"};
  private static Random r = new Random(123);
  
  //returns a random String from the above array. 
  private static String getRandomType(){
    int index = r.nextInt(typeOfRooms.length);
    return typeOfRooms[index];
  }
  //returns a random number of rooms between 5 and 50.
  private static int getRandomNumberOfRooms(){
    return r.nextInt(50)+1;
  }
  //End of provided code. 
  
  public static void main(String[] args){ 
    //Student Name: Thomas Wright
    //Student Number: 260769898
    System.out.println("Welcome to the Comp202 booking system.\nPlease enter the name of the hotel you'd like to book."); //welcoming the user
    Scanner read = new Scanner(System.in); //initializing our scanner
    String hotelName=read.nextLine(); //getting user input for the hotel name
    
    Room[] hotelRooms = new Room[getRandomNumberOfRooms()]; //declaring our room array with the the length of number of rooms
    for (int i = 0; i<hotelRooms.length; i++) { //loop to iterate through each room
      Room hotelRoom = new Room(getRandomType()); //initializing the room at the start of the loop
      hotelRooms[i] = hotelRoom; //initializing the room array with a room so it the elements aren't null
    }
    Hotel myHotel = new Hotel(hotelName, hotelRooms); //declaring and initializing our new hotel
    
    while(true) { //this loop will run until something breaks it. In our case, until option 5 is selected
      System.out.println("\n*********************************************************************"); // \n to skip a line before this prints
      System.out.println("Welcome to " + hotelName + ". Please select one of the following options: ");
      System.out.println("1) Make a reservation");
      System.out.println("2) Cancel a reservation");//printing the hotel booking menu
      System.out.println("3) See an invoice"); //printed each line seperately for easier reading
      System.out.println("4) See hotel info");
      System.out.println("5) Exit the Booking System");
      System.out.println("*********************************************************************\n"); // \n to skip a line after this prints
      
      int choice = read.nextInt(); //We can assume that the user is always inputting a variable
      if (choice == 1) { //the user chooses to make a reservation
        System.out.println("Please enter your name: "); //asking the user for an input to be the guest's name used to book a room
        String guestName = read.next(); //taking the user input as the guest's name
        read.nextLine(); //clearing the buffer
        System.out.println("What type of room would you like to reserve?"); //asking user for an input to be what type of room they reserved
        String roomType = read.nextLine(); //taking the user input as the room type for their reservation
        myHotel.createReservation(guestName, roomType);  //creating the specific reservation by calling the createreservation method
      }
      else if (choice == 2) { //the user chooses to cancel a reservation
        System.out.println("Please enter the name you used to book your reservation."); //asking the user for an input to be the guest's name used to book a room
        String guestName = read.next(); //taking the user input as the guest's name
        read.nextLine(); //clearing the buffer
        System.out.println("What type of room did you reserve?"); //asking user for an input to be what type of room they reserved
        String roomType = read.nextLine(); //taking the user input as the room type for their reservation
        myHotel.cancelReservation(guestName, roomType); //cancelling the specific reservation by calling the cancelreservation method
      }
      else if (choice == 3) { //the user chooses to get an invoice
        System.out.println("Please enter your name: "); //asking the user for an input to be the guest's name
        String guestName = read.next(); //taking user input as the guest's name
        read.nextLine(); //skipping the buffer
        myHotel.printInvoice(guestName); //calling the printInvoice method on a specific guest to show their invoice
      }
      else if (choice == 4) { //the user chooses to see hotel info
        System.out.println(myHotel.toString()); //printing the hotel info by calling the toString method
      }
      else if (choice == 5) { //the user chooses to exit the booking system
        return; //ending the program
      }
      else{ //the user has not chosen one of the options
        System.out.println("Invalid input. Please select an option 1 through 5."); //informing the user of their mistake
      }
    }
  }
}