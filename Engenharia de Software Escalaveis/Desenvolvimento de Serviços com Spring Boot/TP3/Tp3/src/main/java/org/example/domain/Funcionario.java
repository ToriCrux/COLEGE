package org.example.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
@Table(name = "funcionario")
public class Funcionario {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank @Column(nullable = false, length = 120)
    private String nome;

    @Email @NotBlank @Column(nullable = false, unique = true, length = 160)
    private String email;

    @NotBlank @Column(nullable = false, length = 80)
    private String cargo; // ex.: vendedor, estoquista

    @Positive @Column(nullable = false, precision = 14, scale = 2)
    private BigDecimal salario;

    @PastOrPresent @Column(nullable = false)
    private LocalDate dataAdmissao;

    @ManyToOne(optional = true)
    private Departamento departamento; // seção em que trabalha
}
