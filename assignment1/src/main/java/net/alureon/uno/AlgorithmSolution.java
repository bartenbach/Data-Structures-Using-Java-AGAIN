package net.alureon.uno;

/**
 * The class represents an object to contain the solution a given algorithm finds to the subsequence problem.
 */
public class AlgorithmSolution {

    /**
     * The maximum subsequence sum found by the algorithm.
     */
    private int maximumSum;
    /**
     * The starting index of the maximum subsequence.
     */
    private int startingIndex;
    /**
     * The ending index of the maximum subsequence.
     */
    private int endingIndex;

    /**
     * The contructor for instantiating an AlgorithmSolution object.
     * @param maximumSum The maximum subsequence sum that the algorithm found.
     * @param startingIndex The starting index of the maximum subsequence.
     * @param endingIndex The ending index of the maximum subsequence.
     */
    public AlgorithmSolution(final int maximumSum, final int startingIndex, final int endingIndex) {
        this.maximumSum = maximumSum;
        this.startingIndex = startingIndex;
        this.endingIndex = endingIndex;
    }

    /**
     * Returns the maximum sum field.
     * @return The maximum sum.
     */
    public int getMaximumSum() {
        return this.maximumSum;
    }

    /**
     * Returns the starting index of the subsequence.
     * @return the starting index of the subsequence.
     */
    public int getStartingIndex() {
        return this.startingIndex;
    }

    /**
     * Returns the ending index of the subsequence.
     * @return the ending index of the subsequence.
     */
    public int getEndingIndex() {
        return this.endingIndex;
    }
}
