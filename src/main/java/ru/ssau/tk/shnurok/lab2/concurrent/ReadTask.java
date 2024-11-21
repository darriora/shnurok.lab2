package ru.ssau.tk.shnurok.lab2.concurrent;

import ru.ssau.tk.shnurok.lab2.functions.coredefenitions.TabulatedFunction;

public class ReadTask implements Runnable {

    private final TabulatedFunction func;

    public ReadTask(TabulatedFunction func) {
        this.func = func;
    }

    @Override
    public void run() {
        for (int i = 0; i < func.getCount(); ++i) {
            synchronized (func) {
                double x = func.getX(i);
                double y = func.getY(i);
                System.out.printf("After read: i = %d, x = %f, y = %f%n\n", i, x, y);
            }
        }
    }
}
