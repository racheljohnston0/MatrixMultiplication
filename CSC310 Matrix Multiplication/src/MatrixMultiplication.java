import java.io.*;

/**
 * @author: Rachel Johnston
 * CSC310 Programming Assignment
 */
public class MatrixMultiplication {

    private int[][] A;
    private int[][] B;
    private int[][] C;

    //code should be able to accept input from a file that contains a 2^nx2^n matrices

    public static void main(String[] args) throws FileNotFoundException {
        MatrixMultiplication m = new MatrixMultiplication();

        m.generateCSV(5, "sample file.csv", 3);
    }

    private static final int NUMBER_OF_TRIALS = 20;

    /**
     * use this method to run formal investigation
     * @param n matrix size (2^n)
     * @throws IOException
     * @throws InputTooLargeException
     */
    public void executeFormalInvestigation(int n) throws IOException, InputTooLargeException {
        int currentTrialNumber = 0;
        long time;
        //this.generateCSV(n, "my 20 matrix pairs.csv", 20);
        int cutOff = n;
        //System.out.println("Brute force: ");
        System.out.println("BF and Strassens: ");
        System.out.println("Cut off value: " + cutOff);
        for (int i = 0; i < NUMBER_OF_TRIALS; i++)
        {
            currentTrialNumber++;
            this.convertCSVtoMatrix("my 20 matrix pairs", i);
            long startTime = System.currentTimeMillis();
            //C = bruteForceAlg(A, B);
            C = bfAndStrassensComboAlg(A, B, cutOff);
            long endTime = System.currentTimeMillis();
            time = endTime - startTime;
            System.out.println("Trial #" + currentTrialNumber + ": " + time + " ms");
        }
    }

    /**
     * use this method to run informal investigation
     * result: cutoff point is 9
     * @param n
     * @throws IOException
     * @throws InputTooLargeException
     */
    public void executeInformalInvestigation(int n) throws IOException, InputTooLargeException {
        int cutoffPoint = (int)Math.floor(n/2);
        int trials = 4;
        for (int i = 0; i < trials; i++)
        {
            this.generateCSV(cutoffPoint, "my matrix pair.csv", 1);
            this.convertCSVtoMatrix("my matrix pair.csv", 1);

            long startTime = System.currentTimeMillis();
            this.bruteForceAlg(A, B);
            long endTime = System.currentTimeMillis();
            long time = endTime - startTime;
            System.out.println("Brute force w/ " + cutoffPoint +": " + time + " ms");

            startTime = System.currentTimeMillis();
            this.bfAndStrassensComboAlg(A, B, cutoffPoint);
            endTime = System.currentTimeMillis();
            time = endTime - startTime;
            System.out.println("BFStrassens w/ " + cutoffPoint +": "  + time + " ms");
            cutoffPoint++;
        }
    }

    private static final int NUMBER_OF_MATRICES_PER_PAIR = 2;

    /**
     * generates pairs of matrices into a csv file
     * @param n
     * @param fileName
     * @param pairs
     * @throws FileNotFoundException
     */
    public void generateCSV(int n, String fileName, int pairs) throws FileNotFoundException
    {
        File csvFile = new File(fileName);
        PrintWriter pw = new PrintWriter(csvFile);

        pw.print("n, 2^n, number of pairs,,,,,,,,,,,,,,,,,,,,,,,,,,,,,\n");
        int s = (int) Math.pow(2, n);
        pw.print(n + "," + s + "," + pairs + ",,,,,,,,,,,,,,,,,,,,,,,,,,,,,\n");


        for (int x = 0; x < pairs; x++)
        {
            for (int k = 0; k < NUMBER_OF_MATRICES_PER_PAIR; k++)
            {
                for (int i = 0; i < s; i++)
                {
                    for (int j = 0; j < s; j++)
                    {
                        int m = (int) (Math.random() * 9);
                        pw.print(m);
                        pw.print(',');
                    }
                    pw.print('\n');
                }
                //line of commas in between a pair of matrices, only prints blank line BETWEEN matrix pairs, not after
                if (k == 0)
                {
                    pw.print(",,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,\n");
                }
            }
            //marks end of pair, prints the pair number out
            pw.print("~" + (x+1));
            pw.print(",,,,,,,,,,,,,,,,,,,,,,,,,,,,,,\n");
        }
        //marks end of file
        pw.print("###,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,");
        pw.close();
    }

    /**
     * puts values in CSV file into matrices
     * converted matrices are stored in instance variables A and B
     * @param fileName
     */
    public void convertCSVtoMatrix(String fileName, int pairNum) throws IOException {
        RandomAccessFile file = new RandomAccessFile(fileName, "r");

        file.readLine();
        String currentLine = file.readLine();
        String[] numbers = currentLine.split(",");

        int n = Integer.parseInt(numbers[0]);
        int size = (int) Math.pow(2, n);
        int matrixPairs = Integer.parseInt(numbers[2]);

        //keeps track of what pair it is on in the csv file
        int pairN = 0;

        A = new int[size][size];
        B = new int[size][size];
        C = new int[size][size];

        //size is the number of lines per matrix

        if (pairNum > 1)
        {
            //skips to wherever the requested pair starts
            for (int i = 0; i < 2*size + 2; i++)
            {
                file.readLine();
            }
        }

        currentLine = file.readLine();
        numbers = currentLine.split(",");

        // ### means end of file
        if (numbers[0].equals("###") == false) {
            int[] intNumbers = new int[numbers.length];
            for (int i = 0; i < numbers.length; i++) {
                intNumbers[i] = Integer.parseInt(numbers[i]);
            }
            int rowA = 0;
            while (numbers.length != 0) {
                for (int j = 0; j < size; j++) {
                    A[rowA][j] = intNumbers[j];
                }
                currentLine = file.readLine();
                numbers = currentLine.split(",");
                for (int i = 0; i < numbers.length; i++) {
                    intNumbers[i] = Integer.parseInt(numbers[i]);
                }
                rowA++;
            }
            currentLine = file.readLine();

            int rowB = 0;
            numbers = currentLine.split(",");
            for (int i = 0; i < numbers.length; i++) {
                intNumbers[i] = Integer.parseInt(numbers[i]);
            }
            while (numbers[0].length() == 1) {
                for (int j = 0; j < size; j++) {
                    B[rowB][j] = intNumbers[j];
                }
                currentLine = file.readLine();
                numbers = currentLine.split(",");
                if (numbers[0].length() <= 1) {
                    for (int i = 0; i < numbers.length; i++) {
                        intNumbers[i] = Integer.parseInt(numbers[i]);
                    }
                }
                rowB++;
            }
            String[] mark = currentLine.split(",");
            String pairMark = mark[0];
            pairMark = pairMark.substring(1);
            pairN = Integer.parseInt(pairMark);
        }
    }

    /**
     * brute force matrix multiplication
     * @param matrixA
     * @param matrixB
     * @return result
     */
    public int[][] bruteForceAlg(int matrixA[][], int matrixB[][])
    {
        int result[][];
        //make sure that it is possible to multiply the the two matrices together
        assert(matrixA[0].length == matrixB.length);

        //initialize empty result array using the input matrix sizes
        int matrixSizeA = matrixA.length;
        int matrixSizeB = matrixB[0].length;
        result = new int[matrixSizeA][matrixSizeB];

        for (int i = 0; i < matrixSizeA; i++)
        {
            for (int j = 0; j < matrixB.length; j++)
            {
                for (int k = 0; k < matrixB.length; k++)
                {
                    result[i][j] += matrixA[i][k] * matrixB[k][j];
                }
            }
        }
        return result;
    }

    /**
     * strassen's matrix multiplication
     * @param matrixA
     * @param matrixB
     * @return
     */
    public int[][] strassensAlg(int matrixA[][], int matrixB[][])
    {
        //divide matrices into 4
        int n = matrixA.length;
        //do division once so its less expensive
        int m = n/2;

        int result[][] = new int[n][n];
        if (n == 1) //when matrix is 1x1
        {
            result[0][0] = matrixA[0][0] * matrixB[0][0];
        }
        else
        {
            int[][] A11 = new int[m][m];
            int[][] A12 = new int[m][m];
            int[][] A21 = new int[m][m];
            int[][] A22 = new int[m][m];

            int[][] B11 = new int[m][m];
            int[][] B12 = new int[m][m];
            int[][] B21 = new int[m][m];
            int[][] B22 = new int[m][m];

            split(matrixA, A11, A12, A21, A22);
            split(matrixB, B11, B12, B21, B22);

            int[][] m1 = strassensAlg(add(A11, A22), add(B11, B22));
            int[][] m2 = strassensAlg(add(A21, A22), B11);
            int[][] m3 = strassensAlg(A11, sub(B12, B22));
            int[][] m4 = strassensAlg(A22, sub(B21, B11));
            int[][] m5 = strassensAlg(add(A11, A12), B22);
            int[][] m6 = strassensAlg(sub(A21, A11), add(B11, B12));
            int[][] m7 = strassensAlg(sub(A12, A22), add(B21, B22));

            int[][] c11 = add(sub(add(m1, m4), m5), m7);
            int[][] c12 = add(m3, m5);
            int[][] c21 = add(m2, m4);
            int[][] c22 = add(sub(add(m1, m3), m2), m6);

            join(result, c11, c12, c21, c22);
        }
        return result;
    }

    /**
     * adds two matrices together
     * @param A 1st matrix
     * @param B 2nd matrix
     * @return C (sum of two matrices)
     */
    private int[][] add(int[][]A, int[][] B)
    {
        int n = A.length;
        int[][] C = new int[n][n];
        for (int i = 0; i < n; i++)
        {
            for (int j = 0; j < n; j++)
            {
                C[i][j] = A[i][j] + B[i][j];
            }
        }
        return C;
    }

    /**
     * subtracts two matrices
     * @param A 1st matrix
     * @param B 2nd matrix
     * @return C (difference of two matrices)
     */
    private int[][] sub(int[][] A, int[][] B)
    {
        int n = A.length;
        int[][] C = new int[n][n];
        for (int i = 0; i < n; i++)
        {
            for (int j = 0; j < n; j++)
            {
                C[i][j] = A[i][j] - B[i][j];
            }
        }
        return C;
    }

    /**
     * joins matrices together (opposite of split())
     * @param matrix to combine following matrices in
     * @param A top left
     * @param B top right
     * @param C bottom left
     * @param D bottom right
     */
    private void join(int matrix[][], int A[][], int B[][], int C[][], int D[][])
    {
        //order of parameters matter
        int n = matrix.length;

        int m = (int)Math.floor(n/2);

        for (int i = 0; i < m; i++)
        {
            for (int j = 0; j < m; j++)
            {
                matrix[i][j] = A[i][j];
                matrix[i][j+m] = B[i][j];
                matrix[i+m][j] = C[i][j];
                matrix[i+m][j+m] = D[i][j];
            }
        }
    }

    /**
     * splits the matrix up (opposite of join())
     * @param matrix to be split
     * @param A top left
     * @param B top right
     * @param C bottom left
     * @param D bottom right
     */
    private void split(int matrix[][], int A[][], int B[][], int C[][], int D[][])
    {
        //order of parameters matter
        int n = (int) Math.floor(matrix.length/2);

        for (int i = 0; i < n; i++)
        {
            for (int j = 0; j < n; j++)
            {
                A[i][j] = matrix[i][j];
                B[i][j] = matrix[i][j+n];
                C[i][j] = matrix[i+n][j];
                D[i][j] = matrix[i+n][j+n];
            }
        }
    }

    /**
     * combined algorithm that uses strassen's until the array is some siz of 2^n x 2^n
     * and then completes the rest using brute force; user supplies n
     * @param matrixA
     * @param matrixB
     * @param n determines size of matrix that'll be used in brute force
     * @return resulting matrix
     * @throws InputTooLargeException
     */
    public int[][] bfAndStrassensComboAlg(int[][] matrixA, int[][] matrixB, int n) throws InputTooLargeException {

        int[][] result = new int[matrixA.length][matrixB.length];

        int matrixLength = matrixA.length;
        int inputSize = (int)Math.pow(2, n);

        //optimization
        int m = (matrixLength/2);

        if (matrixLength == inputSize) {
            for (int i = 0; i < matrixLength; i++) {
                for (int j = 0; j < matrixLength; j++) {
                    for (int k = 0; k < matrixLength; k++) {
                        result[i][j] += matrixA[i][k] * matrixB[k][j];
                    }
                }
            }
        } else if (matrixLength > inputSize) {
            int[][] A11 = new int[m][m]; int[][] A12 = new int[m][m];
            int[][] A21 = new int[m][m]; int[][] A22 = new int[m][m];

            int[][] B11 = new int[m][m]; int[][] B12 = new int[m][m];
            int[][] B21 = new int[m][m]; int[][] B22 = new int[m][m];

            split(matrixA, A11, A12, A21, A22);
            split(matrixB, B11, B12, B21, B22);

            int[][] m1 = bfAndStrassensComboAlg(add(A11, A22), add(B11, B22), n);
            int[][] m2 = bfAndStrassensComboAlg(add(A21, A22), B11, n);
            int[][] m3 = bfAndStrassensComboAlg(A11, sub(B12, B22), n);
            int[][] m4 = bfAndStrassensComboAlg(A22, sub(B21, B11), n);
            int[][] m5 = bfAndStrassensComboAlg(add(A11, A12), B22, n);
            int[][] m6 = bfAndStrassensComboAlg(sub(A21, A11), add(B11, B12), n);
            int[][] m7 = bfAndStrassensComboAlg(sub(A12, A22), add(B21, B22), n);

            int[][] c11 = add(sub(add(m1, m4), m5), m7);
            int[][] c12 = add(m3, m5);
            int[][] c21 = add(m2, m4);
            int[][] c22 = add(sub(add(m1, m3), m2), m6);

            join(result, c11, c12, c21, c22);
        } else {
            throw new InputTooLargeException("Input size is bigger than the matrix sizes. Enter a smaller number.");
        }
        return result;
    }

    /**
     * getter for A[][]
     * @return A
     */
    public int[][] getmatrixA()
    {
        return A;
    }

    /**
     * getter for B[][]
     * @return B
     */
    public int[][] getmatrixB()
    {
        return B;
    }

    /**
     * sets matrixC
     * @param result
     */
    public void setResultMatrix(int[][] result)
    {
        C = result;
    }

    /**
     * getter for C[][] aka result
     * @return C
     */
    public int[][] getmatrixC()
    {
        return C;
    }

    /**
     * prints a matrix
     * @param matrix
     */
    public void printMatrix(int[][] matrix)
    {
        String s = "";
        for (int i = 0; i < matrix.length; i++)
        {
            for (int j = 0; j < matrix.length; j++)
            {
                s = s + matrix[i][j] + " ";
            }
            s = s + "\n";
        }
        System.out.println(s);
    }
}
