package br.com.PetRegistry.DTORequests;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class AdotanteUpdateDTO {

    @NotBlank(message = "O nome do responsável é obrigatório")
    @Size(max = 100, message = "O nome do responsável deve ter no máximo 100 caracteres")
    private final String nomeResponsavel;

    @NotBlank(message = "O contato é obrigatório")
    private final String contato;

    @NotBlank(message = "O endereço é obrigatório")
    private final String enderecoCompleto;

    public AdotanteUpdateDTO(String nomeResponsavel, String contato, String enderecoCompleto) {
        this.nomeResponsavel = nomeResponsavel;
        this.contato = contato;
        this.enderecoCompleto = enderecoCompleto;
    }

    public String getNomeResponsavel() { return nomeResponsavel; }
    public String getContato() { return contato; }
    public String getEnderecoCompleto() { return enderecoCompleto; }
}