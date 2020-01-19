import java.util.ArrayList;
import java.util.List;

public class MonitorBasedQueue {

    public static void main(String[] args) {

        List<Integer> mbq = new ArrayList<Integer>();

        Thread p1 = new Thread(new Producer(mbq));
        Thread p2 = new Thread(new Producer(mbq));
        Thread c1 = new Thread(new Consumer(mbq));
        Thread c2 = new Thread(new Consumer(mbq));

        //start the Threads
        p1.start();
        p2.start();
        c1.start();
        c2.start();

        //join the Threads
        try {
            p1.join();
            p2.join();
            c1.join();
            c2.join();
        }
        catch (InterruptedException e)
        {
            e.printStackTrace();
        }

    }
}