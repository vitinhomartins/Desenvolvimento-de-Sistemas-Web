package com.biblioteca.biblioteca.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "leitores")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Leitor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "O apelido é obrigatório")
    @Size(min = 3, max = 50,
            message = "O apelido deve ter entre 3 e 50 caracteres")
    @Column(nullable = false, length = 50)
    private String apelido;

    @Size(max = 500,
            message = "A biografia deve possuir no máximo 500 caracteres")
    @Column(length = 500)
    private String biografia;

    @NotNull(message = "A meta anual de livros é obrigatória")
    @Min(value = 1,
            message = "A meta anual deve ser no mínimo 1 livro")
    @Column(nullable = false)
    private Integer metaAnualLivros;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    @OneToOne
    @JoinColumn(name = "usuario_id", nullable = false, unique = true)
    @JsonBackReference
    private Usuario usuario;

    @OneToMany(mappedBy = "leitor",
            cascade = CascadeType.ALL,
            orphanRemoval = true)
    @JsonManagedReference
    private List<Livro> livros;

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