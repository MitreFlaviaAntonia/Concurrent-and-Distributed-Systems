import static java.lang.Thread.sleep;

public class Philosopher implements Runnable {

    // The forks on either sides of the Philosopher
    private final Object firstFork;
    private final Object secondFork;
    private final Channel<Boolean> leftForkChannel;
    private final Channel<Boolean> rightForkChannel;

    Philosopher(Object firstFork, Object secondFork, Channel<Boolean> leftForkChannel, Channel<Boolean> rightForkChannel) {
        this.firstFork = firstFork;
        this.secondFork = secondFork;
        this.leftForkChannel = leftForkChannel;
        this.rightForkChannel = rightForkChannel;
    }

    @Override
    public void run() {
        try {
            System.out.println(Thread.currentThread().getName() +" Thinking");
            sleep(10); //we assume that thinking takes some time
            while (true) {

                synchronized (firstFork) {

                    //pick up first fork
                    if((Thread.currentThread().getName().contains("5")))
                        rightForkChannel.Send(true, "right", Thread.currentThread().getName());
                    else
                        leftForkChannel.Send(true,"left", Thread.currentThread().getName());

                    synchronized (secondFork) {

                        //pick up second fork
                        if ((Thread.currentThread().getName().contains("5")))
                            leftForkChannel.Send(true, "left", Thread.currentThread().getName());
                        else
                            rightForkChannel.Send(true, "right", Thread.currentThread().getName());

                        //put down second fork
                        if ((Thread.currentThread().getName().contains("5")))
                            leftForkChannel.Receive("left", Thread.currentThread().getName());
                        else
                            rightForkChannel.Receive("right", Thread.currentThread().getName());
                    }

                    //put down first fork
                    if((Thread.currentThread().getName().contains("5")))
                        rightForkChannel.Receive("right", Thread.currentThread().getName());
                    else
                        leftForkChannel.Receive("left", Thread.currentThread().getName());

                }
            }

        }
        catch (InterruptedException e)
        {
            e.printStackTrace();
        }
    }
}
