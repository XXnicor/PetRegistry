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

    /**
     * Converte um DTO de requisição em uma nova entidade Pet.
     * A lógica de buscar o LarTemporario foi movida para o PetService.
     *
     * @param dto O DTO com os dados do novo pet.
     * @param lar Opcional: a entidade LarTemporario já buscada pelo serviço.
     * @return Uma nova entidade Pet, pronta para ser persistida.
     */
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
                false, // Valores padrão de sociabilidade devem ser definidos no DTO ou serviço
                false,
                false
        );

        return new Pet(
                dto.getStatusPet(),
                profile,
                lar // O relacionamento de objeto é passado diretamente.
        );
    }

    /**
     * Atualiza uma entidade Pet existente com os dados de um DTO de atualização.
     *
     * @param dto O DTO com os dados a serem atualizados.
     * @param pet A entidade Pet a ser modificada.
     */
    public void updateEntityFromDto(PetUpdateDTO dto, Pet pet) {
        PetProfile atual = pet.getProfile();

        // Cria um novo PetProfile imutável com os dados atualizados
        PetProfile novoPerfil = new PetProfile(
                dto.getNome() != null ? dto.getNome() : atual.getNomeCompleto(),
                atual.getFotoUrl(), // Foto não é atualizável por este DTO
                atual.getTipo(),    // Tipo não é atualizável
                atual.getSexo(),    // Sexo não é atualizável
                dto.getPortePet() != null ? dto.getPortePet() : atual.getPorte(),
                dto.getIdade() > 0 ? dto.getIdade() : atual.getIdadeAproximada(),
                atual.getDataEntrada(), // Data de entrada não é atualizável
                dto.getHistoricoSaude() != null ? dto.getHistoricoSaude() : atual.getHistoricoMedico(),
                dto.getDescricao() != null ? dto.getDescricao() : atual.getObservacoes(),
                atual.isCastrado(), // Saúde não é atualizável por este DTO
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
                pet.getObservacoes()
        );
    }
}