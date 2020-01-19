public class MainThread {

    public static void main(String[] args) {

        Thread thread1 = new Thread((new MyThread(1)));
        thread1.start();
        Thread thread2 = new Thread((new MyThread(2)));
        thread2.start();
        try{
            thread1.join();
            thread2.join();
        }
        catch(InterruptedException e) {
            e.printStackTrace();
        }
    }
}