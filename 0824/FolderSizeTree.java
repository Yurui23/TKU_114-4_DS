import java.util.ArrayList;
import java.util.List;

class FolderNode {
    String name;
    int ownSize;
    FolderNode left, right;
    FolderNode(String name, int ownSize) { 
        this.name = name; 
        this.ownSize = ownSize; 
    }
}

public class FolderSizeTree {
    static int maxSubtreeSize = -1;
    static String maxSubtreeName = "";

    public static int calculateSizes(FolderNode node, List<String> leafFolders) {
        if (node == null) return 0;

        if (node.left == null && node.right == null) {
            leafFolders.add(node.name);
        }

        int leftSize = calculateSizes(node.left, leafFolders);
        int rightSize = calculateSizes(node.right, leafFolders);
        int totalSize = node.ownSize + leftSize + rightSize;

        if (totalSize > maxSubtreeSize) {
            maxSubtreeSize = totalSize;
            maxSubtreeName = node.name;
        }

        return totalSize;
    }

    public static void main(String[] args) {
        FolderNode root = new FolderNode("root", 10);
        root.left = new FolderNode("docs", 50);
        root.right = new FolderNode("images", 20);
        root.left.left = new FolderNode("work", 100);

        List<String> leaves = new ArrayList<>();
        int total = calculateSizes(root, leaves);

        System.out.println(total);
        System.out.println(maxSubtreeName + " (" + maxSubtreeSize + ")");
        System.out.println(leaves);
    }
}