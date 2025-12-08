package br.com.PetRegistry.model;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.util.Objects;

@Entity
@Table(name = "pet")
public class Pet {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private PetStatus status;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "lar_temporario_id")
    private LarTemporario larTemporario;

    @Embedded
    private PetProfile profile;

    protected Pet() {
    }

    public Pet(PetStatus status, PetProfile profile, LarTemporario larTemporario) {
        this.status = status;
        this.profile = Objects.requireNonNull(profile, "Perfil do pet obrigatório");
        this.larTemporario = larTemporario;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public PetStatus getStatus() {
        return status;
    }

    public LarTemporario getLarTemporario() {
        return larTemporario;
    }

    public PetProfile getProfile() {
        return profile;
    }

    // Setters para campos que podem mudar
    public void setStatus(PetStatus status) {
        this.status = status;
    }

    public void setLarTemporario(LarTemporario larTemporario) {
        this.larTemporario = larTemporario;
    }

    public void atualizarPerfil(PetProfile novoPerfil) {
        this.profile = Objects.requireNonNull(novoPerfil, "Perfil do pet obrigatório");
    }


    public String getNomeCompleto() {
        return profile.getNomeCompleto();
    }

    public String getFotoUrl() {
        return profile.getFotoUrl();
    }

    public PetType getTipo() {
        return profile.getTipo();
    }

    public SexType getSexo() {
        return profile.getSexo();
    }

    public Porte getPorte() {
        return profile.getPorte();
    }

    public int getIdadeAproximada() {
        return profile.getIdadeAproximada();
    }

    public LocalDate getDataEntrada() {
        return profile.getDataEntrada();
    }

    public String getHistoricoMedico() {
        return profile.getHistoricoMedico();
    }

    public String getObservacoes() {
        return profile.getObservacoes();
    }

    public boolean isCastrado() {
        return profile.isCastrado();
    }

    public boolean isVacinado() {
        return profile.isVacinado();
    }

    public boolean isSociavelComCaes() {
        return profile.isSociavelComCaes();
    }

    public boolean isSociavelComGatos() {
        return profile.isSociavelComGatos();
    }

    public boolean isSociavelComCriancas() {
        return profile.isSociavelComCriancas();
    }

    // Método auxiliar para obter o ID do lar temporário (pode ser null)
    public Long getLarTemporarioId() {
        return larTemporario != null ? larTemporario.getId() : null;
    }

    @Override
    public String toString() {
        return "Pet{" +
                "id=" + id +
                ", status=" + status +
                ", larTemporarioId=" + (larTemporario != null ? larTemporario.getId() : "null") +
                ", profile=" + profile +
                '}';
    }
}