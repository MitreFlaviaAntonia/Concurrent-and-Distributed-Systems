public class MyThread extends Thread {

    private static int n = 0;
    private int ThreadIndex;

    MyThread(int threadIndex) {
        ThreadIndex = threadIndex;
    }

    public void run() {

        int temp, i = 0;

        //System.out.println("current thread id: "+currentThread().getId());
        while(i<10) {
            System.out.println("Current thread:" + ThreadIndex +" n = " + n);
            temp = n;
            n = temp + 1;
            i++;
        }

        System.out.println(n);
    }

}

