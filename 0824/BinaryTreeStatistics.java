class StatNode {
    int val;
    StatNode left, right;
    StatNode(int val) { this.val = val; }
}

public class BinaryTreeStatistics {

    public static int size(StatNode node) {
        if (node == null) return 0;
        return 1 + size(node.left) + size(node.right);
    }

    public static int sum(StatNode node) {
        if (node == null) return 0;
        return node.val + sum(node.left) + sum(node.right);
    }

    public static Integer maximum(StatNode node) {
        if (node == null) return null;
        Integer maxLeft = maximum(node.left);
        Integer maxRight = maximum(node.right);
        int currentMax = node.val;
        if (maxLeft != null) currentMax = Math.max(currentMax, maxLeft);
        if (maxRight != null) currentMax = Math.max(currentMax, maxRight);
        return currentMax;
    }

    public static int leafCount(StatNode node) {
        if (node == null) return 0;
        if (node.left == null && node.right == null) return 1;
        return leafCount(node.left) + leafCount(node.right);
    }

    public static int height(StatNode node) {
        if (node == null) return -1;
        return 1 + Math.max(height(node.left), height(node.right));
    }

    public static boolean contains(StatNode node, int target) {
        if (node == null) return false;
        if (node.val == target) return true;
        return contains(node.left, target) || contains(node.right, target);
    }

    public static void main(String[] args) {
        StatNode root = new StatNode(10);
        root.left = new StatNode(5);
        root.right = new StatNode(20);
        root.left.left = new StatNode(2);

        System.out.println(size(root));
        System.out.println(sum(root));
        System.out.println(maximum(root));
        System.out.println(maximum(null));
        System.out.println(leafCount(root));
        System.out.println(height(root));
        System.out.println(contains(root, 20));
    }
}