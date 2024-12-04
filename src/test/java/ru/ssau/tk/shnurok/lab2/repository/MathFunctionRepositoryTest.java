package ru.ssau.tk.shnurok.lab2.repository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;
import ru.ssau.tk.shnurok.lab2.entity.MathFunctionEntity;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.ANY) // Используем встраиваемую базу данных
public class MathFunctionRepositoryTest {

    @Autowired
    private MathFunctionRepository mathFunctionRepository;

    @BeforeEach
    public void setUp() {
        // Очистка базы данных перед каждым тестом
        mathFunctionRepository.deleteAll();
    }

    @Test
    @Rollback
    public void testFindByMathFunctionName() {
        // Создание и сохранение сущности
        MathFunctionEntity function1 = new MathFunctionEntity();
        function1.setMathFunctionName("Linear");
        mathFunctionRepository.save(function1);

        MathFunctionEntity function2 = new MathFunctionEntity();
        function2.setMathFunctionName("Quadratic");
        mathFunctionRepository.save(function2);

        // Поиск по имени функции
        List<MathFunctionEntity> foundFunctions = mathFunctionRepository.findByMathFunctionName("Linear");

        // Проверка, что функция найдена
        assertThat(foundFunctions).hasSize(1);
        assertThat(foundFunctions.get(0).getMathFunctionName()).isEqualTo("Linear");
    }

    @Test
    @Rollback
    public void testFindByMathFunctionName_NoResult() {
        // Поиск по имени функции, которая не существует
        List<MathFunctionEntity> foundFunctions = mathFunctionRepository.findByMathFunctionName("NonExistent");

        // Проверка, что результат пустой
        assertThat(foundFunctions).isEmpty();
    }
}
