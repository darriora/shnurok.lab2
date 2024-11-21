package ru.ssau.tk.shnurok.lab2.concurrent;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.ssau.tk.shnurok.lab2.functions.coredefenitions.TabulatedFunction;
import ru.ssau.tk.shnurok.lab2.functions.realizations.ArrayTabulatedFunction;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class WriteTaskTest {

    private TabulatedFunction tabulatedFunction;
    private Thread writeThread;

    @BeforeEach
    void setUp() {
        double[] xValues = {0.0, 1.0, 2.0};
        double[] yValues = {0.0, 1.0, 4.0};
        tabulatedFunction = new ArrayTabulatedFunction(xValues, yValues);
        writeThread = new Thread(new WriteTask(tabulatedFunction, 10.0));
    }

    @Test
    void testWriteTask() throws InterruptedException {
        writeThread.start();
        writeThread.join();

        // Проверяем, что все значения y были успешно обновлены
        assertEquals(10.0, tabulatedFunction.getY(0));
        assertEquals(10.0, tabulatedFunction.getY(1));
        assertEquals(10.0, tabulatedFunction.getY(2));
    }

    @Test
    void testWriteTaskConcurrency() throws InterruptedException {
        // Запускаем несколько потоков записи одновременно
        Thread writeThread2 = new Thread(new WriteTask(tabulatedFunction, 20.0));

        writeThread.start();
        writeThread2.start();

        writeThread.join();
        writeThread2.join();

        // После выполнения потоков, будем проверять Y
        // Ожидаем, что на некоторых позициях bude 10.0, а на других 20.0
        assertTrue(tabulatedFunction.getY(0) == 10.0 || tabulatedFunction.getY(0) == 20.0);
        assertTrue(tabulatedFunction.getY(1) == 10.0 || tabulatedFunction.getY(1) == 20.0);
        assertTrue(tabulatedFunction.getY(2) == 10.0 || tabulatedFunction.getY(2) == 20.0);
    }
}