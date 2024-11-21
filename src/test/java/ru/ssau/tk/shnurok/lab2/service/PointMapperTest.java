package ru.ssau.tk.shnurok.lab2.service;

import org.junit.jupiter.api.Test;
import ru.ssau.tk.shnurok.lab2.dto.PointDTO;
import ru.ssau.tk.shnurok.lab2.entity.PointEntity;

import static org.junit.jupiter.api.Assertions.*;

class PointMapperTest {

    @Test
    void testPointEntityToDTO() {
        PointEntity entity = new PointEntity(1, null, 1.0, 2.0);
        PointDTO dto = PointMapper.pointEntityToDTO(entity);

        assertNotNull(dto);
        assertEquals(entity.getId(), dto.getId());
        assertEquals(entity.getXVal(), dto.getXVal());

        dto = null;
        assertNull(dto);
    }

    @Test
    void testPointDTOToPointEntity() {
        PointDTO dto = new PointDTO(1, 1, 1.0, 2.0);
        PointEntity entity = PointMapper.pointDTOToPointEntity(dto);

        assertNotNull(entity);
        assertEquals(dto.getId(), entity.getId());
        assertEquals(dto.getXVal(), entity.getXVal());

        entity = null;
        assertNull(entity);
    }
}
