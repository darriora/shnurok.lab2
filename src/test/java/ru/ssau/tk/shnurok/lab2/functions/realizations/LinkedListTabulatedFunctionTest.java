package ru.ssau.tk.shnurok.lab2.functions.realizations;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ru.ssau.tk.shnurok.lab2.functions.coredefenitions.MathFunction;

import java.util.Iterator;
import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.assertEquals;

class LinkedListTabulatedFunctionTest {

    @Test
    public void testRemove() {
        LinkedListTabulatedFunction function = new LinkedListTabulatedFunction(new double[] {1, 2, 3}, new double[] {4, 5, 6});
        function.remove(1);
        assertEquals(2, function.getCount());
        assertEquals(1, function.getX(0), 0.01);
        assertEquals(4, function.getY(0), 0.01);
        assertEquals(3, function.getX(1), 0.01);
        assertEquals(6, function.getY(1), 0.01);
    }

    @Test
    public void testConstructorWithArrays() {
        double[] xValues = {1, 2, 3};
        double[] yValues = {2, 3, 4};
        LinkedListTabulatedFunction function = new LinkedListTabulatedFunction(xValues, yValues);
        assertEquals(3, function.getCount());
        assertEquals(1, function.getX(0));
        assertEquals(2, function.getY(0));
    }

    @Test
    public void testConstructorWithMathFunction() {
        MathFunction source = x -> x * x; // example math function
        LinkedListTabulatedFunction function = new LinkedListTabulatedFunction(source, 0, 3, 4);
        assertEquals(4, function.getCount());

        // Проверяем, что количество элементов соответствует ожиданиям
        assertEquals(4, function.getCount());

        // Check values at x = 1, 2, 3
        assertEquals(1, function.getX(1), 0.01);
        assertEquals(1, function.getY(1), 0.01);

        assertEquals(2, function.getX(2), 0.01);
        assertEquals(4, function.getY(2), 0.01);

        assertEquals(3, function.getX(3), 0.01);
        assertEquals(9, function.getY(3), 0.01);
    }


    @Test
    public void testLeftBound() {
        LinkedListTabulatedFunction function = new LinkedListTabulatedFunction(new double[] {1, 2, 3}, new double[] {4, 5, 6});
        assertEquals(1, function.leftBound(), 0.01);
    }

    @Test
    public void testRightBound() {
        LinkedListTabulatedFunction function = new LinkedListTabulatedFunction(new double[] {1, 2, 3}, new double[] {4, 5, 6});
        assertEquals(3, function.rightBound(), 0.01);
    }

    @Test
    public void testExtrapolateLeft() {
        LinkedListTabulatedFunction function = new LinkedListTabulatedFunction(new double[]{1, 2}, new double[]{2, 4});
        assertEquals(0, function.extrapolateLeft(0), 0.01);
    }

    @Test
    public void testExtrapolateRight() {
        LinkedListTabulatedFunction function = new LinkedListTabulatedFunction(new double[]{1, 2}, new double[]{2, 4});
        assertEquals(6, function.extrapolateRight(3), 0.01);
    }

    @Test
    public void testFloorIndexOfX() {
        LinkedListTabulatedFunction function = new LinkedListTabulatedFunction(new double[] {1, 2, 3}, new double[] {4, 5, 6});
        assertEquals(0, function.floorIndexOfX(1));
        assertEquals(0, function.floorIndexOfX(1.5));
        assertEquals(1, function.floorIndexOfX(2.5));
        assertEquals(1, function.floorIndexOfX(3));

    }

    @Test
    public void testGetCount() {
        LinkedListTabulatedFunction function = new LinkedListTabulatedFunction(new double[] {1, 2, 3}, new double[] {4, 5, 6});
        assertEquals(3, function.getCount());
    }

    @Test
    public void testGetX() {
        LinkedListTabulatedFunction function = new LinkedListTabulatedFunction(new double[]{1, 2}, new double[]{2, 3});
        assertEquals(1, function.getX(0));
        assertEquals(2, function.getX(1));
    }

    @Test
    public void testGetY() {
        LinkedListTabulatedFunction function = new LinkedListTabulatedFunction(new double[]{1, 2}, new double[]{2, 3});
        assertEquals(2, function.getY(0));
        assertEquals(3, function.getY(1));
    }

    @Test
    public void testSetY() {
        LinkedListTabulatedFunction function = new LinkedListTabulatedFunction(new double[]{1, 2}, new double[]{2, 3});
        function.setY(0, 5);
        assertEquals(5, function.getY(0));
    }

    @Test
        public void testIterator(){
            double[] x = new double[]{0.0, 1.0, 2.0, 3.0};
            double[] y = new double[]{1.0, 0.0, 2.0, -1.0};

            LinkedListTabulatedFunction func = new LinkedListTabulatedFunction(x, y);

            {
                Iterator<Point> iterator = func.iterator();
                int i = 0;
                while (iterator.hasNext()) {
                    Point point = iterator.next();
                    assertEquals(x[i], point.x);
                    assertEquals(y[i], point.y);
                    i++;
                }
                Assertions.assertThrows(NoSuchElementException.class, iterator::next);
            }

            {
                int i = 0;
                for (Point point : func) {
                    assertEquals(x[i], point.x);
                    assertEquals(y[i], point.y);
                    i++;
                }
            }

            LinkedListTabulatedFunction func2 = new LinkedListTabulatedFunction(new IdentityFunction(), 0, 10, 11);

        }


}