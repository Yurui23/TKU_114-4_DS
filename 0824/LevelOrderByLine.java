import java.util.ArrayDeque;
import java.util.Queue;

class LevelNode {
    String val;
    LevelNode left, right;
    LevelNode(String val) { this.val = val; }
}

public class LevelOrderByLine {

    public static void printByLevel(LevelNode root) {
        if (root == null) return;
        Queue<LevelNode> queue = new ArrayDeque<>();
        queue.offer(root);

        while (!queue.isEmpty()) {
            int levelSize = queue.size();
            System.out.print("Count " + levelSize + " : ");
            for (int i = 0; i < levelSize; i++) {
                LevelNode current = queue.poll();
                System.out.print(current.val + " ");
                if (current.left != null) queue.offer(current.left);
                if (current.right != null) queue.offer(current.right);
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        LevelNode root = new LevelNode("1");
        root.left = new LevelNode("2");
        root.right = new LevelNode("3");
        root.left.left = new LevelNode("4");
        root.right.right = new LevelNode("5");

        printByLevel(root);
        System.out.println("---");
        printByLevel(null);
    }
}