package SS8.bai3;

import java.util.Scanner;
import java.util.Stack;

interface Command {
    void execute();
    void undo();
}

class Light {
    public void on() { System.out.println("Đèn: Bật"); }
    public void off() { System.out.println("Đèn: Tắt"); }
}

class AirConditioner {
    private int temperature = 25;
    public void setTemperature(int temp) {
        this.temperature = temp;
        System.out.println("Điều hòa: Nhiệt độ = " + temperature);
    }
    public int getTemperature() { return temperature; }
}

class LightOnCommand implements Command {
    private Light light;
    public LightOnCommand(Light light) { this.light = light; }
    public void execute() { light.on(); }
    public void undo() { light.off(); }
}

class LightOffCommand implements Command {
    private Light light;
    public LightOffCommand(Light light) { this.light = light; }
    public void execute() { light.off(); }
    public void undo() { light.on(); }
}

class ACSetTempCommand implements Command {
    private AirConditioner ac;
    private int prevTemp;
    private int newTemp;

    public ACSetTempCommand(AirConditioner ac, int newTemp) {
        this.ac = ac;
        this.newTemp = newTemp;
    }

    public void execute() {
        prevTemp = ac.getTemperature();
        ac.setTemperature(newTemp);
    }

    public void undo() {
        System.out.print("Undo: ");
        ac.setTemperature(prevTemp);
    }
}

class RemoteControl {
    private Command[] onCommands;
    private Stack<Command> undoStack;

    public RemoteControl() {
        onCommands = new Command[5];
        undoStack = new Stack<>();
    }

    public void setCommand(int slot, Command command) {
        onCommands[slot] = command;
    }

    public void pressButton(int slot) {
        if (onCommands[slot] != null) {
            onCommands[slot].execute();
            undoStack.push(onCommands[slot]);
        } else {
            System.out.println("Nút này chưa được gán lệnh.");
        }
    }

    public void pressUndo() {
        if (!undoStack.isEmpty()) {
            Command lastCommand = undoStack.pop();
            lastCommand.undo();
        } else {
            System.out.println("Không còn lệnh nào để Undo.");
        }
    }
}

public class bai3 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        RemoteControl remote = new RemoteControl();
        Light livingRoomLight = new Light();
        AirConditioner bedroomAC = new AirConditioner();

        remote.setCommand(1, new LightOnCommand(livingRoomLight));
        System.out.println("Đã gán LightOnCommand cho nút 1");

        remote.setCommand(2, new LightOffCommand(livingRoomLight));
        System.out.println("Đã gán LightOffCommand cho nút 2");

        remote.setCommand(3, new ACSetTempCommand(bedroomAC, 26));
        System.out.println("Đã gán ACSetTempCommand(26) cho nút 3");

        while (true) {
            System.out.println("\n1. Nút 1 | 2. Nút 2 | 3. Nút 3 | 4. Undo | 0. Thoát");
            int choice = sc.nextInt();

            switch (choice) {
                case 1 -> remote.pressButton(1);
                case 2 -> remote.pressButton(2);
                case 3 -> remote.pressButton(3);
                case 4 -> remote.pressUndo();
                case 0 -> {
                    sc.close();
                    return;
                }
                default -> System.out.println("Lựa chọn sai.");
            }
        }
    }
}