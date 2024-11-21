package ru.ssau.tk.shnurok.lab2.functions.realizations;

import ru.ssau.tk.shnurok.lab2.functions.coredefenitions.MathFunction;

public class SqrFunction implements MathFunction {

    @Override
    public double apply(double x){
        return Math.pow(x,2);
    }
}