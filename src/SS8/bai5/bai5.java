package SS8.bai5;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

interface Observer {
    void update(int temperature);
}

interface Subject {
    void attach(Observer o);
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

    public void attach(Observer o) {
        observers.add(o);
    }

    public void notifyObservers() {
        for (Observer o : observers) {
            o.update(temperature);
        }
    }
}

class Light {
    public void off() {
        System.out.println("Đèn: Tắt");
    }
}

class Fan implements Observer {
    public void setLow() {
        System.out.println("Quạt: Chạy tốc độ thấp");
    }

    @Override
    public void update(int temp) {
        if (temp > 30) {
            System.out.println("Quạt: Nhiệt độ cao, chạy tốc độ mạnh");
        }
    }
}

class AirConditioner implements Observer {
    public void setTemp(int temp) {
        System.out.println("Điều hòa: Nhiệt độ = " + temp);
    }

    @Override
    public void update(int temp) {
        if (temp > 30) {
            System.out.println("Điều hòa: Tự động tăng cường làm mát cho " + temp + "°C");
        }
    }
}

interface Command {
    void execute();
}

class SleepModeCommand implements Command {
    private Light light;
    private AirConditioner ac;
    private Fan fan;

    public SleepModeCommand(Light light, AirConditioner ac, Fan fan) {
        this.light = light;
        this.ac = ac;
        this.fan = fan;
    }

    @Override
    public void execute() {
        System.out.println("SleepMode: Tắt đèn");
        System.out.println("SleepMode: Điều hòa set 28°C");
        System.out.println("SleepMode: Quạt tốc độ thấp");
        light.off();
        ac.setTemp(28);
        fan.setLow();
    }
}

public class bai5 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        Light light = new Light();
        Fan fan = new Fan();
        AirConditioner ac = new AirConditioner();
        TemperatureSensor sensor = new TemperatureSensor();

        sensor.attach(fan);
        sensor.attach(ac);

        Command sleepMode = new SleepModeCommand(light, ac, fan);

        while (true) {
            System.out.println("\n1. Kích hoạt chế độ ngủ | 2. Thay đổi nhiệt độ (giả lập) | 0. Thoát");
            System.out.print("Chọn: ");
            int choice = sc.nextInt();

            if (choice == 1) {
                sleepMode.execute();
            } else if (choice == 2) {
                System.out.print("Nhập nhiệt độ: ");
                int temp = sc.nextInt();
                sensor.setTemperature(temp);
            } else if (choice == 0) {
                break;
            }
        }
        sc.close();
    }
}