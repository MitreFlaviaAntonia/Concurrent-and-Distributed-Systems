public class MyThread extends Thread {

    private DivisionTerms divisionTerms = new DivisionTerms();
    private MainThread mainThread = new MainThread();

    private int ThreadIndex;

    MyThread(int threadIndex) {
        ThreadIndex = threadIndex;
    }

    public void run() {

        long position, intervalRightMargin, CheckPrime;

        position = ThreadIndex;
        intervalRightMargin = divisionTerms.IntervalRightMargin(ThreadIndex);

        System.out.println( "Interval number: " + ThreadIndex + " interval margins: " + ThreadIndex + " " + intervalRightMargin);

        while(position>=ThreadIndex && position<=intervalRightMargin) {

            if(!(position%(mainThread.getThreadsNr()+1)==0 && position > (mainThread.getThreadsNr()+1)))
                CheckPrime = divisionTerms.CheckPrime(position);
            else
                if((position==(mainThread.getThreadsNr()+1)))
                    CheckPrime = divisionTerms.CheckPrime(position);
                else
                    CheckPrime = 0;

            if(CheckPrime!=0)
                System.out.println( "Thread number " + ThreadIndex + " = " + position);
            position = position + mainThread.getThreadsNr()+1;
        }
    }
}