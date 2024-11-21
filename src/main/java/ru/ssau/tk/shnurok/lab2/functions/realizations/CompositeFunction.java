package ru.ssau.tk.shnurok.lab2.functions.realizations;

import ru.ssau.tk.shnurok.lab2.functions.coredefenitions.MathFunction;

public class CompositeFunction implements MathFunction {
    private final MathFunction firstFunction;
    private final MathFunction secondFunction;

    public CompositeFunction(MathFunction first, MathFunction second) {
        this.firstFunction = first;
        this.secondFunction = second;
    }

    @Override
    public double apply(double x) {
        double firstResult = firstFunction.apply(x);
        return secondFunction.apply(firstResult);
    }

}
