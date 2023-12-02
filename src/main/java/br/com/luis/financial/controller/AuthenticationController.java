package br.com.luis.financial.controller;

import br.com.luis.financial.domain.user.AuthenticationDTO;
import br.com.luis.financial.infra.security.TokenJWTData;
import br.com.luis.financial.infra.security.TokenService;
import br.com.luis.financial.models.User;
import br.com.luis.financial.repositories.UserRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthenticationController {

    @Autowired
    private TokenService tokenService;

    @Autowired
    private UserRepository repository;

    @Autowired
    private AuthenticationManager manager;

    @PostMapping("/login")
    public ResponseEntity login(@RequestBody @Valid AuthenticationDTO data) {
        var token = new UsernamePasswordAuthenticationToken(data.login(), data.password());
        var auth = manager.authenticate(token);

        var tokenJWT = tokenService.generateToken((User) auth.getPrincipal());
        return ResponseEntity.ok(new TokenJWTData(tokenJWT));
    }

    @PostMapping("/register")
    public ResponseEntity register(@RequestBody @Valid AuthenticationDTO data) {
        if (this.repository.findByLogin(data.login()) != null) return ResponseEntity.badRequest().build();

        String encryptedPassword = new BCryptPasswordEncoder().encode(data.password());

        User newUser = new User(data.login(), encryptedPassword);

        this.repository.save(newUser);

        return ResponseEntity.ok().build();

    }
}
