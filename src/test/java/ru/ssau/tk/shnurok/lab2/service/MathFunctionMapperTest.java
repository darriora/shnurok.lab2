package ru.ssau.tk.shnurok.lab2.service;

import org.junit.jupiter.api.Test;
import ru.ssau.tk.shnurok.lab2.dto.MathFunctionDTO;
import ru.ssau.tk.shnurok.lab2.entity.MathFunctionEntity;

import static org.junit.jupiter.api.Assertions.*;

public class MathFunctionMapperTest {

    @Test
    public void testFunctionEntityToDTO_Null() {
        assertNull(MathFunctionMapper.functionEntityToDTO(null));
    }

    @Test
    public void testFunctionEntityToDTO_ValidEntity() {
        MathFunctionEntity entity = new MathFunctionEntity();
        entity.setId(1);
        entity.setMathFunctionName("example_function");
        entity.setCount(10);
        entity.setXFrom(0.0);
        entity.setXTo(10.0);

        MathFunctionDTO dto = MathFunctionMapper.functionEntityToDTO(entity);

        assertNotNull(dto);
        assertEquals(1, dto.getId());
        assertEquals("example_function", dto.getMathFunctionName());
        assertEquals(10, dto.getCount());
        assertEquals(0.0, dto.getXFrom());
        assertEquals(10.0, dto.getXTo());
    }

    @Test
    public void testFunctionDTOToFunctionEntity_Null() {
        assertNull(MathFunctionMapper.functionDTOToFunctionEntity(null));
    }

    @Test
    public void testFunctionDTOToFunctionEntity_ValidDTO() {
        MathFunctionDTO dto = new MathFunctionDTO();
        dto.setId(1);
        dto.setMathFunctionName("example_function");
        dto.setCount(10);
        dto.setXFrom(0.0);
        dto.setXTo(10.0);

        MathFunctionEntity entity = MathFunctionMapper.functionDTOToFunctionEntity(dto);

        assertNotNull(entity);
        assertEquals(1, entity.getId());
        assertEquals("example_function", entity.getMathFunctionName());
        assertEquals(10, entity.getCount());
        assertEquals(0.0, entity.getXFrom());
        assertEquals(10.0, entity.getXTo());
    }
}
