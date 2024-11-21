package ru.ssau.tk.shnurok.lab2.io;

import ru.ssau.tk.shnurok.lab2.functions.coredefenitions.TabulatedFunction;
import ru.ssau.tk.shnurok.lab2.functions.realizations.ArrayTabulatedFunction;
import ru.ssau.tk.shnurok.lab2.functions.realizations.LinkedListTabulatedFunction;

import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class TabulatedFunctionFileOutputStream {

    public static void main(String[] args) {
        try (
                BufferedOutputStream arrayOutputStream = new BufferedOutputStream(new FileOutputStream("output/array_function.bin"));
                BufferedOutputStream linkedListOutputStream = new BufferedOutputStream(new FileOutputStream("output/linked_list_function.bin"))
        ) {
            // Создаем табулированные функции
            TabulatedFunction arrayFunction = new ArrayTabulatedFunction(new double[]{0.0, 0.5, 1.0}, new double[]{0.0, 0.25, 1.0});
            TabulatedFunction linkedListFunction = new LinkedListTabulatedFunction(new double[]{0.0, 0.5, 1.0}, new double[]{0.0, 0.25, 1.0});

            // Записываем функции в соответствующие потоки
            FunctionsIO.writeTabulatedFunction(arrayOutputStream, arrayFunction);
            FunctionsIO.writeTabulatedFunction(linkedListOutputStream, linkedListFunction);
        } catch (IOException ex) {
            ex.printStackTrace(); // Обработка исключения
        }
    }
}
