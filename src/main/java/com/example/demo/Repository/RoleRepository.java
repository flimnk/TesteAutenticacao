package com.example.demo.Repository;

import com.example.demo.Domain.Role.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface RoleRepository extends JpaRepository<Role, Long> {
   Role findByName(String name);
}
