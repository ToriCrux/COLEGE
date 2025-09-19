package org.example.api.controller;

import lombok.RequiredArgsConstructor;
import org.example.domain.entity.Professor;
import org.example.repository.ProfessorRepository;
import org.example.security.JwtUtil;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final ProfessorRepository professorRepository;
    private final JwtUtil jwtUtil;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest) {
        Optional<Professor> professorOpt = professorRepository.findByEmail(loginRequest.email());

        if (professorOpt.isPresent()) {
            String senhaBanco = professorOpt.get().getSenha();
            String senhaDigitada = loginRequest.senha();

            if (senhaBanco != null && senhaDigitada != null &&
                    senhaBanco.trim().equals(senhaDigitada.trim())) {

                String token = jwtUtil.gerarToken(loginRequest.email());
                return ResponseEntity.ok(new TokenResponse(token));
            }
        }

        return ResponseEntity.status(403).body("Credenciais inválidas");
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody LoginRequest loginRequest) {
        Professor novo = new Professor();
        novo.setNome("Novo Professor");
        novo.setEmail(loginRequest.email());
        novo.setSenha(loginRequest.senha());

        professorRepository.save(novo);
        return ResponseEntity.ok("Professor criado com sucesso!");
    }

    public record LoginRequest(String email, String senha) {}
    public record TokenResponse(String token) {}
}
