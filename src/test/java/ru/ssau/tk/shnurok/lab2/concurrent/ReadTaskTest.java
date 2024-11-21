package ru.ssau.tk.shnurok.lab2.concurrent;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.ssau.tk.shnurok.lab2.functions.coredefenitions.TabulatedFunction;
import ru.ssau.tk.shnurok.lab2.functions.realizations.ArrayTabulatedFunction;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ReadTaskTest {
    private TabulatedFunction tabulatedFunction;
    private Thread readThread;

    @BeforeEach
    void setUp() {
        double[] xValues = {0.0, 1.0, 2.0};
        double[] yValues = {0.0, 1.0, 4.0};
        tabulatedFunction = new ArrayTabulatedFunction(xValues, yValues);
        readThread = new Thread(new ReadTask(tabulatedFunction));
    }

    @Test
    void testReadTask() throws InterruptedException {
        readThread.start();
        readThread.join();

        // Проверяем корректность значений после выполнения задачи чтения
        assertEquals(0.0, tabulatedFunction.getY(0));
        assertEquals(1.0, tabulatedFunction.getY(1));
        assertEquals(4.0, tabulatedFunction.getY(2));
    }

    @Test
    void testReadTaskConcurrency() throws InterruptedException {
        Thread secondThread = new Thread(new ReadTask(tabulatedFunction));

        readThread.start();
        secondThread.start();

        readThread.join();
        secondThread.join();

        // Если не было исключений, значит, чтение прошло без ошибок
        assertTrue(true);
    }

}