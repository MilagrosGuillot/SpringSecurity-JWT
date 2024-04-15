package com.plataforma.serviciosProfesionales.persistence.entitys;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Data
@Table(name = "categoriasdetrabajo")
public class CategoriasDeTrabajo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Categorias categorias;
    private String Descripcion;
}
