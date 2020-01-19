import java.util.List;

public class Producer extends Thread{

    private final List<Integer> queue;
    private int item = 0;

    public Producer(List<Integer> queue) {
        this.queue = queue;
    }

    public void run() {

        for (int i = 0; i < 6; i++) {

            synchronized (queue)
            {
                System.out.println("Producer " + Thread.currentThread().getId() + " produces: " + item);
                queue.add(item);
                queue.notifyAll();// when an element is added, the consumer is notified to start consuming again
            }

            item++;

            try {
                sleep(1000);
            }
            catch (InterruptedException e)
            {
                e.printStackTrace();
            }
        }
    }
}
