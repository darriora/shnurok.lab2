package ru.ssau.tk.shnurok.lab2.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MathFunctionDTO {
    private int id;
    private String mathFunctionName;
    private int count;
    private double xFrom;
    private double xTo;
    private List<PointDTO> points;

}
