package ru.ssau.tk.shnurok.lab2.io;

import ru.ssau.tk.shnurok.lab2.functions.coredefenitions.TabulatedFunction;
import ru.ssau.tk.shnurok.lab2.functions.factory.ArrayTabulatedFunctionFactory;
import ru.ssau.tk.shnurok.lab2.functions.factory.LinkedListTabulatedFunctionFactory;
import ru.ssau.tk.shnurok.lab2.functions.factory.TabulatedFunctionFactory;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class TabulatedFunctionFileReader {
    public static void main(String[] args){
        String path = "input/function.txt";

        try (BufferedReader arrFileReader = new BufferedReader(
                new FileReader(path)
        );
             BufferedReader lListReader = new BufferedReader(
                     new FileReader(path)
             )
        ){
            TabulatedFunctionFactory arrFactory = new ArrayTabulatedFunctionFactory();
            TabulatedFunctionFactory lListFactory = new LinkedListTabulatedFunctionFactory();

            TabulatedFunction arrFunction = FunctionsIO.readTabulatedFunction(arrFileReader,arrFactory);
            TabulatedFunction lListFunction = FunctionsIO.readTabulatedFunction(lListReader,lListFactory);

            System.out.println(arrFunction.toString());
            System.out.println(lListFunction.toString());
        } catch (IOException exception){
            exception.printStackTrace();
        }
    }
}
