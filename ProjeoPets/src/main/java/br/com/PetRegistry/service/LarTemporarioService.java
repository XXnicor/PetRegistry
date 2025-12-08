package br.com.PetRegistry.service;

import br.com.PetRegistry.DTORequests.LarTemporarioRequestDTO;
import br.com.PetRegistry.Util.RegrasDeNegocioExceptions;
import br.com.PetRegistry.DTORequests.LarTemporarioUpdateDTO;
import br.com.PetRegistry.model.Evento;
import br.com.PetRegistry.model.LarTemporario;
import br.com.PetRegistry.model.Pet;
import br.com.PetRegistry.model.PetStatus;
import br.com.PetRegistry.repository.EventoRepository;
import br.com.PetRegistry.repository.LarTemporarioRepository;
import br.com.PetRegistry.repository.PetRepository;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class LarTemporarioService {

    private final LarTemporarioRepository lartemporarioRepository;
    private final PetRepository petRepository;
    private final EventoRepository eventoRepository;

    public LarTemporarioService(LarTemporarioRepository lartemporarioRepository, PetRepository petRepository,
                                EventoRepository eventoRepository) {
        this.lartemporarioRepository = lartemporarioRepository;
        this.petRepository = petRepository;
        this.eventoRepository = eventoRepository;
    }

    public LarTemporario cadastrarLar(LarTemporarioRequestDTO dto) {
        if (dto.getCapacidadeMaxima() <= 0) {
            throw new RegrasDeNegocioExceptions("A capacidade máxima deve ser maior que zero");
        }

        LarTemporario larTemporario = new LarTemporario(0, dto.getNomeResponsavel(), dto.getContato(),
                dto.getEnderecoCompleto(), dto.getCapacidadeMaxima(), false);

        return lartemporarioRepository.saveLarTemporario(larTemporario);
    }

    public List<LarTemporario> listarTodos() {
        return lartemporarioRepository.findAllLarTemporario();
    }

    @Transactional
    public void alocarPet(Long petId, Long larId) {

        Pet pet = petRepository.findById(petId)
                .orElseThrow(() -> new RegrasDeNegocioExceptions("Pet não encontrado com ID: " + petId));

        LarTemporario larTemporario = lartemporarioRepository.findLarTemporarioById(larId)
                .orElseThrow(() -> new RegrasDeNegocioExceptions("Lar temporário não encontrado com ID: " + larId));

        if (larTemporario.isSemVagas()) {
            throw new RegrasDeNegocioExceptions("O lar temporário não possui vagas disponíveis.");
        }

        if (pet.getStatus() != PetStatus.DISPONIVEL_ADOCAO) {
            throw new RegrasDeNegocioExceptions("Apenas pets com status DISPONIVEL_ADOCAO podem ser alocados.");
        }

        pet.setStatus(PetStatus.EM_LAR_TEMPORARIO);
        pet.setLarTemporario(larTemporario);
        petRepository.update(pet);

        Evento evento = new Evento(pet, "ALOCACAO_LAR_TEMPORARIO", "Pet alocado em lar temporário");
        eventoRepository.save(evento);
    }

    public LarTemporario buscarLarPorId(long id) {
        return lartemporarioRepository.findLarTemporarioById(id)
                .orElseThrow(() -> new IllegalArgumentException("Lar temporário não encontrado com ID: " + id));
    }

    public LarTemporario atualizarLar(long id, LarTemporarioUpdateDTO dto) {

        LarTemporario larExistente = lartemporarioRepository.findLarTemporarioById(id)
                .orElseThrow(() -> new IllegalArgumentException("Lar temporário não encontrado com ID: " + id));


        larExistente.setNomeResponsavel(dto.getNomeResponsavel());
        larExistente.setContato(dto.getContato());
        larExistente.setEnderecoCompleto(dto.getEnderecoCompleto());


        return lartemporarioRepository.updateLarTemporario(larExistente);
    }

    public void removerLar(long id) {
        Optional<LarTemporario> larExistente = lartemporarioRepository.findLarTemporarioById(id);
        if (larExistente.isEmpty()) {
            throw new IllegalArgumentException("Lar temporário não encontrado com ID: " + id);
        }
        lartemporarioRepository.deleteLarTemporarioById(id);
    }
}
