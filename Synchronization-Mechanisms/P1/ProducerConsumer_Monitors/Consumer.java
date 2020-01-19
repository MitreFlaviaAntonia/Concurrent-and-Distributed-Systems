import java.util.List;

public class Consumer extends Thread {

    private final List<Integer> queue;

    public Consumer (List<Integer> queue) {
        this.queue = queue;
    }

    public void run() {

        for (int i = 0; i < 6; i++) {

            synchronized (queue)
            {
                if (queue.size() != 0) {
                    int nr = queue.size() -1;
                    Integer item = queue.remove(nr);
                    System.out.println("Consumer "+ Thread.currentThread().getId()+ " consumes: " + item);
                }
                else
                    {
                    try {
                        queue.wait();// the consumer waits while the queue is empty
                    }
                    catch (InterruptedException e)
                    {
                        e.printStackTrace();
                    }
                }
            }

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