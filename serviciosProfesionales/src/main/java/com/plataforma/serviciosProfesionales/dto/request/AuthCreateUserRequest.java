package com.plataforma.serviciosProfesionales.dto.request;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;

public record AuthCreateUserRequest(@NotBlank(message = "Please add your name") String username,
                                    @NotBlank(message = "Please add your password") String password,
                                    @Valid AuthCreateRoleRequest roleRequest) {
}
