import java.util.concurrent.Semaphore;

public class DiningPhilosophers {


    public static void main(String[] args) {
        Philosopher[] philosophers = new Philosopher[5]; //5 philosophers
        Semaphore[] forks = new Semaphore[philosophers.length]; //5 forks (a fork between two philosophers)

        for (int i = 0; i < forks.length; i++) {
            forks[i] = new Semaphore(1); //every fork is a semaphore with one permit (only one philosopher can grab it at a time)
        }

        for (int i = 0; i < philosophers.length; i++) {
            //we count the forks from right to left, as this is the logical order of staying at a table
            Semaphore rightFork = forks[i]; // the right fork is the first fork numbered
            Semaphore leftFork = forks[(i + 1) % forks.length]; // the left fork is the next fork numbered

            philosophers[i] = new Philosopher(leftFork, rightFork);//a new Philosopher instance is created

            Thread t = new Thread(philosophers[i], "Philosopher " + (i + 1)); //a new thread is created, for every philosopher
            t.start(); //the new created thread is started, for every philosopher
        }
    }
}