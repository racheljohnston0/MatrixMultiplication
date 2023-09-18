# Run MatrixMultiplication

CSC310 Programming Assignment

Rachel Johnston

### __File Info__:

**MatrixMultiplication**: contains all of the algorithms for Strassen's and Brute Force
    
    executeFormalInvestigation(int n): used to run formal investigations for assignment

    executeInformalInvestigation(int n): used to run informal investigations for assignment

    generateCSV(int n, String fileName, int pairs): generates CSV file that is already loaded into the project

    convertCSVtoMatrix(String fileName, int pairNum): converts CSV file to matrices and stores them into instance variables

    *bruteForceAlg(int matrixA[][], int matrixB[][]): brute force implementation of matrix multiplication

    *strassensAlg(int matrixA[][], int matrixB[][]): Strassen's implementation of matrix multiplication (always recurses down to 1x1)

    *bfAndStrassensComboAlg(int[][] matrixA, int[][] matrixB, int n): combination of brute force and Strassen's where once the matrices get to a certain size, it switches from Strassen's to brute force

    add(), sub(), join(), split() are all private methods that take care of mathematical operations used in Strassens

    instance variables: A[][], B[][], C[][]
        A[][]: first matrix factor
        B[][]: second matrix factor
        each have getters that are used in Runner
        C[][] has setter (stores the product matrix)

      printMatrix(int[][] matrix): allows you to print a given matrix (recommend to only be used on small matrices)


**Runner**: contains main method that runs methods in MatrixMultiplication, currently contains example of how to run MatrixMultiplication

**sample file.csv**: sample of how the csv file is set up. 
First line holds labels for the second line (size, matrix size, number of pairs). 
Second line contains the file summary information. 
A line of just commas (`,,,,,...`) marks the separation between a pair of matrices. 
`````~n,,,,,,....````` marks the end of a pair (n = pair number). 
`###,,,,,,...` marks the end of the file, so the program knows when to stop.

**my matrix pair.csv**: contains one pair of 32x32 (2^5) matrices (used in informal test)

**n = 8 matrix pair.csv**: contains one pair of 256x256 (2^8) matrices (used in informal investigation)

**n = 9 matrix pair.csv**: contains one pair of 512x512 (2^9) matrices (used in informal investigation)

**n = 10 matrix pair.csv**: contains one pair of 1024x1024 (2^10) matrices (used in informal investigation)

**my 20 matrix pairs.csv**: contains 20 pairs of 512x512 (2^9) matrices (used in formal investigation)

**TestMatrixMultiplication**: JUNIT4 test class that tests functionality of all methods in MatrixMultiplication

**InputTooLargeException**: Exception thrown when the user specifies a cutoff point in bfAndStrassensComboAlg() that is larger than the factor matrices

### __How to run the program__:

1. Create/load desired file into the project library (Can be empty or preloaded).
2. Use Runner class.
2. Initialize new Matrix Multiplication `m` object.
3. Declare three local matrices that will be used to store matrices read from CSV files.
4. If generating a CSV file (file should be empty, if not, it will be overwritten):
      5. Choose a size and store it in `int n`
   6. Store file name in `String fileName` (Make sure there are no typos)
   7. Choose how many total pairs you want to generate and store it in `int pairs`
   5. Call `generateCSV(n, fileName, pairs)` on `m`
6. Once csv file is generated or if loading a preloaded file, call `convertCSVtoMatrix(fileName, pairNum)`
      7. `pairNum` refers to the specific pair in the CSV file that you want to multiply together (if there is only one pair in the file, put 1)
8. Use `getmatrixA()` to store first matrix (read from CSV) in first local matrix
9. Use `getmatrixB()` to store second matrix (read from CSV) in second local matrix
10. Run `bruteForceAlg(A, B)` or `bfAndStrassensComboAlg(A, B, x)` and store result in third local matrix
    11. `x` is the cutoff point to be used in `bfAndStrassensComboAlg()` to determine what size matrix we will start using the brute force algorithm for.
12. You have the option of printing each of matrices using `printMatrix(int[][] matrix)`, if you want to see the results. However, matrices can get big really quick, so I would only do this on small matrices.


