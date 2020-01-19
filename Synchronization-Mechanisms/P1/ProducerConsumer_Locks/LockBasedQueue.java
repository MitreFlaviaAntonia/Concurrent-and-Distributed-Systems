import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class LockBasedQueue<T> {

    volatile int head, tail;
    T[] items;
    Lock lock;

    public LockBasedQueue(int capacity) {
        head = 0; tail = 0;
        lock = new ReentrantLock();
        items = (T[]) new Object[capacity];
    }

    public void enq(T item) {
        lock.lock();
        try {
            items[tail % items.length] = item;
            tail++;
        }
        finally {
            System.out.println("Producer named " + Thread.currentThread().getName()+" produced item : " + item);
            lock.unlock();
        }
    }

    public void deq() {
        lock.lock();
        try {
            T item = items[head % items.length];
            head++;
        }
        finally {
            System.out.println("Consumer named " + Thread.currentThread().getName() + " consumed item : " + items[(head-1) % items.length]);
            lock.unlock();
        }
    }

    public static void main(String[] args) {

        LockBasedQueue<Integer> lbq = new LockBasedQueue<Integer>(6);

        Producer p1 = new Producer(lbq);
        Producer p2 = new Producer(lbq);
        Consumer c1 = new Consumer(lbq);
        Consumer c2 = new Consumer(lbq);

        p1.start();
        p2.start();
        c1.start();
        c2.start();

        try {
            p1.join();
            p2.join();
            c1.join();
            c2.join();
        }
        catch(InterruptedException e) {
            e.printStackTrace();
        }

    }
}