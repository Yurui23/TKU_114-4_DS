class ArrayStack<T> {
    private Object[] array;
    private int top;

    public ArrayStack(int capacity) {
        int cap = Math.max(1, capacity);
        this.array = new Object[cap];
        this.top = -1;
    }

    public boolean push(T item) {
        if (isFull()) {
            return false;
        }
        array[++top] = item;
        return true;
    }

    @SuppressWarnings("unchecked")
    public T pop() {
        if (isEmpty()) {
            return null;
        }
        T item = (T) array[top];
        array[top--] = null;
        return item;
    }

    @SuppressWarnings("unchecked")
    public T peek() {
        if (isEmpty()) {
            return null;
        }
        return (T) array[top];
    }

    public int size() {
        return top + 1;
    }

    public boolean isEmpty() {
        return top == -1;
    }

    public boolean isFull() {
        return top == array.length - 1;
    }
}

public class GenericArrayStackDemo {
    public static void main(String[] args) {
        System.out.println("--- 測試 ArrayStack<String> ---");
        ArrayStack<String> stringStack = new ArrayStack<>(3);
        System.out.println("Is empty: " + stringStack.isEmpty());
        stringStack.push("Java");
        stringStack.push("Spring");
        stringStack.push("Docker");
        System.out.println("Is full: " + stringStack.isFull());
        System.out.println("Push overflow success: " + stringStack.push("Kubernetes"));
        System.out.println("Peek: " + stringStack.peek());
        System.out.println("Pop: " + stringStack.pop());
        System.out.println("Size: " + stringStack.size());

        System.out.println("\n--- 測試 ArrayStack<Integer> ---");
        ArrayStack<Integer> intStack = new ArrayStack<>(2);
        intStack.push(10);
        intStack.push(20);
        System.out.println("Peek: " + intStack.peek());
        System.out.println("Pop: " + intStack.pop());
        System.out.println("Pop: " + intStack.pop());
        System.out.println("Pop empty: " + intStack.pop());
    }
}