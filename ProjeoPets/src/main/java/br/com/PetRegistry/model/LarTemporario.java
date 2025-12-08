package br.com.PetRegistry.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "larTemporario")
public class LarTemporario {
    @Id
    private Long id;
    private String nomeResponsavel;
    private String contato;
    private String enderecoCompleto;
    private int capacidadeMaxima;
    private boolean semVagas;

    public LarTemporario(long id, String nomeResponsavel, String contato, String enderecoCompleto, int capacidadeMaxima, boolean semVagas) {
        this.id = id;
        this.nomeResponsavel = nomeResponsavel;
        this.contato = contato;
        this.enderecoCompleto = enderecoCompleto;
        this.capacidadeMaxima = capacidadeMaxima;
        this.semVagas = semVagas;
    }

    public LarTemporario() {

    }

    public void setId(Long id) {
        this.id = id;
    }

    public long getId() {
        return id;
    }
    public void setId(long id){
        this.id = id;
    }
    public String getNomeResponsavel() {
        return nomeResponsavel;
    }
    public String getContato() {
        return contato;
    }
    public String getEnderecoCompleto() {
        return enderecoCompleto;
    }
    public int getCapacidadeMaxima() {
        return capacidadeMaxima;
    }
    public boolean isSemVagas() {return semVagas;}
    public void setNomeResponsavel(String nomeResponsavel) {
        this.nomeResponsavel = nomeResponsavel;
    }
    public void setContato(String contato) {
        this.contato = contato;
    }
    public void setEnderecoCompleto(String enderecoCompleto) {
        this.enderecoCompleto = enderecoCompleto;
    }



    @Override
    public String toString() {
        return "LarTemporario{" +
                "id=" + id +
                ", nomeResponsavel='" + nomeResponsavel + '\'' +
                ", contato='" + contato + '\'' +
                ", enderecoCompleto='" + enderecoCompleto + '\'' +
                ", capacidadeMaxima=" + capacidadeMaxima +
                ", semVagas=" + semVagas +
                '}';
    }
}
