package ru.ssau.tk.shnurok.lab2.operations.implementations;

import org.junit.jupiter.api.Test;
import ru.ssau.tk.shnurok.lab2.functions.coredefenitions.MathFunction;
import ru.ssau.tk.shnurok.lab2.operations.implementations.LeftSteppingDifferentialOperator;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class LeftSteppingDifferentialOperatorTest {

    @Test
    public void testDerive() {
        double step = 0.1;
        LeftSteppingDifferentialOperator operator = new LeftSteppingDifferentialOperator(step);

        MathFunction function = x ->x*x;

        MathFunction derivative = operator.derive(function);
        assertEquals(1.9, derivative.apply(1.0),1e-5); // f'(1) = 2
        assertEquals(3.9, derivative.apply(2.0),1e-5); // f'(2) = 4
        assertEquals(5.9, derivative.apply(3.0),1e-5); // f'(3) = 6
    }
}
