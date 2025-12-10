package br.com.PetRegistry.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ContatoEmergenciaTest {

    @Test
    void deveCriarContatoValido() {
        ContatoEmergencia contato = new ContatoEmergencia("Ana Souza", "+5511998765432");

        assertEquals("Ana Souza", contato.getNomeCompleto());
        assertEquals("+5511998765432", contato.getTelefone());
    }

    @Test
    void deveFalharQuandoNomeMuitoCurto() {
        assertThrows(IllegalArgumentException.class, () -> new ContatoEmergencia("An", "+5511998765432"));
    }

    @Test
    void deveFalharQuandoTelefoneInvalido() {
        assertThrows(IllegalArgumentException.class, () -> new ContatoEmergencia("Ana Souza", "123"));
    }

    @Test
    void equalsEDiferencaPorValor() {
        ContatoEmergencia a = new ContatoEmergencia("Ana Souza", "+5511999999999");
        ContatoEmergencia b = new ContatoEmergencia("Ana Souza", "+5511999999999");
        ContatoEmergencia c = new ContatoEmergencia("Bruno Lima", "+5511888888888");

        assertEquals(a, b);
        assertNotEquals(a, c);
        assertEquals(a.hashCode(), b.hashCode());
    }
}

