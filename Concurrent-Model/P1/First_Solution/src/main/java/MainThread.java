public class MainThread {

    private static long Quotient;
    private static long Remainder;

    private static void setQuotient(long quotient) {
        Quotient = quotient;
    }

    private static void setRemainder(long remainder) {
        Remainder = remainder;
    }

    long getQuotient() {
        return Quotient;
    }

    long getRemainder() {
        return Remainder;
    }

    public static void main(String[] args) {

        DivisionTerms div = new DivisionTerms();
        RandomGenerator randomGenerator = new RandomGenerator();

        long Number = randomGenerator.getRandomNr();

        long ThreadsNr = Number+1;// initialising TheadsNr with Number+1 to enter the while
        while(ThreadsNr>Number/2||ThreadsNr==0)
            ThreadsNr = randomGenerator.getRandomNr();//assures that the threads nr is smaller than the number

        System.out.println("Number: " + Number + "\nNumber of threads: " + ThreadsNr);

        setQuotient(div.CalculateQuotient(Number, ThreadsNr));
        setRemainder(div.CalculateRemainder(Number, ThreadsNr));

        Thread[] thread = new Thread[(int) (ThreadsNr+1)];

        for (long i = 1; i < thread.length; i++) {
            thread[(int) i] = new MyThread(i);
            thread[(int) i].start();
        }

        for (long i = 1; i < thread.length; i++) {
            {
                try {
                    thread[(int) i].join();
                }
                catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }

        System.out.println("Number: "+Number+"\nNumber of threads: "+ThreadsNr);
    }
}