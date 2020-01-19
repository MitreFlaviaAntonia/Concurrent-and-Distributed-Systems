import java.util.concurrent.Semaphore;

class ReadersWriters {

    private static Semaphore reader = new Semaphore(1, true);
    private static Semaphore writer = new Semaphore(1, true);
    private  static int CountReaders = 0;

    static class Reader implements Runnable {
        @Override
        public void run() {

            try {
                reader.acquire();

                CountReaders++;
                if (CountReaders == 1)
                    writer.acquire();

                reader.release();

                System.out.println("Reader " + Thread.currentThread().getName() + " reads.");
                Thread.sleep(200);
                System.out.println("Reader " + Thread.currentThread().getName() + " stops reading.");

                reader.acquire();

                CountReaders--;
                if (CountReaders == 0)
                    writer.release();

                reader.release();
            }
            catch (InterruptedException e)
            {
                e.printStackTrace();
            }

        }
    }

    static class Writer implements Runnable {
        @Override
        public void run() {

            try {
                writer.acquire();

                System.out.println("Writer "+Thread.currentThread().getName() + " writes.");
                Thread.sleep(1500);
                System.out.println("Writer "+Thread.currentThread().getName() + " stops writing.");

                writer.release();
            }
            catch (InterruptedException e)
            {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        Reader read = new Reader();
        Writer write = new Writer();

        Thread t1 = new Thread(read);
        t1.setName("1");

        Thread t2 = new Thread(read);
        t2.setName("2");

        Thread t3 = new Thread(write);
        t3.setName("3");

        Thread t4 = new Thread(read);
        t4.setName("4");

        Thread t5 = new Thread(write);
        t5.setName("5");

        Thread t6 = new Thread(write);
        t6.setName("6");

        t3.start();
        t5.start();
        t1.start();
        t6.start();
        t2.start();
        t4.start();
    }
}