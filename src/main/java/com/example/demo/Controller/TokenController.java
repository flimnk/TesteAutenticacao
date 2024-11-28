package com.example.demo.Controller;

import com.example.demo.Domain.User.UserResponse;
import com.example.demo.Domain.User.UserReuquest;
import com.example.demo.Repository.UserRepository;
import com.example.demo.Service.AutenticacaoService;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.time.Instant;

@RestController
public class TokenController {

    @Autowired
    private JwtEncoder jwtEncoder;
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;




    @PostMapping("/login")
    public ResponseEntity<UserResponse> login(@RequestBody UserReuquest userReuquest) {

        var user = userRepository.findByEmail(userReuquest.email());

        if (user.isEmpty() || !user.get().isLoginCorrect(userReuquest, passwordEncoder)) {
            throw new BadCredentialsException("user or password is invalid!");
        }
        var now = Instant.now();
        var expiresIn = 300L;
        var claims = JwtClaimsSet.builder().
                issuer("mybackend")
                .subject(user.get().getUserId().toString()).
                issuedAt(now).
                expiresAt(now.plusSeconds(expiresIn)).
                build();
        var jwtValue = jwtEncoder.encode(JwtEncoderParameters.from(claims)).getTokenValue();

        return ResponseEntity.ok(new UserResponse(jwtValue, expiresIn));


    }
}
