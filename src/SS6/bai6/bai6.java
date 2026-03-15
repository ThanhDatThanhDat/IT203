package SS6.bai6;

import java.lang.management.ManagementFactory;
import java.lang.management.ThreadInfo;
import java.lang.management.ThreadMXBean;
import java.util.*;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;

class Room {
    private String name;
    private AtomicInteger totalTickets;
    private AtomicInteger soldTickets = new AtomicInteger(0);
    private final int price = 50000; // Giá vé đồng giá 50k

    public Room(String name, int tickets) {
        this.name = name;
        this.totalTickets = new AtomicInteger(tickets);
    }

    public synchronized boolean sellTicket() {
        if (soldTickets.get() < totalTickets.get()) {
            soldTickets.incrementAndGet();
            return true;
        }
        return false;
    }

    public void addMoreTickets(int count) {
        totalTickets.addAndGet(count);
    }

    public String getName() { return name; }
    public int getSold() { return soldTickets.get(); }
    public int getTotal() { return totalTickets.get(); }
    public long getRevenue() { return (long) soldTickets.get() * price; }
}

class SimulationControl {
    private AtomicBoolean isPaused = new AtomicBoolean(false);
    private final Object pauseLock = new Object();

    public void pause() { isPaused.set(true); }
    public void resume() {
        isPaused.set(false);
        synchronized (pauseLock) {
            pauseLock.notifyAll();
        }
    }

    public void checkPause() throws InterruptedException {
        if (isPaused.get()) {
            synchronized (pauseLock) {
                pauseLock.wait();
            }
        }
    }

    public boolean isPaused() { return isPaused.get(); }
}

class BookingCounter implements Runnable {
    private String name;
    private List<Room> rooms;
    private SimulationControl control;

    public BookingCounter(String name, List<Room> rooms, SimulationControl control) {
        this.name = name;
        this.rooms = rooms;
        this.control = control;
    }

    @Override
    public void run() {
        try {
            while (!Thread.currentThread().isInterrupted()) {
                control.checkPause();

                Room target = rooms.get(new Random().nextInt(rooms.size()));
                if (target.sellTicket()) {
                    System.out.println("[" + name + "] Đã bán 1 vé tại " + target.getName());
                }

                Thread.sleep(1500 + new Random().nextInt(1000));
            }
        } catch (InterruptedException e) {
            System.out.println(name + " đã dừng hoạt động.");
        }
    }
}

class DeadlockDetector {
    public static void check() {
        ThreadMXBean bean = ManagementFactory.getThreadMXBean();
        long[] threadIds = bean.findDeadlockedThreads();
        if (threadIds != null) {
            System.err.println("!!! PHÁT HIỆN DEADLOCK !!!");
            ThreadInfo[] infos = bean.getThreadInfo(threadIds);
            for (ThreadInfo info : infos) {
                System.err.println("- Thread: " + info.getThreadName() + " đang bị treo.");
            }
        } else {
            System.out.println(">>> Quét hệ thống: Không phát hiện Deadlock.");
        }
    }
}

public class bai6 {
    private static List<Room> rooms = new ArrayList<>();
    private static ExecutorService executor;
    private static SimulationControl control = new SimulationControl();
    private static Scanner scanner = new Scanner(System.in);
    private static boolean isRunning = false;

    public static void main(String[] args) {
        while (true) {
            System.out.println("\n--- MENU RẠP CHIẾU PHIM ---");
            System.out.println("1. Bắt đầu mô phỏng");
            System.out.println("2. Tạm dừng mô phỏng");
            System.out.println("3. Tiếp tục mô phỏng");
            System.out.println("4. Thêm vé vào phòng");
            System.out.println("5. Xem thống kê");
            System.out.println("6. Phát hiện deadlock");
            System.out.println("7. Thoát");
            System.out.print("Lựa chọn của bạn: ");

            int choice = scanner.nextInt();
            switch (choice) {
                case 1: startSimulation(); break;
                case 2: control.pause(); System.out.println("Đã tạm dừng."); break;
                case 3: control.resume(); System.out.println("Đã tiếp tục."); break;
                case 4: addTickets(); break;
                case 5: showStats(); break;
                case 6: DeadlockDetector.check(); break;
                case 7: stopSystem(); return;
                default: System.out.println("Lựa chọn không hợp lệ.");
            }
        }
    }

    private static void startSimulation() {
        if (isRunning) {
            System.out.println("Mô phỏng đang chạy rồi!");
            return;
        }
        System.out.print("Nhập số phòng: ");
        int numRooms = scanner.nextInt();
        System.out.print("Nhập số vé mỗi phòng: ");
        int numTickets = scanner.nextInt();
        System.out.print("Nhập số quầy: ");
        int numCounters = scanner.nextInt();

        for (int i = 0; i < numRooms; i++) {
            rooms.add(new Room("Phòng " + (char)('A' + i), numTickets));
        }

        executor = Executors.newFixedThreadPool(numCounters);
        for (int i = 1; i <= numCounters; i++) {
            executor.execute(new BookingCounter("Quầy " + i, rooms, control));
        }
        isRunning = true;
        System.out.println("Hệ thống khởi tạo thành công!");
    }

    private static void addTickets() {
        if (rooms.isEmpty()) {
            System.out.println("Chưa có phòng nào!");
            return;
        }
        System.out.print("Chọn số thứ tự phòng (0, 1...): ");
        int idx = scanner.nextInt();
        System.out.print("Số vé thêm: ");
        int count = scanner.nextInt();
        rooms.get(idx).addMoreTickets(count);
        System.out.println("Đã cập nhật vé.");
    }

    private static void showStats() {
        System.out.println("\n=== THỐNG KÊ HIỆN TẠI ===");
        long totalRevenue = 0;
        for (Room r : rooms) {
            System.out.println("- " + r.getName() + ": Đã bán " + r.getSold() + "/" + r.getTotal());
            totalRevenue += r.getRevenue();
        }
        System.out.println(">>> Tổng doanh thu: " + String.format("%,d", totalRevenue) + " VNĐ");
    }

    private static void stopSystem() {
        System.out.println("Đang dừng hệ thống...");
        if (executor != null) {
            executor.shutdownNow();
        }
        System.out.println("Kết thúc chương trình.");
    }
}