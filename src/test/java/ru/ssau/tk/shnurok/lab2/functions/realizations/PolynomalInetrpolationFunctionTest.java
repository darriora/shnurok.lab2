package ru.ssau.tk.shnurok.lab2.functions.realizations;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class PolynomalInetrpolationFunctionTest {

    @Test
    public void ApplyTest(){
        double[] xVal = {1.,2.,3.};
        double[] yVal = {3.,8.,17.};

        PolynomialInterpolationFunction polynomalInetrpolationFunction = new PolynomialInterpolationFunction(xVal,yVal);

        assertEquals(8,polynomalInetrpolationFunction.apply(2));
        assertEquals(498,polynomalInetrpolationFunction.apply(16));

    }

    public void ConstructTest(){
        double[] xVal = {1.,2.,3.};
        double[] yVal = {3.,8.,17.};
    }
}