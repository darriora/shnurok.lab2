package ru.ssau.tk.shnurok.lab2.functions.realizations;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class SqrFunctionTest {

    @Test
    public void testApply_ReturnsValidResult(){
        SqrFunction sqrFunction = new SqrFunction();

        assertEquals(1,sqrFunction.apply(1));
        assertEquals(1,sqrFunction.apply(-1));
        assertEquals(4,sqrFunction.apply(2));
        assertEquals(0,sqrFunction.apply(0));
        assertEquals(1.44,sqrFunction.apply(1.2));
    }
}