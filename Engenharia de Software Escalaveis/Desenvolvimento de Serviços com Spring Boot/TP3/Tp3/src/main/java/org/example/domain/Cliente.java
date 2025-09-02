package org.example.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;

@Entity
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
@Table(name = "cliente")
public class Cliente {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank @Column(nullable = false, length = 120)
    private String nome;

    @Email @NotBlank @Column(nullable = false, length = 160)
    private String email;

    @NotBlank @Column(nullable = false, unique = true, length = 20)
    private String documento;

    @NotBlank @Column(nullable = false, length = 20)
    private String telefone;
}
