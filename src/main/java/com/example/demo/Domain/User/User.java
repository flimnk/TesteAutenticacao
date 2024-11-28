package com.example.demo.Domain.User;


import com.example.demo.Domain.Barbeiro.Barbeiro;
import com.example.demo.Domain.Cliente.Cliente;
import com.example.demo.Domain.Role.Role;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.security.crypto.password.PasswordEncoder;


import java.time.Instant;
import java.util.Set;
import java.util.UUID;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "tb_users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;

    @Column(unique = true, nullable = false)
    private String username;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false, unique = true)
    private String email;

    @OneToOne(fetch = FetchType.LAZY, optional = true)
    @JoinColumn(name = "cliente_id", referencedColumnName = "id")
    private Cliente cliente; // Relacionamento com Cliente

    @OneToOne(fetch = FetchType.LAZY, optional = true)
    @JoinColumn(name = "barbeiro_id", referencedColumnName = "id")
    private Barbeiro barbeiro; // Relacionamento com Barbeiro

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private TipoUsuario tipoUsuario;

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(
            name = "tb_users_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id")
    )
    private Set<Role> roles;

    @CreationTimestamp
    private Instant creationTimestamp;

    public boolean isLoginCorrect(UserReuquest userRequest, PasswordEncoder passwordEncoder) {
        return passwordEncoder.matches(userRequest.password(), this.password);
    }
}


