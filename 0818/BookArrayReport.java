class Book {
    private String id;
    private String name;
    private int price;
    private int stock;

    public Book(String id, String name, int price, int stock) {
        this.id = id;
        this.name = name;
        this.price = Math.max(0, price);
        this.stock = Math.max(0, stock);
    }

    public String getId() { return id; }
    public String getName() { return name; }
    public int getPrice() { return price; }
    public int getStock() { return stock; }

    @Override
    public String toString() {
        return "書號: " + id + " | 書名: " + name + " | 價格: $" + price + " | 庫存: " + stock;
    }
}

public class BookArrayReport {
    public static void main(String[] args) {
        Book[] books = {
            new Book("B001", "Java 程式設計實務", 680, 5),
            new Book("B002", "資料結構與演算法", 750, 2),
            new Book("B003", "Python Data Science", 820, 1),
            new Book("B004", "Web 前端開發指南", 550, 8)
        };

        System.out.println("=== 1. 所有書籍列表 ===");
        for (Book book : books) {
            System.out.println(book);
        }

        int totalValue = 0;
        Book highestPriceBook = books[0];

        for (Book book : books) {
            totalValue += book.getPrice() * book.getStock();
            if (book.getPrice() > highestPriceBook.getPrice()) {
                highestPriceBook = book;
            }
        }

        System.out.println("----------------------------------------");
        System.out.println("=== 2. 庫存總價值 ===");
        System.out.println("總價值: $" + totalValue);

        System.out.println("----------------------------------------");
        System.out.println("=== 3. 價格最高的書籍 ===");
        System.out.println(highestPriceBook);

        System.out.println("----------------------------------------");
        System.out.println("=== 4. 庫存 <= 3 的書籍 ===");
        for (Book book : books) {
            if (book.getStock() <= 3) {
                System.out.println(book);
            }
        }
    }
}