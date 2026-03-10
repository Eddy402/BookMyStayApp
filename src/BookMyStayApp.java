/**
 * UseCase2RoomInitialization
 *
 * Demonstrates room initialization using inheritance,
 * abstraction, and static availability.
 *
 * Book My Stay App - Version 2.1
 *
 * @author Edson
 * @version 2.1
 */

abstract class Room {

    private String roomType;
    private int beds;
    private double price;

    public Room(String roomType, int beds, double price) {
        this.roomType = roomType;
        this.beds = beds;
        this.price = price;
    }

    public String getRoomType() {
        return roomType;
    }

    public int getBeds() {
        return beds;
    }

    public double getPrice() {
        return price;
    }

    public abstract void displayRoomDetails();
}

/* Single Room Class */
class SingleRoom extends Room {

    public SingleRoom() {
        super("Single Room", 1, 3000);
    }

    @Override
    public void displayRoomDetails() {
        System.out.println("Room Type : " + getRoomType());
        System.out.println("Beds      : " + getBeds());
        System.out.println("Price     : ₹" + getPrice());
    }
}

/* Double Room Class */
class DoubleRoom extends Room {

    public DoubleRoom() {
        super("Double Room", 2, 5000);
    }

    @Override
    public void displayRoomDetails() {
        System.out.println("Room Type : " + getRoomType());
        System.out.println("Beds      : " + getBeds());
        System.out.println("Price     : ₹" + getPrice());
    }
}

/* Suite Room Class */
class SuiteRoom extends Room {

    public SuiteRoom() {
        super("Suite Room", 3, 9000);
    }

    @Override
    public void displayRoomDetails() {
        System.out.println("Room Type : " + getRoomType());
        System.out.println("Beds      : " + getBeds());
        System.out.println("Price     : ₹" + getPrice());
    }
}

/* Main Application Class */
public class BookMyStayApp {

    public static void main(String[] args) {

        System.out.println("====================================");
        System.out.println("      BOOK MY STAY APP v2.1");
        System.out.println("   Room Types & Availability");
        System.out.println("====================================");

        /* Creating room objects */
        Room single = new SingleRoom();
        Room doubleRoom = new DoubleRoom();
        Room suite = new SuiteRoom();

        /* Static availability variables */
        int singleAvailability = 10;
        int doubleAvailability = 6;
        int suiteAvailability = 3;

        System.out.println("\n--- Room Details ---\n");

        single.displayRoomDetails();
        System.out.println("Available : " + singleAvailability + " rooms\n");

        doubleRoom.displayRoomDetails();
        System.out.println("Available : " + doubleAvailability + " rooms\n");

        suite.displayRoomDetails();
        System.out.println("Available : " + suiteAvailability + " rooms\n");

        System.out.println("====================================");
        System.out.println("Thank you for using Book My Stay!");
    }
}