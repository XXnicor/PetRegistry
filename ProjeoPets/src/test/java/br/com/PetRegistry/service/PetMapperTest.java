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
        LocalDate hoje = LocalDate.now();
        PetRequestDTO dto = new PetRequestDTO(
                "Bolt",
                "https://imgs/b.png",
                null,
                null,
                3,
                "Cheio de energia",
                null,
                true,
                true,
                PetType.CACHORRO,
                SexType.MACHO,
                Porte.MEDIO,
                PetStatus.DISPONIVEL_ADOCAO,
                hoje,
                null
        );

        Pet pet = mapper.toEntity(dto, null);

        assertNotNull(pet);
        assertEquals("Bolt", pet.getNomeCompleto());
        assertEquals(PetStatus.DISPONIVEL_ADOCAO, pet.getStatus());
        assertEquals(3, pet.getProfile().getIdadeAproximada());
        assertTrue(pet.getProfile().isCastrado());
        assertNull(pet.getLarTemporario());
    }

    @Test
    void updateEntityFromDto_deveAtualizarSomenteCamposPresentesNoDTO() {
        LocalDate entrada = LocalDate.of(2024, 1, 10);
        PetProfile perfilOriginal = new PetProfile(
                "Luna", "https://imgs/luna.png", PetType.GATO, SexType.FEMEA,
                Porte.PEQUENO, 2, entrada, "Sem comorbidades", "Muito dócil",
                true, true, true, false, true
        );
        LarTemporario larOriginal = new LarTemporario();
        Pet petOriginal = new Pet(PetStatus.DISPONIVEL_ADOCAO, perfilOriginal, larOriginal);

        PetUpdateDTO atualizacao = new PetUpdateDTO(
                "Luna Silva",
                3,
                "Carinhosa e curiosa",
                null,
                null,
                null
        );

        mapper.updateEntityFromDto(atualizacao, petOriginal);

        assertEquals("Luna Silva", petOriginal.getNomeCompleto());
        assertEquals(3, petOriginal.getProfile().getIdadeAproximada());
        assertEquals("Carinhosa e curiosa", petOriginal.getProfile().getObservacoes());
        assertEquals(PetStatus.DISPONIVEL_ADOCAO, petOriginal.getStatus());
        assertEquals(Porte.PEQUENO, petOriginal.getProfile().getPorte());
        assertEquals("Sem comorbidades", petOriginal.getProfile().getHistoricoMedico());
        assertNotNull(petOriginal.getLarTemporario());
    }
}