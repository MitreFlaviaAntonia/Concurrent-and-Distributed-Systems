public class DiningPhilosophers {

    public static Philosopher[] philosophers;
    public static Object[] forks;
    public static Channel[] forkChannels;

    public static void main(String[] args) {

        philosophers = new Philosopher[5]; //5 philosophers
        forks = new Object[philosophers.length];
        forkChannels = new Channel[philosophers.length]; //5 forks (a fork between two philosophers)

        //creating the channels
        for (int i = 0; i < forkChannels.length; i++) {
            forkChannels[i] = new Channel<Boolean>();
        }

        //creating the forks
        for (int i = 0; i < forks.length; i++) {
            forks[i] = new Object();
        }

        for (int i = 0; i < philosophers.length; i++) {
            //we count the forks from right to left, as this is the logical order of staying at a table
            Object rightFork = forks[i]; // the right fork is the first fork numbered
            Object leftFork = forks[(i + 1) % forks.length]; // the left fork is the next fork numbered

            Channel<Boolean> rightForkChannel = forkChannels[i]; // the right fork is the first fork numbered
            Channel<Boolean> leftForkChannel = forkChannels[(i + 1) % forkChannels.length]; // the left fork is the next fork numbered

            if(i < 4)
                philosophers[i] = new Philosopher(leftFork, rightFork, leftForkChannel, rightForkChannel);
            else
                //avoiding a deadlock
                philosophers[i] = new Philosopher(rightFork, leftFork, rightForkChannel, leftForkChannel);//the last philosopher picks up first the right fork, then the left fork

            Thread t = new Thread(philosophers[i], "Philosopher " + (i + 1)); //a new thread is created, for every philosopher
            t.start(); //the new created thread is started, for every philosopher
        }
    }
}