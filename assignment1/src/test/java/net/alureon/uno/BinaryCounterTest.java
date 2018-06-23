package net.alureon.uno;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BinaryCounterTest {

    @Test
    public void testGetBinaryOnes() {
        assertEquals(1, BinaryCounter.getBinaryOnes(1));
        assertEquals(1, BinaryCounter.getBinaryOnes(2));
        assertEquals(2, BinaryCounter.getBinaryOnes(3));
        assertEquals(1, BinaryCounter.getBinaryOnes(4));
        assertEquals(2, BinaryCounter.getBinaryOnes(5));
        assertEquals(2, BinaryCounter.getBinaryOnes(6));
        assertEquals(3, BinaryCounter.getBinaryOnes(7));
        assertEquals(1, BinaryCounter.getBinaryOnes(8));
        assertEquals(2, BinaryCounter.getBinaryOnes(9));
        assertEquals(3, BinaryCounter.getBinaryOnes(11));
        assertEquals(2, BinaryCounter.getBinaryOnes(12));
        assertEquals(1, BinaryCounter.getBinaryOnes(64));
        assertEquals(8, BinaryCounter.getBinaryOnes(255));
    }

}