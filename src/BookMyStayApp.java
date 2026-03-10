import java.util.LinkedList;
import java.util.Queue;

/**
 * UseCase5BookingRequestQueue
 *
 * Demonstrates booking request handling using a Queue
 * to ensure First-Come-First-Served processing.
 *
 * Book My Stay App - Version 5.1
 *
 * @author Edson
 * @version 5.1
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

    public void displayReservation() {
        System.out.println("Guest : " + guestName + " | Requested Room : " + roomType);
    }
}

/* ---------------- Booking Request Queue ---------------- */

class BookingRequestQueue {

    private Queue<Reservation> requestQueue;

    public BookingRequestQueue() {
        requestQueue = new LinkedList<>();
    }

    /* Add booking request */
    public void addRequest(Reservation reservation) {
        requestQueue.add(reservation);
        System.out.println("Booking request added for " + reservation.getGuestName());
    }

    /* Display all requests in queue */
    public void displayQueue() {

        System.out.println("\nCurrent Booking Request Queue");
        System.out.println("--------------------------------");

        for (Reservation r : requestQueue) {
            r.displayReservation();
        }
    }
}

/* ---------------- Main Application ---------------- */

public class BookMyStayApp {

    public static void main(String[] args) {

        System.out.println("====================================");
        System.out.println("        BOOK MY STAY APP v5.1");
        System.out.println("     Booking Request Queue System");
        System.out.println("====================================");

        /* Initialize booking queue */
        BookingRequestQueue bookingQueue = new BookingRequestQueue();

        /* Simulate guest booking requests */
        Reservation r1 = new Reservation("Alice", "Single Room");
        Reservation r2 = new Reservation("Bob", "Double Room");
        Reservation r3 = new Reservation("Charlie", "Suite Room");

        /* Add requests to queue */
        bookingQueue.addRequest(r1);
        bookingQueue.addRequest(r2);
        bookingQueue.addRequest(r3);

        /* Display queue order */
        bookingQueue.displayQueue();

        System.out.println("\nRequests stored in FIFO order.");
        System.out.println("Room allocation will be handled later.");
    }
}