import java.util.concurrent.Semaphore;

class SemaphoreBasedQueue {

        private int item;
        private static Semaphore ConsumerSemaphore = new Semaphore(0); //nr of permits is 0, we want the put operation to execute first
        private static Semaphore ProducerSemaphore = new Semaphore(1);

        void get()
        {
            try
            {
                ConsumerSemaphore.acquire();//needed for consuming an item
            }
            catch (InterruptedException e)
            {
                e.printStackTrace();
            }

            System.out.println("Consumer named " + Thread.currentThread().getName() + " consumed item : " + item);

            ProducerSemaphore.release();//the consumer has finished consuming an item, the producer should know
        }

        void put(int item)
        {
            try
            {
                ProducerSemaphore.acquire();//needed for producing an item
            }
            catch (InterruptedException e)
            {
                e.printStackTrace();
            }

            this.item = item;

            System.out.println("Producer named " + Thread.currentThread().getName()+" produced item : " + item);

            ConsumerSemaphore.release();//the producer has finished producing an item, the consumer should know
        }

    public static void main(String[] args)
    {
        SemaphoreBasedQueue semaphoreBasedQueue = new SemaphoreBasedQueue();

        Producer p1 = new Producer(semaphoreBasedQueue);
        Producer p2 = new Producer(semaphoreBasedQueue);
        Consumer c1 = new Consumer(semaphoreBasedQueue);
        Consumer c2 = new Consumer(semaphoreBasedQueue);

        p1.start();
        p2.start();
        c1.start();
        c2.start();

        try
        {
            p1.join();
            p2.join();
            c1.join();
            c2.join();
        }
        catch(InterruptedException e)
        {
            e.printStackTrace();
        }

    }
}