package org.example;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

class MyBigNumberTest {

    private MyBigNumber myBigNumber;

    @BeforeEach
    void setUp() {
        myBigNumber = new MyBigNumber();
    }

    @Test
    public void testSumWithEqualLengthNumbers() {
        assertEquals("1357", myBigNumber.sum("1234", "0123"));
    }

    @Test
    void testSumWithDifferentLengthNumbers() {
        assertEquals("13023", myBigNumber.sum("12345", "678"));
    }

    @Test
    void testSumWithCarry() {
        assertEquals("10001", myBigNumber.sum("9999", "2"));
    }

    @Test
    void testSumWithLeadingZeros() {
        assertEquals("1234", myBigNumber.sum("0001234", "0000000"));
    }


    @Test
    void testSumWithZeros() {
        assertEquals("0", myBigNumber.sum("0", "0"));
    }

    @Test
    void testSumWithInvalidInput() {
        assertNull(myBigNumber.sum("123", "abc"));
    }

    @Test
    void testSumWithEmptyStrings() {
        assertNull(myBigNumber.sum("", ""));
    }

    @Test
    void testSumWithNegativeNumbers() {
        assertNull(myBigNumber.sum("-123", "456"));
    }
}