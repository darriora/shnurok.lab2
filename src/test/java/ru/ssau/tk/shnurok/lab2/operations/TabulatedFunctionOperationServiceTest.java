package ru.ssau.tk.shnurok.lab2.operations;




import org.junit.jupiter.api.Test;
import ru.ssau.tk.shnurok.lab2.functions.coredefenitions.TabulatedFunction;
import ru.ssau.tk.shnurok.lab2.functions.factory.ArrayTabulatedFunctionFactory;
import ru.ssau.tk.shnurok.lab2.functions.factory.TabulatedFunctionFactory;
import ru.ssau.tk.shnurok.lab2.functions.realizations.ArrayTabulatedFunction;
import ru.ssau.tk.shnurok.lab2.functions.realizations.Point;
import ru.ssau.tk.shnurok.lab2.operations.TabulatedFunctionOperationService;

import static org.junit.jupiter.api.Assertions.*;


class TabulatedFunctionOperationServiceTest {
    @Test
    public void testAsPoint(){
        double[] xVal = {0,2,3};
        double[] yVal = {0,4,6};
        TabulatedFunction tabulatedFunction = new ArrayTabulatedFunction(xVal,yVal);

        Point[] expectedP = {
                new Point(0,0),
                new Point(2,4),
                new Point(3,6)
        };

        Point[] actualP = TabulatedFunctionOperationService.asPoints(tabulatedFunction);

        for (int i = 0; i<expectedP.length;i++){
            assertEquals(expectedP[i].x,actualP[i].x);
            assertEquals(expectedP[i].y,actualP[i].y);
        }
    }

    @Test
    public void testCreateFactory(){
        TabulatedFunctionFactory tabulatedFunctionFactory = new ArrayTabulatedFunctionFactory();
        TabulatedFunctionOperationService service = new TabulatedFunctionOperationService();
        service.setFactory(tabulatedFunctionFactory);
        assertEquals(tabulatedFunctionFactory,service.getFactory());
    }

    @Test
    public void testConstructor(){
        TabulatedFunctionOperationService tabulatedFunctionOperationService = new TabulatedFunctionOperationService();
        assertNotNull(tabulatedFunctionOperationService.getFactory());
    }

    @Test
    public void testParametrizedConstructor(){
        TabulatedFunctionFactory tabulatedFunctionFactory = new ArrayTabulatedFunctionFactory();
        TabulatedFunctionOperationService tabulatedFunctionOperationService = new TabulatedFunctionOperationService(tabulatedFunctionFactory);
        assertEquals(tabulatedFunctionFactory,tabulatedFunctionOperationService.getFactory());
    }

    @Test
    public void testSetFactory(){
        TabulatedFunctionFactory tabulatedFunctionFactory = new ArrayTabulatedFunctionFactory();
        TabulatedFunctionOperationService tabulatedFunctionOperationService = new TabulatedFunctionOperationService();
        tabulatedFunctionOperationService.setFactory(tabulatedFunctionFactory);
        assertEquals(tabulatedFunctionFactory,tabulatedFunctionOperationService.getFactory());
    }

    @Test
    public void testGetFactory(){
        TabulatedFunctionFactory tabulatedFunctionFactory = new ArrayTabulatedFunctionFactory();
        TabulatedFunctionOperationService tabulatedFunctionOperationService = new TabulatedFunctionOperationService(tabulatedFunctionFactory);
        assertEquals(tabulatedFunctionFactory,tabulatedFunctionOperationService.getFactory());
    }

    @Test
    public void testAddition(){
        double[] xVal = {0,2,4};
        double[] yValA = {1,3,5};
        double[] yValB = {3,2,1};

        TabulatedFunction funcA = new ArrayTabulatedFunction(xVal,yValA);
        TabulatedFunction funcB = new ArrayTabulatedFunction(xVal,yValB);

        TabulatedFunctionOperationService service = new TabulatedFunctionOperationService();

        double[] expYVal= {4,5,6};
        TabulatedFunction result = service.addition(funcA,funcB);
        for (int i = 0; i<xVal.length;i++){
            assertEquals(expYVal[i],result.getY(i));
        }
    }

    @Test
    public void testMultiply(){
        double[] xVal = {0,2,4};
        double[] yValA = {1,3,5};
        double[] yValB = {3,2,1};

        TabulatedFunction funcA = new ArrayTabulatedFunction(xVal,yValA);
        TabulatedFunction funcB = new ArrayTabulatedFunction(xVal,yValB);

        TabulatedFunctionOperationService service = new TabulatedFunctionOperationService();

        double[] expYVal= {3,6,5};
        TabulatedFunction result = service.multiply(funcA,funcB);
        for (int i = 0; i<xVal.length;i++){
            assertEquals(expYVal[i],result.getY(i));
        }
    }

    @Test
    public void testSubtraction(){
        double[] xVal = {0,2,4};
        double[] yValA = {1,3,5};
        double[] yValB = {3,2,1};

        TabulatedFunction funcA = new ArrayTabulatedFunction(xVal,yValA);
        TabulatedFunction funcB = new ArrayTabulatedFunction(xVal,yValB);

        TabulatedFunctionOperationService service = new TabulatedFunctionOperationService();

        double[] expYVal= {-2,1,4};
        TabulatedFunction result = service.subtraction(funcA,funcB);
        for (int i = 0; i<xVal.length;i++){
            assertEquals(expYVal[i],result.getY(i));
        }
    }

    @Test
    public void testDivision(){
        double[] xVal = {0,2,4};
        double[] yValA = {6,8,5};
        double[] yValB = {3,2,1};

        TabulatedFunction funcA = new ArrayTabulatedFunction(xVal,yValA);
        TabulatedFunction funcB = new ArrayTabulatedFunction(xVal,yValB);

        TabulatedFunctionOperationService service = new TabulatedFunctionOperationService();

        double[] expYVal= {2,4,5};
        TabulatedFunction result = service.division(funcA,funcB);
        for (int i = 0; i<xVal.length;i++){
            assertEquals(expYVal[i],result.getY(i));
        }
    }
 }

