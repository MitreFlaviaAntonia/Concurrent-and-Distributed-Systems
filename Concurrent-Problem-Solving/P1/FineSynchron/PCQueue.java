import java.util.concurrent.Semaphore;

class PCQueue {
    private static Semaphore prod = new Semaphore(1);
    private static Semaphore cons = new Semaphore(0);
    private final Object lock = new Object();
    private final Object lock1 = new Object();

    void put() {
        synchronized(this.lock) {
            try {
                prod.acquire();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            cons.release();
        }
    }

    void get() {
        synchronized(this.lock1) {
            try {
                cons.acquire();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            prod.release();
        }
    }
}
