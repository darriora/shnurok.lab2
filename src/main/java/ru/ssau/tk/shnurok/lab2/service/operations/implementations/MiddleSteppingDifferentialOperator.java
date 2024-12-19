package ru.ssau.tk.shnurok.lab2.service.operations.implementations;

import ru.ssau.tk.shnurok.lab2.functions.coredefenitions.MathFunction;
import ru.ssau.tk.shnurok.lab2.service.operations.abstractclasses.SteppingDifferentialOperator;

public class MiddleSteppingDifferentialOperator extends SteppingDifferentialOperator {

    public MiddleSteppingDifferentialOperator(double step) {
        super(step);
    }

    public MathFunction derive (MathFunction function){
        return new MathFunction() {
            @Override
            public double apply(double x) {
                return (function.apply(x+step/2)- function.apply(x-step/2))/step;
            }
        };
    }
}
