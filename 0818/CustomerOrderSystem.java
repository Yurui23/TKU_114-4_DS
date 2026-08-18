class Customer {
    private String customerId;
    private String name;

    public Customer(String customerId, String name) {
        this.customerId = (customerId == null) ? "UNKNOWN" : customerId.trim();
        this.name = (name == null) ? "UNKNOWN" : name.trim();
    }

    public String getCustomerId() { return customerId; }
    public String getName() { return name; }

    @Override
    public String toString() {
        return name + " (" + customerId + ")";
    }
}

class OrderItem {
    private String productName;
    private int price;
    private int quantity;

    public OrderItem(String productName, int price, int quantity) {
        this.productName = (productName == null) ? "Unspecified" : productName.trim();
        this.price = Math.max(0, price);
        this.quantity = Math.max(0, quantity);
    }

    public String getProductName() { return productName; }
    public int getPrice() { return price; }
    public int getQuantity() { return quantity; }
    public int getSubtotal() { return price * quantity; }

    @Override
    public String toString() {
        return productName + " x" + quantity + " (單價: $" + price + ", 小計: $" + getSubtotal() + ")";
    }
}

class CustomerOrder {
    private String orderId;
    private Customer customer;
    private OrderItem[] items;

    public CustomerOrder(String orderId, Customer customer, OrderItem[] items) {
        this.orderId = (orderId == null) ? "UNKNOWN" : orderId.trim();
        this.customer = customer;
        if (items == null) {
            this.items = new OrderItem[0];
        } else {
            this.items = items.clone();
        }
    }

    public int calculateTotalAmount() {
        int total = 0;
        for (OrderItem item : items) {
            if (item != null) {
                total += item.getSubtotal();
            }
        }
        return total;
    }

    public int calculateTotalQuantity() {
        int count = 0;
        for (OrderItem item : items) {
            if (item != null) {
                count += item.getQuantity();
            }
        }
        return count;
    }

    public void printSummary() {
        System.out.println("========================================");
        System.out.println("訂單單號: " + orderId);
        System.out.println("顧客資訊: " + (customer != null ? customer.toString() : "無顧客資訊"));
        System.out.println("----------------------------------------");
        System.out.println("訂購品項明細:");
        for (OrderItem item : items) {
            if (item != null) {
                System.out.println(" - " + item);
            }
        }
        System.out.println("----------------------------------------");
        System.out.println("商品總件數: " + calculateTotalQuantity() + " 件");
        System.out.println("訂單總金額: $" + calculateTotalAmount());
        System.out.println("========================================");
    }
}

public class CustomerOrderSystem {
    public static void main(String[] args) {
        System.out.println("=== 課後作業二：訂單與顧客管理測試 ===");

        Customer client = new Customer("C202401", "張曉明");

        OrderItem[] cart = {
            new OrderItem("無線鍵盤", 1200, 2),
            new OrderItem("光學滑鼠", 650, 1),
            new OrderItem("27吋顯示器", 5800, 1)
        };

        CustomerOrder order = new CustomerOrder("ORD-9981", client, cart);
        order.printSummary();
    }
}