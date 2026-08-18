class Instructor {
    private String id;
    private String name;

    public Instructor(String id, String name) {
        this.id = (id == null || id.trim().isEmpty()) ? "Unknown" : id.trim();
        this.name = (name == null || name.trim().isEmpty()) ? "Unknown" : name.trim();
    }

    public String getId() { return id; }
    public String getName() { return name; }

    @Override
    public String toString() {
        return name + " (ID: " + id + ")";
    }
}

class Course {
    private String courseCode;
    private String title;
    private Instructor instructor;

    public Course(String courseCode, String title, Instructor instructor) {
        this.courseCode = (courseCode == null || courseCode.trim().isEmpty()) ? "Unknown" : courseCode.trim();
        this.title = (title == null || title.trim().isEmpty()) ? "Unknown" : title.trim();
        this.instructor = instructor;
    }

    public String summary() {
        String instructorInfo = (instructor != null) ? instructor.toString() : "未指定授課教師";
        return "課程代碼: " + courseCode + " | 課程名稱: " + title + " | 授課教師: " + instructorInfo;
    }

    public String getCourseCode() { return courseCode; }
    public String getTitle() { return title; }
    public Instructor getInstructor() { return instructor; }
}

public class CourseComposition {
    public static void main(String[] args) {
        System.out.println("=== 課堂實作題二：課程與授課者 Composition 測試 ===");

        Instructor profWang = new Instructor("T101", "王大明教授");

        Course course1 = new Course("CS101", "物件導向程式設計", profWang);
        Course course2 = new Course("CS102", "資料結構", profWang);

        System.out.println(course1.summary());
        System.out.println(course2.summary());

        System.out.println("----------------------------------------");
        boolean isSameReference = (course1.getInstructor() == course2.getInstructor());
        System.out.println("兩門課程是否共用同一個 Instructor 物件實例: " + isSameReference);
    }
}