package ru.ssau.tk.shnurok.lab2.functions.realizations;

import org.junit.jupiter.api.Test;
import ru.ssau.tk.shnurok.lab2.functions.factory.ArrayTabulatedFunctionFactory;
import ru.ssau.tk.shnurok.lab2.functions.factory.LinkedListTabulatedFunctionFactory;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class StrictTabulatedFunctionTest {

    @Test
    public void testArrayTabulatedFunctionFactoryCreateStrict() {
        ArrayTabulatedFunctionFactory factory = new ArrayTabulatedFunctionFactory();
        double[] xValues = {1.0, 2.0, 3.0};
        double[] yValues = {1.0, 4.0, 9.0};

        StrictTabulatedFunction strictFunction = (StrictTabulatedFunction) factory.createStrict(xValues, yValues);

        assertEquals(3, strictFunction.getCount());
        assertEquals(4.0, strictFunction.apply(2.0));
        assertThrows(UnsupportedOperationException.class, () -> strictFunction.apply(1.5));
    }

    @Test
    public void testLinkedListTabulatedFunctionFactoryCreateStrict() {
        LinkedListTabulatedFunctionFactory factory = new LinkedListTabulatedFunctionFactory();
        double[] xValues = {1.0, 2.0, 3.0};
        double[] yValues = {1.0, 4.0, 9.0};

        StrictTabulatedFunction strictFunction = (StrictTabulatedFunction) factory.createStrict(xValues, yValues);

        assertEquals(3, strictFunction.getCount());
        assertEquals(9.0, strictFunction.apply(3.0));
        assertThrows(UnsupportedOperationException.class, () -> strictFunction.apply(0.0));
    }
}
