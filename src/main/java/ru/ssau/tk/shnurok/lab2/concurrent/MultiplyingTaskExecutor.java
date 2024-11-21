package ru.ssau.tk.shnurok.lab2.concurrent;

import ru.ssau.tk.shnurok.lab2.functions.coredefenitions.TabulatedFunction;
import ru.ssau.tk.shnurok.lab2.functions.realizations.LinkedListTabulatedFunction;
import ru.ssau.tk.shnurok.lab2.functions.realizations.UnitFunction;

import java.util.ArrayList;
import java.util.List;

public class MultiplyingTaskExecutor {
    public void main(String[] args) throws InterruptedException{
        TabulatedFunction tabulatedFunction =
                new LinkedListTabulatedFunction(new UnitFunction(),1,1000,1000);

        List<Thread> threads = new ArrayList<>();

        for (int i = 0; i <10; ++i){
            MultiplyingTask multiplyingTask = new MultiplyingTask(tabulatedFunction);
            threads.add(new Thread(multiplyingTask));
        }

        for (Thread thread: threads) thread.start();

        Thread.sleep(200);

        System.out.println("Function after multiplying");
        for (int i = 0; i<tabulatedFunction.getCount(); ++i){
            System.out.printf("x: %f, y: %f%n", tabulatedFunction.getX(i),tabulatedFunction.getY(i));
        }
    }
}
