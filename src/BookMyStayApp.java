import java.util.*;

/**
 * UseCase6RoomAllocationService
 *
 * Demonstrates reservation confirmation and safe room allocation
 * using Queue, HashMap, and Set to prevent double booking.
 *
 * Book My Stay App - Version 6.1
 *
 * @author Edson
 * @version 6.1
 */

/* ---------------- Reservation Class ---------------- */

class Reservation {

    private String guestName;
    private String roomType;

    public Reservation(String guestName, String roomType) {
        this.guestName = guestName;
        this.roomType = roomType;
    }

    public String getGuestName() {
        return guestName;
    }

    public String getRoomType() {
        return roomType;
    }
}

/* ---------------- Inventory Service ---------------- */

class InventoryService {

    private HashMap<String, Integer> inventory;

    public InventoryService() {
        inventory = new HashMap<>();

        inventory.put("Single Room", 2);
        inventory.put("Double Room", 2);
        inventory.put("Suite Room", 1);
    }

    public int getAvailability(String roomType) {
        return inventory.getOrDefault(roomType, 0);
    }

    public void decrementRoom(String roomType) {
        int current = inventory.get(roomType);
        inventory.put(roomType, current - 1);
    }

    public void displayInventory() {
        System.out.println("\nCurrent Inventory:");
        for (Map.Entry<String, Integer> entry : inventory.entrySet()) {
            System.out.println(entry.getKey() + " : " + entry.getValue());
        }
    }
}

/* ---------------- Booking Service ---------------- */

class BookingService {

    private Queue<Reservation> requestQueue;
    private InventoryService inventoryService;

    /* Map: RoomType → Set of Allocated Room IDs */
    private HashMap<String, Set<String>> allocatedRooms;

    public BookingService(Queue<Reservation> requestQueue, InventoryService inventoryService) {

        this.requestQueue = requestQueue;
        this.inventoryService = inventoryService;
        this.allocatedRooms = new HashMap<>();
    }

    /* Generate unique room ID */
    private String generateRoomId(String roomType) {

        String prefix = roomType.replace(" ", "").substring(0, 2).toUpperCase();
        int number = new Random().nextInt(900) + 100;

        return prefix + number;
    }

    /* Process reservations */
    public void processBookings() {

        System.out.println("\nProcessing Booking Requests...");
        System.out.println("--------------------------------");

        while (!requestQueue.isEmpty()) {

            Reservation reservation = requestQueue.poll();
            String roomType = reservation.getRoomType();

            int available = inventoryService.getAvailability(roomType);

            if (available > 0) {

                String roomId;

                allocatedRooms.putIfAbsent(roomType, new HashSet<>());

                do {
                    roomId = generateRoomId(roomType);
                }
                while (allocatedRooms.get(roomType).contains(roomId));

                allocatedRooms.get(roomType).add(roomId);

                inventoryService.decrementRoom(roomType);

                System.out.println("Reservation Confirmed for " +
                        reservation.getGuestName() +
                        " | Room Type: " + roomType +
                        " | Room ID: " + roomId);
            }
            else {
                System.out.println("Sorry " + reservation.getGuestName() +
                        ", no " + roomType + " available.");
            }
        }
    }

    public void displayAllocatedRooms() {

        System.out.println("\nAllocated Rooms:");
        System.out.println("--------------------");

        for (Map.Entry<String, Set<String>> entry : allocatedRooms.entrySet()) {

            System.out.println(entry.getKey() + " → " + entry.getValue());
        }
    }
}

/* ---------------- Main Application ---------------- */

public class BookMyStayApp {

    public static void main(String[] args) {

        System.out.println("====================================");
        System.out.println("       BOOK MY STAY APP v6.1");
        System.out.println("      Room Allocation Service");
        System.out.println("====================================");

        /* Create booking queue */
        Queue<Reservation> requestQueue = new LinkedList<>();

        requestQueue.add(new Reservation("Alice", "Single Room"));
        requestQueue.add(new Reservation("Bob", "Double Room"));
        requestQueue.add(new Reservation("Charlie", "Single Room"));
        requestQueue.add(new Reservation("David", "Suite Room"));

        /* Initialize services */
        InventoryService inventory = new InventoryService();
        BookingService bookingService = new BookingService(requestQueue, inventory);

        /* Process bookings */
        bookingService.processBookings();

        /* Show allocations */
        bookingService.displayAllocatedRooms();

        /* Show updated inventory */
        inventory.displayInventory();
    }
}