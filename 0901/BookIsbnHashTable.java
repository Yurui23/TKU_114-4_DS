import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

class BookEntry {
    String isbn;
    String title;
    BookEntry(String isbn, String title) {
        this.isbn = isbn;
        this.title = title;
    }
}

public class BookIsbnHashTable {
    private List<LinkedList<BookEntry>> buckets;
    private int size;

    public BookIsbnHashTable(int capacity) {
        buckets = new ArrayList<>(capacity);
        for (int i = 0; i < capacity; i++) {
            buckets.add(new LinkedList<>());
        }
    }

    private int getIndex(String isbn) {
        if (isbn == null) return 0;
        return Math.floorMod(isbn.hashCode(), buckets.size());
    }

    public void addOrUpdate(String isbn, String title) {
        if (isbn == null) return;
        int index = getIndex(isbn);
        for (BookEntry entry : buckets.get(index)) {
            if (entry.isbn.equals(isbn)) {
                entry.title = title; // Update
                return; // 確保 Update 不增加 size
            }
        }
        buckets.get(index).add(new BookEntry(isbn, title));
        size++;
    }

    public String search(String isbn) {
        if (isbn == null) return null;
        int index = getIndex(isbn);
        for (BookEntry entry : buckets.get(index)) {
            if (entry.isbn.equals(isbn)) return entry.title;
        }
        return null;
    }

    public boolean remove(String isbn) {
        if (isbn == null) return false;
        int index = getIndex(isbn);
        LinkedList<BookEntry> bucket = buckets.get(index);
        for (int i = 0; i < bucket.size(); i++) {
            if (bucket.get(i).isbn.equals(isbn)) {
                bucket.remove(i);
                size--;
                return true;
            }
        }
        return false;
    }

    public int getSize() { return size; }
    public double getLoadFactor() { return (double) size / buckets.size(); }

    public void bucketReport() {
        for (int i = 0; i < buckets.size(); i++) {
            System.out.print("Bucket " + i + ": ");
            for (BookEntry e : buckets.get(i)) {
                System.out.print("[" + e.isbn + ":" + e.title + "] ");
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        BookIsbnHashTable table = new BookIsbnHashTable(5);
        table.addOrUpdate("123", "Book A");
        table.addOrUpdate("123", "Book A Revised");
        table.bucketReport();
        System.out.println("Size (should be 1): " + table.getSize());
    }
}