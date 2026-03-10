import java.util.HashMap;

/**
 * UseCase4RoomSearch
 *
 * Demonstrates read-only room search using centralized inventory.
 * Only available rooms are displayed.
 *
 * Book My Stay App - Version 4.1
 *
 * @author Edson
 * @version 4.1
 */

/* ---------------- Room Domain Model ---------------- */

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

    public abstract void displayDetails();
}

/* Concrete Room Types */

class SingleRoom extends Room {

    public SingleRoom() {
        super("Single Room", 1, 3000);
    }

    public void displayDetails() {
        System.out.println("Room Type : " + getRoomType());
        System.out.println("Beds      : " + getBeds());
        System.out.println("Price     : ₹" + getPrice());
    }
}

class DoubleRoom extends Room {

    public DoubleRoom() {
        super("Double Room", 2, 5000);
    }

    public void displayDetails() {
        System.out.println("Room Type : " + getRoomType());
        System.out.println("Beds      : " + getBeds());
        System.out.println("Price     : ₹" + getPrice());
    }
}

class SuiteRoom extends Room {

    public SuiteRoom() {
        super("Suite Room", 3, 9000);
    }

    public void displayDetails() {
        System.out.println("Room Type : " + getRoomType());
        System.out.println("Beds      : " + getBeds());
        System.out.println("Price     : ₹" + getPrice());
    }
}

/* ---------------- Inventory (State Holder) ---------------- */

class RoomInventory {

    private HashMap<String, Integer> inventory;

    public RoomInventory() {

        inventory = new HashMap<>();

        inventory.put("Single Room", 5);
        inventory.put("Double Room", 2);
        inventory.put("Suite Room", 0); // unavailable
    }

    public int getAvailability(String roomType) {
        return inventory.getOrDefault(roomType, 0);
    }
}

/* ---------------- Search Service ---------------- */

class RoomSearchService {

    private RoomInventory inventory;

    public RoomSearchService(RoomInventory inventory) {
        this.inventory = inventory;
    }

    public void searchAvailableRooms(Room[] rooms) {

        System.out.println("\nAvailable Rooms");
        System.out.println("---------------------------");

        for (Room room : rooms) {

            int available = inventory.getAvailability(room.getRoomType());

            if (available > 0) {   // Validation check
                room.displayDetails();
                System.out.println("Available : " + available + " rooms\n");
            }
        }
    }
}

/* ---------------- Main Application ---------------- */

public class BookMyStayApp {

    public static void main(String[] args) {

        System.out.println("====================================");
        System.out.println("        BOOK MY STAY APP v4.1");
        System.out.println("        Room Search Service");
        System.out.println("====================================");

        /* Initialize inventory */
        RoomInventory inventory = new RoomInventory();

        /* Create room objects */
        Room[] rooms = {
                new SingleRoom(),
                new DoubleRoom(),
                new SuiteRoom()
        };

        /* Search service */
        RoomSearchService searchService = new RoomSearchService(inventory);

        /* Perform room search */
        searchService.searchAvailableRooms(rooms);

        System.out.println("Search completed. No inventory changes made.");
    }
}