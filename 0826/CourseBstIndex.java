import java.util.ArrayList;
import java.util.List;

class Course {
    String courseCode;
    int credit;
    Course(String courseCode, int credit) {
        this.courseCode = courseCode;
        this.credit = credit;
    }
}

class CourseNode {
    Course course;
    CourseNode left, right;
    CourseNode(Course course) { this.course = course; }
}

public class CourseBstIndex {
    private CourseNode root;

    public boolean add(Course course) {
        if (course == null || course.courseCode == null || course.credit < 1 || course.credit > 6) return false;
        if (find(course.courseCode) != null) return false;
        root = addRec(root, course);
        return true;
    }

    private CourseNode addRec(CourseNode node, Course course) {
        if (node == null) return new CourseNode(course);
        int cmp = course.courseCode.compareTo(node.course.courseCode);
        if (cmp < 0) node.left = addRec(node.left, course);
        else if (cmp > 0) node.right = addRec(node.right, course);
        return node;
    }

    public Course find(String code) {
        if (code == null) return null;
        CourseNode curr = root;
        while (curr != null) {
            int cmp = code.compareTo(curr.course.courseCode);
            if (cmp == 0) return curr.course;
            if (cmp < 0) curr = curr.left;
            else curr = curr.right;
        }
        return null;
    }

    public boolean remove(String code) {
        if (code == null || find(code) == null) return false;
        root = removeRec(root, code);
        return true;
    }

    private CourseNode removeRec(CourseNode node, String code) {
        if (node == null) return null;
        int cmp = code.compareTo(node.course.courseCode);
        if (cmp < 0) node.left = removeRec(node.left, code);
        else if (cmp > 0) node.right = removeRec(node.right, code);
        else {
            if (node.left == null) return node.right;
            if (node.right == null) return node.left;
            node.course = getMin(node.right);
            node.right = removeRec(node.right, node.course.courseCode);
        }
        return node;
    }

    private Course getMin(CourseNode node) {
        Course min = node.course;
        while (node.left != null) {
            min = node.left.course;
            node = node.left;
        }
        return min;
    }

    public boolean updateCredit(String code, int newCredit) {
        if (newCredit < 1 || newCredit > 6) return false;
        Course c = find(code);
        if (c != null) {
            c.credit = newCredit;
            return true;
        }
        return false;
    }

    public List<Course> codeRangeQuery(String startCode, String endCode) {
        List<Course> result = new ArrayList<>();
        if (startCode == null || endCode == null || startCode.compareTo(endCode) > 0) return result;
        rangeRec(root, startCode, endCode, result);
        return result;
    }

    private void rangeRec(CourseNode node, String startCode, String endCode, List<Course> result) {
        if (node == null) return;
        if (startCode.compareTo(node.course.courseCode) < 0) rangeRec(node.left, startCode, endCode, result);
        if (node.course.courseCode.compareTo(startCode) >= 0 && node.course.courseCode.compareTo(endCode) <= 0) {
            result.add(node.course);
        }
        if (endCode.compareTo(node.course.courseCode) > 0) rangeRec(node.right, startCode, endCode, result);
    }

    public void inorderReport() {
        inorderRec(root);
        System.out.println();
    }

    private void inorderRec(CourseNode node) {
        if (node != null) {
            inorderRec(node.left);
            System.out.print("[" + node.course.courseCode + ":" + node.course.credit + "] ");
            inorderRec(node.right);
        }
    }

    public static void main(String[] args) {
        CourseBstIndex idx = new CourseBstIndex();
        idx.add(new Course("CS101", 3));
        idx.add(new Course("CS102", 4));
        idx.add(new Course("MA101", 7)); 
        idx.updateCredit("CS101", 2);
        idx.remove("CS102");
        idx.add(new Course("EE101", 3));
        idx.inorderReport();
        List<Course> q = idx.codeRangeQuery("CS000", "EE999");
        for (Course c : q) System.out.print(c.courseCode + " ");
    }
}