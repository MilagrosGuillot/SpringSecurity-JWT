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
@Data
@Builder

public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String username;

    private String password;

    private String Correo;

    @Column(name = "is_enabled")
    private boolean isEnabled;

    @Column(name = "account_No_Expired")
    private boolean accountNoExpired;

    @Column(name = "account_No_Locked")
    private boolean accountNoLocked;

    @Column(name = "credential_No_Expired")
    private boolean credentialNoExpired;

   @ManyToMany(fetch = FetchType.EAGER,cascade = CascadeType.ALL)
   @JoinTable(name = "Usuarios_Roles", joinColumns = @JoinColumn(name = "usuario_id"), inverseJoinColumns = @JoinColumn(name = "roles_id"))
   private Set<Rol>roles = new HashSet<>();

    @ManyToMany(fetch = FetchType.EAGER,cascade = CascadeType.ALL)
    @JoinTable(name = "Usuarios_profesionales", joinColumns = @JoinColumn(name = "usuario_id"), inverseJoinColumns = @JoinColumn(name = "profesional_id"))
    private Set<Profesional> profesional = new HashSet<>();


}
