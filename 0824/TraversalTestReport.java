import java.util.ArrayList;
import java.util.ArrayDeque;
import java.util.List;
import java.util.Queue;

class TestNode {
    int val;
    TestNode left, right;
    TestNode(int val) { this.val = val; }
}

public class TraversalTestReport {

    public static void runReport(TestNode root, String treeName) {
        System.out.println("Tree: " + treeName);
        System.out.println("Pre:   " + pre(root));
        System.out.println("In:    " + in(root));
        System.out.println("Post:  " + post(root));
        System.out.println("Level: " + level(root));
        System.out.println("---");
    }

    private static List<Integer> pre(TestNode node) {
        List<Integer> l = new ArrayList<>();
        if (node == null) return l;
        l.add(node.val);
        l.addAll(pre(node.left));
        l.addAll(pre(node.right));
        return l;
    }

    private static List<Integer> in(TestNode node) {
        List<Integer> l = new ArrayList<>();
        if (node == null) return l;
        l.addAll(in(node.left));
        l.add(node.val);
        l.addAll(in(node.right));
        return l;
    }

    private static List<Integer> post(TestNode node) {
        List<Integer> l = new ArrayList<>();
        if (node == null) return l;
        l.addAll(post(node.left));
        l.addAll(post(node.right));
        l.add(node.val);
        return l;
    }

    private static List<Integer> level(TestNode node) {
        List<Integer> l = new ArrayList<>();
        if (node == null) return l;
        Queue<TestNode> q = new ArrayDeque<>();
        q.offer(node);
        while (!q.isEmpty()) {
            TestNode c = q.poll();
            l.add(c.val);
            if (c.left != null) q.offer(c.left);
            if (c.right != null) q.offer(c.right);
        }
        return l;
    }

    public static void main(String[] args) {
        runReport(null, "Empty");

        TestNode single = new TestNode(1);
        runReport(single, "Single Node");

        TestNode onlyLeft = new TestNode(1);
        onlyLeft.left = new TestNode(2);
        onlyLeft.left.left = new TestNode(3);
        runReport(onlyLeft, "Only Left");

        TestNode complete = new TestNode(1);
        complete.left = new TestNode(2);
        complete.right = new TestNode(3);
        complete.left.left = new TestNode(4);
        complete.left.right = new TestNode(5);
        complete.right.left = new TestNode(6);
        complete.right.right = new TestNode(7);
        runReport(complete, "Complete Tree");
    }
}