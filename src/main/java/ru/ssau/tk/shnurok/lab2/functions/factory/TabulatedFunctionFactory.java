package ru.ssau.tk.shnurok.lab2.functions.factory;

import ru.ssau.tk.shnurok.lab2.functions.coredefenitions.TabulatedFunction;
import ru.ssau.tk.shnurok.lab2.functions.realizations.StrictTabulatedFunction;

public interface TabulatedFunctionFactory {

    default TabulatedFunction createStrict(double[] xValues, double[] yValues) {
        TabulatedFunction function = create(xValues, yValues);
        return new StrictTabulatedFunction(function);
    }
    
    TabulatedFunction create(double[] xValues, double[] yValues);

}
