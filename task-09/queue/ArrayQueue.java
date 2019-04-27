package queue;

public class ArrayQueue extends AbstractQueue {
    private Object[] elements = new Object[10];
    private int head = 0;
    private int tail = 0;

    private int next(int x) {
        return (x + 1) % elements.length;
    }

    private int prev(int x) {
        return (x + elements.length - 1) % elements.length;
    }

    private void ensureCapacity(int requiredSize) {
        if (elements.length <= requiredSize) {
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

    @Override
    protected void enqueueImpl(Object element) {
        ensureCapacity(size);
        elements[tail] = element;
        tail = next(tail);
    }

    @Override
    protected void pushImpl(Object element) {
        ensureCapacity(size);
        head = prev(head);
        elements[head] = element;
    }

    @Override
    protected Object elementImpl() {
        return elements[head];
    }

    @Override
    protected Object peekImpl() {
        return elements[prev(tail)];
    }

    @Override
    protected void dequeueImpl() {
        elements[head] = null;
        head = next(head);
        ensureCapacity(size);
    }

    @Override
    protected void removeImpl() {
        tail = prev(tail);
        elements[tail] = null;
        ensureCapacity(size);
    }

    @Override
    protected void clearImpl() {
        elements = new Object[10];
        head = tail = 0;
    }

    @Override
    public Object[] toArray() {
        Object[] result = new Object[size];

        if (head <= tail) {
            System.arraycopy(this.elements, head, result, 0, tail - head);
        } else {
            System.arraycopy(this.elements, head, result, 0, elements.length - head);
            System.arraycopy(this.elements, 0, result, elements.length - head, tail);
        }

        return result;
    }

    @Override
    protected ArrayQueue createInstance() {
        return new ArrayQueue();
    }
}
