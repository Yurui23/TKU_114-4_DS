import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

class Entry {
    String key;
    String value;
    Entry(String key, String value) {
        this.key = key;
        this.value = value;
    }
}

public class ResizableStringMap {
    private List<LinkedList<Entry>> buckets;
    private int size;

    public ResizableStringMap(int initialCapacity) {
        buckets = new ArrayList<>(initialCapacity);
        for (int i = 0; i < initialCapacity; i++) {
            buckets.add(new LinkedList<>());
        }
    }

    private int getIndex(String key, int capacity) {
        if (key == null) return 0;
        return Math.floorMod(key.hashCode(), capacity);
    }

    public void put(String key, String value) {
        if (key == null) return;
        int index = getIndex(key, buckets.size());
        for (Entry entry : buckets.get(index)) {
            if (entry.key.equals(key)) {
                entry.value = value; // 更新 key，size 不增加
                return;
            }
        }
        
        buckets.get(index).add(new Entry(key, value));
        size++;

        if (getLoadFactor() > 0.75) {
            rehash();
        }
    }

    public String get(String key) {
        if (key == null) return null;
        int index = getIndex(key, buckets.size());
        for (Entry entry : buckets.get(index)) {
            if (entry.key.equals(key)) return entry.value;
        }
        return null;
    }

    private void rehash() {
        int newCapacity = buckets.size() * 2 + 1;
        List<LinkedList<Entry>> newBuckets = new ArrayList<>(newCapacity);
        for (int i = 0; i < newCapacity; i++) {
            newBuckets.add(new LinkedList<>());
        }

        for (LinkedList<Entry> bucket : buckets) {
            for (Entry entry : bucket) {
                // 依新 bucket count 重新計算 index
                int newIndex = getIndex(entry.key, newCapacity);
                newBuckets.get(newIndex).add(entry);
            }
        }
        buckets = newBuckets;
    }

    public double getLoadFactor() {
        return (double) size / buckets.size();
    }

    public int getSize() { return size; }
    public int getBucketCount() { return buckets.size(); }

    public static void main(String[] args) {
        ResizableStringMap map = new ResizableStringMap(2);
        map.put("A", "1");
        map.put("B", "2");
        System.out.println("Buckets before rehash: " + map.getBucketCount());
        map.put("C", "3"); // Trigger rehash
        System.out.println("Buckets after rehash: " + map.getBucketCount());
        map.put("A", "100"); // Update test
        System.out.println("Size (should be 3): " + map.getSize());
    }
}