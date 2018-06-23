package net.alureon.uno;

/**
 * Static class with methods related to binary representations.
 */
final class BinaryCounter {

    /**
     * Static utility class.
     */
    private BinaryCounter() { }

    /**
     * Returns the number of binary 1's in the binary representation of the supplied integer.
     * @param input The input integer
     * @return the number of binary ones, or zero if negative.
     */
    static int getBinaryOnes(final int input) {
        if (input > 0) {
            if (input % 2 == 0) {
                return getBinaryOnes(input / 2);
            }
            return 1 + getBinaryOnes(input - 1);
        }
        return 0;
    }
}
