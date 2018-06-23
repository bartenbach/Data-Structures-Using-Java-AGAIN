/*
 * Program Title: Algorithm Modification
 * Author:  Blake Bartenbach
 * Class: CSCI3320, Summer 2018
 * Assignment #1
 */
package net.alureon.uno;

import java.util.Random;
import java.util.Scanner;

/**
 * This class stores the various algorithms for the assignment and also serves as the main entry point
 * of the program.
 */
public final class AlgorithmModification {

    /**
     * The upper bound for the random numbers generated in this program's array.
     */
    private static final int MAX_RANDOM_UPPER_BOUND = 9999;
    /**
     * The lower bound for the random numbers generated in this program's array.
     */
    private static final int MIN_RANDOM_LOWER_BOUND = -9999;
    /**
     * If the size of the array is lower than this number, we will print out the randomly generated array.
     */
    private static final int MAX_SIZE_TO_PRINT_ARRAY = 50;

    /**
     * Not an object.
     */
    private AlgorithmModification() {
    }

    /**
     * The main entry point of the program.
     *
     * @param args The args from the command line.
     */
    public static void main(final String[] args) {
        // Get the problem size from the user.
        int problemSizeFromUser = getProblemSizeFromUser();

        // Generate array of random integers.
        int[] randomIntegers = getRandomlyGeneratedArray(problemSizeFromUser);

        // The assignment explicitly says we only print the array and execute if the input value is less than
        // this static constant.
        if (problemSizeFromUser < MAX_SIZE_TO_PRINT_ARRAY) {
            printArray(randomIntegers);
        }
        long startTime, endTime;
        // run second algorithm
        System.out.println("Algorithm 2:");
        startTime = System.currentTimeMillis();
        AlgorithmSolution algorithmSolution2 = SubsequenceAlgorithm.algorithmTwo(randomIntegers);
        endTime = System.currentTimeMillis();
        System.out.println("MaxSum: " + algorithmSolution2.getMaximumSum() + ", S_index: "
                + algorithmSolution2.getStartingIndex() + ", E_Index: " + algorithmSolution2.getEndingIndex());
        System.out.println("Execution Time: " + (endTime - startTime) + " milliseconds\n");

        // run third algorithm
        System.out.println("Algorithm 3:");
        startTime = System.currentTimeMillis();
        AlgorithmSolution algorithmSolution3 = SubsequenceAlgorithm.algorithmThree(randomIntegers, 0,
                randomIntegers.length - 1);
        endTime = System.currentTimeMillis();
        System.out.println("MaxSum: " + algorithmSolution3.getMaximumSum() + ", S_index: "
                + algorithmSolution3.getStartingIndex() + ", E_Index: " + algorithmSolution3.getEndingIndex());
        System.out.println("Execution Time: " + (endTime - startTime) + " milliseconds\n");

        // run fourth algorithm
        System.out.println("Algorithm 4:");
        startTime = System.currentTimeMillis();
        AlgorithmSolution algorithmSolution4 = SubsequenceAlgorithm.algorithmFour(randomIntegers);
        endTime = System.currentTimeMillis();
        System.out.println("MaxSum: " + algorithmSolution4.getMaximumSum() + ", S_index: "
                + algorithmSolution4.getStartingIndex() + ", E_Index: " + algorithmSolution4.getEndingIndex());
        System.out.println("Execution Time: " + (endTime - startTime) + " milliseconds\n");
    }

    /**
     * Generates and returns an array of N size of randomly generated integers within the assigned values.
     *
     * @param n the size of the array to generate
     * @return The randomly generated array within defined constraints
     */
    private static int[] getRandomlyGeneratedArray(final int n) {
        int[] randomIntegers = new int[n];
        Random rdm = new Random();
        for (int i = 0; i < n; i++) {
            int randomInt = rdm.nextInt((MAX_RANDOM_UPPER_BOUND - MIN_RANDOM_LOWER_BOUND) + 1)
                    + MIN_RANDOM_LOWER_BOUND;
            randomIntegers[i] = randomInt;
        }
        return randomIntegers;
    }

    /**
     * Prints an array out to the console in a user readable format.
     *
     * @param array The array to print out.
     */
    private static void printArray(final int[] array) {
        StringBuilder sb = new StringBuilder();
        sb.append("[ ");
        for (int x : array) {
            sb.append(x).append(", ");
        }
        sb.deleteCharAt(sb.length() - 2); // remove extra ', ' at the end
        sb.append("]\n");
        System.out.println(sb.toString());
    }

    /**
     * Get the problem size from the user.
     *
     * @return The problem size entered by the user.
     */
    private static int getProblemSizeFromUser() {
        System.out.print("Please enter the size of the problem (N): ");
        Scanner scanner = new Scanner(System.in);
        return scanner.nextInt();
    }


}
