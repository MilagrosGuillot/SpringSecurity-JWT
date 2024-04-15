package com.plataforma.serviciosProfesionales.controller;

import com.plataforma.serviciosProfesionales.dto.response.UsersResponse;
import com.plataforma.serviciosProfesionales.services.UserDetailServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/users")

public class UsersController {
    @Autowired
    private UserDetailServiceImpl userDetailService;

    @GetMapping()
    public ResponseEntity<List<UsersResponse>> getUsuario() {
        List<UsersResponse> responses = userDetailService.getAllUsers();

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(responses);
    }


}
