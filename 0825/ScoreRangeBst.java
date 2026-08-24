class ScoreKey implements Comparable<ScoreKey> {
    int score;
    String studentId;

    ScoreKey(int score, String studentId) {
        this.score = score;
        this.studentId = studentId;
    }

    @Override
    public int compareTo(ScoreKey other) {
        int cmp = Integer.compare(this.score, other.score);
        if (cmp != 0) return cmp;
        return this.studentId.compareTo(other.studentId);
    }
}

class ScoreNode {
    ScoreKey key;
    ScoreNode left, right;
    ScoreNode(ScoreKey key) { this.key = key; }
}

public class ScoreRangeBst {
    private ScoreNode root;

    public void insert(int score, String studentId) {
        if (studentId == null) return;
        ScoreKey newKey = new ScoreKey(score, studentId);
        root = insertRec(root, newKey);
    }

    private ScoreNode insertRec(ScoreNode node, ScoreKey key) {
        if (node == null) return new ScoreNode(key);
        int cmp = key.compareTo(node.key);
        if (cmp < 0) node.left = insertRec(node.left, key);
        else if (cmp > 0) node.right = insertRec(node.right, key);
        return node;
    }

    public void printScoreRange(int minScore, int maxScore) {
        if (minScore > maxScore) return;
        printRangeRec(root, minScore, maxScore);
        System.out.println();
    }

    private void printRangeRec(ScoreNode node, int minScore, int maxScore) {
        if (node == null) return;
        if (minScore < node.key.score) printRangeRec(node.left, minScore, maxScore);
        if (node.key.score >= minScore && node.key.score <= maxScore) {
            System.out.print("(" + node.key.score + "," + node.key.studentId + ") ");
        }
        if (maxScore > node.key.score) printRangeRec(node.right, minScore, maxScore);
    }

    public static void main(String[] args) {
        ScoreRangeBst bst = new ScoreRangeBst();
        bst.insert(85, "S001");
        bst.insert(90, "S002");
        bst.insert(85, "S003");
        bst.insert(70, "S004");
        bst.insert(95, "S005");

        bst.printScoreRange(80, 90);
    }
}