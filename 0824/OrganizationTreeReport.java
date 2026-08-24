import java.util.ArrayList;
import java.util.ArrayDeque;
import java.util.List;
import java.util.Queue;

class OrgNode {
    String name;
    OrgNode left, right;
    OrgNode(String name) { this.name = name; }
}

public class OrganizationTreeReport {

    public static String findParent(OrgNode root, String target) {
        if (root == null || root.name.equals(target)) return null;
        if ((root.left != null && root.left.name.equals(target)) || 
            (root.right != null && root.right.name.equals(target))) {
            return root.name;
        }
        String leftSearch = findParent(root.left, target);
        if (leftSearch != null) return leftSearch;
        return findParent(root.right, target);
    }

    public static int findDepth(OrgNode node, String target, int depth) {
        if (node == null) return -1;
        if (node.name.equals(target)) return depth;
        int left = findDepth(node.left, target, depth + 1);
        if (left != -1) return left;
        return findDepth(node.right, target, depth + 1);
    }

    public static List<String> pathFromRoot(OrgNode node, String target) {
        List<String> path = new ArrayList<>();
        if (node == null) return path;
        if (node.name.equals(target)) {
            path.add(node.name);
            return path;
        }
        List<String> leftPath = pathFromRoot(node.left, target);
        if (!leftPath.isEmpty()) {
            path.add(node.name);
            path.addAll(leftPath);
            return path;
        }
        List<String> rightPath = pathFromRoot(node.right, target);
        if (!rightPath.isEmpty()) {
            path.add(node.name);
            path.addAll(rightPath);
            return path;
        }
        return path;
    }

    public static void printByLevel(OrgNode root) {
        if (root == null) return;
        Queue<OrgNode> q = new ArrayDeque<>();
        q.offer(root);
        while (!q.isEmpty()) {
            OrgNode curr = q.poll();
            System.out.print(curr.name + " ");
            if (curr.left != null) q.offer(curr.left);
            if (curr.right != null) q.offer(curr.right);
        }
        System.out.println();
    }

    public static void main(String[] args) {
        OrgNode ceo = new OrgNode("CEO");
        ceo.left = new OrgNode("VP1");
        ceo.right = new OrgNode("VP2");
        ceo.left.left = new OrgNode("M1");

        System.out.println(findParent(ceo, "M1"));
        System.out.println(findDepth(ceo, "M1", 0));
        System.out.println(pathFromRoot(ceo, "M1"));
        printByLevel(ceo);
    }
}