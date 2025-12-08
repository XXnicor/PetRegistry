package br.com.PetRegistry.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

import java.time.LocalDate;
import java.util.Objects;

@Entity
public final class PetProfile {
    @Id
    private Long id;
    private String nomeCompleto;
    private String fotoUrl;
    private PetType tipo;
    private SexType sexo;
    private Porte porte;
    private int idadeAproximada;
    private LocalDate dataEntrada;
    private String historicoMedico;
    private String observacoes;
    private boolean castrado;
    private boolean vacinado;
    private boolean sociavelComCaes;
    private boolean sociavelComGatos;
    private boolean sociavelComCriancas;

    public PetProfile(String nomeCompleto,
                      String fotoUrl,
                      PetType tipo,
                      SexType sexo,
                      Porte porte,
                      int idadeAproximada,
                      LocalDate dataEntrada,
                      String historicoMedico,
                      String observacoes,
                      boolean castrado,
                      boolean vacinado,
                      boolean sociavelComCaes,
                      boolean sociavelComGatos,
                      boolean sociavelComCriancas) {
        if (nomeCompleto == null || nomeCompleto.isBlank()) {
            throw new IllegalArgumentException("Nome do pet é obrigatório");
        }
        this.nomeCompleto = nomeCompleto;
        this.fotoUrl = fotoUrl;
        this.tipo = Objects.requireNonNull(tipo, "Tipo do pet é obrigatório");
        this.sexo = Objects.requireNonNull(sexo, "Sexo do pet é obrigatório");
        this.porte = Objects.requireNonNull(porte, "Porte do pet é obrigatório");
        if (idadeAproximada < 0) {
            throw new IllegalArgumentException("Idade não pode ser negativa");
        }
        this.idadeAproximada = idadeAproximada;
        this.dataEntrada = Objects.requireNonNull(dataEntrada, "Data de entrada é obrigatória");
        this.historicoMedico = historicoMedico;
        this.observacoes = observacoes;
        this.castrado = castrado;
        this.vacinado = vacinado;
        this.sociavelComCaes = sociavelComCaes;
        this.sociavelComGatos = sociavelComGatos;
        this.sociavelComCriancas = sociavelComCriancas;
    }

    public PetProfile() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNomeCompleto() {
        return nomeCompleto;
    }

    public String getFotoUrl() {
        return fotoUrl;
    }

    public PetType getTipo() {
        return tipo;
    }

    public SexType getSexo() {
        return sexo;
    }

    public Porte getPorte() {
        return porte;
    }

    public int getIdadeAproximada() {
        return idadeAproximada;
    }

    public LocalDate getDataEntrada() {
        return dataEntrada;
    }

    public String getHistoricoMedico() {
        return historicoMedico;
    }

    public String getObservacoes() {
        return observacoes;
    }

    public boolean isCastrado() {
        return castrado;
    }

    public boolean isVacinado() {
        return vacinado;
    }

    public boolean isSociavelComCaes() {
        return sociavelComCaes;
    }

    public boolean isSociavelComGatos() {
        return sociavelComGatos;
    }

    public boolean isSociavelComCriancas() {
        return sociavelComCriancas;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof PetProfile that)) return false;
        return idadeAproximada == that.idadeAproximada &&
                castrado == that.castrado &&
                vacinado == that.vacinado &&
                sociavelComCaes == that.sociavelComCaes &&
                sociavelComGatos == that.sociavelComGatos &&
                sociavelComCriancas == that.sociavelComCriancas &&
                Objects.equals(nomeCompleto, that.nomeCompleto) &&
                Objects.equals(fotoUrl, that.fotoUrl) &&
                tipo == that.tipo &&
                sexo == that.sexo &&
                porte == that.porte &&
                Objects.equals(dataEntrada, that.dataEntrada) &&
                Objects.equals(historicoMedico, that.historicoMedico) &&
                Objects.equals(observacoes, that.observacoes);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nomeCompleto, fotoUrl, tipo, sexo, porte, idadeAproximada, dataEntrada,
                historicoMedico, observacoes, castrado, vacinado, sociavelComCaes, sociavelComGatos,
                sociavelComCriancas);
    }

    @Override
    public String toString() {
        return "PetProfile{" +
                "nomeCompleto='" + nomeCompleto + '\'' +
                ", tipo=" + tipo +
                ", sexo=" + sexo +
                ", porte=" + porte +
                ", idadeAproximada=" + idadeAproximada +
                ", dataEntrada=" + dataEntrada +
                '}';
    }
}

