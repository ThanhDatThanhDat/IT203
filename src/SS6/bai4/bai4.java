package SS6.bai4;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

class Ticket {
    private String ticketId;
    private String roomName;
    private boolean isSold;

    public Ticket(String ticketId, String roomName) {
        this.ticketId = ticketId;
        this.roomName = roomName;
        this.isSold = false;
    }

    public String getTicketId() { return ticketId; }
    public String getRoomName() { return roomName; }
    public boolean isSold() { return isSold; }
    public void setSold(boolean sold) { isSold = sold; }
}

class TicketPool {
    private String roomName;
    private List<Ticket> tickets;

    public TicketPool(String roomName, int quantity) {
        this.roomName = roomName;
        this.tickets = new ArrayList<>();
        for (int i = 1; i <= quantity; i++) {
            String id = roomName.substring(roomName.length() - 1) + "-" + String.format("%03d", i);
            tickets.add(new Ticket(id, roomName));
        }
    }

    public synchronized Ticket sellTicket() {
        for (Ticket t : tickets) {
            if (!t.isSold()) {
                t.setSold(true);
                return t;
            }
        }
        return null; // Hết vé
    }

    public synchronized boolean hasTickets() {
        for (Ticket t : tickets) {
            if (!t.isSold()) return true;
        }
        return false;
    }

    public String getRoomName() { return roomName; }
}
class BookingCounter implements Runnable {
    private String counterName;
    private TicketPool roomA;
    private TicketPool roomB;
    private int soldCount = 0;
    private Random random = new Random();

    public BookingCounter(String name, TicketPool a, TicketPool b) {
        this.counterName = name;
        this.roomA = a;
        this.roomB = b;
    }

    @Override
    public void run() {
        while (roomA.hasTickets() || roomB.hasTickets()) {
            Ticket soldTicket = null;

            if (random.nextBoolean()) {
                soldTicket = roomA.sellTicket();
                if (soldTicket == null) soldTicket = roomB.sellTicket();
            } else {
                soldTicket = roomB.sellTicket();
                if (soldTicket == null) soldTicket = roomA.sellTicket();
            }

            if (soldTicket != null) {
                soldCount++;
                System.out.println(counterName + " đã bán vé: " + soldTicket.getTicketId() + " (Phòng " + soldTicket.getRoomName() + ")");
            }

            try { Thread.sleep(100); } catch (InterruptedException e) {}
        }
    }

    public int getSoldCount() { return soldCount; }
}

public class bai4 {
    public static void main(String[] args) throws InterruptedException {
        // Khởi tạo kho vé
        TicketPool roomA = new TicketPool("Phòng A", 10);
        TicketPool roomB = new TicketPool("Phòng B", 10);

        BookingCounter bc1 = new BookingCounter("Quầy 1", roomA, roomB);
        BookingCounter bc2 = new BookingCounter("Quầy 2", roomA, roomB);

        Thread t1 = new Thread(bc1);
        Thread t2 = new Thread(bc2);

        System.out.println("=== MỞ CỬA BÁN VÉ ===");
        t1.start();
        t2.start();

        t1.join();
        t2.join();

        System.out.println("\n=== KẾT THÚC CHƯƠNG TRÌNH ===");
        System.out.println("Quầy 1 bán được: " + bc1.getSoldCount() + " vé");
        System.out.println("Quầy 2 bán được: " + bc2.getSoldCount() + " vé");

        int remainingA = roomA.hasTickets() ? 1 : 0;
        int remainingB = roomB.hasTickets() ? 1 : 0;
        System.out.println("Vé còn lại phòng A: " + (roomA.hasTickets() ? "Còn" : "0"));
        System.out.println("Vé còn lại phòng B: " + (roomB.hasTickets() ? "0" : "0"));
    }
}