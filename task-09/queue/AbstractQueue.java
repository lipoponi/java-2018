package queue;

import java.util.function.Function;
import java.util.function.Predicate;

public abstract class AbstractQueue implements Queue {
    protected int size = 0;

    protected abstract void enqueueImpl(Object element);

    protected abstract void pushImpl(Object element);

    protected abstract Object elementImpl();

    protected abstract Object peekImpl();

    protected abstract void dequeueImpl();

    protected abstract void removeImpl();

    protected abstract void clearImpl();

    protected abstract Queue createInstance();

    @Override
    public void enqueue(Object element) {
        assert element != null : "invalid element";
        size++;
        enqueueImpl(element);
    }

    @Override
    public void push(Object element) {
        assert element != null : "invalid element";
        size++;
        pushImpl(element);
    }

    @Override
    public Object element() {
        assert !isEmpty() : "dequeue is empty";
        return elementImpl();
    }

    @Override
    public Object peek() {
        assert !isEmpty() : "dequeue is empty";
        return peekImpl();
    }

    @Override
    public Object dequeue() {
        Object result = element();
        size--;

        dequeueImpl();

        return result;
    }

    @Override
    public Object remove() {
        Object result = peek();
        size--;

        removeImpl();

        return result;
    }

    @Override
    public void clear() {
        size = 0;
        clearImpl();
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public Queue filter(Predicate<Object> checker) {
        Object[] elements = this.toArray();
        Queue result = createInstance();

        for (Object elem : elements) {
            if (checker.test(elem)) {
                result.enqueue(elem);
            }
        }

        return result;
    }

    @Override
    public Queue map(Function<Object, Object> function) {
        Object[] elements = this.toArray();
        Queue result = createInstance();

        for (Object elem : elements) {
            result.enqueue(function.apply(elem));
        }

        return result;
    }
}