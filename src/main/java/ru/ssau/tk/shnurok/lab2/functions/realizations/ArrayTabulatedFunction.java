package ru.ssau.tk.shnurok.lab2.functions.realizations;

import ru.ssau.tk.shnurok.lab2.exeptions.InterpolationException;
import ru.ssau.tk.shnurok.lab2.functions.coredefenitions.AbstractTabulatedFunction;
import ru.ssau.tk.shnurok.lab2.functions.coredefenitions.Insertable;
import ru.ssau.tk.shnurok.lab2.functions.coredefenitions.MathFunction;
import ru.ssau.tk.shnurok.lab2.functions.coredefenitions.Removable;

import java.io.Serial;
import java.io.Serializable;
import java.util.Arrays;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class ArrayTabulatedFunction extends AbstractTabulatedFunction implements Insertable, Removable, Serializable {


    @Serial
    private static final long serialVersionUID = -7942049767492914984L;
    protected double[] xValues;
    protected double[] yValues;
    protected int count;


    public ArrayTabulatedFunction(double[] xValues, double[] yValues) {
        ArrayTabulatedFunction.checkLengthIsTheSame(xValues,yValues);
        ArrayTabulatedFunction.checkSorted(xValues);

        this.xValues = Arrays.copyOf(xValues,xValues.length);
        this.yValues = Arrays.copyOf(yValues,yValues.length);
        this.count = xValues.length;
    }

    public ArrayTabulatedFunction(MathFunction source, double xFrom, double xTo, int count) {

        if (xFrom > xTo){
            double tmp = xFrom;
            xFrom = xTo;
            xTo = tmp;
        }

        this.count = count;
        this.xValues = new double[count];
        this.yValues = new double[count];

        if(xFrom == xTo){
            Arrays.fill(xValues,xFrom);
            Arrays.fill(yValues,source.apply(xFrom));
        } else {
            double step = (xTo - xFrom) / (count - 1);
            for (int i = 0; i < count; i++) {
                xValues[i] = xFrom + i*step;
                yValues[i] = source.apply(xValues[i]);
            }
        }

    }

    @Override
    protected int floorIndexOfX(double x) {
        if(x<xValues[0]) return 0;
        else if(x >= xValues[count-1]) return count-1;
        for(int i = 1; i<count;i++){
            if (xValues[i]>x)return i-1;
        }
        return count-1;
    }

    @Override
    protected double extrapolateLeft(double x) {
        return interpolate(x,xValues[0],xValues[1],yValues[0],yValues[1]);
    }

    @Override
    protected double extrapolateRight(double x) {
        return interpolate(x,xValues[count-2],xValues[count-1],yValues[count-2],yValues[count-1]);
    }

    @Override
    protected double interpolate(double x, int floorIndex) {
        if (x>xValues[floorIndex+1]||x<xValues[floorIndex]) throw new InterpolationException("x out of interpolate bounds");

        return interpolate(x,xValues[floorIndex-1],xValues[floorIndex],yValues[floorIndex-1],yValues[floorIndex]);
    }

    @Override
    public int getCount() {
        return count;
    }

    @Override
    public double getX(int index) {
        return xValues[index];
    }

    @Override
    public double getY(int index) {
        return yValues[index];
    }

    @Override
    public void setY(int index, double value) {
        yValues[index] = value;
    }

    @Override
    public int indexOfX(double x) {
        for (int i = 0; i<xValues.length;i++){
            if (xValues[i] == x) return i;
        }
        return -1;
    }

    @Override
    public int indexOfY(double y) {
        for (int i = 0; i<yValues.length; i++){
            if (yValues[i] == y) return i;
        }
        return -1;
    }

    @Override
    public double leftBound() {
        return xValues[0];
    }

    @Override
    public double rightBound() {
        return xValues[xValues.length-1];
    }

    @Override
    public void insert(double x, double y) {
        int index = indexOfX(x);
        if (index != -1) {
            yValues[index] = y;
        } else {
            double[] newXValues = new double[xValues.length + 1];
            double[] newYValues = new double[yValues.length + 1];
            System.arraycopy(xValues, 0, newXValues, 0, xValues.length);
            System.arraycopy(yValues, 0, newYValues, 0, yValues.length);
            int insertIndex = floorIndexOfX(x);
            System.arraycopy(xValues, insertIndex, newXValues, insertIndex + 1, xValues.length - insertIndex);
            System.arraycopy(yValues, insertIndex, newYValues, insertIndex + 1, yValues.length - insertIndex);
            newXValues[insertIndex] = x;
            newYValues[insertIndex] = y;
            xValues = newXValues;
            yValues = newYValues;
            count++;
        }
    }
    @Override
    public void remove(int index) {
        if (index < 0 || index >= count) {
            throw new IndexOutOfBoundsException("index is outside");
        }
        double[] newXValues = new double[xValues.length - 1];
        double[] newYValues = new double[yValues.length - 1];
        System.arraycopy(xValues, 0, newXValues, 0, index);
        System.arraycopy(yValues, 0, newYValues, 0, index);
        System.arraycopy(xValues, index + 1, newXValues, index, xValues.length - index - 1);
        System.arraycopy(yValues, index + 1, newYValues, index, yValues.length - index - 1);
        xValues = newXValues;
        yValues = newYValues;
        count--;
    }

    @Override
    public Iterator<Point> iterator() {
        return new Iterator<Point>() {
            private int i = 0;

            @Override
            public boolean hasNext() {
                return i < count;
            }

            @Override
            public Point next() {
                if(!hasNext()) throw new NoSuchElementException("");
            return new Point(xValues[i],yValues[i++]);
            }
        };
    }

}