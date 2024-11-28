package com.example.demo.Domain.User;


import jakarta.validation.constraints.NotBlank;

public record UserReuquest(
        @NotBlank
        String email ,

        @NotBlank
        String password) {


}
