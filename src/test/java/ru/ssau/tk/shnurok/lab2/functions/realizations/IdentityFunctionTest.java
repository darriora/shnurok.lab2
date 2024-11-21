package ru.ssau.tk.shnurok.lab2.functions.realizations;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class IdentityFunctionTest {
    @Test
    public void testApply_ReturnValidX() {
        IdentityFunction func = new IdentityFunction();

        assertEquals(1.0, func.apply(1.0));
        assertEquals(-3.0, func.apply(-3.0));
        assertEquals(6.3, func.apply(6.3));
        assertEquals(8, func.apply(8.0));
        assertEquals(0.5, func.apply(0.5));
    }

}