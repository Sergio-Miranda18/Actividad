package com.example.Activdad.Persistence.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Vehiculo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String placa;

    private boolean estado = true;

    @Column
    private String tipo; // Carro o Camión

    @OneToOne
    private Conductor conductor; // Relación con Conductor
}
