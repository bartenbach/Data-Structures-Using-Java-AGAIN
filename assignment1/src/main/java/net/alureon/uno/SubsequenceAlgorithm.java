package net.alureon.uno;

/**
 * This class holds the various subsequence algorithms for the assignment.
 */
final class SubsequenceAlgorithm {

    /**
     * Static utility class.
     */
    private SubsequenceAlgorithm() {
    }

    /**
     * Performs the second subsequence algorithm on the supplied array of integers.
     *
     * @param input The input array to find the max subsequence of.
     * @return The max contiguous subsequence in the array.
     */
    static AlgorithmSolution algorithmTwo(final int[] input) {
        int startingIndex = 0;
        int endingIndex = 0;
        int maxSum = 0;
        for (int i = 0; i < input.length; i++) {
            int thisSum = 0;
            for (int j = i; j < input.length; j++) {
                thisSum += input[j];
                if (thisSum > maxSum) {
                    startingIndex = i;
                    endingIndex = j;
                    maxSum = thisSum;
                }
            }
        }
        return new AlgorithmSolution(maxSum, startingIndex, endingIndex);
    }

    /**
     * Uses algorithm four to find the maximum subsequence of a given integer array.
     *
     * @param input The input array of integers.
     * @return The maximum subsequence found.
     */
    static AlgorithmSolution algorithmFour(final int[] input) {
        int maxSum = 0;
        int thisSum = 0;
        int currentStartingIndex = 0;
        int startingIndex = 0;
        int endingIndex = 0;
        boolean started = false;
        for (int j = 0; j < input.length; j++) {
            if (input[j] > 0 && !started) {
                started = true;
                currentStartingIndex = j;
            }
            thisSum += input[j];
            if (thisSum > maxSum) {
                endingIndex = j;
                startingIndex = currentStartingIndex;
                maxSum = thisSum;
            } else if (thisSum < 0) {
                if (j + 1 < input.length) {
                    currentStartingIndex = j + 1;
                }
                started = false;
                thisSum = 0;
            }
        }
        return new AlgorithmSolution(maxSum, startingIndex, endingIndex);
    }

    /**
     * Performs the third subsequence algorithm on a given input array.
     *
     * @param input The input array of integers.
     * @param left  The left index used for computing.
     * @param right The right index to use for computing.
     * @return The max subsequence between the given indices.
     */
    static AlgorithmSolution algorithmThree(final int[] input, final int left, final int right) {
        if (left == right) {
            if (input[left] > 0) {
                return new AlgorithmSolution(input[left], left, left);
            }
            return new AlgorithmSolution(0, 0, 0); // return 0?
        }

        int center = (left + right) / 2;
        AlgorithmSolution maxLeftSum = algorithmThree(input, left, center);
        AlgorithmSolution maxRightSum = algorithmThree(input, center + 1, right);

        int maxLeftBorderSum = 0, leftBorderSum = 0;
        int startingIndex = input.length;
        int endingIndex = input.length;
        int currentEndingIndex = 0;
        boolean started = false;
        for (int i = center; i >= left; i--) {
            if (!started && input[i] > 0) {
                currentEndingIndex = i;
                started = true;
            }
            leftBorderSum += input[i];
            if (leftBorderSum > maxLeftBorderSum) {
                startingIndex = i;
                endingIndex = currentEndingIndex;
                maxLeftBorderSum = leftBorderSum;
            } else if (leftBorderSum < 0) {
                if (i - 1 > 0) {
                    currentEndingIndex = i - 1;
                }
                started = false;
            }
        }

        int maxRightBorderSum = 0, rightBorderSum = 0;
        for (int i = center + 1; i <= right; i++) {
            rightBorderSum += input[i];
            if (rightBorderSum > maxRightBorderSum) {
                endingIndex = i;
                maxRightBorderSum = rightBorderSum;
            }
        }
        if (maxLeftSum.getMaximumSum() > maxRightSum.getMaximumSum()
                && maxLeftSum.getMaximumSum() > (maxLeftBorderSum + maxRightBorderSum)) {
            return new AlgorithmSolution(maxLeftSum.getMaximumSum(), maxLeftSum.getStartingIndex(),
                    maxLeftSum.getEndingIndex());
        } else if (maxRightSum.getMaximumSum() > maxLeftSum.getMaximumSum()
                && maxRightSum.getMaximumSum() > maxLeftBorderSum + maxRightBorderSum) {
            return new AlgorithmSolution(maxRightSum.getMaximumSum(), maxRightSum.getStartingIndex(),
                    maxRightSum.getEndingIndex());
        } else {
            return new AlgorithmSolution(maxLeftBorderSum + maxRightBorderSum, startingIndex, endingIndex);
        }
    }
}
