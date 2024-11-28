package com.example.demo.infra.Security;

import com.example.demo.Domain.Role.Role;
import com.example.demo.Domain.User.TipoUsuario;
import com.example.demo.Domain.User.User;
import com.example.demo.Repository.RoleRepository;
import com.example.demo.Repository.UserRepository;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;

@Configuration
public class AdminUserConfig implements CommandLineRunner {

    private final RoleRepository roleRepository;
    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    public AdminUserConfig(RoleRepository roleRepository,
                           UserRepository userRepository,
                           BCryptPasswordEncoder passwordEncoder) {
        this.roleRepository = roleRepository;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    @Transactional
    public void run(String... args) throws Exception {

        var roleAdmin = roleRepository.findByName(Role.Values.ADMIN.name());

        System.out.println(roleAdmin);
        var userAdmin = userRepository.findByUsername("admin");


        System.out.println(userAdmin.isEmpty());
        userAdmin.ifPresentOrElse(
                user -> {
                    System.out.println("admin ja existe");
                },
                () -> {

                    var user = new User();
                    System.out.println("parte 1");
                    user.setUsername("admin");
                    user.setEmail("admin@gmail.com");
                    user.setTipoUsuario(TipoUsuario.ADIMIN);

                    System.out.println("parte 2");
                    user.setPassword(passwordEncoder.encode("123"));
                    System.out.println("parte 3");
                    user.setRoles(Set.of(roleAdmin));
                    System.out.println("parte 5");
                    System.out.println(user);
                    userRepository.save(user);
                }
        );
        System.out.println("oi");
    }

}