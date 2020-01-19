public class DiagonalArea implements Runnable {

    private GrandSorcerersCircle grandSorcerersCircle = new GrandSorcerersCircle();
    private Coven[] covens = grandSorcerersCircle.getCovens(); //get the covens

    @Override
    public void run() {

        while(true) {

            for(int CovenIndex = 0; CovenIndex< grandSorcerersCircle.getCovensNumber(); CovenIndex++) { //for every coven

                if (covens[CovenIndex].asleepCounter == covens[CovenIndex].CovenSize) { //if the diagonal is full of sleeping demons
                    grandSorcerersCircle.sleeping.tryAcquire(); //try to acquire the semaphore, if no demon is moving it will succeed
                    System.out.println("Diagonal area full at coven " + CovenIndex +"\n Demons start wakeing up" );
                    covens[CovenIndex].asleepCounter = 0; //they will all wake up, so the asleep counter resets

                    for (int j = 0; j < covens[CovenIndex].DemonList.size(); j++) { //for every demon from the coven

                        if (covens[CovenIndex].DemonList.get(j).isAsleep) //if the demon is asleep
                            covens[CovenIndex].DemonList.get(j).isAsleep = false; //the demon wakes up
                    }
                    grandSorcerersCircle.sleeping.release();
                }
            }
        }
    }
}
