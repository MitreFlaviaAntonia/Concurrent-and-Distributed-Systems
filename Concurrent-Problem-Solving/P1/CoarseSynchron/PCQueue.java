import java.util.concurrent.Semaphore;

class PCQueue {
    private boolean flag = true;
    private static Semaphore prod = new Semaphore(1);
    private static Semaphore cons = new Semaphore(0);

    synchronized void put() {
        try {
            prod.acquire();
            this.flag = false;
            this.notify();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        cons.release();

        while(!this.flag) {
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }

    synchronized void get() {
        try {
            cons.acquire();
            this.notify();
            this.flag = true;
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        prod.release();

        while(this.flag) {
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }
}
