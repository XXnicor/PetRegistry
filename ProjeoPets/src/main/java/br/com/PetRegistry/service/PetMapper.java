package br.com.PetRegistry.service;

import br.com.PetRegistry.DTORequests.PetRequestDTO;
import br.com.PetRegistry.DTORequests.PetResponseDTO;
import br.com.PetRegistry.DTORequests.PetUpdateDTO;
import br.com.PetRegistry.model.LarTemporario;
import br.com.PetRegistry.model.Pet;
import br.com.PetRegistry.model.PetProfile;
import org.springframework.stereotype.Component;

@Component
public class PetMapper {
    public Pet toEntity(PetRequestDTO dto, LarTemporario lar) {

        PetProfile profile = new PetProfile(
                dto.getNome(),
                dto.getFotoUrl(),
                dto.getPetType(),
                dto.getSexType(),
                dto.getPortePet(),
                dto.getIdade(),
                dto.getEntradaPet(),
                dto.getHistoricoSaude(),
                dto.getDescricao(),
                dto.isCastrado(),
                dto.isVacinado(),
                false,
                false,
                false
        );

        return new Pet(
                dto.getStatusPet(),
                profile,
                lar
        );
    }
    public void updateEntityFromDto(PetUpdateDTO dto, Pet pet) {
        PetProfile atual = pet.getProfile();

        PetProfile novoPerfil = new PetProfile(
                dto.getNome() != null ? dto.getNome() : atual.getNomeCompleto(),
                atual.getFotoUrl(),
                atual.getTipo(),
                atual.getSexo(),
                dto.getPortePet() != null ? dto.getPortePet() : atual.getPorte(),
                dto.getIdade() > 0 ? dto.getIdade() : atual.getIdadeAproximada(),
                atual.getDataEntrada(),
                dto.getHistoricoSaude() != null ? dto.getHistoricoSaude() : atual.getHistoricoMedico(),
                dto.getDescricao() != null ? dto.getDescricao() : atual.getObservacoes(),
                atual.isCastrado(),
                atual.isVacinado(),
                atual.isSociavelComCaes(),
                atual.isSociavelComGatos(),
                atual.isSociavelComCriancas()
        );

        pet.atualizarPerfil(novoPerfil);
    }

    public PetResponseDTO toResponse(Pet pet) {
        return new PetResponseDTO(
                pet.getId(),
                pet.getNomeCompleto(),
                pet.getFotoUrl(),
                pet.getTipo(),
                pet.getSexo(),
                pet.getPorte(),
                pet.getStatus(),
                pet.getIdadeAproximada(),
                pet.getDataEntrada(),
                pet.getObservacoes(),
                pet.getLarTemporarioId()
        );
    }
}