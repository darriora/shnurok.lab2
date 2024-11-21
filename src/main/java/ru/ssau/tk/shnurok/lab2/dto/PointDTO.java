package ru.ssau.tk.shnurok.lab2.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PointDTO {
    private int id;
    private int functionId;
    private double xVal;
    private double yVal;
}
