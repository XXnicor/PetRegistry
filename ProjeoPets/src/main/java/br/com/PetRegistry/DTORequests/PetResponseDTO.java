package br.com.PetRegistry.DTORequests;

import br.com.PetRegistry.model.PetStatus;
import br.com.PetRegistry.model.PetType;
import br.com.PetRegistry.model.Porte;
import br.com.PetRegistry.model.SexType;

import java.time.LocalDate;

public class PetResponseDTO {
    private final long id;
    private final String nomeCompleto;
    private final String fotoUrl;
    private final PetType tipo;
    private final SexType sexo;
    private final Porte porte;
    private final PetStatus status;
    private final int idadeAproximada;
    private final LocalDate dataEntrada;
    private final String observacoes;

    public PetResponseDTO(long id,
                          String nomeCompleto,
                          String fotoUrl,
                          PetType tipo,
                          SexType sexo,
                          Porte porte,
                          PetStatus status,
                          int idadeAproximada,
                          LocalDate dataEntrada,
                          String observacoes) {
        this.id = id;
        this.nomeCompleto = nomeCompleto;
        this.fotoUrl = fotoUrl;
        this.tipo = tipo;
        this.sexo = sexo;
        this.porte = porte;
        this.status = status;
        this.idadeAproximada = idadeAproximada;
        this.dataEntrada = dataEntrada;
        this.observacoes = observacoes;
    }

    public long getId() { return id; }
    public String getNomeCompleto() { return nomeCompleto; }
    public String getFotoUrl() { return fotoUrl; }
    public PetType getTipo() { return tipo; }
    public SexType getSexo() { return sexo; }
    public Porte getPorte() { return porte; }
    public PetStatus getStatus() { return status; }
    public int getIdadeAproximada() { return idadeAproximada; }
    public LocalDate getDataEntrada() { return dataEntrada; }
    public String getObservacoes() { return observacoes; }
}
