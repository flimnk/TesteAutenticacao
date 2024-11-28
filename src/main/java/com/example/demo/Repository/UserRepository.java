package com.example.demo.Repository;

import com.example.demo.Domain.User.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


public interface UserRepository extends JpaRepository<User,Long> {
   Optional<User> findByEmail(String email);

   Optional<User> findByUsername(String admin);
}
