class Consumer extends Thread {

    private SemaphoreBasedQueue queue;

    Consumer(SemaphoreBasedQueue queue)
    {
        this.queue = queue;
    }

    public void run()
    {
        for (int i = 0; i < 6; i++)
        {
            queue.get();
        }
    }
} 