package ru.ssau.tk.shnurok.lab2.service;

import org.mapstruct.*;
import org.springframework.stereotype.Component;
import ru.ssau.tk.shnurok.lab2.dto.MathFunctionDTO;
import ru.ssau.tk.shnurok.lab2.dto.PointDTO;
import ru.ssau.tk.shnurok.lab2.entity.MathFunctionEntity;
import ru.ssau.tk.shnurok.lab2.entity.PointEntity;

import java.util.ArrayList;
import java.util.stream.Collectors;
@Component
@Mapper(componentModel = "spring",uses = {PointMapper.class})
public interface MathFunctionMapper {
    @Mapping(target="points")
    MathFunctionEntity toEntity(MathFunctionDTO dto);
    MathFunctionDTO toDTO(MathFunctionEntity entity);

    @AfterMapping
    default void mapPoints(@MappingTarget MathFunctionEntity mathFunctionEntity, MathFunctionDTO mathFunctionDTO,
                           @Context PointMapper pointMapper){
        if (mathFunctionDTO.getPoints()!= null){
            if(mathFunctionEntity.getPoints() == null){
                mathFunctionEntity.setPoints(new ArrayList<>());
            }
            mathFunctionEntity.getPoints().addAll(mathFunctionDTO.getPoints().stream()
                    .map(pointDTO -> pointMapper.toEntityWithFunction(pointDTO,mathFunctionEntity))
                    .collect(Collectors.toList()));
        }
    }

    default PointEntity toEntityWithFunction(PointDTO pointDTO,MathFunctionEntity mathFunctionEntity){
        PointEntity pointEntity= new PointEntity();
        pointEntity.setXVal(pointDTO.getXVal());
        pointEntity.setYVal(pointDTO.getYVal());
        pointEntity.setFunctionEntity(mathFunctionEntity);
        return pointEntity;
    }


}
