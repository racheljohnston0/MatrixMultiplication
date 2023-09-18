# CSC310: Design and Analysis of Algorithms Programming Assignment: Matrix Multiplication
This programming assignment was designed for students to understand the practical implementations for Strassen's algorithm for matrix multiplication, which usually switches to the brute-force method after the matrix sizes become smaller than some crossover point. We were to run an experiment to determine such a crossover point for our personal machine.
The goal was to find a reasonable, and possibly optimal, array size for Strassen's to switch from a recursive solution back to the brute force method.

The requirements for the assignment were as follows:
1. Write your code in either Java, C, or Python (I chose Java).
2. Implement the brute force method of matrix multiplication.
3. Implement Strassen’s algorithm such that is recurses fully down to 1x1 matrices.
4. Implement a combined algorithm that uses Strassen’s algorithm until the array is of some size 2n and then completes the smaller matrix multiplications using brute force. Make sure the user can supply the value of n.
5. Code should accept input from a file that contains two 2nx2n matrices (see sample file)
6. Run an informal investigation to determine how large the matrices need to be before the theoretical advantages of Strassen's algorithm start to show.
7. Once you have a fixed size, start running formal experiments. This time, your factor is the cutoff point, not the matrix dimension. Determine the "sweet spot" for the cutoff by looking for the maximum runtime advantage.
8. Report your results in a spreadsheet that calculates the average and standard deviation across the 20 runs time for each size and each method.
9. Justify your choice of cutoff point by graphing the results. Be sure to include the standard deviations as error bars so you have some idea of when the averages actually separate.

The directory contains a README.md containing all of the descriptions of the methods and files.
