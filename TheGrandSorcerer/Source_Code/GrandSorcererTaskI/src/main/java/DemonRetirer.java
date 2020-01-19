import java.util.concurrent.Semaphore;

import static java.lang.Thread.sleep;

public class DemonRetirer implements Runnable {

    private RandomNumberGenerator randomNumberGenerator = new RandomNumberGenerator();
    private GrandSorcerersCircle grandSorcerersCircle = new GrandSorcerersCircle();
    private Coven[] covens = grandSorcerersCircle.getCovens();
    private Semaphore demonRetirer = new Semaphore(1);

    @Override
    public void run() {
        Demon demon;
        int Index;
        int CovenIndex;
        while(true) {
            try {
                demonRetirer.tryAcquire(); //the demon retiring must be synchronised
                sleep(10000); //sleep for some time
                CovenIndex = randomNumberGenerator.generateRandomNumber(0,grandSorcerersCircle.getCovensNumber()); //get a random coven index from the existing ones
                Index = randomNumberGenerator.generateRandomNumber(0,covens[CovenIndex].DemonList.size()); //get a random demon from the coven's demon list
                demon = covens[CovenIndex].DemonList.get(Index); //save the demon
                demon.isRetired = true; //this demon becomes retired, so it will end it's execution soon
                System.out.println("DEMON RETIRED");
                demonRetirer.release(); //the retiring ended so the semaphore is released
            }
            catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }


}
