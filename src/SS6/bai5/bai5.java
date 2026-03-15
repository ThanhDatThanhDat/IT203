package SS6.bai5;

import java.util.*;

class Ticket {
    private String id;
    private String roomName;
    private boolean isSold = false;
    private boolean isHeld = false;
    private long holdExpiryTime = 0;
    private boolean isVIP = false;

    public Ticket(String id, String roomName, boolean isVIP) {
        this.id = id;
        this.roomName = roomName;
        this.isVIP = isVIP;
    }

    public String getId() { return id; }
    public boolean isAvailable() { return !isSold && !isHeld; }
    public boolean isExpired() { return isHeld && System.currentTimeMillis() > holdExpiryTime; }

    public void hold(long durationMs) {
        this.isHeld = true;
        this.holdExpiryTime = System.currentTimeMillis() + durationMs;
    }

    public void release() {
        this.isHeld = false;
        this.holdExpiryTime = 0;
    }

    public void sell() {
        this.isSold = true;
        this.isHeld = false;
    }

    @Override
    public String toString() {
        return id + (isVIP ? " (VIP)" : "");
    }
}

class TicketPool {
    private String roomName;
    private List<Ticket> tickets = new ArrayList<>();

    public TicketPool(String roomName, int count) {
        this.roomName = roomName;
        for (int i = 1; i <= count; i++) {
            tickets.add(new Ticket(roomName.substring(roomName.length() - 1) + "-" + String.format("%03d", i), roomName, i <= 2)); // 2 vé đầu là VIP
        }
    }

    public synchronized Ticket holdTicket(String counterName) throws InterruptedException {
        while (true) {
            for (Ticket t : tickets) {
                if (t.isAvailable()) {
                    t.hold(5000);
                    return t;
                }
            }
            System.out.println(counterName + ": Phòng " + roomName + " hết vé hoặc đang bị giữ, chờ...");
            wait(2000);
        }
    }

    public synchronized void sellHeldTicket(Ticket t, String counterName) {
        if (t != null && !t.isExpired()) {
            t.sell();
            System.out.println(">>> " + counterName + ": Thanh toán THÀNH CÔNG vé " + t);
        } else {
            System.out.println(">>> " + counterName + ": Thanh toán THẤT BẠI (Vé hết hạn hoặc lỗi)");
        }
    }

    public synchronized void releaseExpiredTickets() {
        for (Ticket t : tickets) {
            if (t.isExpired()) {
                t.release();
                System.out.println("TimeoutManager: Vé " + t.getId() + " hết hạn giữ, đã trả lại kho.");
                notifyAll();
            }
        }
    }

    public String getRoomName() { return roomName; }
}

class TimeoutManager extends Thread {
    private List<TicketPool> pools;

    public TimeoutManager(List<TicketPool> pools) {
        this.pools = pools;
        this.setDaemon(true);
    }

    @Override
    public void run() {
        while (true) {
            for (TicketPool pool : pools) {
                pool.releaseExpiredTickets();
            }
            try { Thread.sleep(1000); } catch (InterruptedException e) { break; }
        }
    }
}

class BookingCounter extends Thread {
    private String counterName;
    private TicketPool pool;
    private boolean willPay;

    public BookingCounter(String name, TicketPool pool, boolean willPay) {
        this.counterName = name;
        this.pool = pool;
        this.willPay = willPay;
    }

    @Override
    public void run() {
        try {
            Ticket t = pool.holdTicket(counterName);
            System.out.println(counterName + ": Đã giữ vé " + t + ". Vui lòng thanh toán trong 5s.");
            Thread.sleep(3000);

            if (willPay) {
                pool.sellHeldTicket(t, counterName);
            } else {
                System.out.println(counterName + ": Khách hàng rời đi, không thanh toán.");
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

public class bai5 {
    public static void main(String[] args) throws InterruptedException {
        TicketPool roomA = new TicketPool("Phòng A", 3);
        TicketPool roomB = new TicketPool("Phòng B", 3);
        List<TicketPool> allPools = Arrays.asList(roomA, roomB);

        TimeoutManager timer = new TimeoutManager(allPools);
        timer.start();

        BookingCounter c1 = new BookingCounter("Quầy 1", roomA, true);
        BookingCounter c2 = new BookingCounter("Quầy 2", roomA, false);
        BookingCounter c3 = new BookingCounter("Quầy 3", roomA, true);

        c1.start();
        Thread.sleep(500);
        c2.start();
        Thread.sleep(500);
        c3.start();

        c1.join();
        c2.join();
        c3.join();

        System.out.println("\n=== HẾT PHIÊN GIAO DỊCH ===");
    }
}