class AuditNode {
    int val;
    AuditNode left, right;
    AuditNode(int val) { this.val = val; }
}

public class BstOperationAudit {
    private AuditNode root;
    private int size;

    public void add(int val) {
        boolean[] added = {false};
        root = addRec(root, val, added);
        if (added[0]) size++;
        report("ADD " + val, added[0]);
    }

    private AuditNode addRec(AuditNode node, int val, boolean[] added) {
        if (node == null) {
            added[0] = true;
            return new AuditNode(val);
        }
        if (val < node.val) node.left = addRec(node.left, val, added);
        else if (val > node.val) node.right = addRec(node.right, val, added);
        return node;
    }

    public void remove(int val) {
        boolean[] removed = {false};
        root = removeRec(root, val, removed);
        if (removed[0]) size--;
        report("REMOVE " + val, removed[0]);
    }

    private AuditNode removeRec(AuditNode node, int val, boolean[] removed) {
        if (node == null) return null;
        if (val < node.val) node.left = removeRec(node.left, val, removed);
        else if (val > node.val) node.right = removeRec(node.right, val, removed);
        else {
            removed[0] = true;
            if (node.left == null) return node.right;
            if (node.right == null) return node.left;
            node.val = getMin(node.right);
            node.right = removeRec(node.right, node.val, new boolean[1]);
        }
        return node;
    }

    private int getMin(AuditNode node) {
        int min = node.val;
        while (node.left != null) {
            min = node.left.val;
            node = node.left;
        }
        return min;
    }

    private int height(AuditNode node) {
        if (node == null) return -1;
        return 1 + Math.max(height(node.left), height(node.right));
    }

    private boolean isValid(AuditNode node, long min, long max) {
        if (node == null) return true;
        if (node.val <= min || node.val >= max) return false;
        return isValid(node.left, min, node.val) && isValid(node.right, node.val, max);
    }

    private void printInorder(AuditNode node) {
        if (node != null) {
            printInorder(node.left);
            System.out.print(node.val + " ");
            printInorder(node.right);
        }
    }

    private void report(String operation, boolean result) {
        System.out.print(operation + " | Result: " + result + " | Inorder: [ ");
        printInorder(root);
        System.out.println("] | Size: " + size + " | Height: " + height(root) + " | Valid: " + isValid(root, Long.MIN_VALUE, Long.MAX_VALUE));
    }

    public static void main(String[] args) {
        BstOperationAudit bst = new BstOperationAudit();
        bst.add(50);
        bst.add(30);
        bst.add(70);
        bst.add(20);
        bst.add(40);
        bst.add(60);
        bst.add(80);
        bst.add(50); 

        bst.remove(99); 
        bst.remove(20); 
        bst.remove(30); 
        bst.remove(50); 
    }
}