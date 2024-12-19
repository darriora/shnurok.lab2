package ru.ssau.tk.shnurok.lab2.service.operations;

import ru.ssau.tk.shnurok.lab2.exeptions.InconsistentFunctionsException;
import ru.ssau.tk.shnurok.lab2.functions.coredefenitions.TabulatedFunction;
import ru.ssau.tk.shnurok.lab2.functions.factory.ArrayTabulatedFunctionFactory;
import ru.ssau.tk.shnurok.lab2.functions.factory.TabulatedFunctionFactory;
import ru.ssau.tk.shnurok.lab2.functions.realizations.Point;

public class TabulatedFunctionOperationService {
    private TabulatedFunctionFactory factory;

    public TabulatedFunctionOperationService(TabulatedFunctionFactory factory) {
        this.factory = factory;
    }

    public TabulatedFunctionOperationService() {
        this.factory = new ArrayTabulatedFunctionFactory();
    }


    private interface BiOperation{
        double apply(double u, double v);
    }

    private TabulatedFunction doOperation(TabulatedFunction a, TabulatedFunction b, BiOperation operation){
        if (a.getCount() != b.getCount()) throw new InconsistentFunctionsException("x arrays should be the same length");

        Point[] pointsA = asPoints(a);
        Point[] pointsB = asPoints(b);

        double[] xValues = new double[pointsA.length];
        double[] yValues = new double[pointsA.length];

        for (int i = 0; i<pointsA.length;i++){
            if(pointsA[i].getX()!=pointsB[i].getX()) throw new InconsistentFunctionsException("x arrays should be the same");

            xValues[i] = pointsA[i].getX();
            yValues[i] = operation.apply(pointsA[i].getY(),pointsB[i].getY());
        }

        return factory.create(xValues,yValues);
    }

    public TabulatedFunction addition(TabulatedFunction a, TabulatedFunction b) throws InconsistentFunctionsException{
        return doOperation(a,b,((u, v) -> u + v));
    }
    public TabulatedFunction multiply(TabulatedFunction a, TabulatedFunction b) throws InconsistentFunctionsException{
        return doOperation(a,b,((u, v) -> u * v));
    }
    public TabulatedFunction subtraction(TabulatedFunction a, TabulatedFunction b) throws InconsistentFunctionsException{
        return doOperation(a,b,((u, v) -> u - v));
    }
    public TabulatedFunction division(TabulatedFunction a, TabulatedFunction b) throws InconsistentFunctionsException{
        return doOperation(a,b,((u, v) -> u / v));
    }

    public static Point[] asPoints(TabulatedFunction tabulatedFunction){
        int count = tabulatedFunction.getCount(); // Получаем количество точек
        Point[] points = new Point[count]; // Инициализируем массив точек

        int i = 0;
        for (Point point : tabulatedFunction) { // Проходим по табулированной функции
            points[i++] = point; // Записываем точку в массив

        }
        return points; // Возвращаем массив точек
    }

    public void setFactory(TabulatedFunctionFactory factory) {
        this.factory = factory;
    }

    public TabulatedFunctionFactory getFactory(){
        return factory;
    }
}
