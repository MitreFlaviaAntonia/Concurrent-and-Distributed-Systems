import java.util.concurrent.Semaphore;
import static java.lang.Thread.sleep;

public class Philosopher implements Runnable {

    //will be used to block the Philosophers number that can hold the left fork at the same time
    private Semaphore room;

    //used to write what action the Philosopher does
    private void writeAction(String action) {
        System.out.println(Thread.currentThread().getName() + " " + action);
    }

    // The forks on either sides of the Philosopher
    private Semaphore leftFork;
    private Semaphore rightFork;

    Philosopher(Semaphore leftFork, Semaphore rightFork) {
        room = new Semaphore(4); //sets the Philosophers number that can hold the left fork at the same time to 4
        this.leftFork = leftFork;
        this.rightFork = rightFork;
    }

    @Override
    public void run() {
        try {
            while (true) {

                // thinking
                writeAction(" Thinking");
                sleep(1000); //we assume that thinking takes some time

                try {
                    room.acquire(); //if a philosopher wants to pick up a fork, he must have room to do it
                    leftFork.acquire(); //prevents this fork from being picked by other philosopher
                    writeAction(" Picked up left fork");

                    try {
                        rightFork.acquire(); //prevents this fork from being picked by other philosopher
                        // eating
                        writeAction(" Picked up right fork - eating");
                        sleep(1000); //we assume that eating takes some time
                    }
                    catch (InterruptedException e)
                    {
                        e.printStackTrace();
                    }
                    finally
                    {
                        rightFork.release(); //the right fork is put down
                        writeAction(" Put down right fork");
                    }

                }
                catch(InterruptedException e)
                {
                    e.printStackTrace();
                }
                finally {
                    // Back to thinking
                    writeAction(" Put down left fork. Back to thinking");
                    leftFork.release(); //the left fork is put down, so
                    room.release(); // there is room for another philosopher
                }
            }
        }
        catch (InterruptedException e)
        {
            Thread.currentThread().interrupt();
        }
    }
}