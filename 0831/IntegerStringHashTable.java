import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

class HashEntry {
    int key;
    String value;
    HashEntry(int key, String value) {
        this.key = key;
        this.value = value;
    }
}

public class IntegerStringHashTable {
    private List<LinkedList<HashEntry>> buckets;
    private int capacity;
    private int size;

    public IntegerStringHashTable(int capacity) {
        this.capacity = Math.max(1, capacity);
        this.buckets = new ArrayList<>(this.capacity);
        for (int i = 0; i < this.capacity; i++) {
            buckets.add(new LinkedList<>());
        }
    }

    private int getBucketIndex(int key) {
        return Math.floorMod(key, capacity);
    }

    public void put(int key, String value) {
        LinkedList<HashEntry> bucket = buckets.get(getBucketIndex(key));
        for (HashEntry entry : bucket) {
            if (entry.key == key) {
                entry.value = value; // 相同 key 更新
                return;
            }
        }
        bucket.add(new HashEntry(key, value));
        size++;
    }

    public String get(int key) {
        LinkedList<HashEntry> bucket = buckets.get(getBucketIndex(key));
        for (HashEntry entry : bucket) {
            if (entry.key == key) return entry.value;
        }
        return null;
    }

    public boolean containsKey(int key) {
        return get(key) != null;
    }

    public void remove(int key) {
        LinkedList<HashEntry> bucket = buckets.get(getBucketIndex(key));
        for (int i = 0; i < bucket.size(); i++) {
            if (bucket.get(i).key == key) {
                bucket.remove(i);
                size--;
                return;
            }
        }
    }

    public int size() {
        return size;
    }

    public void bucketReport() {
        for (int i = 0; i < capacity; i++) {
            System.out.print("Bucket " + i + ": ");
            for (HashEntry e : buckets.get(i)) {
                System.out.print("[" + e.key + ":" + e.value + "] ");
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        IntegerStringHashTable ht = new IntegerStringHashTable(5);
        ht.put(1, "A");
        ht.put(6, "B");
        ht.put(-4, "C"); // 測試負數
        ht.put(1, "A_UPDATED"); // 測試更新
        
        ht.bucketReport();
        System.out.println("Size: " + ht.size());
        
        ht.remove(6);
        System.out.println("Contains 6? " + ht.containsKey(6));
    }
}