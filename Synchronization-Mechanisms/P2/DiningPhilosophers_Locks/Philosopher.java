import java.util.concurrent.locks.Lock;
import static java.lang.Thread.sleep;

public class Philosopher implements Runnable {

    //used to write what action the Philosopher does
    private void writeAction(String action)
    {
        System.out.println(Thread.currentThread().getName() + " " + action);
    }

    // The forks on either sides of the Philosopher
    private Lock leftFork;
    private Lock rightFork;

    Philosopher(Lock leftFork, Lock rightFork) {

        this.leftFork = leftFork;
        this.rightFork = rightFork;
    }

    @Override
    public void run() {
        try {
            writeAction(" Thinking");
            sleep(1000); //we assume that thinking takes some time
            while (true) {

                // thinking
                //writeAction(" Thinking");
                sleep(1000); //we assume that thinking takes some time

                boolean FL = leftFork.tryLock(); //if this fork from being picked by a philosopher, FL will be true
                if(!FL)
                {
                    continue;
                }

                if(Thread.currentThread().getName().contains("5"))
                        writeAction(" Picked up right fork");
                    else
                        writeAction(" Picked up left fork");

                boolean FR = rightFork.tryLock(); //if this fork from being picked by a philosopher, FR will be true

                if(!FR)
                {
                    if(Thread.currentThread().getName().contains("5"))
                        writeAction(" Put down right fork. Back to thinking");
                    else
                        writeAction(" Put down left fork. Back to thinking");
                    leftFork.unlock();
                    continue;
                }
                // eating
                if(Thread.currentThread().getName().contains("5"))
                    writeAction(" Picked up left fork - eating");
                else
                    writeAction(" Picked up right fork - eating");
                sleep(1000); //we assume that eating takes some time

                //the right fork is put down
                if(Thread.currentThread().getName().contains("5"))
                    writeAction(" Put down left fork. Put down right fork. Back to thinking.");
                else
                writeAction(" Put down right fork. Put down left fork. Back to thinking.");
            }
        }
        catch (InterruptedException e)
        {
            Thread.currentThread().interrupt();
        }
    }
}