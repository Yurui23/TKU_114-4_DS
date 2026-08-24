class TreeNode {
    String val;
    TreeNode left, right;
    TreeNode(String val) { this.val = val; }
}

public class BinaryTreeStructureReport {

    public static void printLeaves(TreeNode node) {
        if (node == null) return;
        if (node.left == null && node.right == null) System.out.print(node.val + " ");
        printLeaves(node.left);
        printLeaves(node.right);
    }

    public static int getSize(TreeNode node) {
        if (node == null) return 0;
        return 1 + getSize(node.left) + getSize(node.right);
    }

    public static int getLeafCount(TreeNode node) {
        if (node == null) return 0;
        if (node.left == null && node.right == null) return 1;
        return getLeafCount(node.left) + getLeafCount(node.right);
    }

    public static int getHeight(TreeNode node) {
        if (node == null) return -1;
        return 1 + Math.max(getHeight(node.left), getHeight(node.right));
    }

    public static void report(TreeNode root) {
        System.out.println("Root: " + (root != null ? root.val : "null"));
        System.out.print("Leaves: ");
        printLeaves(root);
        System.out.println("\nSize: " + getSize(root));
        System.out.println("Leaf Count: " + getLeafCount(root));
        System.out.println("Height: " + getHeight(root));
        System.out.println("---");
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode("A");
        root.left = new TreeNode("B");
        root.right = new TreeNode("C");
        root.left.left = new TreeNode("D");
        root.left.right = new TreeNode("E");
        root.right.left = new TreeNode("F");
        root.right.right = new TreeNode("G");

        report(root);
        report(null);
        report(new TreeNode("Single"));
    }
}