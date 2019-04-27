package queue;

public class LinkedQueue extends AbstractQueue {
    private static class Node {
        final Object value;
        Node prev = null;
        Node next = null;

        Node(Object value) {
            this.value = value;
        }
    }

    private Node head = null;
    private Node tail = null;

    @Override
    protected void enqueueImpl(Object element) {
        if (head == null || tail == null) {
            head = tail = new Node(element);
        } else {
            tail.next = new Node(element);
            tail = tail.next;
        }
    }

    @Override
    protected void pushImpl(Object element) {
        if (head == null || tail == null) {
            head = tail = new Node(element);
        } else {
            head.prev = new Node(element);
            head = head.prev;
        }
    }

    @Override
    protected Object elementImpl() {
        return head.value;
    }

    @Override
    protected Object peekImpl() {
        return tail.value;
    }

    @Override
    protected void dequeueImpl() {
        head = head.next;
    }

    @Override
    protected void removeImpl() {
        tail = tail.prev;
    }

    @Override
    protected void clearImpl() {
        head = tail = null;
    }

    @Override
    public Object[] toArray() {
        Object[] result = new Object[size];

        Node elem = head;
        for (int i = 0; i < size; i++) {
            result[i] = elem.value;
            elem = elem.next;
        }

        return result;
    }

    @Override
    protected LinkedQueue createInstance() {
        return new LinkedQueue();
    }
}