package queue;

// n - count of elements in sequence
// a[i] - sequence

public class ArrayQueueADT {
    // Inv: (n >= 0) && (a[i] != null for i = 0..n - 1)
    private Object[] elements = new Object[10];
    private int size = 0;
    private int head = 0;
    private int tail = 0;

    // Pre: (elements.length != 0) && (0 <= x < elements.length) && self != null
    private static int next(ArrayQueueADT self, int x) {
        return (x + 1) % self.elements.length;
    }
    // Result: x' = (x + 1) % elements.length

    // Pre: (elements.length != 0) && (0 <= x < elements.length) && self != null
    private static int prev(ArrayQueueADT self, int x) {
        return (x + self.elements.length - 1) % self.elements.length;
    }
    // Post: (Result == x - 1 && x > 0) || (Result == elements.length && x == 0)

    //Pre: requiredSize >= 0 && self != null
    private static void ensureCapacity(ArrayQueueADT self, int requiredSize) {
        if (self.elements.length <= requiredSize || requiredSize * 4 < self.elements.length) {
            Object[] newArray = new Object[requiredSize * 2 + 1];
            int index = 0;

            for (int i = self.head; i != self.tail; i = next(self, i)) {
                newArray[index++] = self.elements[i];
            }

            self.elements = newArray;
            self.head = 0;
            self.tail = index;
        }
    }
    // Post: (requiredSize < elements'.length <= requiredSize * 4) && (n' == n) && (a'[i] == a[i] for i = 0...n - 1)

    // Pre: (element != null) && self != null
    public static void enqueue(ArrayQueueADT self, Object element) {
        assert self != null;
        assert element != null : "invalid element";

        ensureCapacity(self, ++self.size);
        self.elements[self.tail] = element;
        self.tail = next(self, self.tail);
    }
    // (n' == n + 1) && (a'[i] == a[i] for i = 0..n - 1) && (a'[n] == element)

    // Pre: (element != null) && self != null
    public static void push(ArrayQueueADT self, Object element) {
        assert self != null;
        assert element != null : "invalid element";

        ensureCapacity(self, ++self.size);
        self.head = prev(self, self.head);
        self.elements[self.head] = element;
    }
    // (n' == n + 1) && (a'[i + 1] == a[i] for i = 0..n - 1) && (a'[0] == element)

    // Pre: n > 0 && self != null
    public static Object element(ArrayQueueADT self) {
        assert self != null;
        assert !isEmpty(self) : "dequeue is empty";

        return self.elements[self.head];
    }
    // Post: (n' == n) && (a'[i] == a[i] for i = 0...n - 1) && (result == a[0])

    // Pre: n > 0 && self != null
    public static Object peek(ArrayQueueADT self) {
        assert self != null;
        assert !isEmpty(self) : "dequeue is empty";

        return self.elements[prev(self, self.tail)];
    }
    // Post: (n' == n) && (a'[i] == a[i] for i = 0...n - 1) && (result == a[n - 1])

    // Pre: n > 0 && self != null
    public static Object dequeue(ArrayQueueADT self) {
        assert self != null;
        assert !isEmpty(self) : "dequeue is empty";

        Object result = self.elements[self.head];
        self.head = next(self, self.head);
        ensureCapacity(self, --self.size);
        return result;
    }
    // Post: (n' == n - 1) && (a'[i - 1] == a[i] for i = 1...n - 1) && (result == a[0])

    // Pre: n > 0 && self != null
    public static Object remove(ArrayQueueADT self) {
        assert self != null;
        assert !isEmpty(self) : "dequeue is empty";

        self.tail = prev(self, self.tail);
        Object result = self.elements[self.tail];
        ensureCapacity(self, --self.size);
        return result;
    }
    // Post: (n' == n - 1) && (a'[i] == a[i] for i = 0...n - 2) && (result == a[n - 1])

    // Pre: true && self != null
    public static int size(ArrayQueueADT self) {
        assert self != null;
        return self.size;
    }
    // Post: (n' == n) && (a'[i] == a[i] for i = 0...n - 1) && (result == n)

    // Pre: true && self != null
    public static boolean isEmpty(ArrayQueueADT self) {
        assert self != null;
        return self.size == 0;
    }
    // Post: (n' == n) && (a'[i] == a[i] for i = 0...n - 1) && (result == (n == 0))

    // Pre: true && self != null
    public static void clear(ArrayQueueADT self) {
        assert self != null;
        self.elements = new Object[10];
        self.size = self.head = self.tail = 0;
    }
    // Post: n == 0
}
