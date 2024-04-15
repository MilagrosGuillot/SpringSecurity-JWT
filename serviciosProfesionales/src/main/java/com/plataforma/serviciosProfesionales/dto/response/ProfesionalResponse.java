package com.plataforma.serviciosProfesionales.dto.response;

import com.plataforma.serviciosProfesionales.persistence.entitys.Calificaciones;
import com.plataforma.serviciosProfesionales.persistence.entitys.CategoriasDeTrabajo;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class ProfesionalResponse {
    private Long id;

    private String username;

    private String password;

    private String Correo;

    private String DNI;

    private Calificaciones calificaciones;

    private Set<CategoriasDeTrabajo> categoriasDeTrabajos = new HashSet<>();
}