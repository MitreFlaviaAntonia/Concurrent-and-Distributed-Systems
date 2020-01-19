import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import static java.lang.Thread.sleep;

public class GrandSorcererHelper implements Runnable {

    private static GrandSorcerersCircle grandSorcerersCircle = new GrandSorcerersCircle();
    private static RandomNumberGenerator randomNumberGenerator = new RandomNumberGenerator();

    static Demon[] demons = new Demon[10000];
    private Witch[] witches = new Witch[200];
    private Undead[] undeads = new Undead[grandSorcerersCircle.getUndeadsNumber()];


    private void setGrandSorcerersCircle(GrandSorcerersCircle GrandSorcerersCircle) {
        grandSorcerersCircle = GrandSorcerersCircle;
    }

    private void setDemons(Demon[] demons) {
        GrandSorcererHelper.demons = demons;
    }

    static Demon[] getDemons(){
        return demons;
    }

    private void setWitches(Witch[] Witches) {
        witches = Witches;
    }

    private void setUndeads(Undead[] Undeads) {
        undeads = Undeads;
    }

    Undead[] getUndeads() {
        return undeads;
    }

    private static void DemonCreator(int demonIndex, Demon[] demons) { // creates demons and assigns them to a random coven

        int CovenIndex = randomNumberGenerator.generateRandomNumber(0, grandSorcerersCircle.getCovensNumber());

        if(demonIndex <= grandSorcerersCircle.getMaxDemonsNumber()) {
            System.out.println("\nDemon index is " + demonIndex + " from coven: " + CovenIndex + ", max nr of demons: " + grandSorcerersCircle.getMaxDemonsNumber() + ".\n");
            demons[demonIndex] = new Demon(demonIndex, CovenIndex);
            demons[demonIndex].setWallHitsCounter(0);
            demons[demonIndex].covens[CovenIndex].setDemonsNumber(demons[demonIndex].covens[CovenIndex].getDemonsNumber()+1);
            Thread[] t = new Thread[grandSorcerersCircle.getMaxDemonsNumber()]; //a new thread is created, for every demon
            t[demonIndex] = new Thread(demons[demonIndex]);
            t[demonIndex].start();
        }
    }

    private static void WitchesCreator(int witchIndex, Witch[] witch) { // creates demons and assigns them to a random cove

        if(witchIndex <= 200) { //200 is the maximum of witches

            witch[witchIndex] = new Witch();
            Thread[] t = new Thread[200]; //a new thread is created, for every demon
            t[witchIndex] = new Thread(witch[witchIndex]);
            t[witchIndex].start();
        }
    }

    private static void UndeadCreator(int undeadIndex, Undead[] undead) { // creates demons and assigns them to a random cove

        if(undeadIndex <= grandSorcerersCircle.getUndeadsNumber()) { //the maximum of undeads
            System.out.println("\nUndead index is " + undeadIndex);
            undead[undeadIndex] = new Undead(undeadIndex);
            Thread[] t = new Thread[grandSorcerersCircle.getUndeadsNumber()]; //a new thread is created, for every demon
            t[undeadIndex] = new Thread(undead[undeadIndex]);
            t[undeadIndex].start();
        }
    }

    static void retireDemons(int destroyedDemonsNumber, int UndeadIndex, int CovenIndex) { // destroys demons

        int randomDemonIndex;
        Demon demon;
        System.out.println("Undead " + UndeadIndex + " makes " + destroyedDemonsNumber + " demons retire from " + CovenIndex + " current demons number " + grandSorcerersCircle.getCovens()[CovenIndex].getDemonsNumber());
        for(int i = 0; i < destroyedDemonsNumber; i++) {

            do {
                randomDemonIndex = randomNumberGenerator.generateRandomNumber(0, grandSorcerersCircle.getCovens()[CovenIndex].getDemonsNumber()); //generate a random demon index
            }
            while(getDemons()[randomDemonIndex].isRetired || getDemons()[randomDemonIndex].CovenIndex != CovenIndex); //while the demon generated is already retired or its coven is not corresponding to the attacked coven

            demon = getDemons()[randomDemonIndex];
            demon.isRetired = true; //the demon retires
            demon.covens[CovenIndex].covenMatrix.setDemonIndex(-1); // a value that is never generated for a demon index, indicating there is no demon
            demon.covens[CovenIndex].CovenMatrix[demon.getLine()][demon.getColumn()] = 0; // the position where the retired demon was becomes free
            demon.covens[CovenIndex].setDemonsNumber(demon.covens[CovenIndex].getDemonsNumber()-1); //the current number of demons from the coven decreases
            System.out.println("Undead " + UndeadIndex + " made demon " + randomDemonIndex + " retire");
        }

        if(grandSorcerersCircle.getMaxDemonsNumber() - destroyedDemonsNumber >= 0)
            grandSorcerersCircle.setMaxDemonsNumber1(grandSorcerersCircle.getMaxDemonsNumber()-destroyedDemonsNumber);

    }

    private Lock lock = new ReentrantLock();
    private Semaphore semaphore = new Semaphore(1);

    @Override
    public void run() {

        int demonIndex = 0;
        int witchIndex = 0;
        int undeadIndex = 0;
        int sleepTime;

        for(int i=0;i<=grandSorcerersCircle.getUndeadsNumber();i++)
        {
            undeads[0] = new Undead(0);
        }
        setGrandSorcerersCircle(grandSorcerersCircle);


        try {
            lock.lock();
            while (witchIndex < 200) {
                WitchesCreator(witchIndex, witches);
                witchIndex++;
            }
            setWitches(witches);
            lock.unlock();

            while (undeadIndex < grandSorcerersCircle.getUndeadsNumber()) {
                semaphore.acquire();
                UndeadCreator(undeadIndex, undeads);
                undeadIndex++;
                semaphore.release();
            }
            setUndeads(undeads);

            while (demonIndex < grandSorcerersCircle.getMaxDemonsNumber()) {
                lock.lock();
                sleepTime = randomNumberGenerator.generateRandomNumber(100,500); //generates a random time to create the next demon
                sleep(sleepTime);
                DemonCreator(demonIndex, demons);
                demons[demonIndex].isRetired = false;
                demonIndex++;
                lock.unlock();
            }
            setDemons(demons);

        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

}