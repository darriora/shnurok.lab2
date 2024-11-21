package ru.ssau.tk.shnurok.lab2.concurrent;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.ssau.tk.shnurok.lab2.functions.coredefenitions.TabulatedFunction;
import ru.ssau.tk.shnurok.lab2.functions.realizations.ArrayTabulatedFunction;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MultiplyingTaskTest {

    private TabulatedFunction function;

    @BeforeEach
    public void setUp() {
        // Создаем тестовую функцию с начальными значениями
        double[] xValues = {0.0, 1.0, 2.0, 3.0};
        double[] yValues = {0.0, 1.0, 2.0, 3.0};
        function = new ArrayTabulatedFunction(xValues, yValues);
    }

    @Test
    public void testMultiplyingTask() throws InterruptedException {
        // Создаем задачу умножения
        MultiplyingTask task = new MultiplyingTask(function);

        // Запускаем поток для выполнения задачи
        Thread thread = new Thread(task);
        thread.start();

        // Ждем завершения потока
        thread.join();

        // Проверяем, что значения функции были удвоены
        for (int i = 0; i < function.getCount(); i++) {
            assertEquals(i * 2, function.getY(i), 0.001);
        }
    }
}
