import static java.lang.Thread.sleep;

public class Philosopher implements Runnable {

    //used to write what action the Philosopher does
    private void writeAction(String action) {
        System.out.println(Thread.currentThread().getName() + " " + action);
    }

    // The forks on either sides of this Philosopher
    private Object leftFork;
    private Object rightFork;

    public Philosopher(Object leftFork, Object rightFork) {

        this.leftFork = leftFork;
        this.rightFork = rightFork;
    }

    @Override
    public void run() {
        try {
            while (true) {

                // thinking
                writeAction("Thinking");
                sleep(1000); //we assume that thinking takes some time

                //the left fork is used
                synchronized (leftFork) {

                    if(Thread.currentThread().getName().contains("5"))
                        writeAction("Picked up right fork");
                    else
                        writeAction("Picked up left fork");

                    //the right fork is used
                    synchronized (rightFork) {

                        // eating
                        if(Thread.currentThread().getName().contains("5"))
                            writeAction("Picked up left fork - eating");
                        else
                            writeAction("Picked up right fork - eating");

                        sleep(1000); //we assume that eating takes some time

                        if(Thread.currentThread().getName().contains("5"))
                            writeAction("Put down left fork");
                        else
                            writeAction("Put down right fork");

                    }

                    // Back to thinking
                    if(Thread.currentThread().getName().contains("5"))
                        writeAction("Put down right fork. Back to thinking.");
                    else
                        writeAction("Put down left fork. Back to thinking.");
                }
            }
        }
        catch (InterruptedException e)
        {
            e.printStackTrace();
        }

    }
}