import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import static java.lang.Thread.sleep;

public class Witch implements Runnable {

    private RandomNumberGenerator randomNumberGenerator = new RandomNumberGenerator();
    private GrandSorcerersCircle grandSorcerersCircle = new GrandSorcerersCircle();
    private GrandSorcererHelper grandSorcererHelper = new GrandSorcererHelper();
    private Coven[] covens = grandSorcerersCircle.getCovens();

    private Undead[] undeads = grandSorcererHelper.getUndeads();

    private Potion potion = new Potion();
    private MagicPortal magicPortal = new MagicPortal();
    private GrandSorcerer grandSorcerer = new GrandSorcerer();

    private void readIngredients(int CovenIndex) {

        for(int i=0; i<10; i++) {
            if(grandSorcerersCircle.getCovens()[CovenIndex].getIngredientsCount(i)!=0)
                System.out.println("Witches read ingredient type: " + grandSorcerersCircle.getCovens()[CovenIndex].getIngredient(i) + " and count: " + grandSorcerersCircle.getCovens()[CovenIndex].getIngredientsCount(i));
        }
    }


    private int selectRandomCoven(int covensNumber) //select a random coven
    {
        return randomNumberGenerator.generateRandomNumber(0,covensNumber);
    }

    private boolean fightUndead(int undeadIndex) {
        undeads[undeadIndex].setFighting();
        int numberOfPotions = randomNumberGenerator.generateRandomNumber(2,5);
        return grandSorcerer.givePotions(numberOfPotions); //if the grand sorcerer can provide the number of necessary potions return true, else return false
    }

    private Lock lock = new ReentrantLock();

    @Override
    public void run() {
        int covenIndex;


        while(true) {

            covenIndex = selectRandomCoven(grandSorcerersCircle.getCovensNumber()); //selecting a random coven to read ingredients from
            try {

                grandSorcerersCircle.getCovens()[covenIndex].maxWitches.acquire(); //if a witch reads, she should acquire the semaphore for reading ingredients from the selected coven
                lock.lock(); //reading ingredients and creating potions must be synchronised
                //the grand sorcerer should not read potions while witches are creating them
                readIngredients(covenIndex); //read ingredients from this coven

                sleep(500); //a time must pass until the next reading
                for(int i = 0; i< 20; i++) //there are 20 potions
                {
                    if(potion.createPotion(potion.IngredientsLists.get(i), potion.IngredientsNecessaryAmountLists.get(i), covenIndex))
                    {
                        magicPortal.increasePotionsCount(i,1);
                        System.out.println(potion.Potions.get(i) + " was created");
                    }
                    sleep(randomNumberGenerator.generateRandomNumber(10,30)); //making a potion takes some time
                }

                if(covens[covenIndex].getUndeadsNumber()!=0) {
                    for(int i=0; i<covens[covenIndex].getUndeadsNumber(); i++)
                        for(int j=0; j<grandSorcerersCircle.getUndeadsNumber(); j++)
                            if(grandSorcererHelper.getUndeads()[i].getCovenIndex()==covenIndex)
                                grandSorcererHelper.getUndeads()[i].isDefeated = fightUndead(j);
                }

                lock.unlock(); //the witch has finished reading ingredients and creating potions so the lock is unlocked

            }
             catch (InterruptedException e) {
                e.printStackTrace();
            }
            grandSorcerersCircle.getCovens()[covenIndex].maxWitches.release(); //release the semaphore for reading from this coven
            grandSorcerer.readPotions(magicPortal.potionsCount);
        }
    }
}
