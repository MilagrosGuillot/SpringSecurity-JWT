package com.plataforma.serviciosProfesionales.persistence.repository;

import com.plataforma.serviciosProfesionales.persistence.entitys.Rol;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RolRepository extends CrudRepository<Rol, Long> {

    List<Rol> findRolByRolEnumIn(List<String> roleNames);
}