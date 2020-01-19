import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**Final class used for parallel matrix multiplication, using Executors of MultiplyTask threads.
 * @see MultiplyTask*/
public class ParallelMatrixMultiplication {

    /**First matrix operand*/
    private double[][] a;
    /**Second matrix operand*/
    private double[][] b;
    /**Result matrix*/
    private double[][] c;

    protected static final int MATRIX_SIZE = 1024,
            POOL_SIZE = Runtime.getRuntime().availableProcessors(),
            MINIMUM_THRESHOLD = 64;

    /**An executor of a fix number of threads,given by the number of available processors*/
    protected static ExecutorService exec = Executors.newFixedThreadPool(POOL_SIZE);

    /**Pass a and b matrices ,and instantiate the c matrix*/
    ParallelMatrixMultiplication(double[][] a, double[][] b) {
        // assumption : a and b are both double[MATRIX_SIZE][MATRIX_SIZE]
        this.a = a;
        this.b = b;
        this.c = new double[MATRIX_SIZE][MATRIX_SIZE];
    }

    /**Default constructor , used for debugging code*/
    ParallelMatrixMultiplication() {
        a = new double[MATRIX_SIZE][MATRIX_SIZE];
        b = new double[MATRIX_SIZE][MATRIX_SIZE];
        c = new double[MATRIX_SIZE][MATRIX_SIZE];

        for (int i = 0; i < a.length; ++i) {
            for (int j = 0; j < a.length; ++j) {
                a[i][j] = 1.0;
                b[i][j] = 1.0;
            }
        }
    }

    /**Return the result matrix
     * @return Result matrix*/
    public double[][] getResult() {
        return c;
    }

    /**Check for differences*/
    public void check() {

        for (int i = 0; i < c.length; ++i) {
            for (int j = 0; j < c.length; ++j) {
                if (Math.abs(c[i][j]-a.length) > Double.MIN_VALUE) {
                    System.out.format("%.3f\n",c[i][j]);
                }
            }
        }
        System.out.println("DONE");
    }

    /**multiplyRecursive(0, 0, 0, 0, 0, 0, a.length), using MultiplyTask threads
     * @see MultiplyTask*/
    public void multiply() {

        Future f = exec.submit(new MultiplyTask(a, b, c, 0, 0, 0, 0, 0, 0, a.length));
        try {
            f.get();
            exec.shutdown();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
