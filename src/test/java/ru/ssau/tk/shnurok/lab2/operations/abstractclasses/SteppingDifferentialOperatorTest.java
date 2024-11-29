package ru.ssau.tk.shnurok.lab2.operations.abstractclasses;

import org.junit.jupiter.api.Test;
import ru.ssau.tk.shnurok.lab2.functions.coredefenitions.MathFunction;

import static org.junit.jupiter.api.Assertions.*;

public class SteppingDifferentialOperatorTest {

    // Подкласс для тестирования абстрактного класса
    private static class TestSteppingDifferentialOperator extends SteppingDifferentialOperator {
        public TestSteppingDifferentialOperator(double step) {
            super(step);
        }

        @Override
        public MathFunction derive(MathFunction function) {
            return null;
        }
    }

    @Test
    public void testConstructorValidStep() {
        // Arrange & Act
        TestSteppingDifferentialOperator operator = new TestSteppingDifferentialOperator(0.1);

        // Assert
        assertNotNull(operator);
    }

    @Test
    public void testConstructorZeroStep() {
        // Act & Assert
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            new TestSteppingDifferentialOperator(0);
        });
        assertEquals("step should be scalable positive", exception.getMessage());
    }

    @Test
    public void testConstructorNegativeStep() {
        // Act & Assert
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            new TestSteppingDifferentialOperator(-0.1);
        });
        assertEquals("step should be scalable positive", exception.getMessage());
    }

    @Test
    public void testConstructorNaNStep() {
        // Act & Assert
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            new TestSteppingDifferentialOperator(Double.NaN);
        });
        assertEquals("step should be scalable positive", exception.getMessage());
    }

    @Test
    public void testConstructorInfiniteStep() {
        // Act & Assert
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            new TestSteppingDifferentialOperator(Double.POSITIVE_INFINITY);
        });
        assertEquals("step should be scalable positive", exception.getMessage());
    }
}
