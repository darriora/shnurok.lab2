package ru.ssau.tk.shnurok.lab2.functions.realizations;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class UnitFunctionTest {

    @Test
    public void testApply_ReturnsValidUnit(){
        UnitFunction unitFunction = new UnitFunction();
        assertEquals(1,unitFunction.apply(52));
    }
}