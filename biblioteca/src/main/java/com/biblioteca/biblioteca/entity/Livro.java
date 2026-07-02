package com.biblioteca.biblioteca.entity;

import com.biblioteca.biblioteca.enums.StatusLeitura;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "livros")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Livro {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "O título é obrigatório")
    @Size(min = 2, max = 150, message = "O título deve ter entre 2 e 150 caracteres")
    @Column(nullable = false, length = 150)
    private String titulo;

    @NotBlank(message = "O autor é obrigatório")
    @Size(min = 2, max = 100, message = "O autor deve ter entre 2 e 100 caracteres")
    @Column(nullable = false, length = 100)
    private String autor;

    @NotBlank(message = "O gênero é obrigatório")
    @Size(max = 50, message = "O gênero deve ter no máximo 50 caracteres")
    @Column(nullable = false, length = 50)
    private String genero;

    @NotNull(message = "O ano de publicação é obrigatório")
    @Min(value = 1000, message = "O ano de publicação deve ser válido")
    @Max(value = 2100, message = "O ano de publicação não pode ser maior que 2100")
    @Column(nullable = false)
    private Integer anoPublicacao;

    @NotNull(message = "O status de leitura é obrigatório")
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private StatusLeitura statusLeitura;

    @Min(value = 0, message = "A nota mínima é 0")
    @Max(value = 5, message = "A nota máxima é 5")
    private Integer nota;

    @Size(max = 1000, message = "O comentário deve ter no máximo 1000 caracteres")
    @Column(columnDefinition = "TEXT")
    private String comentario;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    @ManyToOne
    @JoinColumn(name = "leitor_id", nullable = false)
    @JsonBackReference
    private Leitor leitor;

    @PrePersist
    public void prePersist() {
        createdAt = LocalDateTime.now();
        updatedAt = LocalDateTime.now();
    }

    @PreUpdate
    public void preUpdate() {
        updatedAt = LocalDateTime.now();
    }
}