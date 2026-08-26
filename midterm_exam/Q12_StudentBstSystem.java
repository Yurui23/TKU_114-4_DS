import java.util.ArrayList;
import java.util.List;

public class Q12_StudentBstSystem {

    public static class Student {
        private final int id;
        private final String name;
        private int score;

        public Student(int id, String name, int score) {
            if (id <= 0 || name == null || name.trim().isEmpty()) {
                throw new IllegalArgumentException("Invalid ID or Name.");
            }
            this.id = id;
            this.name = name.trim();
            this.score = Math.max(0, Math.min(100, score));
        }

        public int getId() { return id; }
        public String getName() { return name; }
        public int getScore() { return score; }

        @Override
        public String toString() {
            return id + " " + name + " " + score;
        }
    }

    private static class Node {
        Student student;
        Node left, right;
        Node(Student student) { this.student = student; }
    }

    private Node root;

    public boolean add(Student student) {
        if (student == null || find(student.getId()) != null) return false;
        root = addRec(root, student);
        return true;
    }

    private Node addRec(Node node, Student student) {
        if (node == null) return new Node(student);
        if (student.getId() < node.student.getId()) node.left = addRec(node.left, student);
        else if (student.getId() > node.student.getId()) node.right = addRec(node.right, student);
        return node;
    }

    public Student find(int id) {
        Node curr = root;
        while (curr != null) {
            if (id == curr.student.getId()) return curr.student;
            if (id < curr.student.getId()) curr = curr.left;
            else curr = curr.right;
        }
        return null;
    }

    public boolean updateScore(int id, int score) {
        Student target = find(id);
        if (target != null) {
            target.score = Math.max(0, Math.min(100, score));
            return true;
        }
        return false;
    }

    public boolean remove(int id) {
        if (find(id) == null) return false;
        root = removeRec(root, id);
        return true;
    }

    private Node removeRec(Node node, int id) {
        if (node == null) return null;
        if (id < node.student.getId()) {
            node.left = removeRec(node.left, id);
        } else if (id > node.student.getId()) {
            node.right = removeRec(node.right, id);
        } else {
            if (node.left == null) return node.right;
            if (node.right == null) return node.left;
            node.student = getMin(node.right);
            node.right = removeRec(node.right, node.student.getId());
        }
        return node;
    }

    private Student getMin(Node node) {
        Student min = node.student;
        while (node.left != null) {
            min = node.left.student;
            node = node.left;
        }
        return min;
    }

    public List<Student> studentsBetween(int lowId, int highId) {
        List<Student> result = new ArrayList<>();
        if (lowId > highId) return result;
        rangeRec(root, lowId, highId, result);
        return result;
    }

    private void rangeRec(Node node, int lowId, int highId, List<Student> result) {
        if (node == null) return;
        if (lowId < node.student.getId()) rangeRec(node.left, lowId, highId, result);
        if (node.student.getId() >= lowId && node.student.getId() <= highId) {
            result.add(node.student);
        }
        if (highId > node.student.getId()) rangeRec(node.right, lowId, highId, result);
    }

    public List<Student> inorder() {
        List<Student> result = new ArrayList<>();
        inorderRec(root, result);
        return result;
    }

    private void inorderRec(Node node, List<Student> result) {
        if (node != null) {
            inorderRec(node.left, result);
            result.add(node.student);
            inorderRec(node.right, result);
        }
    }
}