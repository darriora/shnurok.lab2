package ru.ssau.tk.shnurok.lab2.operations.abstractclasses;

import ru.ssau.tk.shnurok.lab2.functions.coredefenitions.MathFunction;
import ru.ssau.tk.shnurok.lab2.operations.interfaces.DifferentialOperator;

public abstract class SteppingDifferentialOperator implements DifferentialOperator<MathFunction> {
    protected double step;

    public SteppingDifferentialOperator(double step) {
        if(step<=0||Double.isNaN(step)||Double.isInfinite(step)) throw new IllegalArgumentException("step should be scalable positive");
        this.step = step;
    }
}
