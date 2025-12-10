package br.com.PetRegistry.model;

import java.util.Objects;

public class ContatoEmergencia {

    private final String nomeCompleto;
    private final String telefone;

    public ContatoEmergencia(String nomeCompleto, String telefone) {
        validarNome(nomeCompleto);
        validarTelefone(telefone);
        this.nomeCompleto = nomeCompleto;
        this.telefone = telefone;
    }

    private void validarNome(String nome) {
        if (nome == null || nome.trim().length() < 3) {
            throw new IllegalArgumentException("Nome deve ter no mínimo 3 caracteres");
        }
    }

    private void validarTelefone(String telefone) {
        if (telefone == null || telefone.length() < 10) {
            throw new IllegalArgumentException("Telefone inválido");
        }
    }

    public String getNomeCompleto() {
        return nomeCompleto;
    }

    public String getTelefone() {
        return telefone;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ContatoEmergencia that = (ContatoEmergencia) o;
        return Objects.equals(nomeCompleto, that.nomeCompleto) &&
               Objects.equals(telefone, that.telefone);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nomeCompleto, telefone);
    }

    @Override
    public String toString() {
        return "ContatoEmergencia{" +
                "nomeCompleto='" + nomeCompleto + '\'' +
                ", telefone='" + telefone + '\'' +
                '}';
    }
}

