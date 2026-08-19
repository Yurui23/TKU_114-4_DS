import java.util.Arrays;

class DynamicArray<T> {
    private Object[] data;
    private int size;

    public DynamicArray() {
        this.data = new Object[2];
        this.size = 0;
    }

    public void add(T value) {
        if (size == data.length) {
            data = Arrays.copyOf(data, data.length * 2);
        }
        data[size++] = value;
    }

    public void add(int index, T value) {
        if (index < 0 || index > size) {
            return;
        }
        if (size == data.length) {
            data = Arrays.copyOf(data, data.length * 2);
        }
        System.arraycopy(data, index, data, index + 1, size - index);
        data[index] = value;
        size++;
    }

    @SuppressWarnings("unchecked")
    public T get(int index) {
        if (index < 0 || index >= size) {
            return null;
        }
        return (T) data[index];
    }

    @SuppressWarnings("unchecked")
    public T set(int index, T value) {
        if (index < 0 || index >= size) {
            return null;
        }
        T oldValue = (T) data[index];
        data[index] = value;
        return oldValue;
    }

    @SuppressWarnings("unchecked")
    public T remove(int index) {
        if (index < 0 || index >= size) {
            return null;
        }
        T removedValue = (T) data[index];
        int numMoved = size - index - 1;
        if (numMoved > 0) {
            System.arraycopy(data, index + 1, data, index, numMoved);
        }
        data[--size] = null;
        return removedValue;
    }

    public int size() {
        return size;
    }

    public int capacity() {
        return data.length;
    }
}

public class DynamicArrayPractice {
    public static void main(String[] args) {
        DynamicArray<String> strArr = new DynamicArray<>();
        strArr.add("A");
        strArr.add("B");
        strArr.add("C");
        System.out.println("String 測試 - 容量: " + strArr.capacity() + "，大小: " + strArr.size());
        
        strArr.remove(1);
        System.out.println("String 測試移除後 - 大小: " + strArr.size() + "，元素0: " + strArr.get(0) + "，元素1: " + strArr.get(1));

        DynamicArray<Integer> intArr = new DynamicArray<>();
        intArr.add(10);
        intArr.add(20);
        intArr.add(1, 15);
        intArr.set(2, 25);
        System.out.println("Integer 測試 - 大小: " + intArr.size() + "，容量: " + intArr.capacity());
        System.out.println("Integer 測試元素1: " + intArr.get(1));

        DynamicArray<String> edgeArr = new DynamicArray<>();
        edgeArr.add(-1, "Error");
        edgeArr.add(100, "Error");
        System.out.println("空結構 get(-1): " + edgeArr.get(-1));
        System.out.println("空結構 get(size): " + edgeArr.get(edgeArr.size()));
        System.out.println("空結構 set(-1, Val): " + edgeArr.set(-1, "Val"));
        System.out.println("空結構 remove(-1): " + edgeArr.remove(-1));
        System.out.println("空結構 remove(size): " + edgeArr.remove(edgeArr.size()));
        System.out.println("空結構 remove(0): " + edgeArr.remove(0));
    }
}