package com.example.demo.Repository;

import com.example.demo.Domain.Barbeiro.Barbeiro;
import com.example.demo.Domain.User.User;
import org.springframework.data.jpa.repository.JpaRepository;


public interface BarbeiroRepository extends JpaRepository<Barbeiro,Long> {
}
