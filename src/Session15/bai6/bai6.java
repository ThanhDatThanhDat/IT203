package Session15.bai6;

import java.util.*;

class Patient {
    String id, name, gender;
    int age;

    public Patient(String id, String name, int age, String gender) {
        this.id = id; this.name = name; this.age = age; this.gender = gender;
    }
}

class PatientWaitingQueue {
    Queue<Patient> waitingQueue = new LinkedList<>();

    public void addPatient(Patient p) {
        waitingQueue.add(p);
        System.out.println("Đã thêm bệnh nhân: " + p.name + " vào hàng chờ.");
    }

    public int getTotalPatients() { return waitingQueue.size(); }
}

class EditAction {
    String description, editedBy, editTime;

    public EditAction(String description, String editedBy, String editTime) {
        this.description = description; this.editedBy = editedBy; this.editTime = editTime;
    }
}

class MedicalRecordHistory {
    Stack<EditAction> editStack = new Stack<>();
    String recordId;

    public void addLog(EditAction action) {
        editStack.push(action);
    }
}

class Ticket {
    int ticketNumber;
    String issuedTime;

    public Ticket(int ticketNumber, String issuedTime) {
        this.ticketNumber = ticketNumber; this.issuedTime = issuedTime;
    }
}

class TicketSystem {
    Queue<Ticket> ticketQueue = new LinkedList<>();
    int currentNumber = 0;

    public void issueTicket() {
        currentNumber++;
        ticketQueue.add(new Ticket(currentNumber, "10:00"));
        System.out.println("Số thứ tự mới: " + currentNumber);
    }

    public void callNext() {
        if (!ticketQueue.isEmpty()) {
            System.out.println("Mời số: " + ticketQueue.poll().ticketNumber);
        }
    }
}

class InputAction {
    String fieldName, oldValue, newValue, actionTime;

    public InputAction(String fieldName, String oldValue, String newValue, String actionTime) {
        this.fieldName = fieldName; this.oldValue = oldValue;
        this.newValue = newValue; this.actionTime = actionTime;
    }
}

class UndoManager {
    Deque<InputAction> undoStack = new ArrayDeque<>();
    int maxUndoSteps = 10;

    public void saveAction(InputAction action) {
        if (undoStack.size() >= maxUndoSteps) {
            undoStack.removeLast();
        }
        undoStack.push(action);
        System.out.println("Đã lưu thao tác trên trường: " + action.fieldName);
    }

    public void undo() {
        if (!undoStack.isEmpty()) {
            InputAction lastAction = undoStack.pop();
            System.out.println("Đang hoàn tác... Trả giá trị '" + lastAction.oldValue + "' về cho " + lastAction.fieldName);
        } else {
            System.out.println("Không có gì để hoàn tác.");
        }
    }
}

public class bai6 {
    public static void main(String[] args) {
        System.out.println("=== HỆ THỐNG BỆNH VIỆN THÔNG MINH ===");

        TicketSystem ts = new TicketSystem();
        ts.issueTicket();
        ts.issueTicket();
        ts.callNext();

        UndoManager undoer = new UndoManager();
        undoer.saveAction(new InputAction("Chẩn đoán", "Đau dạ dày", "Viêm loét dạ dày", "10:30"));
        undoer.undo();
    }
}