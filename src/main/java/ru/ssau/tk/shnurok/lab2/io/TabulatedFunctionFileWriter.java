package ru.ssau.tk.shnurok.lab2.io;

import ru.ssau.tk.shnurok.lab2.functions.coredefenitions.TabulatedFunction;
import ru.ssau.tk.shnurok.lab2.functions.realizations.ArrayTabulatedFunction;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Paths;

public class TabulatedFunctionFileWriter {

    public static void main(String[] args){

        try(BufferedWriter arrayFileWriter = new BufferedWriter(
                new FileWriter(Paths.get("output/array function.txt").toAbsolutePath().toString())
        );

        BufferedWriter lListFileWriter = new BufferedWriter(
                new FileWriter("output/linked list function.txt")
        )
        ){
            TabulatedFunction arrayFunction = new ArrayTabulatedFunction(
                    new double[]{0.0,0.52,0.69}, new double[]{0.0, 52, 69}
            );
            TabulatedFunction lListFunction = new ArrayTabulatedFunction(
                    new double[]{0.0,0.52,0.69}, new double[]{0.0, 52, 69}
            );

            FunctionsIO.writeTabulatedFunction(arrayFileWriter,arrayFunction);
            FunctionsIO.writeTabulatedFunction(lListFileWriter,lListFunction);
        }
        catch (IOException exception){
            exception.printStackTrace();
        }
    }
}
