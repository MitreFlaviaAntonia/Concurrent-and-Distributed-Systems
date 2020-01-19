public class MyThread extends Thread {

    private long ThreadIndex;

    MyThread(int threadIndex) {
        ThreadIndex = threadIndex;
    }

    private DivisionTerms divisionTerms = new DivisionTerms();

    public void run() {

        long position, LeftIntervalMargin, RightIntervalMargin;

        LeftIntervalMargin = divisionTerms.IntervalLeftMargin(ThreadIndex);
        position = LeftIntervalMargin;
        RightIntervalMargin = divisionTerms.IntervalRightMargin(ThreadIndex);

        System.out.println( "\nInterval number: " + ThreadIndex + " interval margins: " + LeftIntervalMargin + " " + RightIntervalMargin + "\n" );

        while(position>=LeftIntervalMargin && position<=RightIntervalMargin) {
            long CheckPrime = divisionTerms.CheckPrime(position);
            if(CheckPrime!=0)
                System.out.println( "Thread number " + ThreadIndex + ", prime number = " + position);
            position++;
        }

    }
}