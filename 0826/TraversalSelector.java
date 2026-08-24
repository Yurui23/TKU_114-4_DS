class ExprNode {
    String val;
    ExprNode left, right;
    ExprNode(String val) { this.val = val; }
}

public class TraversalSelector {
    public static void prefix(ExprNode node) {
        if (node == null) return;
        System.out.print(node.val + " ");
        prefix(node.left);
        prefix(node.right);
    }

    public static void infix(ExprNode node) {
        if (node == null) return;
        boolean isOperator = node.left != null || node.right != null;
        if (isOperator) System.out.print("( ");
        infix(node.left);
        System.out.print(node.val + " ");
        infix(node.right);
        if (isOperator) System.out.print(") ");
    }

    public static void postfix(ExprNode node) {
        if (node == null) return;
        postfix(node.left);
        postfix(node.right);
        System.out.print(node.val + " ");
    }

    public static void main(String[] args) {
        ExprNode root = new ExprNode("+");
        root.left = new ExprNode("*");
        root.right = new ExprNode("C");
        root.left.left = new ExprNode("A");
        root.left.right = new ExprNode("B");

        prefix(root);
        System.out.println();
        infix(root);
        System.out.println();
        postfix(root);
        System.out.println();
    }
}