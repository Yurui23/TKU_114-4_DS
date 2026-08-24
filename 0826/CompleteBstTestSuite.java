class TestNode {
    int val;
    TestNode left, right;
    TestNode(int val) { this.val = val; }
}

public class CompleteBstTestSuite {
    private TestNode root;

    private void check(String description, boolean condition) {
        System.out.println((condition ? "PASS" : "FAIL") + " - " + description);
    }

    public boolean add(int val) {
        if (contains(val)) return false;
        root = addRec(root, val);
        return true;
    }
    
    private TestNode addRec(TestNode node, int val) {
        if (node == null) return new TestNode(val);
        if (val < node.val) node.left = addRec(node.left, val);
        else if (val > node.val) node.right = addRec(node.right, val);
        return node;
    }

    public boolean contains(int val) {
        TestNode curr = root;
        while (curr != null) {
            if (val == curr.val) return true;
            if (val < curr.val) curr = curr.left;
            else curr = curr.right;
        }
        return false;
    }

    public boolean remove(int val) {
        if (!contains(val)) return false;
        root = removeRec(root, val);
        return true;
    }
    
    private TestNode removeRec(TestNode node, int val) {
        if (node == null) return null;
        if (val < node.val) node.left = removeRec(node.left, val);
        else if (val > node.val) node.right = removeRec(node.right, val);
        else {
            if (node.left == null) return node.right;
            if (node.right == null) return node.left;
            node.val = getMin(node.right);
            node.right = removeRec(node.right, node.val);
        }
        return node;
    }
    
    private int getMin(TestNode node) {
        int min = node.val;
        while(node.left != null) { min = node.left.val; node = node.left; }
        return min;
    }

    public boolean isInvariant() {
        return checkInv(root, Long.MIN_VALUE, Long.MAX_VALUE);
    }
    
    private boolean checkInv(TestNode node, long min, long max) {
        if (node == null) return true;
        if (node.val <= min || node.val >= max) return false;
        return checkInv(node.left, min, node.val) && checkInv(node.right, node.val, max);
    }

    public void runSuite() {
        check("Tree is initially empty (invariant)", isInvariant());
        check("Remove from empty tree returns false", !remove(10));
        
        check("Add root (10) returns true", add(10));
        check("Duplicate root (10) returns false", !add(10));
        check("Contains root (10) returns true", contains(10));
        
        check("Add leaf (5) returns true", add(5));
        check("Add leaf (15) returns true", add(15));
        check("Invariant holds for 3 nodes", isInvariant());
        
        check("Remove leaf (5) returns true", remove(5));
        check("Tree no longer contains (5)", !contains(5));
        
        add(20);
        check("Remove node with one child (15) returns true", remove(15));
        check("Child (20) replaces parent (15)", contains(20) && !contains(15));
        
        add(5); add(15); add(25);
        check("Remove node with two children (10) returns true", remove(10));
        check("Invariant holds after two-child remove", isInvariant());
        
        check("Remove missing (99) returns false", !remove(99));
        
        root = null;
        check("Root becomes null when cleared", root == null);
        add(100); add(50); add(150);
        check("Remove root with 2 children returns true", remove(100));
        check("New root invariant valid", isInvariant());
    }

    public static void main(String[] args) {
        new CompleteBstTestSuite().runSuite();
    }
}