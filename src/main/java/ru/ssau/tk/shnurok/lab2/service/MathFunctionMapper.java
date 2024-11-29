package ru.ssau.tk.shnurok.lab2.service;

import ru.ssau.tk.shnurok.lab2.dto.MathFunctionDTO;
import ru.ssau.tk.shnurok.lab2.entity.MathFunctionEntity;

public class MathFunctionMapper {

    public static MathFunctionDTO functionEntityToDTO(MathFunctionEntity entity) {
        if (entity == null) {
            return null;
        }

        MathFunctionDTO dto = new MathFunctionDTO();
        dto.setId(entity.getId());
        dto.setFunctionName(entity.getMathFunctionName());
        dto.setCount(entity.getCount());
        dto.setXFrom(entity.getXFrom() != null ? entity.getXFrom() : 0.0);
        dto.setXTo(entity.getXTo() != null ? entity.getXTo() : 0.0);

        return dto;
    }

    public static MathFunctionEntity functionDTOToFunctionEntity(MathFunctionDTO dto) {
        if (dto == null) {
            return null;
        }

        MathFunctionEntity entity = new MathFunctionEntity();
        entity.setId(dto.getId());
        entity.setMathFunctionName(dto.getFunctionName());
        entity.setCount(dto.getCount());
        entity.setXFrom(dto.getXFrom());
        entity.setXTo(dto.getXTo());

        return entity;
    }
}
