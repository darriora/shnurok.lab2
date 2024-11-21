package ru.ssau.tk.shnurok.lab2.service;

import org.junit.jupiter.api.Test;
import ru.ssau.tk.shnurok.lab2.dto.MathFunctionDTO;
import ru.ssau.tk.shnurok.lab2.entity.MathFunctionEntity;

import static org.junit.jupiter.api.Assertions.*;

class MathFunctionMapperTest {

    @Test
    void testFunctionEntityToDTO() {
        MathFunctionEntity entity = new MathFunctionEntity(1, "Example", 10, 0.0, 10.0, null);
        MathFunctionDTO dto = MathFunctionMapper.functionEntityToDTO(entity);

        assertNotNull(dto);
        assertEquals(entity.getId(), dto.getId());
        assertEquals(entity.getFunctionName(), dto.getFunctionName());

        dto = null;
        assertNull(dto);
    }

    @Test
    void testFunctionDTOToFunctionEntity() {
        MathFunctionDTO dto = new MathFunctionDTO(1, "Example", 10, 0.0, 10.0);
        MathFunctionEntity entity = MathFunctionMapper.functionDTOToFunctionEntity(dto);

        assertNotNull(entity);
        assertEquals(dto.getId(), entity.getId());
        assertEquals(dto.getFunctionName(), entity.getFunctionName());

        entity = null;
        assertNull(entity);
    }

}
