class Order {
    int orderId;
    double amount;
    Order(int orderId, double amount) {
        this.orderId = orderId;
        this.amount = amount;
    }
}

class OrderNode {
    Order order;
    OrderNode left, right;
    OrderNode(Order order) { this.order = order; }
}

public class OrderBstSystem {
    private OrderNode root;
    private int totalOrders;
    private double totalAmount;

    public void add(Order order) {
        if (order == null || find(order.orderId) != null) return;
        root = addRec(root, order);
        totalOrders++;
        totalAmount += order.amount;
    }

    private OrderNode addRec(OrderNode node, Order order) {
        if (node == null) return new OrderNode(order);
        if (order.orderId < node.order.orderId) node.left = addRec(node.left, order);
        else if (order.orderId > node.order.orderId) node.right = addRec(node.right, order);
        return node;
    }

    public Order find(int orderId) {
        OrderNode current = root;
        while (current != null) {
            if (orderId == current.order.orderId) return current.order;
            if (orderId < current.order.orderId) current = current.left;
            else current = current.right;
        }
        return null;
    }

    public void updateAmount(int orderId, double newAmount) {
        Order o = find(orderId);
        if (o != null) {
            totalAmount = totalAmount - o.amount + newAmount;
            o.amount = newAmount;
        }
    }

    public void cancel(int orderId) {
        Order o = find(orderId);
        if (o != null) {
            totalAmount -= o.amount;
            totalOrders--;
            root = cancelRec(root, orderId);
        }
    }

    private OrderNode cancelRec(OrderNode node, int orderId) {
        if (node == null) return null;
        if (orderId < node.order.orderId) node.left = cancelRec(node.left, orderId);
        else if (orderId > node.order.orderId) node.right = cancelRec(node.right, orderId);
        else {
            if (node.left == null) return node.right;
            if (node.right == null) return node.left;
            node.order = minValue(node.right);
            node.right = cancelRec(node.right, node.order.orderId);
        }
        return node;
    }

    private Order minValue(OrderNode node) {
        Order min = node.order;
        while (node.left != null) {
            min = node.left.order;
            node = node.left;
        }
        return min;
    }

    public void rangeReport(int low, int high) {
        if (low > high) return;
        rangeRec(root, low, high);
        System.out.println();
    }

    private void rangeRec(OrderNode node, int low, int high) {
        if (node == null) return;
        if (low < node.order.orderId) rangeRec(node.left, low, high);
        if (node.order.orderId >= low && node.order.orderId <= high) {
            System.out.print("[" + node.order.orderId + ":" + node.order.amount + "] ");
        }
        if (high > node.order.orderId) rangeRec(node.right, low, high);
    }

    public void summary() {
        System.out.println(totalOrders);
        System.out.println(totalAmount);
    }

    public static void main(String[] args) {
        OrderBstSystem sys = new OrderBstSystem();
        sys.add(new Order(1001, 250.5));
        sys.add(new Order(1005, 500.0));
        sys.add(new Order(1003, 120.0));
        sys.add(new Order(1002, 300.0));

        sys.updateAmount(1003, 150.0);
        sys.cancel(1005);
        sys.rangeReport(1002, 1004);
        sys.summary();
    }
}