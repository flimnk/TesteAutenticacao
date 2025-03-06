package com.example.demo.dto.user;


import com.example.demo.Domain.Cliente.ClienteResponse;
import com.example.demo.Domain.User.TipoUsuario;

public record UserGetResponse(
        Long userId,
        String username,
        String email,
        TipoUsuario tipoUsuario,
        ClienteResponse cliente,       // Preenchido se for CLIENTE
        BarbeiroResponse barbeiro      // Preenchido se for BARBEIRO
) { }
