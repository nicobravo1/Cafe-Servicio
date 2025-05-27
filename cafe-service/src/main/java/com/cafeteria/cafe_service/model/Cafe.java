package com.cafeteria.cafe_service.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Cafe {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;
    private String tipo;      // ej: espresso, latte, americano
    private String tamaño;    // ej: chico, mediano, grande
    private double precio;
}