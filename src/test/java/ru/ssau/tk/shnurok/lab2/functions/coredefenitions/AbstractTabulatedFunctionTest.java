package ru.ssau.tk.shnurok.lab2.functions.coredefenitions;

import org.junit.jupiter.api.Test;
import ru.ssau.tk.shnurok.lab2.functions.realizations.LinkedListTabulatedFunction;

import static org.junit.jupiter.api.Assertions.assertEquals;

class AbstractTabulatedFunctionTest {

    @Test
    public void testToString() {
        // Создаем табулированную функцию
        double[] xValues = {0.0, 0.5, 1.0};
        double[] yValues = {0.0, 0.25, 1.0};
        LinkedListTabulatedFunction function = new LinkedListTabulatedFunction(xValues, yValues);

        // Ожидаемая строка
        String expectedString = "LinkedListTabulatedFunction size = 3\n[0.0; 0.0]\n[0.5; 0.25]\n[1.0; 1.0]\n";

        // Проверяем строковое представление
        assertEquals(expectedString, function.toString());
    }
}