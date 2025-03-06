package com.example.demo.Domain.User;

import jakarta.validation.constraints.NotBlank;

public record CadastroUserClienteRequest(
        @NotBlank String email , @NotBlank String password , @NotBlank String username,  @NotBlank String telefone ) {
}
