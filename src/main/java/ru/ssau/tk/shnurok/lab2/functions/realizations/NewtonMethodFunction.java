package ru.ssau.tk.shnurok.lab2.functions.realizations;

import ru.ssau.tk.shnurok.lab2.functions.coredefenitions.MathFunction;

public class NewtonMethodFunction implements MathFunction {

    private final static double EPS  = 1E-7;
    private final MathFunction function;
    private final double tolerance;

    public NewtonMethodFunction(MathFunction function, double tolerance) {
        this.function = function;
        this.tolerance = tolerance;
    }
    private double derivative(double x) {
        return function.apply(x + EPS) / EPS / 2 - function.apply(x - EPS) / EPS / 2;
    }

    @Override
    public double apply(double x) {
        double xn;
        do {
            xn = x;
            double f = function.apply(x);
            double pr_f = derivative(x);

            if (Math.abs(pr_f) < tolerance) return Double.NaN; // производная слишком мала, то метод не сходится

            x = xn - f / pr_f;

        }while (Math.abs(xn - x) > tolerance); // продолжаем до тех пор, пока изменение больше заданного порога tolerance

        return x;
    }
}
