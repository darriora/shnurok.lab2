package ru.ssau.tk.shnurok.lab2.operations.implementations;

import ru.ssau.tk.shnurok.lab2.concurrent.SynchronizedTabulatedFunction;
import ru.ssau.tk.shnurok.lab2.functions.coredefenitions.TabulatedFunction;
import ru.ssau.tk.shnurok.lab2.functions.factory.ArrayTabulatedFunctionFactory;
import ru.ssau.tk.shnurok.lab2.functions.factory.TabulatedFunctionFactory;
import ru.ssau.tk.shnurok.lab2.functions.realizations.Point;
import ru.ssau.tk.shnurok.lab2.operations.TabulatedFunctionOperationService;
import ru.ssau.tk.shnurok.lab2.operations.interfaces.DifferentialOperator;

public class TabulatedDifferentialOperator implements DifferentialOperator<TabulatedFunction> {

    private TabulatedFunctionFactory factory;

    // Конструктор по умолчанию
    public TabulatedDifferentialOperator() {
        this.factory = new ArrayTabulatedFunctionFactory();
    }

    // Конструктор с фабрикой
    public TabulatedDifferentialOperator(TabulatedFunctionFactory factory) {
        this.factory = factory;
    }

    // Геттер для фабрики
    public TabulatedFunctionFactory getFactory() {
        return factory;
    }

    // Сеттер для фабрики
    public void setFactory(TabulatedFunctionFactory factory) {
        this.factory = factory;
    }

    @Override
    public TabulatedFunction derive(TabulatedFunction function) {
        Point[] points = TabulatedFunctionOperationService.asPoints(function);

        int pointsLength = points.length;
        double[] xValues = new double[pointsLength];
        double[] yValues = new double[pointsLength];

        int index;
        for (index = 0; index < pointsLength - 1; index++) {
            xValues[index] = points[index].x;
            yValues[index] = (points[index+1].y - points[index].y)/(points[index+1].x-points[index].x);
        }
        xValues[index] = points[index].x;
        yValues[pointsLength - 1] = yValues[index - 1];

        return factory.create(xValues, yValues);
    }

    public TabulatedFunction deriveSynchronously(TabulatedFunction function) {
        SynchronizedTabulatedFunction synchronizedTabulatedFunction;

        if (function instanceof SynchronizedTabulatedFunction) {
            synchronizedTabulatedFunction = (SynchronizedTabulatedFunction) function;
        } else {
            synchronizedTabulatedFunction = new SynchronizedTabulatedFunction(function);
        }

        return synchronizedTabulatedFunction.doSynchronously(this::derive);
    }

}
