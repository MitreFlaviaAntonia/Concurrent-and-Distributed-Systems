public class Producer extends Thread {
    private PCQueue queue;
    private int number;

    Producer(int number, PCQueue queue) {
        this.queue = queue;
        this.number = number;
    }

    public void run() {
        for(int i = 0; i <= this.number; ++i) {
            this.queue.put();
            System.out.println("The producer produced: \n" + i);
        }

    }
}
