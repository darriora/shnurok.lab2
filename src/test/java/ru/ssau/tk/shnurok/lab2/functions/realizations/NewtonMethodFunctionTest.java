package ru.ssau.tk.shnurok.lab2.functions.realizations;

import org.junit.jupiter.api.Test;
import ru.ssau.tk.shnurok.lab2.functions.coredefenitions.MathFunction;

import static org.junit.jupiter.api.Assertions.assertEquals;

class NewtonMethodFunctionTest {

    @Test
    public void testRootFindingPositive() { //простая функция
        MathFunction function = x -> x * x - 4;
        NewtonMethodFunction newton = new NewtonMethodFunction(function, 1E-7);
        double root = newton.apply(1);
        assertEquals(2.0, root, 1E-6);
    }

    @Test
    public void testRootFindingNegative() { //отрицательное значение
        MathFunction function = x -> x * x - 4;
        NewtonMethodFunction newton = new NewtonMethodFunction(function, 1E-7);
        double root = newton.apply(-1);
        assertEquals(-2.0, root, 1E-6);
    }

    @Test
    public void testNonConvergence() {  // производная 0 в 0 и метод не сходится
        MathFunction function = x -> x * x;
        NewtonMethodFunction newton = new NewtonMethodFunction(function, 1E-7);
        double root = newton.apply(0);
        assertEquals(Double.NaN, root, 0);
    }

    @Test
    public void testHighPrecision() {  //более сложная функция
        MathFunction function = x -> Math.cos(x) - x;
        NewtonMethodFunction newton = new NewtonMethodFunction(function, 1E-10);
        double root = newton.apply(0.5);
        assertEquals(0.7390851332, root, 1E-10); // известный корень
    }

}