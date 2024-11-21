package ru.ssau.tk.shnurok.lab2.functions.realizations;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ZeroFunctionTest {

    @Test
    public void testApply_returnValidZero(){
        ZeroFunction zeroFunction = new ZeroFunction();
        assertEquals(0,zeroFunction.apply(52));
    }

}