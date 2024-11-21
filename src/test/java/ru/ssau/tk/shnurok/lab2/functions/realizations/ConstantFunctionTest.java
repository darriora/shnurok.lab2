package ru.ssau.tk.shnurok.lab2.functions.realizations;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ConstantFunctionTest {

    @Test
    public void testApply_ReturnsValidConstant(){
        ConstantFunction constantFunction = new ConstantFunction(52);

        assertEquals(52,constantFunction.apply(17));
    }

    @Test
    public void testApply_GetValidConstant(){
        ConstantFunction constantFunction = new ConstantFunction(5.2);
        assertEquals(5.2,constantFunction.getConstant());
    }
}