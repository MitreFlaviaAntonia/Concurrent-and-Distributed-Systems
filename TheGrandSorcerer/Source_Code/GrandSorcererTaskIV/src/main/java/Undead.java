import java.util.concurrent.Semaphore;
import static java.lang.Thread.sleep;

public class Undead implements Runnable {

    private int UndeadIndex;
    private GrandSorcerersCircle grandSorcerersCircle = new GrandSorcerersCircle();
    private RandomNumberGenerator randomNumberGenerator = new RandomNumberGenerator();
    private Coven[] covens = grandSorcerersCircle.getCovens();
    private int CovenIndex = -1;
    private boolean isFighting = false;

    void setFighting(boolean IsFighting ) {
        isFighting = IsFighting;
    }

    private boolean getFighting() {
        return isFighting;
    }

    private void setCovenIndex(int covenIndex) {
        CovenIndex = covenIndex;
    }

    int getCovenIndex() { return CovenIndex; }
    boolean isDefeated = false;
    Undead(int undeadIndex) {
        UndeadIndex = undeadIndex;
    }

    private void RandomVisit(int UndeadIndex, int CovenIndex) {

        System.out.println("Undead " + UndeadIndex + " visits coven " + CovenIndex);
        System.out.println("Current demons nr in coven " + CovenIndex + " is " + covens[CovenIndex].getDemonsNumber());

        if(!getFighting()) {
            if(covens[CovenIndex].getDemonsNumber()>10) { //if there are more than 50 demons at the moment in a coven
                GrandSorcererHelper.retireDemons(randomNumberGenerator.generateRandomNumber(5, 10), UndeadIndex, CovenIndex); //retire a random number of demons (between 5 and 10)
            }
            covens[CovenIndex].loseIngredients(1, CovenIndex); //lose all the ingredients from the coven
        }
        else
        if(!isDefeated) {
            covens[CovenIndex].loseIngredients(0.1, CovenIndex); //lose all the ingredients from the coven
        }
        else
        {
            System.out.println("Undead " + UndeadIndex + " runs scared");
        }
        Coven.setUndeadsNumber(covens[CovenIndex].getUndeadsNumber()-1);
    }

    private Semaphore semaphore = new Semaphore(1);

    @Override
    public void run() {
        int undeadIndex;
        int covenIndex;

        while (true) {

            try {
                semaphore.acquire(); //if more undeads are trying to make demons retire from the same coven, all the demons created end up being retired in a short time

                undeadIndex = UndeadIndex;
                covenIndex = randomNumberGenerator.generateRandomNumber(0,grandSorcerersCircle.getCovensNumber());
                setCovenIndex(covenIndex);
                Coven.setUndeadsNumber(covens[covenIndex].getUndeadsNumber()+1);
                RandomVisit(undeadIndex, covenIndex); //an undead visits a random coven

                semaphore.release();

                sleep(randomNumberGenerator.generateRandomNumber(500,1000));//sleep for some time until another visit

            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
    }
}
