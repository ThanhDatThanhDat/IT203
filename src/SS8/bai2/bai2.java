package SS8.bai2;

import java.util.Scanner;

interface TemperatureSensor {
    double getTemperatureCelsius();
}

class OldThermometer {
    public int getTemperatureFahrenheit() {
        return 78;
    }
}

class ThermometerAdapter implements TemperatureSensor {
    private OldThermometer oldThermometer;

    public ThermometerAdapter(OldThermometer oldThermometer) {
        this.oldThermometer = oldThermometer;
    }

    @Override
    public double getTemperatureCelsius() {
        int fahrenheit = oldThermometer.getTemperatureFahrenheit();
        return (fahrenheit - 32) * 5.0 / 9.0;
    }
}

class Light {
    public void off() { System.out.println("FACADE: Tắt đèn"); }
}

class Fan {
    public void off() { System.out.println("FACADE: Tắt quạt"); }
    public void setLowSpeed() { System.out.println("FACADE: Quạt chạy tốc độ thấp"); }
}

class AirConditioner {
    public void off() { System.out.println("FACADE: Tắt điều hòa"); }
    public void setTemperature(int temp) { System.out.println("FACADE: Điều hòa set " + temp + "°C"); }
}

class SmartHomeFacade {
    private Light light;
    private Fan fan;
    private AirConditioner ac;
    private TemperatureSensor tempSensor;

    public SmartHomeFacade(Light light, Fan fan, AirConditioner ac, TemperatureSensor tempSensor) {
        this.light = light;
        this.fan = fan;
        this.ac = ac;
        this.tempSensor = tempSensor;
    }

    public void leaveHome() {
        System.out.println("--- CHẾ ĐỘ RỜI NHÀ ---");
        light.off();
        fan.off();
        ac.off();
    }

    public void sleepMode() {
        System.out.println("--- CHẾ ĐỘ NGỦ ---");
        light.off();
        ac.setTemperature(28);
        fan.setLowSpeed();
    }

    public void getCurrentTemperature() {
        double celsius = tempSensor.getTemperatureCelsius();
        System.out.printf("Nhiệt độ hiện tại: %.1f°C\n", celsius);
    }
}

public class bai2 {
    public static void main(String[] args) {
        OldThermometer oldSensor = new OldThermometer();
        TemperatureSensor adapter = new ThermometerAdapter(oldSensor);

        Light light = new Light();
        Fan fan = new Fan();
        AirConditioner ac = new AirConditioner();

        SmartHomeFacade smartHome = new SmartHomeFacade(light, fan, ac, adapter);
        Scanner sc = new Scanner(System.in);

        while (true) {
            System.out.println("\n1. Xem nhiệt độ | 2. Rời nhà | 3. Chế độ ngủ | 4. Thoát");
            int choice = sc.nextInt();
            switch (choice) {
                case 1 -> smartHome.getCurrentTemperature();
                case 2 -> smartHome.leaveHome();
                case 3 -> smartHome.sleepMode();
                case 4 -> {
                    sc.close();
                    return;
                }
            }
        }
    }
}