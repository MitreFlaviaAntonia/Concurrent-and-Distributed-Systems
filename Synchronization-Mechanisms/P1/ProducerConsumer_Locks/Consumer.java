public class Consumer extends Thread {

    private LockBasedQueue queue;

    public Consumer(LockBasedQueue<Integer> pcq) {
        queue = pcq;
    }

    public void run() {

            for (int i = 0; i < 6; i++)
            {
                this.queue.deq();
            }
    }
}