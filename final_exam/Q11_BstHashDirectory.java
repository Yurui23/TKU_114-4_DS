import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Q11_BstHashDirectory {
    
    private static class Node {
        int id;
        Node left, right;
        Node(int id) { this.id = id; }
    }

    private Node root;
    private Map<Integer, String> hashIndex = new HashMap<>();

    public boolean add(int id, String name) {
        if (id <= 0 || name == null || name.trim().isEmpty() || hashIndex.containsKey(id)) {
            return false;
        }
        
        root = addRec(root, id);
        hashIndex.put(id, name.trim());
        return true;
    }

    private Node addRec(Node node, int id) {
        if (node == null) return new Node(id);
        if (id < node.id) node.left = addRec(node.left, id);
        else if (id > node.id) node.right = addRec(node.right, id);
        return node;
    }

    public String findName(int id) {
        return hashIndex.get(id);
    }

    public boolean remove(int id) {
        if (!hashIndex.containsKey(id)) return false;
        
        root = removeRec(root, id);
        hashIndex.remove(id);
        return true;
    }

    private Node removeRec(Node node, int id) {
        if (node == null) return null;
        if (id < node.id) {
            node.left = removeRec(node.left, id);
        } else if (id > node.id) {
            node.right = removeRec(node.right, id);
        } else {
            if (node.left == null) return node.right;
            if (node.right == null) return node.left;
            
            node.id = getMin(node.right);
            node.right = removeRec(node.right, node.id);
        }
        return node;
    }

    private int getMin(Node node) {
        int min = node.id;
        while (node.left != null) {
            min = node.left.id;
            node = node.left;
        }
        return min;
    }

    public List<Integer> idsBetween(int low, int high) {
        List<Integer> result = new ArrayList<>();
        if (low > high) return result;
        rangeRec(root, low, high, result);
        return result;
    }

    private void rangeRec(Node node, int low, int high, List<Integer> result) {
        if (node == null) return;
        if (low < node.id) rangeRec(node.left, low, high, result);
        if (node.id >= low && node.id <= high) result.add(node.id);
        if (high > node.id) rangeRec(node.right, low, high, result);
    }

    public int size() {
        return hashIndex.size();
    }

    // 測試用主程式
    public static void main(String[] args) {
        Q11_BstHashDirectory bstHash = new Q11_BstHashDirectory();
        bstHash.add(10, "Alice");
        bstHash.add(5, "Bob");
        bstHash.add(15, "Charlie");
        
        System.out.println("Find ID 10: " + bstHash.findName(10));
        System.out.println("IDs between 1 and 12: " + bstHash.idsBetween(1, 12));
        
        bstHash.remove(10);
        System.out.println("Size after remove: " + bstHash.size());
    }
}