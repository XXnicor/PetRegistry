package br.com.PetRegistry.service;

import br.com.PetRegistry.DTORequests.PetRequestDTO;
import br.com.PetRegistry.DTORequests.PetUpdateDTO;
import br.com.PetRegistry.model.*;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class PetMapperTest {

    private final PetMapper mapper = new PetMapper();

    @Test
    void toEntity_deveMapearCamposEssenciais() {
        // ARRANGE
        LocalDate hoje = LocalDate.now();
        // Criamos o DTO usando o construtor (sem setters porque é imutável)
        PetRequestDTO dto = new PetRequestDTO(
                "Bolt",                         // nome
                "https://imgs/b.png",           // fotoUrl
                null,                           // especie (opcional)
                null,                           // raca (opcional)
                3,                              // idade
                "Cheio de energia",             // descricao
                null,                           // historicoSaude (opcional)
                true,                           // castrado
                true,                           // vacinado
                PetType.CACHORRO,               // petType
                SexType.MACHO,                  // sexType
                Porte.MEDIO,                    // portePet
                PetStatus.DISPONIVEL_ADOCAO,    // statusPet
                hoje,                           // entradaPet
                null                            // larTemporarioId (opcional)
        );

        // ACT
        // Testamos o cenário sem um LarTemporario associado
        Pet pet = mapper.toEntity(dto, null);

        // ASSERT
        assertNotNull(pet);
        assertEquals("Bolt", pet.getNomeCompleto());
        assertEquals(PetStatus.DISPONIVEL_ADOCAO, pet.getStatus());
        // Usamos getProfile() para acessar dados do perfil
        assertEquals(3, pet.getProfile().getIdadeAproximada());
        assertTrue(pet.getProfile().isCastrado());

        // A asserção chave: o relacionamento de objeto deve ser nulo
        assertNull(pet.getLarTemporario());
    }

    @Test
    void updateEntityFromDto_deveAtualizarSomenteCamposPresentesNoDTO() {
        // ARRANGE: Criamos um Pet da maneira correta agora
        LocalDate entrada = LocalDate.of(2024, 1, 10);
        PetProfile perfilOriginal = new PetProfile(
                "Luna", "https://imgs/luna.png", PetType.GATO, SexType.FEMEA,
                Porte.PEQUENO, 2, entrada, "Sem comorbidades", "Muito dócil",
                true, true, true, false, true
        );
        LarTemporario larOriginal = new LarTemporario();
        // Em um teste real, poderíamos dar um ID ao mock do lar, mas não é necessário aqui.
        Pet petOriginal = new Pet(PetStatus.DISPONIVEL_ADOCAO, perfilOriginal, larOriginal);

        // O DTO de atualização - usando o construtor (sem setters porque é imutável)
        PetUpdateDTO atualizacao = new PetUpdateDTO(
                "Luna Silva",                   // nome
                3,                              // idade
                "Carinhosa e curiosa",          // descricao
                null,                           // historicoSaude (não alteramos)
                null                            // portePet (não alteramos)
        );

        // ACT
        // Chamamos o método com o nome correto
        mapper.updateEntityFromDto(atualizacao, petOriginal);

        // ASSERT: campos enviados foram alterados
        assertEquals("Luna Silva", petOriginal.getNomeCompleto());
        assertEquals(3, petOriginal.getProfile().getIdadeAproximada());
        assertEquals("Carinhosa e curiosa", petOriginal.getProfile().getObservacoes());
        assertEquals(PetStatus.DISPONIVEL_ADOCAO, petOriginal.getStatus());

        // ASSERT: campos não enviados no DTO permanecem do perfil original
        assertEquals(Porte.PEQUENO, petOriginal.getProfile().getPorte());
        assertEquals("Sem comorbidades", petOriginal.getProfile().getHistoricoMedico());

        // ASSERT: o relacionamento de objeto não foi alterado
        assertNotNull(petOriginal.getLarTemporario());
    }
}