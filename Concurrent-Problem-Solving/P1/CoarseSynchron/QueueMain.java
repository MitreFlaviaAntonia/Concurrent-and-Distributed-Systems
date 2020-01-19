public class QueueMain {

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
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}
