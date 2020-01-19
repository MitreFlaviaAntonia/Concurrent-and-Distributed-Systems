import static java.lang.Thread.sleep;

public class Channel<T> {
    int ready = 0;

    synchronized void Send(T mes, String side, String PhilosopherNr) throws InterruptedException {

        if(!PhilosopherNr.contains("5") && side.equals("right") || PhilosopherNr.contains("5") && side.equals("left")) {
            System.out.println(side + " fork picked up by " + PhilosopherNr + ". Eating.");
            sleep(1000); // We assume that eating takes some time
        }
        else
            System.out.println(side + " fork picked up by " + PhilosopherNr +".");

        ++ready; // Notifies potential receivers by making ready = 1

        notifyAll();
    }

    synchronized void Receive(String side, String PhilosopherNr) throws InterruptedException {

        if(!PhilosopherNr.contains("5") && side.equals("left") || PhilosopherNr.contains("5") && side.equals("right"))
            {
                System.out.println(side + " fork put down by " + PhilosopherNr +". Back to thinking.");
                sleep(1000); //we assume that thinking takes some time
            }
        else
            System.out.println(side + " fork put down by " + PhilosopherNr +".");

        while (ready==0)
            wait(); // Expects a message to be available on the channel, when ready = 1

        --ready; // Releases the channel
    }
}
