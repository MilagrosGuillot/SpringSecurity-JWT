package com.plataforma.serviciosProfesionales.persistence.entitys;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
@Data
public class Profesional {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String username;

    private String password;

    private String Correo;

    private String DNI;

    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Calificaciones calificaciones;

    @ManyToMany(fetch = FetchType.EAGER,cascade = CascadeType.ALL)
    @JoinTable(name = "Profesionales_Categorias", joinColumns = @JoinColumn(name = "profesional_id"), inverseJoinColumns = @JoinColumn(name = "categoriasDeTrabajos_id"))
    private Set<CategoriasDeTrabajo> categoriasDeTrabajos = new HashSet<>();
}
