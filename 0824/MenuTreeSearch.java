class MenuNode {
    String name;
    MenuNode left, right;
    MenuNode(String name) { this.name = name; }
}

public class MenuTreeSearch {

    public static boolean contains(MenuNode node, String target) {
        if (node == null) return false;
        if (node.name.equals(target)) return true;
        return contains(node.left, target) || contains(node.right, target);
    }

    public static int findDepth(MenuNode node, String target, int currentDepth) {
        if (node == null) return -1;
        if (node.name.equals(target)) return currentDepth;
        int leftSearch = findDepth(node.left, target, currentDepth + 1);
        if (leftSearch != -1) return leftSearch;
        return findDepth(node.right, target, currentDepth + 1);
    }

    public static int countLeaves(MenuNode node) {
        if (node == null) return 0;
        if (node.left == null && node.right == null) return 1;
        return countLeaves(node.left) + countLeaves(node.right);
    }

    public static void preorderDisplay(MenuNode node) {
        if (node == null) return;
        System.out.print(node.name + " ");
        preorderDisplay(node.left);
        preorderDisplay(node.right);
    }

    public static void main(String[] args) {
        MenuNode root = new MenuNode("Home");
        root.left = new MenuNode("File");
        root.left.left = new MenuNode("New");
        root.left.right = new MenuNode("Save");
        root.right = new MenuNode("Edit");

        System.out.println(contains(root, "Save"));
        System.out.println(findDepth(root, "Edit", 0));
        System.out.println(findDepth(root, "Missing", 0));
        System.out.println(countLeaves(root));
        preorderDisplay(root);
        System.out.println();
    }
}