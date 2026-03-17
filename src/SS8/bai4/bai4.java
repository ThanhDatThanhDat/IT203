package SS8.bai4;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

interface Observer {
    void update(int temperature);
}

interface Subject {
    void attach(Observer o);
    void detach(Observer o);
    void notifyObservers();
}

class TemperatureSensor implements Subject {
    private List<Observer> observers = new ArrayList<>();
    private int temperature;

    public void setTemperature(int temperature) {
        this.temperature = temperature;
        System.out.println("Cảm biến: Nhiệt độ = " + temperature);
        notifyObservers();
    }

    @Override
    public void attach(Observer o) {
        observers.add(o);
    }

    @Override
    public void detach(Observer o) {
        observers.remove(o);
    }

    @Override
    public void notifyObservers() {
        for (Observer observer : observers) {
            observer.update(temperature);
        }
    }
}

class Fan implements Observer {
    @Override
    public void update(int temperature) {
        if (temperature < 20) {
            System.out.println("Quạt: Nhiệt độ thấp, tự động TẮT");
        } else if (temperature <= 25) {
            System.out.println("Quạt: Nhiệt độ vừa phải, chạy tốc độ trung bình");
        } else {
            System.out.println("Quạt: Nhiệt độ cao, chạy tốc độ mạnh");
        }
    }
}

class Humidifier implements Observer {
    @Override
    public void update(int temperature) {
        System.out.println("Máy tạo ẩm: Điều chỉnh độ ẩm cho nhiệt độ " + temperature);
    }
}

public class bai4 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        TemperatureSensor sensor = new TemperatureSensor();

        Fan fan = new Fan();
        Humidifier humidifier = new Humidifier();

        sensor.attach(fan);
        System.out.println("Quạt: Đã đăng ký nhận thông báo");

        sensor.attach(humidifier);
        System.out.println("Máy tạo ẩm: Đã đăng ký");

        while (true) {
            System.out.println("\n1. Thay đổi nhiệt độ | 2. Hủy đăng ký Quạt | 0. Thoát");
            System.out.print("Chọn: ");
            int choice = sc.nextInt();

            if (choice == 1) {
                System.out.print("Nhập nhiệt độ mới: ");
                int temp = sc.nextInt();
                sensor.setTemperature(temp);
            } else if (choice == 2) {
                sensor.detach(fan);
                System.out.println("Đã hủy đăng ký Quạt.");
            } else if (choice == 0) {
                break;
            }
        }
        sc.close();
    }
}