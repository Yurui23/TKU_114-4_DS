import java.util.ArrayList;
import java.util.ArrayDeque;
import java.util.List;
import java.util.Queue;

class ResultNode {
    String val;
    ResultNode left, right;
    ResultNode(String val) { this.val = val; }
}

public class TraversalResultCollector {

    public static List<String> preorder(ResultNode node) {
        List<String> list = new ArrayList<>();
        if (node == null) return list;
        list.add(node.val);
        list.addAll(preorder(node.left));
        list.addAll(preorder(node.right));
        return list;
    }

    public static List<String> inorder(ResultNode node) {
        List<String> list = new ArrayList<>();
        if (node == null) return list;
        list.addAll(inorder(node.left));
        list.add(node.val);
        list.addAll(inorder(node.right));
        return list;
    }

    public static List<String> postorder(ResultNode node) {
        List<String> list = new ArrayList<>();
        if (node == null) return list;
        list.addAll(postorder(node.left));
        list.addAll(postorder(node.right));
        list.add(node.val);
        return list;
    }

    public static List<String> levelorder(ResultNode node) {
        List<String> list = new ArrayList<>();
        if (node == null) return list;
        Queue<ResultNode> q = new ArrayDeque<>();
        q.offer(node);
        while (!q.isEmpty()) {
            ResultNode curr = q.poll();
            list.add(curr.val);
            if (curr.left != null) q.offer(curr.left);
            if (curr.right != null) q.offer(curr.right);
        }
        return list;
    }

    public static void main(String[] args) {
        ResultNode root = new ResultNode("1");
        root.left = new ResultNode("2");
        System.out.println(preorder(root));
        System.out.println(levelorder(null));
    }
}