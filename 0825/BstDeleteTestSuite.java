class TestDelNode {
    int val;
    TestDelNode left, right;
    TestDelNode(int val) { this.val = val; }
}

public class BstDeleteTestSuite {
    private TestDelNode root;

    public void insert(int val) {
        root = insertRec(root, val);
    }

    private TestDelNode insertRec(TestDelNode node, int val) {
        if (node == null) return new TestDelNode(val);
        if (val < node.val) node.left = insertRec(node.left, val);
        else if (val > node.val) node.right = insertRec(node.right, val);
        return node;
    }

    public void delete(int val) {
        root = deleteRec(root, val);
    }

    private TestDelNode deleteRec(TestDelNode node, int val) {
        if (node == null) return null;
        if (val < node.val) node.left = deleteRec(node.left, val);
        else if (val > node.val) node.right = deleteRec(node.right, val);
        else {
            if (node.left == null) return node.right;
            if (node.right == null) return node.left;
            node.val = minValue(node.right);
            node.right = deleteRec(node.right, node.val);
        }
        return node;
    }

    private int minValue(TestDelNode node) {
        int min = node.val;
        while (node.left != null) {
            min = node.left.val;
            node = node.left;
        }
        return min;
    }

    public void inorder() {
        inorderRec(root);
        System.out.println();
    }

    private void inorderRec(TestDelNode node) {
        if (node != null) {
            inorderRec(node.left);
            System.out.print(node.val + " ");
            inorderRec(node.right);
        }
    }

    public static void main(String[] args) {
        BstDeleteTestSuite test = new BstDeleteTestSuite();
        test.delete(10);
        test.inorder();

        test.insert(10);
        test.delete(99);
        test.inorder();

        test.delete(10);
        test.inorder();

        test.insert(20);
        test.insert(10);
        test.delete(20);
        test.inorder();
        test.delete(10);

        test.insert(50);
        test.insert(30);
        test.insert(70);
        test.delete(50);
        test.inorder();

        test.delete(30);
        test.delete(70);
        test.inorder();
    }
}