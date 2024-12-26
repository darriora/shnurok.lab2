package ru.ssau.tk.shnurok.lab2.entity;


import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;

@Entity
@Table(schema ="labs", name = "functions")
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString(exclude = "points")
public class MathFunctionEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private int id;

    @Column(name = "c_function_name")
    @NotNull
    private String mathFunctionName;

    @Column(name = "c_count")
    private int count;

    @Column(name = "c_x_from")
    private Double xFrom;

    @Column(name = "c_x_to")
    private Double xTo;

    @OneToMany(mappedBy = "functionEntity", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private List<PointEntity> points;

}
