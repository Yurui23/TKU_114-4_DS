import java.util.Objects;

class LibraryMember {
    private String memberId;
    private String name;
    private String email;

    public LibraryMember(String memberId, String name, String email) {
        this.memberId = memberId;
        this.name = name;
        this.email = email;
    }

    public String getMemberId() { return memberId; }
    public String getName() { return name; }
    public String getEmail() { return email; }

    @Override
    public String toString() {
        return "Member[ID: " + memberId + ", Name: " + name + ", Email: " + email + "]";
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        LibraryMember other = (LibraryMember) obj;
        return Objects.equals(memberId, other.memberId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(memberId);
    }
}

public class MemberEqualityPractice {
    public static void main(String[] args) {
        LibraryMember m1 = new LibraryMember("M1001", "張小明", "ming_old@example.com");
        LibraryMember m2 = new LibraryMember("M1001", "張小明", "ming_new@example.com");

        System.out.println("=== 物件內容 ===");
        System.out.println("m1: " + m1);
        System.out.println("m2: " + m2);

        System.out.println("----------------------------------------");
        System.out.println("=== 比較結果 ===");
        System.out.println("m1 == m2 記憶體位址比較: " + (m1 == m2));
        System.out.println("m1.equals(m2) 身份識別比較: " + m1.equals(m2));

        System.out.println("----------------------------------------");
        System.out.println("=== 邊界條件測試：與 null 比較 ===");
        System.out.println("m1.equals(null): " + m1.equals(null));
    }
}