import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

class StoreProduct implements Comparable<StoreProduct> {
    private int id;
    private String name;
    private double price;
    private int stock;

    public StoreProduct(int id, String name, double price, int stock) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.stock = stock;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public int getStock() {
        return stock;
    }

    @Override
    public int compareTo(StoreProduct other) {
        return Integer.compare(this.id, other.id);
    }

    @Override
    public String toString() {
        return String.format("ID: %d, Name: %s, Price: %.2f, Stock: %d", id, name, price, stock);
    }
}

public class ProductComparatorPractice {
    public static void main(String[] args) {
        List<StoreProduct> originalProducts = new ArrayList<>();
        originalProducts.add(new StoreProduct(103, "Keyboard", 1500.0, 50));
        originalProducts.add(new StoreProduct(101, "Mouse", 800.0, 100));
        originalProducts.add(new StoreProduct(105, "Mouse Pad", 800.0, 200));
        originalProducts.add(new StoreProduct(102, "Monitor", 5000.0, 50));
        originalProducts.add(new StoreProduct(104, "Headset", 2500.0, 30));

        System.out.println("--- 原始順序 ---");
        for (StoreProduct p : originalProducts) {
            System.out.println(p);
        }
        System.out.println();

        List<StoreProduct> naturalOrderList = new ArrayList<>(originalProducts);
        Collections.sort(naturalOrderList);
        System.out.println("--- 1. Natural order (依 id 升冪) ---");
        for (StoreProduct p : naturalOrderList) {
            System.out.println(p);
        }
        System.out.println();

        List<StoreProduct> priceNameList = new ArrayList<>(originalProducts);
        Comparator<StoreProduct> priceThenName = Comparator
                .comparingDouble(StoreProduct::getPrice)
                .thenComparing(StoreProduct::getName);
        Collections.sort(priceNameList, priceThenName);
        System.out.println("--- 2. Comparator 一 (依 price 升冪，同價時依 name) ---");
        for (StoreProduct p : priceNameList) {
            System.out.println(p);
        }
        System.out.println();

        List<StoreProduct> stockIdList = new ArrayList<>(originalProducts);
        Comparator<StoreProduct> stockThenId = Comparator
                .comparingInt(StoreProduct::getStock)
                .reversed()
                .thenComparingInt(StoreProduct::getId);
        Collections.sort(stockIdList, stockThenId);
        System.out.println("--- 3. Comparator 二 (依 stock 降冪，同庫存時依 id) ---");
        for (StoreProduct p : stockIdList) {
            System.out.println(p);
        }
    }
}