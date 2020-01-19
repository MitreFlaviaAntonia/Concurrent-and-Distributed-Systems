public class QueueMain {
    public QueueMain() {
    }

    public static void main(String[] args) {
        PCQueue queue = new PCQueue();
        int cores = Runtime.getRuntime().availableProcessors();
        Producer producer = new Producer(cores, queue);
        Consumer consumer = new Consumer(cores, queue);
        producer.start();
        consumer.start();

        try {
            producer.join();
            consumer.join();
        } catch (InterruptedException var6) {
            var6.printStackTrace();
        }

    }
}
