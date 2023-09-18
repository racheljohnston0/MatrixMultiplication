import java.io.IOException;
import java.util.Scanner;

public class Runner {

    public static void main(String[] args) throws IOException, InputTooLargeException {

        MatrixMultiplication m = new MatrixMultiplication();

        int[][] A;
        int[][] B;
        int[][] C;

        int n = 2; //set
        String fileName = "my matrix pair.csv"; //set
        int pairs = 1; //set

        //if you want to generate a file run this on an empty file
        m.generateCSV(n, fileName, pairs);

        //run this on the file you want to read in
        m.convertCSVtoMatrix(fileName, 1); //pairNum is the pair in the file you want to multiply together

        A = m.getmatrixA();
        B = m.getmatrixB();

        int x = 1; //cutoff point to be used in following alg to determine what size matrix to switch from strassen's to brute force
        C = m.bfAndStrassensComboAlg(A, B, x);
        m.setResultMatrix(C);

        C = m.bruteForceAlg(A, B);
        m.setResultMatrix(C);

        //have the option of printing matrices if you want to see results (if matrices are small enough)
        m.printMatrix(A);
        m.printMatrix(B);
        m.printMatrix(C);
    }

}

