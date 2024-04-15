package com.plataforma.serviciosProfesionales.dto.response;

import com.plataforma.serviciosProfesionales.persistence.entitys.Categorias;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class CategorysResponse {
    private Categorias categorias;
    private String Descripcion;
}
