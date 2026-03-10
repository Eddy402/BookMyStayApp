import java.util.HashMap;
import java.util.Map;

/**
 * UseCase3InventorySetup
 *
 * Demonstrates centralized room inventory management
 * using HashMap in the Book My Stay application.
 *
 * Book My Stay App - Version 3.1
 *
 * @author Edson
 * @version 3.1
 */

/* Inventory class responsible for managing room availability */
class RoomInventory {

    private HashMap<String, Integer> inventory;

    /* Constructor to initialize room availability */
    public RoomInventory() {
        inventory = new HashMap<>();

        inventory.put("Single Room", 10);
        inventory.put("Double Room", 6);
        inventory.put("Suite Room", 3);
    }

    /* Method to get availability of a specific room */
    public int getAvailability(String roomType) {
        return inventory.getOrDefault(roomType, 0);
    }

    /* Method to update availability */
    public void updateAvailability(String roomType, int newCount) {
        inventory.put(roomType, newCount);
    }

    /* Display full inventory */
    public void displayInventory() {
        System.out.println("\nCurrent Room Inventory:");
        System.out.println("-----------------------");

        for (Map.Entry<String, Integer> entry : inventory.entrySet()) {
            System.out.println(entry.getKey() + " : " + entry.getValue() + " rooms available");
        }
    }
}

/* Main Application Class */
public class BookMyStayApp {

    public static void main(String[] args) {

        System.out.println("====================================");
        System.out.println("      BOOK MY STAY APP v3.1");
        System.out.println("   Centralized Room Inventory");
        System.out.println("====================================");

        /* Initialize inventory */
        RoomInventory inventory = new RoomInventory();

        /* Display current inventory */
        inventory.displayInventory();

        /* Example update */
        System.out.println("\nUpdating Single Room availability...\n");
        inventory.updateAvailability("Single Room", 8);

        /* Display updated inventory */
        inventory.displayInventory();

        System.out.println("\nInventory setup complete.");
    }
}