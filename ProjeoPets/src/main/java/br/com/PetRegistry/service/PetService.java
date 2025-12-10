package br.com.PetRegistry.service;

import br.com.PetRegistry.DTORequests.PetRequestDTO;
import br.com.PetRegistry.DTORequests.PetUpdateDTO;
import br.com.PetRegistry.Util.RegrasDeNegocioExceptions;
import br.com.PetRegistry.model.Evento;
import br.com.PetRegistry.model.LarTemporario;
import br.com.PetRegistry.model.Pet;
import br.com.PetRegistry.model.PetStatus;
import br.com.PetRegistry.repository.EventoRepository;
import br.com.PetRegistry.repository.LarTemporarioRepository;
import br.com.PetRegistry.repository.PetRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class PetService {

    private final PetRepository petRepository;
    private final EventoRepository eventoRepository;
    private final LarTemporarioRepository larTemporarioRepository;
    private final StatusValidator statusValidator;
    private final PetMapper petMapper;

    public PetService(PetRepository petRepository, EventoRepository eventoRepository, LarTemporarioRepository larTemporarioRepository, StatusValidator statusValidator, PetMapper petMapper) {
        this.petRepository = petRepository;
        this.eventoRepository = eventoRepository;
        this.larTemporarioRepository = larTemporarioRepository;
        this.statusValidator = statusValidator;
        this.petMapper = petMapper;
    }

    @Transactional
    public Pet cadastrarPet(PetRequestDTO dto) {
        LarTemporario lar = null;
        if (dto.getLarTemporarioId() != null) {
            lar = larTemporarioRepository.findLarTemporarioById(dto.getLarTemporarioId())
                    .orElseThrow(() -> new RegrasDeNegocioExceptions("Lar temporário com ID " + dto.getLarTemporarioId() + " não encontrado."));
        }
        Pet novoPet = petMapper.toEntity(dto, lar);
        return petRepository.save(novoPet);
    }

    @Transactional(readOnly = true)
    public Optional<Pet> buscarPetPorId(Long id) {
        if (id == null) {
            throw new RegrasDeNegocioExceptions("ID deve ser informado");
        }
        return petRepository.findById(id);
    }

    @Transactional
    public void atualizarStatus(Long id, String status) {
        statusValidator.validarStatusPet(status);
        Pet pet = petRepository.findById(id)
                .orElseThrow(() -> new RegrasDeNegocioExceptions("Pet não encontrado com id: " + id));
        pet.setStatus(PetStatus.valueOf(status));
        petRepository.save(pet);
    }

    @Transactional
    public void registrarEvento(Long petId, String tipoEvento, String descricao) {
        Pet pet = petRepository.findById(petId)
                .orElseThrow(() -> new RegrasDeNegocioExceptions("Pet não encontrado com id: " + petId));
        
        Evento novoEvento = new Evento(pet, tipoEvento, descricao);
        eventoRepository.save(novoEvento);
    }

    @Transactional(readOnly = true)
    public List<Pet> findAll() {
        return petRepository.findAll();
    }

    @Transactional(readOnly = true)
    public List<Pet> findAllPaged(int page, int size) {
        int sanitizedPage = Math.max(0, page);
        int sanitizedSize = size <= 0 ? 20 : Math.min(size, 50);
        int offset = sanitizedPage * sanitizedSize;
        return petRepository.findAllPaged(offset, sanitizedSize);
    }

    @Transactional(readOnly = true)
    public List<Pet> findPetByStatus(String status) {
        statusValidator.validarStatusPet(status);
        PetStatus petStatus = PetStatus.valueOf(status);
        return petRepository.findByStatus(String.valueOf(petStatus));
    }

    @Transactional(readOnly = true)
    public List<Pet> findByName(String nome) {
        String trimmed = nome.trim();
        if (trimmed.isEmpty()) {
            throw new RegrasDeNegocioExceptions("Nome não pode ser vazio");
        }
        return petRepository.findByProfileNomeCompletoContainingIgnoreCase(trimmed);
    }

    @Transactional
    public void deletarPet(Long petId) {
        if (!petRepository.existsById(petId)) {
            throw new RegrasDeNegocioExceptions("Pet não encontrado com id: " + petId);
        }
        petRepository.deleteById(petId);
    }

    @Transactional
    public Pet atualizarPet(Long petId, PetUpdateDTO dto) {
        Pet pet = petRepository.findById(petId)
                .orElseThrow(() -> new RegrasDeNegocioExceptions("Pet não encontrado com id: " + petId));

        petMapper.updateEntityFromDto(dto, pet);

        if (dto.getLarTemporarioId() != null) {
            LarTemporario lar = larTemporarioRepository.findLarTemporarioById(dto.getLarTemporarioId())
                    .orElseThrow(() -> new RegrasDeNegocioExceptions("Lar temporário com ID " + dto.getLarTemporarioId() + " não encontrado"));
            pet.setLarTemporario(lar);
            if (!pet.getStatus().equals(PetStatus.EM_LAR_TEMPORARIO)) {
                pet.setStatus(PetStatus.EM_LAR_TEMPORARIO);
            }
        } else {
            pet.setLarTemporario(null);
        }

        return petRepository.save(pet);
    }

    @Transactional
    public Pet associarLarTemporario(Long petId, Long larTemporarioId) {
        Pet pet = petRepository.findById(petId)
                .orElseThrow(() -> new RegrasDeNegocioExceptions("Pet não encontrado com id: " + petId));

        if (larTemporarioId != null) {
            LarTemporario lar = larTemporarioRepository.findLarTemporarioById(larTemporarioId)
                    .orElseThrow(() -> new RegrasDeNegocioExceptions("Lar temporário com ID " + larTemporarioId + " não encontrado"));
            pet.setLarTemporario(lar);
            if (!pet.getStatus().equals(PetStatus.EM_LAR_TEMPORARIO)) {
                pet.setStatus(PetStatus.EM_LAR_TEMPORARIO);
            }
        } else {
            pet.setLarTemporario(null);
        }

        return petRepository.save(pet);
    }

    @Transactional(readOnly = true)
    public List<Evento> findEventoByPetId(Long petId) {
        if (!petRepository.existsById(petId)) {
            throw new RegrasDeNegocioExceptions("Pet não encontrado com id: " + petId);
        }
        return eventoRepository.findByPetId(petId);
    }

    @Transactional(readOnly = true)
    public List<Pet> findPetsByLarTemporarioId(Long larTemporarioId) {
        if (!larTemporarioRepository.findLarTemporarioById(larTemporarioId).isPresent()) {
            throw new RegrasDeNegocioExceptions("Lar temporário não encontrado com id: " + larTemporarioId);
        }
        return petRepository.findByLarTemporarioId(larTemporarioId);
    }
}