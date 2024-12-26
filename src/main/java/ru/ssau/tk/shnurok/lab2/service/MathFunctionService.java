package ru.ssau.tk.shnurok.lab2.service;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.ssau.tk.shnurok.lab2.dto.MathFunctionDTO;
import ru.ssau.tk.shnurok.lab2.entity.MathFunctionEntity;
import ru.ssau.tk.shnurok.lab2.repository.MathFunctionRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MathFunctionService {

    private final MathFunctionRepository mathFunctionRepository;

    private final MathFunctionMapper mathFunctionMapper;

    private final PointMapper pointMapper;

    @EntityGraph(attributePaths = "points")
    public List<MathFunctionDTO> findAllFunctions(String functionType) {
        if (functionType != null && !functionType.isBlank()) {
            return this.mathFunctionRepository.findByMathFunctionName(functionType)
                    .stream()
                    .map(mathFunctionMapper::toDTO).collect(Collectors.toList());
        } else {
            return this.mathFunctionRepository.findAll()
                    .stream()
                    .map(mathFunctionMapper::toDTO).collect(Collectors.toList());
        }
    }

    @Transactional
    public MathFunctionDTO update(MathFunctionDTO functionDTO) {
        MathFunctionEntity functionEntity = mathFunctionRepository.findById(functionDTO.getId())
                .orElseThrow(()-> new EntityNotFoundException("There are no function with that id: "+functionDTO.getId()));

        if(functionDTO.getMathFunctionName()!=null){
            functionEntity.setMathFunctionName(functionDTO.getMathFunctionName());
        }

        if(0 != functionDTO.getXFrom()){
            functionEntity.setXFrom(functionDTO.getXFrom());
        }

        if(functionDTO.getXTo() != 0){
            functionEntity.setXTo(functionDTO.getXTo());
        }

        if(functionDTO.getPoints() != null){
            functionEntity.getPoints().clear();
            functionEntity.getPoints().addAll(
                    functionDTO.getPoints().stream()
                            .map(pointDTO ->pointMapper.toEntityWithFunction(pointDTO,functionEntity)).toList()
            );
        }

        MathFunctionEntity newFunction = mathFunctionRepository.save(functionEntity);

        return mathFunctionMapper.toDTO(newFunction);
    }

    public MathFunctionDTO read(int id) {
        return this.mathFunctionRepository
                .findById(id)
                .map(mathFunctionMapper::toDTO)
                .orElse(null);
    }

    @Transactional
    public MathFunctionDTO create(MathFunctionDTO functionDTO) {
        MathFunctionEntity functionEntity = mathFunctionMapper.toEntity(functionDTO);
        MathFunctionEntity editedFunction = this.mathFunctionRepository.save(functionEntity);

        return mathFunctionMapper.toDTO(editedFunction);
    }

    public void delete(int id) {
        this.mathFunctionRepository.deleteById(id);
    }

}
