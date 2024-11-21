package ru.ssau.tk.shnurok.lab2.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(schema = "labs", name = "point")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PointEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name = "function_id", nullable = false)
    private MathFunctionEntity functionEntity;

    @Column(name = "c_x_val")
    private Double xVal;

    @Column(name = "c_y_val")
    private Double yVal;

}