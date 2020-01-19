public class Consumer extends Thread {

    private PCQueue queue;
    private int number;

    Consumer(int number, PCQueue queue) {
        this.number = number;
        this.queue = queue;
    }

    public void run() {
        for(int i = 0; i < this.number; ++i) {
            this.queue.get();
            System.out.println("The consumer consumed: " + i + "\n");
        }

    }
}
