package br.com.PetRegistry.DTORequests;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class LarTemporarioUpdateDTO {

    @NotBlank(message = "O nome do responsável é obrigatório")
    @Size(max = 100, message = "O nome do responsável deve ter no máximo 100 caracteres")
    private final String nomeResponsavel;

    @NotBlank(message = "O contato é obrigatório")
    private final String contato;

    @NotBlank(message = "O endereço é obrigatório")
    private final String enderecoCompleto;

    @NotNull(message = "A capacidade máxima é obrigatória")
    @Min(value = 1, message = "A capacidade máxima deve ser de no mínimo 1")
    private final int capacidadeMaxima;

    public LarTemporarioUpdateDTO(String nomeResponsavel, String contato, String enderecoCompleto, int capacidadeMaxima) {
        this.nomeResponsavel = nomeResponsavel;
        this.contato = contato;
        this.enderecoCompleto = enderecoCompleto;
        this.capacidadeMaxima = capacidadeMaxima;
    }

    public String getNomeResponsavel() { return nomeResponsavel; }
    public String getContato() { return contato; }
    public String getEnderecoCompleto() { return enderecoCompleto; }
    public int getCapacidadeMaxima() { return capacidadeMaxima; }
}