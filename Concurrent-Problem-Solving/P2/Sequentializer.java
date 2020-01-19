/**Utility thread class used to simplify a FutureTask in MultiplyTask
 * @see MultiplyTask*/
public class Sequentializer implements Runnable {

    private MultiplyTask first, second;

    Sequentializer(MultiplyTask first, MultiplyTask second) {

        this.first = first;
        this.second = second;
    }

    public void run() {

        first.run();
        second.run();
    }
}
