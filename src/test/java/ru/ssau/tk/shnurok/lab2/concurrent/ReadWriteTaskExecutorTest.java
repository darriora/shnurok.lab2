package ru.ssau.tk.shnurok.lab2.concurrent;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.ssau.tk.shnurok.lab2.functions.coredefenitions.TabulatedFunction;
import ru.ssau.tk.shnurok.lab2.functions.realizations.ConstantFunction;
import ru.ssau.tk.shnurok.lab2.functions.realizations.LinkedListTabulatedFunction;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ReadWriteTaskExecutorTest {

    private TabulatedFunction tabulatedFunction;
    private Thread readThread;
    private Thread writeThread;

    @BeforeEach
    void setUp() {
        tabulatedFunction = new LinkedListTabulatedFunction(new ConstantFunction(-1), 1, 10, 10);
        readThread = new Thread(new ReadTask(tabulatedFunction));
        writeThread = new Thread(new WriteTask(tabulatedFunction, 0.5));
    }

    @Test
    void testReadWriteExecution() throws InterruptedException {
        // Запускаем потоки
        writeThread.start();
        readThread.start();

        // Ждем завершения потоков
        writeThread.join();
        readThread.join();

        // Проверяем, что все значения y обновлены
        for (int i = 0; i < tabulatedFunction.getCount(); i++) {
            assertEquals(0.5, tabulatedFunction.getY(i), 0.001);
        }
    }

    @Test
    void testReadAfterWrite() throws InterruptedException {
        // Запускаем поток записи, затем чтение
        writeThread.start();
        writeThread.join(); // Ждем завершения записи

        readThread.start();
        readThread.join(); // Ждем завершения чтения

        // Убеждаемся, что после записи прочитанные значения корректны
        for (int i = 0; i < tabulatedFunction.getCount(); i++) {
            assertEquals(0.5, tabulatedFunction.getY(i), 0.001);
        }
    }

    @Test
    void testConcurrentReadAndWrite() throws InterruptedException {
        // Убедимся, что потоки завершатся корректно
        readThread.start();
        writeThread.start();

        readThread.join();
        writeThread.join();

        // Поскольку writeTask устанавливает значения, проверяем корректность
        for (int i = 0; i < tabulatedFunction.getCount(); i++) {
            double value = tabulatedFunction.getY(i);
            assertTrue(value == 0.5 || value == -1, "Непредвиденное значение: " + value);
        }
    }

}