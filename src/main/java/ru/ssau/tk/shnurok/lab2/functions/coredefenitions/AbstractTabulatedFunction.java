package ru.ssau.tk.shnurok.lab2.functions.coredefenitions;

import ru.ssau.tk.shnurok.lab2.exeptions.ArrayIsNotSortedException;
import ru.ssau.tk.shnurok.lab2.exeptions.DifferentLengthOfArraysException;
import ru.ssau.tk.shnurok.lab2.functions.realizations.Point;

public abstract class AbstractTabulatedFunction implements TabulatedFunction {

    protected abstract int floorIndexOfX(double x);

    protected abstract double extrapolateLeft(double x);

    protected abstract double extrapolateRight(double x);

    protected abstract double interpolate(double x, int floorIndex);

    protected double interpolate(double x, double leftX, double rightX, double leftY, double rightY){
        return (x-leftX)*(rightY-leftY)/(rightX-leftX) + leftY;
    }


    @Override
    public double apply(double x) {
        if (x < leftBound()) return extrapolateLeft(x);
        else if (x > rightBound()) return extrapolateRight(x);

        else {
            int index = indexOfX(x);
            if (index != -1) {
                return getY(index);
            } else {
                return interpolate(x, floorIndexOfX(x));
            }

        }
    }

    public static void checkLengthIsTheSame(double[] xValues, double[] yValues){
        if (xValues.length != yValues.length) throw new DifferentLengthOfArraysException("Arrays must be same length");
    }

    public static void checkSorted(double[] xValues){
        for (int i = 0; i<xValues.length-1;i++){
            if (xValues[i]>xValues[i+1]) throw new ArrayIsNotSortedException("Arrays must be sorted from smallest to biggest");
        }
    }
    @Override
    public String toString() {
        StringBuilder string = new StringBuilder(getClass().getSimpleName() + " size = " + getCount() + "\n");
        for (Point point : this) {
            string.append("[").append(point.getX()).append("; ").append(point.getY()).append("]\n");
        }

        return string.toString();
    }




}
