package SS6.bai3;

import java.util.ArrayList;
import java.util.List;

class Ticket {
    private String code;
    public Ticket(String code) { this.code = code; }
    public String getCode() { return code; }
}

class TicketPool {
    private String roomName;
    private List<Ticket> tickets = new ArrayList<>();
    private int ticketCounter = 1;

    public TicketPool(String roomName, int initialCount) {
        this.roomName = roomName;
        addTickets(initialCount);
    }

    public synchronized void addTickets(int count) {
        for (int i = 0; i < count; i++) {
            String code = roomName.substring(roomName.length() - 1) + "-" + String.format("%03d", ticketCounter++);
            tickets.add(new Ticket(code));
        }
        System.out.println("--- NHÀ CUNG CẤP: Đã thêm " + count + " vé vào " + roomName + " ---");
    }

    public synchronized Ticket sellTicket() {
        if (!tickets.isEmpty()) {
            return tickets.remove(0);
        }
        return null;
    }

    public synchronized int getRemaining() {
        return tickets.size();
    }

    public String getRoomName() { return roomName; }
}

class TicketSupplier implements Runnable {
    private TicketPool poolA;
    private TicketPool poolB;
    private int supplyCount;
    private int interval;
    private int rounds;

    public TicketSupplier(TicketPool poolA, TicketPool poolB, int supplyCount, int interval, int rounds) {
        this.poolA = poolA;
        this.poolB = poolB;
        this.supplyCount = supplyCount;
        this.interval = interval;
        this.rounds = rounds;
    }

    @Override
    public void run() {
        try {
            for (int i = 0; i < rounds; i++) {
                Thread.sleep(interval); // Chờ theo khoảng thời gian định kỳ
                poolA.addTickets(supplyCount);
                poolB.addTickets(supplyCount);
            }
        } catch (InterruptedException e) {
            System.out.println("Nhà cung cấp bị gián đoạn.");
        }
    }
}

class BookingCounter extends Thread {
    private String counterName;
    private TicketPool pool;
    private int soldCount = 0;

    public BookingCounter(String name, TicketPool pool) {
        this.counterName = name;
        this.pool = pool;
    }

    @Override
    public void run() {
        long endTime = System.currentTimeMillis() + 10000;
        while (System.currentTimeMillis() < endTime) {
            Ticket t = pool.sellTicket();
            if (t != null) {
                soldCount++;
                System.out.println(counterName + " đã bán vé: " + t.getCode());
            }
            try {
                Thread.sleep(400);
            } catch (InterruptedException e) { break; }
        }
    }

    public int getSoldCount() { return soldCount; }
}

public class bai3 {
    public static void main(String[] args) throws InterruptedException {
        TicketPool roomA = new TicketPool("Phòng A", 5);
        TicketPool roomB = new TicketPool("Phòng B", 5);

        TicketSupplier supplierLogic = new TicketSupplier(roomA, roomB, 3, 3000, 3);
        Thread supplierThread = new Thread(supplierLogic);

        BookingCounter c1 = new BookingCounter("Quầy 1", roomA);
        BookingCounter c2 = new BookingCounter("Quầy 2", roomB);

        System.out.println("=== BẮT ĐẦU PHIÊN BÁN VÉ ===");
        c1.start();
        c2.start();
        supplierThread.start();

        c1.join();
        c2.join();
        supplierThread.join();

        System.out.println("\n=== KẾT THÚC CHƯƠNG TRÌNH ===");
        System.out.println("Quầy 1 bán được: " + c1.getSoldCount() + " vé");
        System.out.println("Quầy 2 bán được: " + c2.getSoldCount() + " vé");
        System.out.println("Vé còn lại phòng A: " + roomA.getRemaining());
        System.out.println("Vé còn lại phòng B: " + roomB.getRemaining());
    }
}