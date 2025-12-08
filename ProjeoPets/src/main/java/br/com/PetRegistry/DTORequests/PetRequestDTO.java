package br.com.PetRegistry.DTORequests;

import br.com.PetRegistry.model.PetStatus;
import br.com.PetRegistry.model.PetType;
import br.com.PetRegistry.model.Porte;
import br.com.PetRegistry.model.SexType;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;

public class PetRequestDTO {

    @NotBlank(message = "O nome do pet é obrigatório")
    @Size(max = 100, message = "O nome deve ter no máximo 100 caracteres")
    private final String nome;

    // URL da foto do pet (campo do formulário HTML)
    private final String fotoUrl;

    // Campos opcionais - não coletados no formulário básico
    private final String especie;

    private final String raca;

    @NotNull(message = "A idade é obrigatória")
    @Min(value = 0, message = "A idade não pode ser negativa")
    private final int idade;

    @NotBlank(message = "A descrição é obrigatória")
    @Size(max = 500, message = "A descrição deve ter no máximo 500 caracteres")
    private final String descricao;

    // Histórico de saúde opcional
    private final String historicoSaude;

    // Campos de saúde coletados no formulário HTML
    private final boolean castrado;
    private final boolean vacinado;

    @NotNull(message = "O tipo do pet é obrigatório")
    private final PetType petType;

    @NotNull(message = "O sexo do pet é obrigatório")
    private final SexType sexType;

    @NotNull(message = "O porte do pet é obrigatório")
    private final Porte portePet;

    @NotNull(message = "O status do pet é obrigatório")
    private final PetStatus statusPet;

    @NotNull(message = "A data de entrada é obrigatória")
    private final LocalDate entradaPet;

    // ID opcional do lar temporário
    private final Long larTemporarioId;

    public PetRequestDTO(String nome, String fotoUrl,String especie, String raca, int idade,
                         String descricao, String historicoSaude, boolean castrado, boolean vacinado,
                         PetType petType, SexType sexType, Porte portePet,
                         PetStatus statusPet, LocalDate entradaPet, Long larTemporarioId) {
        this.nome = nome;
        this.fotoUrl = fotoUrl;
        this.especie = especie;
        this.raca = raca;
        this.idade = idade;
        this.descricao = descricao;
        this.historicoSaude = historicoSaude;
        this.castrado = castrado;
        this.vacinado = vacinado;
        this.petType = petType;
        this.sexType = sexType;
        this.portePet = portePet;
        this.statusPet = statusPet;
        this.entradaPet = entradaPet;
        this.larTemporarioId = larTemporarioId;
    }

    public String getNome() { return nome; }

    public String getFotoUrl() { return fotoUrl; }

    public String getEspecie() { return especie; }

    public String getRaca() { return raca; }

    public int getIdade() { return idade; }

    public String getDescricao() { return descricao; }

    public String getHistoricoSaude() { return historicoSaude; }

    public boolean isCastrado() { return castrado; }

    public boolean isVacinado() { return vacinado; }

    public PetType getPetType() { return petType; }

    public SexType getSexType() { return sexType; }

    public Porte getPortePet() { return portePet; }

    public PetStatus getStatusPet() { return statusPet; }

    public LocalDate getEntradaPet() { return entradaPet; }

    public Long getLarTemporarioId() { return larTemporarioId; }

}