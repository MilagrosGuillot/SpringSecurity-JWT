package com.plataforma.serviciosProfesionales.persistence.repository;

import com.plataforma.serviciosProfesionales.persistence.entitys.Profesional;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface ProfesionalRepository extends CrudRepository<Profesional, Long> {
    List<Profesional> findAll();



}

