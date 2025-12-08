package br.com.PetRegistry.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.time.LocalDate;

@Entity
@Table(name = "adotante")
public class Adotante {
    @Id
    private Long id;
    private String nomeCompleto;
    private String cpf;
    private String contato;
    private String endereco;
    private LocalDate dataAprovacao;


    public Adotante(long id, String nomeCompleto, String cpf, String contato, String endereco, LocalDate dataAprovacao) {
        this.id = id;
        this.nomeCompleto = nomeCompleto;
        this.cpf = cpf;
        this.contato = contato;
        this.endereco = endereco;
        this.dataAprovacao = dataAprovacao;
    }

    public Adotante() {

    }

    public void setId(Long id) {
        this.id = id;
    }

    public long getId() {
        return id;
    }

    public String getNomeCompleto() {
        return nomeCompleto;
    }

    public String getCpf() {
        return cpf;
    }

    public String getContato() {
        return contato;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setNomeCompleto(String nomeCompleto) {
        this.nomeCompleto = nomeCompleto;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public void setContato(String contato) {
        this.contato = contato;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public void setId(long id) {
        this.id = id;
    }
    public LocalDate getDataAprovacao() {
        return dataAprovacao;
    }
}
