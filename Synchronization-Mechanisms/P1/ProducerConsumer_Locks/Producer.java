public class Producer extends Thread {

    private LockBasedQueue queue;

    public Producer(LockBasedQueue<Integer> pcq) {
        queue = pcq;
    }

    public void run() {

            for (int i = 0; i < 6; i++)
            {
                queue.enq(i);
            }
    }

}