class CharNode {
    String val;
    CharNode left, right;
    CharNode(String val) { this.val = val; }
}

public class ThreeTraversalPractice {

    public static void preorder(CharNode node) {
        if (node == null) return;
        System.out.print(node.val + " ");
        preorder(node.left);
        preorder(node.right);
    }

    public static void inorder(CharNode node) {
        if (node == null) return;
        inorder(node.left);
        System.out.print(node.val + " ");
        inorder(node.right);
    }

    public static void postorder(CharNode node) {
        if (node == null) return;
        postorder(node.left);
        postorder(node.right);
        System.out.print(node.val + " ");
    }

    public static void main(String[] args) {
        CharNode root = new CharNode("M");
        root.left = new CharNode("F");
        root.left.left = new CharNode("B");
        root.right = new CharNode("T");
        root.right.left = new CharNode("R");
        root.right.right = new CharNode("Z");

        preorder(root);
        System.out.println();
        inorder(root);
        System.out.println();
        postorder(root);
        System.out.println();
    }
}