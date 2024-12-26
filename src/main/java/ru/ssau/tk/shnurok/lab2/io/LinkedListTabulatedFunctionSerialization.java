package ru.ssau.tk.shnurok.lab2.io;

import ru.ssau.tk.shnurok.lab2.functions.coredefenitions.TabulatedFunction;
import ru.ssau.tk.shnurok.lab2.functions.realizations.LinkedListTabulatedFunction;
import ru.ssau.tk.shnurok.lab2.service.operations.implementations.TabulatedDifferentialOperator;

import java.io.*;

public class LinkedListTabulatedFunctionSerialization {
    public static void main(String[] args) {
        String filePath = "output/serialized_linked_list_functions.bin";

        // Создаем табулированную функцию типа LinkedListTabulatedFunction
        double[] xValues = {1.0, 2.0, 3.0};
        double[] yValues = {1.0, 4.0, 9.0};
        LinkedListTabulatedFunction originalFunction = new LinkedListTabulatedFunction(xValues, yValues);

        // Создаем оператор для вычисления производных
        TabulatedDifferentialOperator operator = new TabulatedDifferentialOperator();

        TabulatedFunction firstDerivative = operator.derive(originalFunction);
        TabulatedFunction secondDerivative = operator.derive(firstDerivative);

        // Сериализация всех трех функций
        try (FileOutputStream fileOutputStream = new FileOutputStream(filePath);
             BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(fileOutputStream)) {

            FunctionsIO.serialize(bufferedOutputStream, originalFunction);
            FunctionsIO.serialize(bufferedOutputStream, firstDerivative);
            FunctionsIO.serialize(bufferedOutputStream, secondDerivative);


        } catch (IOException e) {
            e.printStackTrace();
        }

        // Десериализация всех трех функций
        try (FileInputStream fileInputStream = new FileInputStream(filePath);
             BufferedInputStream bufferedInputStream = new BufferedInputStream(fileInputStream)) {

            TabulatedFunction deserializedOriginal = FunctionsIO.deserialize(bufferedInputStream);
            TabulatedFunction deserializedFirstDerivative = FunctionsIO.deserialize(bufferedInputStream);
            TabulatedFunction deserializedSecondDerivative = FunctionsIO.deserialize(bufferedInputStream);

            System.out.println("Десериализованная оригинальная функция:");
            System.out.println(deserializedOriginal.toString());

            System.out.println("Десериализованная первая производная:");
            System.out.println(deserializedFirstDerivative.toString());

            System.out.println("Десериализованная вторая производная:");
            System.out.println(deserializedSecondDerivative.toString());

        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

}
