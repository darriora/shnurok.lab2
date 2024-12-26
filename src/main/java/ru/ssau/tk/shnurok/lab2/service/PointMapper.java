package ru.ssau.tk.shnurok.lab2.service;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.stereotype.Component;
import ru.ssau.tk.shnurok.lab2.dto.PointDTO;
import ru.ssau.tk.shnurok.lab2.entity.MathFunctionEntity;
import ru.ssau.tk.shnurok.lab2.entity.PointEntity;
@Component
@Mapper(componentModel = "spring")
public interface PointMapper {

    @Mapping(target = "functionEntity", ignore = true)
    PointEntity toEntity(PointDTO pointDTO);

    @Mapping(source = "functionEntity.id",target = "functionId")
    PointDTO toDTO(PointEntity entity);

    default PointEntity toEntityWithFunction(PointDTO pointDTO, MathFunctionEntity mathFunctionEntity) {
        PointEntity entity = toEntity(pointDTO);
        entity.setFunctionEntity(mathFunctionEntity);
        return entity;
    }
}