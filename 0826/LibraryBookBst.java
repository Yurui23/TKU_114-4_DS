import java.util.ArrayList;
import java.util.List;

class Book {
    String isbn;
    String title;
    String author;
    boolean available;
    Book(String isbn, String title, String author, boolean available) {
        this.isbn = isbn;
        this.title = title;
        this.author = author;
        this.available = available;
    }
}

class BookNode {
    Book book;
    BookNode left, right;
    BookNode(Book book) { this.book = book; }
}

public class LibraryBookBst {
    private BookNode root;

    public boolean add(Book book) {
        if (book == null || book.isbn == null || find(book.isbn) != null) return false;
        root = addRec(root, book);
        return true;
    }

    private BookNode addRec(BookNode node, Book book) {
        if (node == null) return new BookNode(book);
        int cmp = book.isbn.compareTo(node.book.isbn);
        if (cmp < 0) node.left = addRec(node.left, book);
        else if (cmp > 0) node.right = addRec(node.right, book);
        return node;
    }

    public Book find(String isbn) {
        if (isbn == null) return null;
        BookNode curr = root;
        while (curr != null) {
            int cmp = isbn.compareTo(curr.book.isbn);
            if (cmp == 0) return curr.book;
            if (cmp < 0) curr = curr.left;
            else curr = curr.right;
        }
        return null;
    }

    public boolean borrow(String isbn) {
        Book b = find(isbn);
        if (b != null && b.available) {
            b.available = false;
            return true;
        }
        return false;
    }

    public boolean returnBook(String isbn) {
        Book b = find(isbn);
        if (b != null && !b.available) {
            b.available = true;
            return true;
        }
        return false;
    }

    public boolean remove(String isbn) {
        Book b = find(isbn);
        if (b == null || !b.available) return false;
        root = removeRec(root, isbn);
        return true;
    }

    private BookNode removeRec(BookNode node, String isbn) {
        if (node == null) return null;
        int cmp = isbn.compareTo(node.book.isbn);
        if (cmp < 0) node.left = removeRec(node.left, isbn);
        else if (cmp > 0) node.right = removeRec(node.right, isbn);
        else {
            if (node.left == null) return node.right;
            if (node.right == null) return node.left;
            node.book = getMin(node.right);
            node.right = removeRec(node.right, node.book.isbn);
        }
        return node;
    }
    
    private Book getMin(BookNode node) {
        Book min = node.book;
        while(node.left != null) { min = node.left.book; node = node.left; }
        return min;
    }

    public List<Book> rangeQuery(String startIsbn, String endIsbn) {
        List<Book> res = new ArrayList<>();
        if (startIsbn == null || endIsbn == null || startIsbn.compareTo(endIsbn) > 0) return res;
        rangeRec(root, startIsbn, endIsbn, res);
        return res;
    }

    private void rangeRec(BookNode node, String start, String end, List<Book> res) {
        if (node == null) return;
        if (start.compareTo(node.book.isbn) < 0) rangeRec(node.left, start, end, res);
        if (node.book.isbn.compareTo(start) >= 0 && node.book.isbn.compareTo(end) <= 0) res.add(node.book);
        if (end.compareTo(node.book.isbn) > 0) rangeRec(node.right, start, end, res);
    }

    public void inorderReport() {
        inorderRec(root);
        System.out.println();
    }

    private void inorderRec(BookNode node) {
        if (node != null) {
            inorderRec(node.left);
            System.out.print("[" + node.book.isbn + ":" + node.book.available + "] ");
            inorderRec(node.right);
        }
    }

    public static void main(String[] args) {
        LibraryBookBst lib = new LibraryBookBst();
        lib.add(new Book("B002", "Java", "Author A", true));
        lib.add(new Book("B001", "C++", "Author B", true));
        lib.add(new Book("B003", "Python", "Author C", true));
        
        lib.borrow("B002");
        System.out.println(lib.remove("B002")); 
        lib.returnBook("B002");
        System.out.println(lib.remove("B002")); 
        
        lib.inorderReport();
    }
}