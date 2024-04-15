package com.plataforma.serviciosProfesionales.dto.request;

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
public class ProfesionalRequest {
    private Long id;

    private String username;

    private String password;

    private String Correo;

    private String DNI;

    private Set<CategoriasDeTrabajo> categoriasDeTrabajos = new HashSet<>();
}
