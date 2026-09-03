
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Q04_ChainedHashTable {
    
    private static class Entry {
        int key;
        String value;
        Entry(int key, String value) {
            this.key = key;
            this.value = value;
        }
    }

    private List<LinkedList<Entry>> buckets;
    private int size;

    public Q04_ChainedHashTable(int bucketCount) {
        if (bucketCount <= 0) {
            throw new IllegalArgumentException("Bucket count must be greater than 0");
        }
        buckets = new ArrayList<>(bucketCount);
        for (int i = 0; i < bucketCount; i++) {
            buckets.add(new LinkedList<>());
        }
        this.size = 0;
    }

    private int getIndex(int key) {
        return Math.floorMod(key, buckets.size());
    }

    public void put(int key, String value) {
        int index = getIndex(key);
        LinkedList<Entry> bucket = buckets.get(index);
        
        for (Entry e : bucket) {
            if (e.key == key) {
                e.value = value;
                return;
            }
        }
        
        bucket.add(new Entry(key, value));
        size++;
    }

    public String get(int key) {
        int index = getIndex(key);
        for (Entry e : buckets.get(index)) {
            if (e.key == key) {
                return e.value;
            }
        }
        return null;
    }

    public boolean remove(int key) {
        int index = getIndex(key);
        LinkedList<Entry> bucket = buckets.get(index);
        
        for (int i = 0; i < bucket.size(); i++) {
            if (bucket.get(i).key == key) {
                bucket.remove(i);
                size--;
                return true;
            }
        }
        return false;
    }

    public int size() {
        return size;
    }

    public int longestChain() {
        int max = 0;
        for (LinkedList<Entry> bucket : buckets) {
            if (bucket.size() > max) {
                max = bucket.size();
            }
        }
        return max;
    }

    // 測試用主程式
    public static void main(String[] args) {
        Q04_ChainedHashTable ht = new Q04_ChainedHashTable(3);
        ht.put(1, "Apple");
        ht.put(4, "Banana"); // 測試 collision
        ht.put(1, "Apple-Updated"); // 測試 update
        
        System.out.println("Get Key 1: " + ht.get(1));
        System.out.println("Longest Chain: " + ht.longestChain());
        System.out.println("Size: " + ht.size());
    }
}