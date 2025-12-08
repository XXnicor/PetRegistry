package br.com.PetRegistry.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "evento")
public class Evento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private LocalDateTime data;

    @Column(nullable = false)
    private String tipoEvento;

    private String descricao;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "pet_id", nullable = false)
    private Pet pet;

    protected Evento() {
    }

    public Evento(Pet pet, String tipoEvento, String descricao) {
        this.pet = pet;
        this.tipoEvento = tipoEvento;
        this.descricao = descricao;
        this.data = LocalDateTime.now(); // A data é sempre a de criação
    }

    // Getters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getData() {
        return data;
    }

    public String getTipoEvento() {
        return tipoEvento;
    }

    public String getDescricao() {
        return descricao;
    }

    public Pet getPet() {
        return pet;
    }

    public void setTipoEvento(String tipoEvento) {
        this.tipoEvento = tipoEvento;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
}