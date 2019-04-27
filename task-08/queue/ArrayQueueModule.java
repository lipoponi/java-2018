package queue;

// n - count of elements in sequence
// a[i] - sequence

public class ArrayQueueModule {
    // Inv: (n >= 0) && (a[i] != null for i = 0..n - 1)
    private static Object[] elements = new Object[10];
    private static int size = 0;
    private static int head = 0;
    private static int tail = 0;

    // Pre: (elements.length != 0) && (0 <= x < elements.length)
    private static int next(int x) {
        return (x + 1) % elements.length;
    }
    // Result: x' = (x + 1) % elements.length

    // Pre: (elements.length != 0) && (0 <= x < elements.length)
    private static int prev(int x) {
        return (x + elements.length - 1) % elements.length;
    }
    // Post: (Result == x - 1 && x > 0) || (Result == elements.length && x == 0)

    //Pre: requiredSize >= 0
    private static void ensureCapacity(int requiredSize) {
        if (elements.length <= requiredSize || requiredSize * 4 < elements.length) {
            Object[] newArray = new Object[requiredSize * 2 + 1];
            int index = 0;

            for (int i = head; i != tail; i = next(i)) {
                newArray[index++] = elements[i];
            }

            elements = newArray;
            head = 0;
            tail = index;
        }
    }
    // Post: (requiredSize < elements'.length <= requiredSize * 4) && (n' == n) && (a'[i] == a[i] for i = 0...n - 1)

    // Pre: (element != null)
    public static void enqueue(Object element) {
        assert element != null : "invalid element";

        ensureCapacity(++size);
        elements[tail] = element;
        tail = next(tail);
    }
    // (n' == n + 1) && (a'[i] == a[i] for i = 0..n - 1) && (a'[n] == element)

    // Pre: (element != null)
    public static void push(Object element) {
        assert element != null : "invalid element";

        ensureCapacity(++size);
        head = prev(head);
        elements[head] = element;
    }
    // (n' == n + 1) && (a'[i + 1] == a[i] for i = 0..n - 1) && (a'[0] == element)

    // Pre: n > 0
    public static Object element() {
        assert !isEmpty() : "dequeue is empty";

        return elements[head];
    }
    // Post: (n' == n) && (a'[i] == a[i] for i = 0...n - 1) && (result == a[0])

    // Pre: n > 0
    public static Object peek() {
        assert !isEmpty() : "dequeue is empty";

        return elements[prev(tail)];
    }
    // Post: (n' == n) && (a'[i] == a[i] for i = 0...n - 1) && (result == a[n - 1])

    // Pre: n > 0
    public static Object dequeue() {
        assert !isEmpty() : "dequeue is empty";

        Object result = elements[head];
        head = next(head);
        ensureCapacity(--size);
        return result;
    }
    // Post: (n' == n - 1) && (a'[i - 1] == a[i] for i = 1...n - 1) && (result == a[0])

    // Pre: n > 0
    public static Object remove() {
        assert !isEmpty() : "dequeue is empty";

        tail = prev(tail);
        Object result = elements[tail];
        ensureCapacity(--size);
        return result;
    }
    // Post: (n' == n - 1) && (a'[i] == a[i] for i = 0...n - 2) && (result == a[n - 1])

    // Pre: true
    public static int size() {
        return size;
    }
    // Post: (n' == n) && (a'[i] == a[i] for i = 0...n - 1) && (result == n)

    // Pre: true
    public static boolean isEmpty() {
        return size == 0;
    }
    // Post: (n' == n) && (a'[i] == a[i] for i = 0...n - 1) && (result == (n == 0))

    // Pre: true
    public static void clear() {
        elements = new Object[10];
        size = head = tail = 0;
    }
    // Post: n == 0
}
