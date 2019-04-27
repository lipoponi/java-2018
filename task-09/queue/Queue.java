package queue;

import java.util.function.Function;
import java.util.function.Predicate;

public interface Queue {
    void enqueue(Object element);

    void push(Object element);

    Object element();

    Object peek();

    Object dequeue();

    Object remove();

    int size();

    boolean isEmpty();

    void clear();

    Object[] toArray();

    Queue filter(Predicate<Object> checker);

    Queue map(Function<Object, Object> function);
}