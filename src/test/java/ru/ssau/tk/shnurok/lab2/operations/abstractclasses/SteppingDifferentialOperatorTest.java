package ru.ssau.tk.shnurok.lab2.operations.abstractclasses;

import org.junit.jupiter.api.Test;
import ru.ssau.tk.shnurok.lab2.functions.coredefenitions.MathFunction;
import ru.ssau.tk.shnurok.lab2.operations.abstractclasses.SteppingDifferentialOperator;

import static org.junit.jupiter.api.Assertions.*;
public class SteppingDifferentialOperatorTest{
    private static class TestForSteppingDifferentOperator extends SteppingDifferentialOperator {
        public TestForSteppingDifferentOperator(double step){
            super(step);
        }

        @Override
        public MathFunction derive(MathFunction function){
            return null;
        }
    }


    @Test
    public void testConstructor(){
        TestForSteppingDifferentOperator steppingDifferentialOperator = new TestForSteppingDifferentOperator(11);
        assertNotNull(steppingDifferentialOperator);
        assertEquals(11,steppingDifferentialOperator.getStep());
    }

    @Test
    public void testGetStep(){
        TestForSteppingDifferentOperator testForSteppingDifferentOperator = new TestForSteppingDifferentOperator(11);
        assertEquals(11,testForSteppingDifferentOperator.getStep());
    }

    @Test
    public void testSetStep(){
        TestForSteppingDifferentOperator testForSteppingDifferentOperator = new TestForSteppingDifferentOperator(52);
        testForSteppingDifferentOperator.setStep(11);
        assertEquals(11,testForSteppingDifferentOperator.getStep());
    }
}