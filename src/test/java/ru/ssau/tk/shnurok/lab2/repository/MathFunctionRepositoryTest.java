package ru.ssau.tk.shnurok.lab2.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.jdbc.DataJdbcTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import ru.ssau.tk.shnurok.lab2.entity.MathFunctionEntity;

//import javax.transaction.Transactional;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class MathFunctionRepositoryTest {

    @Autowired
    private MathFunctionRepository mathFunctionRepository;

    @Test
    //@Transactional
    public void testFindByMathFunctionName() {
        // Arrange
        MathFunctionEntity functionEntity1 = new MathFunctionEntity();
        functionEntity1.setMathFunctionName("Test Function");
        functionEntity1.setCount(5);
        functionEntity1.setXFrom(0.0);
        functionEntity1.setXTo(10.0);
        mathFunctionRepository.save(functionEntity1);

        MathFunctionEntity functionEntity2 = new MathFunctionEntity();
        functionEntity2.setMathFunctionName("Another Function");
        functionEntity2.setCount(3);
        functionEntity2.setXFrom(1.0);
        functionEntity2.setXTo(5.0);
        mathFunctionRepository.save(functionEntity2);

        // Act
        List<MathFunctionEntity> foundEntities = mathFunctionRepository.findByMathFunctionName("Test Function");

        // Assert
        assertEquals(1, foundEntities.size());
        assertEquals("Test Function", foundEntities.get(0).getMathFunctionName());
    }
}
