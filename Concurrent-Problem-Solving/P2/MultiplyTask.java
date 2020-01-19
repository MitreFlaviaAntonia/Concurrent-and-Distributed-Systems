import java.util.concurrent.FutureTask;

/**Base class used for parallel matrix multiplication, using Executors and FutureTask classes
 * @see Sequentializer
 * @see ParallelMatrixMultiplication*/
class MultiplyTask implements Runnable{

    /**First matrix operand*/
    private double[][] a;
    /**Second matrix operand*/
    private double[][] b;
    /**Result matrix*/
    private double[][] c;
    //Variables used for matrix segmentation
    private int a_i, a_j, b_i, b_j, c_i, c_j, size;

    MultiplyTask(double[][] a, double[][] b, double[][] c, int a_i, int a_j, int b_i, int b_j, int c_i, int c_j, int size) {
        this.a = a;
        this.b = b;
        this.c = c;
        this.a_i = a_i;
        this.a_j = a_j;
        this.b_i = b_i;
        this.b_j = b_j;
        this.c_i = c_i;
        this.c_j = c_j;
        this.size = size;
    }

    public void run() {
        //System.out.format("[%d,%d]x[%d,%d](%d)\n",a_i,a_j,b_i,b_j,size);
        int h = size/2;

        if (size <= ParallelMatrixMultiplication.MINIMUM_THRESHOLD) {
            for (int i = 0; i < size; ++i) {
                for (int j = 0; j < size; ++j) {
                    for (int k = 0; k < size; ++k) {
                        c[c_i+i][c_j+j] += a[a_i+i][a_j+k] * b[b_i+k][b_j+j];
                    }
                }
            }
        } else {
            MultiplyTask[] tasks = {
                    new MultiplyTask(a, b, c, a_i, a_j, b_i, b_j, c_i, c_j, h),
                    new MultiplyTask(a, b, c, a_i, a_j+h, b_i+h, b_j, c_i, c_j, h),

                    new MultiplyTask(a, b, c, a_i, a_j, b_i, b_j+h, c_i, c_j+h, h),
                    new MultiplyTask(a, b, c, a_i, a_j+h, b_i+h, b_j+h, c_i, c_j+h, h),

                    new MultiplyTask(a, b, c, a_i+h, a_j, b_i, b_j, c_i+h, c_j, h),
                    new MultiplyTask(a, b, c, a_i+h, a_j+h, b_i+h, b_j, c_i+h, c_j, h),

                    new MultiplyTask(a, b, c, a_i+h, a_j, b_i, b_j+h, c_i+h, c_j+h, h),
                    new MultiplyTask(a, b, c, a_i+h, a_j+h, b_i+h, b_j+h, c_i+h, c_j+h, h)
            };

            FutureTask[] fs = new FutureTask[tasks.length/2];

            for (int i = 0; i < tasks.length; i+=2) {
                fs[i/2] = new FutureTask(new Sequentializer(tasks[i], tasks[i+1]), null);
                ParallelMatrixMultiplication.exec.execute(fs[i/2]);
            }
            for (int i = 0; i < fs.length; ++i) {
                fs[i].run();
            }
            try {
                for (int i = 0; i < fs.length; ++i) {
                    fs[i].get();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}