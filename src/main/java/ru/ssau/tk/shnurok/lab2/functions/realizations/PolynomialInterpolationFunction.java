package ru.ssau.tk.shnurok.lab2.functions.realizations;

import ru.ssau.tk.shnurok.lab2.functions.coredefenitions.MathFunction;

public class PolynomialInterpolationFunction implements MathFunction {
    private final double[] x;
    private final double[] y;

    public PolynomialInterpolationFunction(double[] x, double[] y){
        if (x.length != y.length){
            throw new IllegalArgumentException("Arrays should have same length");
        }
        this.x = x;
        this.y = y;
    }

    @Override
    public double apply(double val) {
        return lagrangeInterpolation(val);
    }

    private double lagrangeInterpolation(double val){
        double sum = 0;
        int n = x.length;

        for (int i = 0; i<n; i++){
            double mult = y[i];
            for (int j = 0; j<n; j++){
                if(j!=i){
                    mult = mult*(val-x[j])/(x[i]-x[j]);
                }
            }
            sum+=mult;
        }
        return sum;
    }
}
