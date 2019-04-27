import queue.*;

public class Testing {
    public static void main(String[] args) {
        ArrayQueueModule.enqueue(5);
        ArrayQueueModule.enqueue(6);
        ArrayQueueModule.enqueue(7);
        ArrayQueueModule.enqueue(8);
        System.out.println(ArrayQueueModule.dequeue());
        System.out.println(ArrayQueueModule.dequeue());
        System.out.println(ArrayQueueModule.dequeue());
        System.out.println(ArrayQueueModule.dequeue());

        ArrayQueueADT eboi = new ArrayQueueADT();
        for (int i = 0; i < 10; i++) {
            ArrayQueueADT.enqueue(eboi, i);
        }
        for (int i = 0; i < 10; i++) {
            System.out.println(ArrayQueueADT.dequeue(eboi));
        }

        ArrayQueue boyi = new ArrayQueue();
        for (int i = 0; i < 10; i++) {
            boyi.enqueue(i);
        }

        for (int i = 0; i < 10; i++) {
            System.out.println(boyi.dequeue());
        }
    }
}