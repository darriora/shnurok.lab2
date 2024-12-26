package ru.ssau.tk.shnurok.lab2.functions.factory;

import ru.ssau.tk.shnurok.lab2.functions.coredefenitions.MathFunction;
import ru.ssau.tk.shnurok.lab2.functions.coredefenitions.TabulatedFunction;
import ru.ssau.tk.shnurok.lab2.functions.realizations.StrictTabulatedFunction;
import ru.ssau.tk.shnurok.lab2.functions.realizations.UnmodifiableTabulatedFunction;

public interface TabulatedFunctionFactory {

    TabulatedFunction create(double[] xValues, double[] yValues);

    TabulatedFunction create(MathFunction source, double xFrom, double xTo, int count);

    default TabulatedFunction createStrict(double[] xValues, double[] yValues){
        return new StrictTabulatedFunction(create(xValues,yValues));
    }

    default TabulatedFunction createUnmodifiable (double[] xValues, double[] yValues){
        return new UnmodifiableTabulatedFunction(create(xValues,yValues));
    }

    default TabulatedFunction createStrictUnmodifiable (double[] xValues, double[] yValues){
        return new StrictTabulatedFunction(new UnmodifiableTabulatedFunction(create(xValues,yValues)));
    }

}
