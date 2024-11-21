package ru.ssau.tk.shnurok.lab2.repository;

import org.hibernate.type.descriptor.converter.spi.JpaAttributeConverter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.ssau.tk.shnurok.lab2.entity.MathFunctionEntity;
import ru.ssau.tk.shnurok.lab2.entity.PointEntity;

import java.util.List;

@Repository
public interface PointRepository extends JpaRepository<PointEntity, Integer> {
    List<PointEntity> findByFunctionEntity(MathFunctionEntity functionEntity);
}
