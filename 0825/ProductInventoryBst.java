class Product {
    int id;
    String name;
    int stock;
    Product(int id, String name, int stock) {
        this.id = id;
        this.name = name;
        this.stock = stock;
    }
}

class ProdNode {
    Product product;
    ProdNode left, right;
    ProdNode(Product product) { this.product = product; }
}

public class ProductInventoryBst {
    private ProdNode root;

    public void add(Product product) {
        if (product == null || query(product.id) != null) return;
        root = addRec(root, product);
    }

    private ProdNode addRec(ProdNode node, Product product) {
        if (node == null) return new ProdNode(product);
        if (product.id < node.product.id) node.left = addRec(node.left, product);
        else if (product.id > node.product.id) node.right = addRec(node.right, product);
        return node;
    }

    public Product query(int id) {
        ProdNode current = root;
        while (current != null) {
            if (id == current.product.id) return current.product;
            if (id < current.product.id) current = current.left;
            else current = current.right;
        }
        return null;
    }

    public void restock(int id, int amount) {
        Product p = query(id);
        if (p != null && amount > 0) p.stock += amount;
    }

    public void consume(int id, int amount) {
        Product p = query(id);
        if (p != null && amount > 0 && p.stock >= amount) p.stock -= amount;
    }

    public void delete(int id) {
        if (query(id) != null) root = deleteRec(root, id);
    }

    private ProdNode deleteRec(ProdNode node, int id) {
        if (node == null) return null;
        if (id < node.product.id) node.left = deleteRec(node.left, id);
        else if (id > node.product.id) node.right = deleteRec(node.right, id);
        else {
            if (node.left == null) return node.right;
            if (node.right == null) return node.left;
            node.product = minValue(node.right);
            node.right = deleteRec(node.right, node.product.id);
        }
        return node;
    }

    private Product minValue(ProdNode node) {
        Product min = node.product;
        while (node.left != null) {
            min = node.left.product;
            node = node.left;
        }
        return min;
    }

    public void inorderReport() {
        inorderRec(root);
        System.out.println();
    }

    private void inorderRec(ProdNode node) {
        if (node != null) {
            inorderRec(node.left);
            System.out.print("[" + node.product.id + ":" + node.product.name + ":" + node.product.stock + "] ");
            inorderRec(node.right);
        }
    }

    public static void main(String[] args) {
        ProductInventoryBst inv = new ProductInventoryBst();
        inv.add(new Product(101, "Pen", 50));
        inv.add(new Product(105, "Eraser", 30));
        inv.add(new Product(102, "Ruler", 20));
        
        inv.restock(102, 10);
        inv.consume(101, 5);
        inv.inorderReport();
        
        inv.delete(105);
        inv.inorderReport();
    }
}