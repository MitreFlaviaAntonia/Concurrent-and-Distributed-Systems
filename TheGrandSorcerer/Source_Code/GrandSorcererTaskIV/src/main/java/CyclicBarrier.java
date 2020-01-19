import java.util.concurrent.Semaphore;

class CyclicBarrier {
    private int Parties; // the number of parties permitted
    private int partiesReceived = 0; // the number of parties received
    private Semaphore semaphore = new Semaphore(Parties);
    CyclicBarrier(int parties) {
        Parties = parties;
    }

    void await() throws InterruptedException {

        partiesReceived++; // the parties counter is raised
        semaphore.acquire();
        if(partiesReceived==Parties) // if it does not receive all the parties
        {
            int i=0;
            while(i < Parties) {
                semaphore.release();
                i++;
            }
        }

    }

}
