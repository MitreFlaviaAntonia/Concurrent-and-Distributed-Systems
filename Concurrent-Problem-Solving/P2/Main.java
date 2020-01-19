
/**Initializing parallel and sequential matrix multiplications(a*b),
 * and print the time elapsed for each one.
 * If there are found errors in the computation print the errors count.
 * @see ParallelMatrixMultiplication
 * @see MultiplyTask*/
class Main {

    public static void main(String[] args) {

        /**The matrices size*/
        int mSize = 1024;
        double[][] a = new double[mSize][mSize];
        double[][] b = new double[mSize][mSize];

        //Populating the matrices with rand values between [-500.0,500.0)
        for (int i = 0; i < mSize; ++i) {
            for (int j = 0; j < mSize; ++j) {
                a[i][j] = Math.random() * 1000 - 500;
                b[i][j] = Math.random() * 1000 - 500;
            }
        }

        //---------------------PARALLEL MULTIPLICATION---------------------------------
        System.out.println("Parallel O(N^3) matrix multiplication start...");

        long start = System.currentTimeMillis();
        ParallelMatrixMultiplication pmm = new ParallelMatrixMultiplication(a, b);
        pmm.multiply();
        long finish = System.currentTimeMillis();

        System.out.println("finished.");
        System.out.format("Time elapsed: %d ms\n", finish-start);
        double[][] res = pmm.getResult();

        //---------------------SERIAL MULTIPLICATION-----------------------------------
        double[][] check = new double[mSize][mSize];

        System.out.println("Serial O(N^3) matrix multiplication start...");
        start = System.currentTimeMillis();

        for (int i = 0; i < mSize; ++i) {
            for (int j = 0; j < mSize; ++j) {
                for (int k = 0; k < mSize; ++k) {
                    check[i][j] += a[i][k] * b[k][j];
                }
            }
        }
        finish = System.currentTimeMillis();
        System.out.println("finished.");
        System.out.format("Time elapsed: %d ms\n", finish-start);


        //--------------------CHECKING FOR DIFFERENCES BETWEEN SERIAL AND PARALLEL------
        System.out.println("Validating results...");
        int errors = 0;

        for (int i = 0; i < mSize; ++i) {
            for (int j = 0; j < mSize; ++j) {
                if (Math.abs(check[i][j] - res[i][j]) > Double.MIN_VALUE) {
                    errors++;
                }
            }
        }

        System.out.format("Done. Number of differing entries: %d\n", errors);
    }
}
