class DirNode {
    String name;
    boolean isFile;
    int size;
    DirNode left, right;

    DirNode(String name, boolean isFile, int size) {
        this.name = name;
        this.isFile = isFile;
        this.size = size;
    }
}

public class DirectoryTreeReport {
    private int totalNodes;
    private int fileCount;
    private int dirCount;
    private int maxFileSize = -1;
    private String maxFileName = "";

    public int calculateDirectorySizes(DirNode node) {
        if (node == null) return 0;
        
        totalNodes++;
        if (node.isFile) {
            fileCount++;
            if (node.size > maxFileSize) {
                maxFileSize = node.size;
                maxFileName = node.name;
            }
            return node.size;
        }
        
        dirCount++;
        int leftSize = calculateDirectorySizes(node.left);
        int rightSize = calculateDirectorySizes(node.right);
        node.size = leftSize + rightSize; 
        return node.size;
    }

    public int getHeight(DirNode node) {
        if (node == null) return -1;
        return 1 + Math.max(getHeight(node.left), getHeight(node.right));
    }

    public void report(DirNode root) {
        totalNodes = 0;
        fileCount = 0;
        dirCount = 0;
        maxFileSize = -1;
        maxFileName = "";
        
        calculateDirectorySizes(root);
        
        System.out.println("Total Nodes: " + totalNodes);
        System.out.println("File Count: " + fileCount);
        System.out.println("Dir Count: " + dirCount);
        System.out.println("Height: " + getHeight(root));
        System.out.println("Max File: " + maxFileName + " (" + maxFileSize + ")");
    }

    public static void main(String[] args) {
        DirNode root = new DirNode("root", false, 0);
        root.left = new DirNode("file1.txt", true, 120);
        root.right = new DirNode("subDir", false, 0);
        root.right.left = new DirNode("file2.jpg", true, 2048);
        root.right.right = new DirNode("file3.pdf", true, 500);

        DirectoryTreeReport rep = new DirectoryTreeReport();
        rep.report(root);
    }
}