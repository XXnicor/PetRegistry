package br.com.PetRegistry.DTORequests;

import br.com.PetRegistry.model.Porte;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Size;

public class PetUpdateDTO {

    @Size(max = 100, message = "O nome deve ter no máximo 100 caracteres")
    private final String nome;

    @Min(value = 0, message = "A idade não pode ser negativa")
    private final int idade;

    @Size(max = 500, message = "A descrição deve ter no máximo 500 caracteres")
    private final String descricao;

    private final String historicoSaude;

    private final Porte portePet;

    private final Long larTemporarioId;

    public PetUpdateDTO(String nome, int idade, String descricao, String historicoSaude, Porte portePet, Long larTemporarioId) {
        this.nome = nome;
        this.idade = idade;
        this.descricao = descricao;
        this.historicoSaude = historicoSaude;
        this.portePet = portePet;
        this.larTemporarioId = larTemporarioId;
    }

    public String getNome() { return nome; }
    public int getIdade() { return idade; }
    public String getDescricao() { return descricao; }
    public String getHistoricoSaude() { return historicoSaude; }
    public Porte getPortePet() { return portePet; }
    public Long getLarTemporarioId() { return larTemporarioId; }
}