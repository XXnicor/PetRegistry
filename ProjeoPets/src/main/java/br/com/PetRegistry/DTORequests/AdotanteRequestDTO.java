package br.com.PetRegistry.DTORequests;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;

public class AdotanteRequestDTO {
    @NotBlank(message = "O nome do responsável é obrigatório")
    @Size(max = 100, message = "O nome do responsável deve ter no máximo 100 caracteres")
    private final String nomeResponsavel;

    @NotBlank(message = "O contato é obrigatório")
    private final String contato;

    @NotBlank(message = "O endereço é obrigatório")
    private final String enderecoCompleto;

    @NotBlank(message = "O cpf é obrigatório")
    private final String cpf;

    @NotBlank(message = "A data de aprovação é obrigatória")
    private LocalDate dataAprovacao;


    public AdotanteRequestDTO(String nomeResponsavel, String contato, String enderecoCompleto, String cpf, LocalDate dataAprovacao) {
        this.nomeResponsavel = nomeResponsavel;
        this.contato = contato;
        this.enderecoCompleto = enderecoCompleto;
        this.cpf = cpf;
        this.dataAprovacao = dataAprovacao;
    }
    public String getNomeResponsavel() { return nomeResponsavel; }
    public String getContato() { return contato; }
    public String getEnderecoCompleto() { return enderecoCompleto; }
    public String getCpf() { return cpf; }
    public LocalDate getDataAprovacao() { return dataAprovacao; }
}
