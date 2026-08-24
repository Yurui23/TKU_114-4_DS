class Student {
    String studentId;
    String name;
    Student(String studentId, String name) {
        this.studentId = studentId;
        this.name = name;
    }
}

class StudentNode {
    Student student;
    StudentNode left, right;
    StudentNode(Student student) { this.student = student; }
}

public class StudentBstIndex {
    private StudentNode root;

    public boolean insert(Student student) {
        if (student == null || student.studentId == null || search(student.studentId) != null) return false;
        root = insertRec(root, student);
        return true;
    }

    private StudentNode insertRec(StudentNode node, Student student) {
        if (node == null) return new StudentNode(student);
        int cmp = student.studentId.compareTo(node.student.studentId);
        if (cmp < 0) node.left = insertRec(node.left, student);
        else if (cmp > 0) node.right = insertRec(node.right, student);
        return node;
    }

    public Student search(String studentId) {
        if (studentId == null) return null;
        StudentNode current = root;
        while (current != null) {
            int cmp = studentId.compareTo(current.student.studentId);
            if (cmp == 0) return current.student;
            if (cmp < 0) current = current.left;
            else current = current.right;
        }
        return null;
    }

    public boolean delete(String studentId) {
        if (studentId == null || search(studentId) == null) return false;
        root = deleteRec(root, studentId);
        return true;
    }

    private StudentNode deleteRec(StudentNode node, String studentId) {
        if (node == null) return null;
        int cmp = studentId.compareTo(node.student.studentId);
        if (cmp < 0) {
            node.left = deleteRec(node.left, studentId);
        } else if (cmp > 0) {
            node.right = deleteRec(node.right, studentId);
        } else {
            if (node.left == null) return node.right;
            if (node.right == null) return node.left;
            node.student = minValue(node.right);
            node.right = deleteRec(node.right, node.student.studentId);
        }
        return node;
    }

    private Student minValue(StudentNode node) {
        Student min = node.student;
        while (node.left != null) {
            min = node.left.student;
            node = node.left;
        }
        return min;
    }

    public static void main(String[] args) {
        StudentBstIndex idx = new StudentBstIndex();
        idx.insert(new Student("S002", "Bob"));
        idx.insert(new Student("S001", "Alice"));
        idx.insert(new Student("S003", "Charlie"));
        
        System.out.println(idx.insert(new Student("S002", "Duplicate")));
        Student s = idx.search("S001");
        if (s != null) System.out.println(s.name);
        
        idx.delete("S001");
        System.out.println(idx.search("S001") == null);
    }
}