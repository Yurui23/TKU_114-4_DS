class Member {
    String memberId;
    String email;
    Member(String memberId, String email) {
        this.memberId = memberId;
        this.email = email;
    }
}

class MemberNode {
    Member member;
    MemberNode left, right;
    MemberNode(Member member) { this.member = member; }
}

public class MemberBstIndex {
    private MemberNode root;

    public boolean add(Member member) {
        if (member == null || member.memberId == null || member.email == null || member.email.trim().isEmpty()) return false;
        if (find(member.memberId) != null) return false;
        root = addRec(root, member);
        return true;
    }

    private MemberNode addRec(MemberNode node, Member member) {
        if (node == null) return new MemberNode(member);
        int cmp = member.memberId.compareTo(node.member.memberId);
        if (cmp < 0) node.left = addRec(node.left, member);
        else if (cmp > 0) node.right = addRec(node.right, member);
        return node;
    }

    public Member find(String memberId) {
        if (memberId == null) return null;
        MemberNode curr = root;
        while (curr != null) {
            int cmp = memberId.compareTo(curr.member.memberId);
            if (cmp == 0) return curr.member;
            if (cmp < 0) curr = curr.left;
            else curr = curr.right;
        }
        return null;
    }

    public boolean updateEmail(String memberId, String newEmail) {
        if (newEmail == null || newEmail.trim().isEmpty()) return false;
        Member m = find(memberId);
        if (m != null) {
            m.email = newEmail;
            return true;
        }
        return false;
    }

    public boolean remove(String memberId) {
        if (memberId == null || find(memberId) == null) return false;
        root = removeRec(root, memberId);
        return true;
    }

    private MemberNode removeRec(MemberNode node, String memberId) {
        if (node == null) return null;
        int cmp = memberId.compareTo(node.member.memberId);
        if (cmp < 0) node.left = removeRec(node.left, memberId);
        else if (cmp > 0) node.right = removeRec(node.right, memberId);
        else {
            if (node.left == null) return node.right;
            if (node.right == null) return node.left;
            node.member = getMin(node.right);
            node.right = removeRec(node.right, node.member.memberId);
        }
        return node;
    }

    private Member getMin(MemberNode node) {
        Member min = node.member;
        while (node.left != null) {
            min = node.left.member;
            node = node.left;
        }
        return min;
    }

    public void inorderReport() {
        inorderRec(root);
        System.out.println();
    }

    private void inorderRec(MemberNode node) {
        if (node != null) {
            inorderRec(node.left);
            System.out.print("[" + node.member.memberId + " : " + node.member.email + "] ");
            inorderRec(node.right);
        }
    }

    public static void main(String[] args) {
        MemberBstIndex idx = new MemberBstIndex();
        idx.add(new Member("M002", "bob@test.com"));
        idx.add(new Member("M001", "alice@test.com"));
        idx.add(new Member("M001", "dup@test.com")); 
        idx.add(new Member("M003", "  ")); 
        
        idx.updateEmail("M002", "bob_new@test.com");
        idx.remove("M001");
        
        idx.inorderReport();
    }
}