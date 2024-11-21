package ru.ssau.tk.shnurok.lab2.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.ssau.tk.shnurok.lab2.entity.MathFunctionEntity;

import java.util.List;

@Repository
public interface MathFunctionRepository extends JpaRepository<MathFunctionEntity, Integer> {
    List<MathFunctionEntity> findByMathFunctionName(String mathFunctionName);
}
