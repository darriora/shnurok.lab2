package ru.ssau.tk.shnurok.lab2.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import ru.ssau.tk.shnurok.lab2.dto.MathFunctionDTO;
import ru.ssau.tk.shnurok.lab2.entity.MathFunctionEntity;
import ru.ssau.tk.shnurok.lab2.repository.MathFunctionRepository;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class MathFunctionServiceTest {

    @Mock
    private MathFunctionRepository mathFunctionRepository;

    @InjectMocks
    private MathFunctionService mathFunctionService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testFindAllFunctions() {
        MathFunctionEntity entity = new MathFunctionEntity();
        entity.setId(1);
        entity.setMathFunctionName("example_function");
        entity.setCount(10);
        entity.setXFrom(0.0);
        entity.setXTo(10.0);

        when(mathFunctionRepository.findByMathFunctionName("example_function"))
                .thenReturn(Arrays.asList(entity));

        List<MathFunctionDTO> result = mathFunctionService.findAllFunctions("example_function");

        assertEquals(1, result.size());
        assertEquals("example_function", result.get(0).getMathFunctionName());
    }

    @Test
    public void testCreate() {
        MathFunctionDTO dto = new MathFunctionDTO();
        dto.setMathFunctionName("new_function");
        dto.setCount(5);
        dto.setXFrom(1.0);
        dto.setXTo(5.0);

        MathFunctionEntity entity = MathFunctionMapper.functionDTOToFunctionEntity(dto);
        when(mathFunctionRepository.save(any(MathFunctionEntity.class))).thenReturn(entity);

        MathFunctionDTO result = mathFunctionService.create(dto);

        assertNotNull(result);
        assertEquals("new_function", result.getMathFunctionName());
    }

    @Test
    public void testRead() {
        MathFunctionEntity entity = new MathFunctionEntity();
        entity.setId(1);
        entity.setMathFunctionName("example_function");

        when(mathFunctionRepository.findById(1)).thenReturn(Optional.of(entity));

        MathFunctionDTO result = mathFunctionService.read(1);

        assertNotNull(result);
        assertEquals("example_function", result.getMathFunctionName());
    }

    @Test
    public void testUpdate() {
        MathFunctionDTO dto = new MathFunctionDTO();
        dto.setId(1);
        dto.setMathFunctionName("updated_function");

        MathFunctionEntity entity = MathFunctionMapper.functionDTOToFunctionEntity(dto);
        when(mathFunctionRepository.save(any(MathFunctionEntity.class))).thenReturn(entity);

        MathFunctionDTO result = mathFunctionService.update(dto);

        assertNotNull(result);
        assertEquals("updated_function", result.getMathFunctionName());
    }

    @Test
    public void testDelete() {
        int id = 1;
        mathFunctionService.delete(id);
        verify(mathFunctionRepository, times(1)).deleteById(id);
    }
}
