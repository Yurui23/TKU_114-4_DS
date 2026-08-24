import java.util.ArrayList;
import java.util.List;

class MgtOrder {
    int orderId;
    String customer;
    double amount;
    String status; 

    MgtOrder(int orderId, String customer, double amount, String status) {
        this.orderId = orderId;
        this.customer = customer;
        this.amount = amount;
        this.status = status;
    }
}

class MgtOrderNode {
    MgtOrder order;
    MgtOrderNode left, right;
    MgtOrderNode(MgtOrder order) { this.order = order; }
}

public class OrderManagementBst {
    private MgtOrderNode root;
    private double totalAmount;

    public boolean add(MgtOrder order) {
        if (order == null || order.amount < 0 || find(order.orderId) != null) return false;
        root = addRec(root, order);
        totalAmount += order.amount;
        return true;
    }

    private MgtOrderNode addRec(MgtOrderNode node, MgtOrder order) {
        if (node == null) return new MgtOrderNode(order);
        if (order.orderId < node.order.orderId) node.left = addRec(node.left, order);
        else if (order.orderId > node.order.orderId) node.right = addRec(node.right, order);
        return node;
    }

    public MgtOrder find(int orderId) {
        MgtOrderNode curr = root;
        while (curr != null) {
            if (orderId == curr.order.orderId) return curr.order;
            if (orderId < curr.order.orderId) curr = curr.left;
            else curr = curr.right;
        }
        return null;
    }

    public boolean updateStatus(int orderId, String newStatus) {
        if (newStatus == null) return false;
        MgtOrder o = find(orderId);
        if (o != null && !o.status.equals("CANCELLED")) {
            o.status = newStatus;
            return true;
        }
        return false;
    }

    public boolean cancel(int orderId) {
        MgtOrder o = find(orderId);
        if (o != null && !o.status.equals("CANCELLED")) {
            o.status = "CANCELLED";
            totalAmount -= o.amount;
            return true;
        }
        return false;
    }

    public boolean remove(int orderId) {
        MgtOrder o = find(orderId);
        if (o == null || !o.status.equals("CANCELLED")) return false;
        root = removeRec(root, orderId);
        return true;
    }

    private MgtOrderNode removeRec(MgtOrderNode node, int orderId) {
        if (node == null) return null;
        if (orderId < node.order.orderId) node.left = removeRec(node.left, orderId);
        else if (orderId > node.order.orderId) node.right = removeRec(node.right, orderId);
        else {
            if (node.left == null) return node.right;
            if (node.right == null) return node.left;
            node.order = getMin(node.right);
            node.right = removeRec(node.right, node.order.orderId);
        }
        return node;
    }

    private MgtOrder getMin(MgtOrderNode node) {
        MgtOrder min = node.order;
        while (node.left != null) {
            min = node.left.order;
            node = node.left;
        }
        return min;
    }

    public List<MgtOrder> idRangeReport(int low, int high) {
        List<MgtOrder> res = new ArrayList<>();
        if (low > high) return res;
        rangeRec(root, low, high, res);
        return res;
    }

    private void rangeRec(MgtOrderNode node, int low, int high, List<MgtOrder> res) {
        if (node == null) return;
        if (low < node.order.orderId) rangeRec(node.left, low, high, res);
        if (node.order.orderId >= low && node.order.orderId <= high) res.add(node.order);
        if (high > node.order.orderId) rangeRec(node.right, low, high, res);
    }

    public double getTotalAmount() {
        return totalAmount;
    }

    public static void main(String[] args) {
        OrderManagementBst bst = new OrderManagementBst();
        bst.add(new MgtOrder(1, "Cust A", 100, "NEW"));
        bst.add(new MgtOrder(3, "Cust C", -50, "NEW")); 
        bst.add(new MgtOrder(2, "Cust B", 200, "NEW"));

        bst.cancel(1);
        System.out.println(bst.remove(2)); 
        System.out.println(bst.remove(1)); 
        System.out.println(bst.getTotalAmount()); 
    }
}