package com.plataforma.serviciosProfesionales.controller;

import com.plataforma.serviciosProfesionales.dto.response.ProfesionalResponse;
import com.plataforma.serviciosProfesionales.services.UserDetailServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/profesionals")

public class ProfesionalController {

    @Autowired
    private UserDetailServiceImpl userDetailService;

    @GetMapping()
    public ResponseEntity<List<ProfesionalResponse>> getProfesionals() {
        List<ProfesionalResponse> responses = userDetailService.getAllProfesionals();

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(responses);
    }

    //   @GetMapping("/{categoria}")
    //   public ResponseEntity<List<Categorias>> getCategory(@PathVariable String categoria) {
    //     List<Categorias> responses = userDetailService.getcategory(categoria);

    //  return ResponseEntity
    //          .status(HttpStatus.OK)
    //        .body(responses);
    //   }
}
