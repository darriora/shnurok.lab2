package ru.ssau.tk.shnurok.lab2.concurrent;

import ru.ssau.tk.shnurok.lab2.functions.coredefenitions.TabulatedFunction;
import ru.ssau.tk.shnurok.lab2.functions.realizations.ConstantFunction;
import ru.ssau.tk.shnurok.lab2.functions.realizations.LinkedListTabulatedFunction;

public class ReadWriteTaskExecutor {
    public static void main(String[] args) {
        TabulatedFunction linkedListTabulatedFunction =
                new LinkedListTabulatedFunction(new ConstantFunction(-1), 1, 1000, 1000);

        Thread readThread = new Thread(new ReadTask(linkedListTabulatedFunction));
        Thread writeThread = new Thread(new WriteTask(linkedListTabulatedFunction, 0.5));

        readThread.start();
        writeThread.start();
    }
}
