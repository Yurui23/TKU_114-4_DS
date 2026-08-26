import java.util.ArrayList;
import java.util.List;

public class Q11_BstDeletion {

    private static class Node {
        int value;
        Node left, right;
        Node(int value) { this.value = value; }
    }

    private Node root;
    private int count;

    public boolean add(int value) {
        if (contains(value)) return false;
        root = addRec(root, value);
        count++;
        return true;
    }

    private Node addRec(Node node, int value) {
        if (node == null) return new Node(value);
        if (value < node.value) node.left = addRec(node.left, value);
        else if (value > node.value) node.right = addRec(node.right, value);
        return node;
    }

    public boolean contains(int value) {
        Node current = root;
        while (current != null) {
            if (value == current.value) return true;
            if (value < current.value) current = current.left;
            else current = current.right;
        }
        return false;
    }

    public boolean remove(int value) {
        if (!contains(value)) return false;
        root = removeRec(root, value);
        count--;
        return true;
    }

    private Node removeRec(Node node, int value) {
        if (node == null) return null;
        if (value < node.value) {
            node.left = removeRec(node.left, value);
        } else if (value > node.value) {
            node.right = removeRec(node.right, value);
        } else {
            if (node.left == null) return node.right;
            if (node.right == null) return node.left;
            node.value = getMin(node.right);
            node.right = removeRec(node.right, node.value);
        }
        return node;
    }

    private int getMin(Node node) {
        int min = node.value;
        while (node.left != null) {
            min = node.left.value;
            node = node.left;
        }
        return min;
    }

    public int size() {
        return count;
    }

    public List<Integer> inorder() {
        List<Integer> result = new ArrayList<>();
        inorderRec(root, result);
        return result;
    }

    private void inorderRec(Node node, List<Integer> result) {
        if (node != null) {
            inorderRec(node.left, result);
            result.add(node.value);
            inorderRec(node.right, result);
        }
    }

    public boolean isValid() {
        return isValidRec(root, Long.MIN_VALUE, Long.MAX_VALUE);
    }

    private boolean isValidRec(Node node, long min, long max) {
        if (node == null) return true;
        if (node.value <= min || node.value >= max) return false;
        return isValidRec(node.left, min, node.value) && isValidRec(node.right, node.value, max);
    }
}