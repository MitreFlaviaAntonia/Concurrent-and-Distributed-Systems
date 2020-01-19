class Producer extends Thread {

    private SemaphoreBasedQueue queue;

    Producer(SemaphoreBasedQueue queue)
    {
        this.queue = queue;
    }

    public void run()
    {
        for (int i = 0; i < 6; i++)
        {
            queue.put(i);
        }
    }
} 