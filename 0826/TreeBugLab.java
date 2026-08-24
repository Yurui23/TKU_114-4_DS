class BugNode {
    int val;
    BugNode left, right;
    BugNode(int val) { this.val = val; }
}

public class TreeBugLab {
    private BugNode root;

    public void insert(int val) {
        root = insertRec(root, val);
    }

    private BugNode insertRec(BugNode node, int val) {
        if (node == null) return new BugNode(val);
        if (val < node.val) node.left = insertRec(node.left, val);
        else if (val > node.val) node.right = insertRec(node.right, val);
        return node;
    }

    public boolean search(int val) {
        BugNode curr = root;
        while (curr != null) {
            if (curr.val == val) return true;
            if (val < curr.val) curr = curr.left; 
            else curr = curr.right; 
        }
        return false;
    }

    public void inorder() {
        inorderRec(root);
        System.out.println();
    }

    private void inorderRec(BugNode node) {
        if (node != null) {
            inorderRec(node.left);
            System.out.print(node.val + " "); 
            inorderRec(node.right);
        }
    }

    public void delete(int val) {
        root = deleteRec(root, val);
    }

    private BugNode deleteRec(BugNode node, int val) {
        if (node == null) return null;
        if (val < node.val) {
            node.left = deleteRec(node.left, val);
        } else if (val > node.val) {
            node.right = deleteRec(node.right, val);
        } else {
            if (node.left == null) return node.right; 
            if (node.right == null) return node.left; 
            node.val = getMin(node.right);
            node.right = deleteRec(node.right, node.val);
        }
        return node;
    }

    private int getMin(BugNode node) {
        int min = node.val;
        while (node.left != null) {
            min = node.left.val;
            node = node.left;
        }
        return min;
    }

    public boolean validate() {
        return validateRec(root, Long.MIN_VALUE, Long.MAX_VALUE);
    }

    private boolean validateRec(BugNode node, long min, long max) {
        if (node == null) return true;
        if (node.val <= min || node.val >= max) return false; 
        return validateRec(node.left, min, node.val) && validateRec(node.right, node.val, max); 
    }

    public static void main(String[] args) {
        TreeBugLab lab = new TreeBugLab();
        lab.insert(10);
        lab.insert(5);
        lab.insert(15);

        System.out.println(lab.search(5)); 
        lab.inorder(); 
        
        lab.delete(10);
        lab.inorder(); 

        System.out.println(lab.validate());
    }
}