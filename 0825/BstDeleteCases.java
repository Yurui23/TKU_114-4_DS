class DelNode {
    int val;
    DelNode left, right;
    DelNode(int val) { this.val = val; }
}

public class BstDeleteCases {
    private DelNode root;
    private int size;

    public void insert(int val) {
        if (!contains(val)) {
            root = insertRec(root, val);
            size++;
        }
    }

    private DelNode insertRec(DelNode node, int val) {
        if (node == null) return new DelNode(val);
        if (val < node.val) node.left = insertRec(node.left, val);
        else if (val > node.val) node.right = insertRec(node.right, val);
        return node;
    }

    public boolean contains(int val) {
        DelNode current = root;
        while (current != null) {
            if (val == current.val) return true;
            if (val < current.val) current = current.left;
            else current = current.right;
        }
        return false;
    }

    public boolean delete(int val) {
        if (!contains(val)) return false;
        root = deleteRec(root, val);
        size--;
        return true;
    }

    private DelNode deleteRec(DelNode node, int val) {
        if (node == null) return null;
        if (val < node.val) {
            node.left = deleteRec(node.left, val);
        } else if (val > node.val) {
            node.right = deleteRec(node.right, val);
        } else {
            if (node.left == null) return node.right;
            if (node.right == null) return node.left;
            node.val = minValue(node.right);
            node.right = deleteRec(node.right, node.val);
        }
        return node;
    }

    private int minValue(DelNode node) {
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

    private void inorderRec(DelNode node) {
        if (node != null) {
            inorderRec(node.left);
            System.out.print(node.val + " ");
            inorderRec(node.right);
        }
    }

    public int getSize() {
        return size;
    }

    public static void main(String[] args) {
        BstDeleteCases bst = new BstDeleteCases();
        bst.insert(50);
        bst.insert(30);
        bst.insert(70);
        bst.insert(20);
        bst.insert(40);
        bst.insert(60);
        bst.insert(80);
        bst.insert(35);

        boolean r1 = bst.delete(20);
        System.out.println(r1 + " | Size: " + bst.getSize() + " | Inorder: ");
        bst.inorder();

        boolean r2 = bst.delete(40);
        System.out.println(r2 + " | Size: " + bst.getSize() + " | Inorder: ");
        bst.inorder();

        boolean r3 = bst.delete(50);
        System.out.println(r3 + " | Size: " + bst.getSize() + " | Inorder: ");
        bst.inorder();
    }
}