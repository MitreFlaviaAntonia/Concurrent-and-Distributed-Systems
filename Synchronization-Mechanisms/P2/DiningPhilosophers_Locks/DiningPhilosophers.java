import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class DiningPhilosophers {


    public static void main(String[] args) {
        Philosopher[] philosophers = new Philosopher[5]; //5 philosophers
        Lock[] forks = new Lock[philosophers.length]; //5 forks (a fork between two philosophers)

        for (int i = 0; i < forks.length; i++) {
            forks[i] = new ReentrantLock(); //every fork is a reentrant lock
        }

        for (int i = 0; i < philosophers.length; i++) {
            //we count the forks from right to left, as this is the logical order of staying at a table
            Lock rightFork = forks[i]; // the right fork is the first fork numbered
            Lock leftFork = forks[(i + 1) % forks.length]; // the left fork is the next fork numbered

            if(i < 4)
                philosophers[i] = new Philosopher(leftFork, rightFork);
            else
                //avoiding a livelock
                philosophers[i] = new Philosopher(rightFork, leftFork);//the last philosopher picks up first the right fork, then the left fork

            Thread t = new Thread(philosophers[i], "Philosopher " + (i + 1)); //a new thread is created, for every philosopher
            t.start(); //the new created thread is started, for every philosopher
        }
    }
}