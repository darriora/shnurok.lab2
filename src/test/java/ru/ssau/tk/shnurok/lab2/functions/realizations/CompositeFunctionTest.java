package ru.ssau.tk.shnurok.lab2.functions.realizations;

import org.junit.jupiter.api.Test;
import ru.ssau.tk.shnurok.lab2.functions.coredefenitions.MathFunction;

import static org.junit.jupiter.api.Assertions.assertEquals;


class CompositeFunctionTest {
    @Test
    public void testSimpleFunctions() {
        MathFunction f = x -> x * x;
        MathFunction g = x -> x + 1;
        CompositeFunction h = new CompositeFunction(f, g);
        assertEquals(2.0, h.apply(1.0), 0.01);
    }



    @Test
    public void testCompositeFunctions() {
        MathFunction f = x -> x * x;
        MathFunction g = x -> x + 1;
        CompositeFunction h1 = new CompositeFunction(f, g);
        CompositeFunction h2 = new CompositeFunction(h1, f);
        CompositeFunction h3 = new CompositeFunction(h1, g);
        assertEquals(4.0, h2.apply(1.0), 0.01);
        assertEquals(3.0, h3.apply(1.0), 0.01);
    }

    @Test
    public void testSameFunction() {
        MathFunction f = x -> x * x;
        CompositeFunction h = new CompositeFunction(f, f);
        assertEquals(1.0, h.apply(1.0), 0.01);
    }

}