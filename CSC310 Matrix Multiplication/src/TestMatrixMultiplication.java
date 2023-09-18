import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class TestMatrixMultiplication {

    //use these matrices to test correctness of algs
    private int[][] A = new int[4][4];
    private int[][] B = new int[4][4];

    private int[][] answer = new int[4][4];

    //run this at beginning of tests
    public void assignValues(int matrixA[][], int matrixB[][])
    {
        matrixA[0][0] = 1; matrixA[0][1] = 2; matrixA[0][2] = 3; matrixA[0][3] = 4;
        matrixA[1][0] = 5; matrixA[1][1] = 6; matrixA[1][2] = 7; matrixA[1][3] = 8;
        matrixA[2][0] = 9; matrixA[2][1] = 10; matrixA[2][2] = 11; matrixA[2][3] = 12;
        matrixA[3][0] = 13; matrixA[3][1] = 14; matrixA[3][2] = 15; matrixA[3][3] = 16;


        matrixB[0][0] = 2; matrixB[0][1] = 4; matrixB[0][2] = 6; matrixB[0][3] = 8;
        matrixB[1][0] = 10; matrixB[1][1] = 12; matrixB[1][2] = 14; matrixB[1][3] = 16;
        matrixB[2][0] = 18; matrixB[2][1] = 20; matrixB[2][2] = 22; matrixB[2][3] = 24;
        matrixB[3][0] = 26; matrixB[3][1] = 28; matrixB[3][2] = 30; matrixB[3][3] = 32;

        answer[0][0] = 180; answer[0][1] = 200; answer[0][2] = 220; answer[0][3] = 240;
        answer[1][0] = 404; answer[1][1] = 456; answer[1][2] = 508; answer[1][3] = 560;
        answer[2][0] = 628; answer[2][1] = 712; answer[2][2] = 796; answer[2][3] = 880;
        answer[3][0] = 852; answer[3][1] = 968; answer[3][2] = 1084; answer[3][3] = 1200;
    }
    @Test
    public void testBruteForceAlg()
    {
        assignValues(A, B);
        MatrixMultiplication m = new MatrixMultiplication();
        int[][] C = m.bruteForceAlg(A, B);
        for (int i = 0; i < A.length; i++)
        {
            for (int j = 0; j < A.length; j++)
            {
                assertEquals(answer[i][j], C[i][j]);
            }
        }
    }

    @Test
    public void testStrassensAlg()
    {
        assignValues(A, B);
        MatrixMultiplication m = new MatrixMultiplication();
        int[][] C = m.strassensAlg(A, B);
        for (int i = 0; i < A.length; i++)
        {
            for (int j = 0; j < A.length; j++)
            {
                assertEquals(answer[i][j], C[i][j]);
            }
        }
    }

    @Test
    public void testBfAndStrassensComboAlg() throws InputTooLargeException {
        assignValues(A, B);
        MatrixMultiplication m = new MatrixMultiplication();
        int[][] C = m.bfAndStrassensComboAlg(A, B, 2);
        for (int i = 0; i < A.length; i++)
        {
            for (int j = 0; j < A.length; j++)
            {
                assertEquals(answer[i][j], C[i][j]);
            }
        }
    }

    @Test (expected = InputTooLargeException.class)
    public void comboAlgInputTooBig() throws InputTooLargeException {
        assignValues(A, B);
        MatrixMultiplication m = new MatrixMultiplication();
        int[][] C = m.bfAndStrassensComboAlg(A, B, 3);
    }

//    //commented out since i wanted to test that it works
//    // before making it a private method
//    @Test
//    public void testSplitAndJoin()
//    {
//        assignValues(A, B);
//        MatrixMultiplication m = new MatrixMultiplication();
//        int n = (int) Math.floor(A.length/2);
//        int[][] B = new int[n][n]; // first n/2 rows and columns
//        int[][] C = new int[n][n]; // first n/2 rows and last n/2 columns
//        int[][] D = new int[n][n]; // last n/2 rows and first n/2 columns
//        int[][] E = new int[n][n]; // last n/2 rows and last n/2 columns
//        m.split(A, B, C, D, E);
//
//        int[][] answerB = new int[n][n];
//        answerB[0][0] = 1; answerB[0][1] = 2;
//        answerB[1][0] = 5; answerB[1][1] = 6;
//        int[][] answerC = new int[n][n];
//        answerC[0][0] = 3; answerC[0][1] = 4;
//        answerC[1][0] = 7; answerC[1][1] = 8;
//        int[][] answerD = new int[n][n];
//        answerD[0][0] = 9; answerD[0][1] = 10;
//        answerD[1][0] = 13; answerD[1][1] = 14;
//        int[][] answerE = new int[n][n];
//        answerE[0][0] = 11; answerE[0][1] = 12;
//        answerE[1][0] = 15; answerE[1][1] = 16;
//
//        //tests correctness of split()
//        for (int i = 0; i < n; i++)
//        {
//            for (int j = 0; j < n; j++)
//            {
//                assertEquals(answerB[i][j], B[i][j]);
//                assertEquals(answerC[i][j], C[i][j]);
//                assertEquals(answerD[i][j], D[i][j]);
//                assertEquals(answerE[i][j], E[i][j]);
//            }
//        }
//
//        int test[][] = new int[A.length][A.length];
//        m.join(test, B, C, D, E);
//
//        //tests correctness of join()
//        for (int i = 0; i < A.length; i++)
//        {
//            for (int j = 0; j < A.length; j++)
//            {
//                assertEquals(A[i][j], test[i][j]);
//                assertEquals(A[i][j], test[i][j]);
//                assertEquals(A[i][j], test[i][j]);
//                assertEquals(A[i][j], test[i][j]);
//            }
//        }
//    }

//    //commented out since i wanted to test that it works
//    // before making it a private method
//    @Test
//    public void testAdd()
//    {
//        MatrixMultiplication m = new MatrixMultiplication();
//        int[][] m1 = new int[2][2];
//        int[][] m2 = new int[2][2];
//
//        m1[0][0] = 1; m1[0][1] = 2; m1[1][0] = 3; m1[1][1] = 4;
//        m2[0][0] = 5; m2[0][1] = 6; m2[1][0] = 7; m2[1][1] = 8;
//        int[][] test = m.add(m1, m2);
//
//        int[][] sum = new int[2][2];
//        sum[0][0] = 6; sum[0][1] = 8; sum[1][0] = 10; sum[1][1] = 12;
//
//        for(int i = 0; i < sum.length; i++)
//        {
//            for (int j = 0; j < sum.length; j++)
//            {
//                assertEquals(sum[i][j], test[i][j]);
//            }
//        }
//    }

//    //commented out since i wanted to test that it works
//    // before making it a private method
//    @Test
//    public void testSub()
//    {
//        MatrixMultiplication m = new MatrixMultiplication();
//        int[][] m1 = new int[2][2];
//        int[][] m2 = new int[2][2];
//
//        m1[0][0] = 1; m1[0][1] = 2; m1[1][0] = 3; m1[1][1] = 4;
//        m2[0][0] = 5; m2[0][1] = 13; m2[1][0] = 2; m2[1][1] = 1;
//        int[][] test = m.sub(m1, m2);
//
//        int[][] diff = new int[2][2];
//        diff[0][0] = -4; diff[0][1] = -11; diff[1][0] = 1; diff[1][1] = 3;
//
//        for(int i = 0; i < diff.length; i++)
//        {
//            for (int j = 0; j < diff.length; j++)
//            {
//                assertEquals(diff[i][j], test[i][j]);
//            }
//        }
//    }

//    //commented out since i wanted to test that it works
//    // before making it a private method
//    @Test
//    public void testMultiply()
//    {
//        MatrixMultiplication m = new MatrixMultiplication();
//        int[][] m1 = new int[2][2];
//        int[][] m2 = new int[2][2];
//
//        m1[0][0] = 1; m1[0][1] = 2; m1[1][0] = 3; m1[1][1] = 4;
//        m2[0][0] = 5; m2[0][1] = 6; m2[1][0] = 7; m2[1][1] = 8;
//        int[][] test = m.multiply(m1, m2);
//
//        int[][] product = new int[2][2];
//        product[0][0] = 19; product[0][1] = 22; product[1][0] = 43; product[1][1] = 50;
//
//        for(int i = 0; i < product.length; i++)
//        {
//            for (int j = 0; j < product.length; j++)
//            {
//                assertEquals(product[i][j], test[i][j]);
//            }
//        }
//    }
}
