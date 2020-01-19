public class MainThread {

    private static long Number;
    private static long ThreadsNr;

    private static void setNumber(long number) {
        Number = number;
    }
    private static void setThreadsNr(long threadsNr) {
        ThreadsNr = threadsNr;
    }

    long getNumber() {
        return Number;
    }
    long getThreadsNr() {
        return ThreadsNr;
    }

    public static void main(String[] args) {

        RandomGenerator randomGenerator = new RandomGenerator();

        Number = randomGenerator.generateRandomNr();

        ThreadsNr = Number+1;
        while(ThreadsNr>Number/2||ThreadsNr==0)
            ThreadsNr = randomGenerator.generateRandomNr();

        System.out.println(Number+" "+ThreadsNr);

        setNumber(Number);
        setThreadsNr(ThreadsNr);

        System.out.println("Number: "+Number+"\nNumber of threads: "+ThreadsNr);

        Thread[] thread = new Thread[(int)(ThreadsNr+1)];
        for (int i = 1; i < thread.length; i++) {
            thread[i] = new MyThread(i);
            thread[i].start();
        }

        for (int i = 1; i < thread.length; i++) {
            {
                try {
                    thread[i].join();
                }
                catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
        System.out.println("Number: "+Number+"\nNumber of threads: "+ThreadsNr);
    }
}