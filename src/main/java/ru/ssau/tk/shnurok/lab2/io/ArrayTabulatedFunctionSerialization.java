package ru.ssau.tk.shnurok.lab2.io;

import ru.ssau.tk.shnurok.lab2.functions.coredefenitions.TabulatedFunction;
import ru.ssau.tk.shnurok.lab2.functions.realizations.ArrayTabulatedFunction;
import ru.ssau.tk.shnurok.lab2.service.operations.implementations.TabulatedDifferentialOperator;

import java.io.*;

public class ArrayTabulatedFunctionSerialization {
    public static void main(String[] args){
        String filePath = "output/serialized array functions.bin";

        TabulatedFunction arrTabFunction = new ArrayTabulatedFunction(new double[]{5.2,6.9,9.11}, new double[]{52,69,911});

        TabulatedDifferentialOperator differentialOperator = new TabulatedDifferentialOperator();
        TabulatedFunction firstDerivative = differentialOperator.derive(arrTabFunction);
        TabulatedFunction secondDerivative = differentialOperator.derive(firstDerivative);

        try(BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(new FileOutputStream(filePath))){
            FunctionsIO.serialize(bufferedOutputStream,arrTabFunction);
            FunctionsIO.serialize(bufferedOutputStream,firstDerivative);
            FunctionsIO.serialize(bufferedOutputStream,secondDerivative);
        }
        catch (IOException exception){
            exception.printStackTrace();
        }

        try (BufferedInputStream bufferedInputStream = new BufferedInputStream(new FileInputStream(filePath))){
            TabulatedFunction deserializedArrTabFunction = FunctionsIO.deserialize(bufferedInputStream);
            TabulatedFunction deserializedFirstDerivative = FunctionsIO.deserialize(bufferedInputStream);
            TabulatedFunction deserializedSecondDerivative = FunctionsIO.deserialize(bufferedInputStream);

            System.out.println("Heirloom function: "+ deserializedArrTabFunction.toString());
            System.out.println("First derivative of a function: "+ deserializedFirstDerivative.toString());
            System.out.println("Second derivative of a function: "+ deserializedSecondDerivative.toString());
        }
        catch (IOException | ClassNotFoundException exception){
            exception.printStackTrace();
        }
    }
}
