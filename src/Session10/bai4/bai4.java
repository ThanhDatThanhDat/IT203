package Session10.bai4;

public class bai4 {
    interface RemoteControl {

        void powerOn();

        default void checkBattery() {
            System.out.println("Pin ổn định");
        }
    }

    public static void main(String[] args) {
        RemoteControl smartLight = new RemoteControl() {

            @Override
            public void powerOn() {
                System.out.println("Đèn đã bật");
            }
        };

        smartLight.powerOn();
        smartLight.checkBattery();
    }
}