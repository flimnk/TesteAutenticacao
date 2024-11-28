package com.example.demo.Domain.Barbeiro;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
@Entity
@Getter
@Setter
@AllArgsConstructor
@Table(name = "tb_barbeiros")
public class Barbeiro{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  Long id;

    private String nome;

    private String telefone;

    private String cpf;


}
