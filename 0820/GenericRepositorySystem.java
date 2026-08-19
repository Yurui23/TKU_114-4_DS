import java.util.ArrayList;
import java.util.List;

class Product {
    private String name;
    private double price;

    public Product(String name, double price) {
        this.name = name;
        this.price = price;
    }

    @Override
    public String toString() {
        return "Product{name='" + name + "', price=" + price + "}";
    }
}

class Repository<T> {
    private List<T> items;

    public Repository() {
        this.items = new ArrayList<>();
    }

    public void add(T item) {
        if (item != null) {
            items.add(item);
        }
    }

    public T get(int index) {
        if (index >= 0 && index < items.size()) {
            return items.get(index);
        }
        return null;
    }

    public boolean remove(T item) {
        return items.remove(item);
    }

    public int size() {
        return items.size();
    }

    public void printAll() {
        for (T item : items) {
            System.out.println(item);
        }
    }
}

public class GenericRepositorySystem {
    public static void main(String[] args) {
        Repository<String> stringRepo = new Repository<>();
        stringRepo.add("Apple");
        stringRepo.add("Banana");
        stringRepo.add("Cherry");
        
        System.out.println("String Repository Size: " + stringRepo.size());
        stringRepo.printAll();
        stringRepo.remove("Banana");
        System.out.println("After removing Banana:");
        stringRepo.printAll();

        System.out.println("-------------------------");

        Repository<Product> productRepo = new Repository<>();
        Product p1 = new Product("Laptop", 1200.0);
        Product p2 = new Product("Mouse", 25.0);
        
        productRepo.add(p1);
        productRepo.add(p2);
        
        System.out.println("Product Repository Size: " + productRepo.size());
        productRepo.printAll();
        
        Product retrieved = productRepo.get(0);
        System.out.println("Get index 0: " + retrieved);
    }
}