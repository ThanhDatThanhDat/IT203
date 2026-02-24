package Session11.Kiemtra;

interface IMixable {
    void mix();
}

class Drink {
    protected String name;
    protected double price;

    public Drink(String name, double price) {
        this.name = name;
        this.price = price;
    }

    public double calculatePrice() {
        return price;
    }

    public void displayInfo() {
        System.out.println("Tên: " + name);
        System.out.println("Giá gốc: " + price);
    }
}

class Coffee extends Drink {
    private boolean hasMilk;

    public Coffee(String name, double price, boolean hasMilk) {
        super(name, price);
        this.hasMilk = hasMilk;
    }

    @Override
    public double calculatePrice() {
        if (hasMilk) {
            return price + 5000;
        }
        return price;
    }

    @Override
    public void displayInfo() {
        super.displayInfo();
        if (hasMilk) {
            System.out.println("Loại: Có sữa");
        } else {
            System.out.println("Loại: Đen đá");
        }
    }
}

class FruitJuice extends Drink implements IMixable {
    private int discountPercent;

    public FruitJuice(String name, double price, int discountPercent) {
        super(name, price);
        this.discountPercent = discountPercent;
    }

    @Override
    public double calculatePrice() {
        return price - (price * discountPercent / 100);
    }

    @Override
    public void mix() {
        System.out.println("Đang ép trái cây tươi...");
    }
}

public class Main {
    public static void main(String[] args) {
        Drink[] drinks = new Drink[3];

        drinks[0] = new Coffee("Bạc sỉu", 30000, true);
        drinks[1] = new FruitJuice("Nước cam", 40000, 10);
        drinks[2] = null;

        for (Drink d : drinks) {
            if (d != null) {
                d.displayInfo();
                System.out.println("Thành tiền: " + d.calculatePrice());

                if (d instanceof IMixable) {
                    ((IMixable) d).mix();
                }
                System.out.println("\n");
            }
        }
    }
}