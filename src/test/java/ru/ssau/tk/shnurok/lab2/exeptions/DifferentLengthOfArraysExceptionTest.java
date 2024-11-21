package ru.ssau.tk.shnurok.lab2.exeptions;

import org.junit.jupiter.api.Test;
import ru.ssau.tk.shnurok.lab2.exeptions.DifferentLengthOfArraysException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

class DifferentLengthOfArraysExceptionTest {

    @Test
    void testDefaultConstructor() {
        DifferentLengthOfArraysException exception = new DifferentLengthOfArraysException();
        assertNull(exception.getMessage(), "Сообщение исключения должно быть null");
    }

    @Test
    void testConstructorWithMessage() {
        String message = "Длины массивов различны";
        DifferentLengthOfArraysException exception = new DifferentLengthOfArraysException(message);
        assertEquals(message, exception.getMessage(), "Сообщение исключения не совпадает с ожидаемым");
    }
}
