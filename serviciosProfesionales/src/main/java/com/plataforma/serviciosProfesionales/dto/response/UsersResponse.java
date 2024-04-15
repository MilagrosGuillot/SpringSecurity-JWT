package com.plataforma.serviciosProfesionales.dto.response;

import com.plataforma.serviciosProfesionales.persistence.entitys.Profesional;
import com.plataforma.serviciosProfesionales.persistence.entitys.Rol;
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
public class UsersResponse {
    private String username;
    private String password;
    private String Correo;
    private boolean isEnabled;
    private boolean accountNoExpired;
    private boolean accountNoLocked;
    private boolean credentialNoExpired;
    private Set<Rol> roles = new HashSet<>();
    private Set<Profesional> profesional = new HashSet<>();
}
