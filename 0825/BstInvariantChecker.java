class InvNode {
    int val;
    InvNode left, right;
    InvNode(int val) { this.val = val; }
}

public class BstInvariantChecker {
    
    public static boolean isValidBST(InvNode root) {
        return validate(root, Long.MIN_VALUE, Long.MAX_VALUE);
    }

    private static boolean validate(InvNode node, long min, long max) {
        if (node == null) return true;
        if (node.val <= min || node.val >= max) return false;
        return validate(node.left, min, node.val) && validate(node.right, node.val, max);
    }

    public static void main(String[] args) {
        InvNode validRoot = new InvNode(10);
        validRoot.left = new InvNode(5);
        validRoot.right = new InvNode(15);
        System.out.println(isValidBST(validRoot));

        InvNode invalid1 = new InvNode(10);
        invalid1.left = new InvNode(5);
        invalid1.right = new InvNode(15);
        invalid1.left.right = new InvNode(12);
        System.out.println(isValidBST(invalid1));

        InvNode invalid2 = new InvNode(20);
        invalid2.left = new InvNode(10);
        invalid2.right = new InvNode(30);
        invalid2.right.left = new InvNode(15);
        System.out.println(isValidBST(invalid2));

        InvNode invalid3 = new InvNode(50);
        invalid3.left = new InvNode(30);
        invalid3.left.left = new InvNode(20);
        invalid3.left.left.left = new InvNode(60);
        System.out.println(isValidBST(invalid3));
    }
}