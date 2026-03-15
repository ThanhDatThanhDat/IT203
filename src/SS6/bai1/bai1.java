package SS6.bai1;

import java.util.concurrent.atomic.AtomicInteger;

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

    public synchronized boolean hasTicket() {
        return tickets > 0;
    }

    public synchronized void sellTicket() {
        if (tickets > 0) tickets--;
    }

    public synchronized int getRemaining() {
        return tickets;
    }
}

class BookingCounter extends Thread {
    private String counterName;
    private TicketPool poolA;
    private TicketPool poolB;
    private boolean fixDeadlock;

    public BookingCounter(String name, TicketPool p1, TicketPool p2, boolean fixDeadlock) {
        this.counterName = name;
        this.poolA = p1;
        this.poolB = p2;
        this.fixDeadlock = fixDeadlock;
    }

    @Override
    public void run() {
        for (int i = 0; i < 5; i++) {
            sellCombo();
            try { Thread.sleep(100); } catch (InterruptedException e) {}
        }
    }

    private void sellCombo() {
        TicketPool firstLock, secondLock;

        if (fixDeadlock) {
            if (poolA.getRoomName().compareTo(poolB.getRoomName()) < 0) {
                firstLock = poolA;
                secondLock = poolB;
            } else {
                firstLock = poolB;
                secondLock = poolA;
            }
        } else {
            firstLock = poolA;
            secondLock = poolB;
        }

        System.out.println(counterName + " đang thử lấy khóa: " + firstLock.getRoomName());
        synchronized (firstLock) {
            System.out.println(counterName + " ĐÃ GIỮ khóa: " + firstLock.getRoomName());

            try { Thread.sleep(50); } catch (InterruptedException e) {}

            System.out.println(counterName + " đang chờ lấy khóa: " + secondLock.getRoomName());
            synchronized (secondLock) {
                if (poolA.hasTicket() && poolB.hasTicket()) {
                    poolA.sellTicket();
                    poolB.sellTicket();
                    System.out.println(">>> " + counterName + " BÁN COMBO THÀNH CÔNG!");
                } else {
                    System.out.println(">>> " + counterName + " thất bại: Một trong hai phòng hết vé.");
                }
            }
        }
    }
}

public class bai1 {
    public static void main(String[] args) throws InterruptedException {
        TicketPool roomA = new TicketPool("Phòng A", 10);
        TicketPool roomB = new TicketPool("Phòng B", 10);

        System.out.println("=== BẮT ĐẦU CHẠY THỬ NGHIỆM DEADLOCK ===");
        BookingCounter c1 = new BookingCounter("Quầy 1", roomA, roomB, false);
        BookingCounter c2 = new BookingCounter("Quầy 2", roomB, roomA, false);

        c1.start();
        c2.start();

        Thread.sleep(3000);
        System.out.println("\n--- Bạn có thấy chương trình bị treo không? Đó là Deadlock! ---");
        System.out.println("--- Dừng chương trình và chạy lại với chế độ FIX_DEADLOCK = true ---\n");

        /* 2: PHÒNG CHỐNG DEADLOCK
        // Sửa tham số cuối thành 'true' để kích hoạt logic sắp xếp khóa
        BookingCounter c3 = new BookingCounter("Quầy 3", roomA, roomB, true);
        BookingCounter c4 = new BookingCounter("Quầy 4", roomB, roomA, true);
        c3.start();
        c4.start();
        */
    }
}