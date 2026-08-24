class DupNode {
    int key;
    int count;
    DupNode left, right;
    DupNode(int key) {
        this.key = key;
        this.count = 1;
    }
}

public class BstDuplicateCounter {
    private DupNode root;

    public void insert(int key) {
        root = insertRec(root, key);
    }

    private DupNode insertRec(DupNode node, int key) {
        if (node == null) return new DupNode(key);
        if (key == node.key) {
            node.count++;
        } else if (key < node.key) {
            node.left = insertRec(node.left, key);
        } else {
            node.right = insertRec(node.right, key);
        }
        return node;
    }

    public void inorder() {
        inorderRec(root);
        System.out.println();
    }

    private void inorderRec(DupNode node) {
        if (node != null) {
            inorderRec(node.left);
            System.out.print(node.key + "(" + node.count + ") ");
            inorderRec(node.right);
        }
    }

    public static void main(String[] args) {
        BstDuplicateCounter bst = new BstDuplicateCounter();
        bst.insert(10);
        bst.insert(5);
        bst.insert(10);
        bst.insert(15);
        bst.insert(5);
        bst.insert(10);
        
        bst.inorder();
    }
}