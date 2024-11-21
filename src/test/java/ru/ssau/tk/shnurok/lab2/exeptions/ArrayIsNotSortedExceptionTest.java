package ru.ssau.tk.shnurok.lab2.exeptions;

import org.junit.jupiter.api.Test;
import ru.ssau.tk.shnurok.lab2.exeptions.ArrayIsNotSortedException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

class ArrayIsNotSortedExceptionTest {

    @Test
    void testDefaultConstructor() {
        ArrayIsNotSortedException exception = new ArrayIsNotSortedException();
        assertNull(exception.getMessage(), "Сообщение исключения должно быть null");
    }

    @Test
    void testConstructorWithMessage() {
        String message = "Массив не отсортирован";
        ArrayIsNotSortedException exception = new ArrayIsNotSortedException(message);
        assertEquals(message, exception.getMessage(), "Сообщение исключения не совпадает с ожидаемым");
    }
}
