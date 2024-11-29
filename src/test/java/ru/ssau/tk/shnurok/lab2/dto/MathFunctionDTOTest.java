package ru.ssau.tk.shnurok.lab2.dto;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MathFunctionDTOTest {

    @Test
    public void testAllArgsConstructor() {
        // Arrange
        PointDTO point1 = new PointDTO(1, 1, 2.0, 3.0);
        PointDTO point2 = new PointDTO(2, 1, 4.0, 5.0);
        MathFunctionDTO mathFunctionDTO = new MathFunctionDTO(1, "TestFunction", 2, 0.0, 10.0, Arrays.asList(point1, point2));

        // Act & Assert
        assertEquals(1, mathFunctionDTO.getId());
        assertEquals("TestFunction", mathFunctionDTO.getFunctionName());
        assertEquals(2, mathFunctionDTO.getCount());
        assertEquals(0.0, mathFunctionDTO.getXFrom());
        assertEquals(10.0, mathFunctionDTO.getXTo());
        assertEquals(Arrays.asList(point1, point2), mathFunctionDTO.getPoints());
    }

    @Test
    public void testNoArgsConstructor() {
        // Arrange
        MathFunctionDTO mathFunctionDTO = new MathFunctionDTO();

        // Act & Assert
        assertEquals(0, mathFunctionDTO.getId());
        assertEquals(null, mathFunctionDTO.getFunctionName());
        assertEquals(0, mathFunctionDTO.getCount());
        assertEquals(0.0, mathFunctionDTO.getXFrom());
        assertEquals(0.0, mathFunctionDTO.getXTo());
        assertEquals(null, mathFunctionDTO.getPoints());
    }

    @Test
    public void testSettersAndGetters() {
        // Arrange
        MathFunctionDTO mathFunctionDTO = new MathFunctionDTO();
        PointDTO point = new PointDTO(1, 1, 2.0, 3.0);

        // Act
        mathFunctionDTO.setId(1);
        mathFunctionDTO.setFunctionName("TestFunction");
        mathFunctionDTO.setCount(3);
        mathFunctionDTO.setXFrom(0.0);
        mathFunctionDTO.setXTo(5.0);
        mathFunctionDTO.setPoints(Arrays.asList(point));

        // Assert
        assertEquals(1, mathFunctionDTO.getId());
        assertEquals("TestFunction", mathFunctionDTO.getFunctionName());
        assertEquals(3, mathFunctionDTO.getCount());
        assertEquals(0.0, mathFunctionDTO.getXFrom());
        assertEquals(5.0, mathFunctionDTO.getXTo());
        assertEquals(Arrays.asList(point), mathFunctionDTO.getPoints());
    }
}
