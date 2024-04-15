package com.plataforma.serviciosProfesionales.persistence.repository;

import com.plataforma.serviciosProfesionales.persistence.entitys.Usuario;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
@Repository
public interface UsuarioRepository extends CrudRepository<Usuario, Long> {

    Optional<Usuario> findUsuarioByUsername(String username);

    @Query(value = "SELECT * FROM usuario", nativeQuery = true)
    List<Usuario> findAllUsuarios();
}
