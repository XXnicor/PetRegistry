package br.com.PetRegistry.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

import java.util.Objects;
import java.util.regex.Pattern;

@Entity
public final class ContatoEmergencia {

    private String nomeCompleto;
    private String telefone;


    public ContatoEmergencia(String nomeCompleto, String telefone) {
        this.nomeCompleto = validarNome(nomeCompleto);
        this.telefone = validarTelefone(telefone);
    }

    public ContatoEmergencia() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Id
    private Long id;
    private static final Pattern TELEFONE_PATTERN = Pattern.compile("^\\d{11}$");


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
        return nomeCompleto.equals(that.nomeCompleto) && telefone.equals(that.telefone);
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

    private String validarNome(final String nome) {
        Objects.requireNonNull(nome, "Nome do contato é obrigatório");
        String trimmed = nome.trim();
        if (trimmed.length() < 3) {
            throw new IllegalArgumentException("Nome do contato deve ter ao menos 3 caracteres úteis");
        }
        return trimmed;
    }

    private String validarTelefone(final String numero) {
        Objects.requireNonNull(numero, "Telefone do contato é obrigatório");
        String apenasDigitos = numero.replaceAll("[^0-9]", "");
        if (apenasDigitos.length() == 13 && apenasDigitos.startsWith("55")) {
            apenasDigitos = apenasDigitos.substring(2);
        }
        if (!TELEFONE_PATTERN.matcher(apenasDigitos).matches()) {
            throw new IllegalArgumentException("Telefone deve conter DDD + 9 dígitos (ex.: 11999998888)");
        }
        return "+55" + apenasDigitos;
    }
}
