package SS6.bai2;

import java.util.ArrayList;
import java.util.List;

class TicketPool {
    private String roomName;
    private int tickets;

    public TicketPool(String roomName, int tickets) {
        this.roomName = roomName;
        this.tickets = tickets;
    }

    public String getRoomName() {
        return roomName;
    }

    public synchronized void sellTicket(String sellerName) {
        while (tickets <= 0) {
            try {
                System.out.println(sellerName + ": Hết vé " + roomName + ", đang chờ...");
                wait();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }

        tickets--;
        System.out.println(sellerName + " bán vé thành công tại " + roomName + ". Còn lại: " + tickets);
    }

    public synchronized void addTickets(int amount) {
        this.tickets += amount;
        System.out.println("\n--- NHÀ CUNG CẤP: Đã thêm " + amount + " vé vào " + roomName + " ---");

        notifyAll();
    }
}

class BookingCounter extends Thread {
    private String counterName;
    private TicketPool pool;

    public BookingCounter(String name, TicketPool pool) {
        this.counterName = name;
        this.pool = pool;
    }

    @Override
    public void run() {
        for (int i = 0; i < 5; i++) {
            pool.sellTicket(counterName);
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                break;
            }
        }
    }
}

public class bai2 {
    public static void main(String[] args) throws InterruptedException {
        TicketPool roomA = new TicketPool("Phòng A", 2);

        BookingCounter counter1 = new BookingCounter("Quầy 1", roomA);
        BookingCounter counter2 = new BookingCounter("Quầy 2", roomA);

        counter1.start();
        counter2.start();

        Thread.sleep(3000);
        roomA.addTickets(5);

        counter1.join();
        counter2.join();
        System.out.println("Hết ca làm việc.");
    }
}