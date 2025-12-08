package br.com.PetRegistry.controller;

import br.com.PetRegistry.DTORequests.PetRequestDTO;
import br.com.PetRegistry.DTORequests.PetResponseDTO;
import br.com.PetRegistry.DTORequests.PetUpdateDTO;
import br.com.PetRegistry.model.Evento;
import br.com.PetRegistry.model.Pet;
import br.com.PetRegistry.service.PetMapper;
import br.com.PetRegistry.service.PetService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@RestController
@RequestMapping("/api/pets")
@CrossOrigin(origins = "*")
public class PetController {

    private final PetService petService;
    private final PetMapper petMapper;
    
    @Autowired
    public PetController(PetService petService, PetMapper petMapper) {
        this.petService = petService;
        this.petMapper = petMapper;
    }
    @PostMapping
    public ResponseEntity<PetResponseDTO> cadastrarPet(@Valid @RequestBody PetRequestDTO dto , UriComponentsBuilder uriBuilder) {
        Pet novoPet = petService.cadastrarPet(dto);
        PetResponseDTO response = petMapper.toResponse(novoPet);
        var uri = uriBuilder.path("/pets/{id}").buildAndExpand(novoPet.getId()).toUri();
        return ResponseEntity.created(uri).body(response);
    }
    @GetMapping("/{id}")
    public ResponseEntity<PetResponseDTO> buscarPetPorId(@PathVariable long id) {
        return petService.buscarPetPorId(id)
                .map(petMapper::toResponse)
                .map(ResponseEntity::ok)
                .orElse( ResponseEntity.notFound().build());
    }

    @GetMapping
    public ResponseEntity<List<PetResponseDTO>> listarTodos() {
        List<Pet> pets = petService.findAllPaged(0, 100);
        List<PetResponseDTO> responseList = pets.stream()
                .map(petMapper::toResponse)
                .toList();
        return ResponseEntity.ok(responseList);
    }

    @GetMapping("/status/{status}")
    public ResponseEntity<List<PetResponseDTO>> listarPorStatus(@PathVariable String status) {
        List<Pet> pets = petService.findPetByStatus(status);
        List<PetResponseDTO> responseList = pets.stream()
                .map(petMapper::toResponse)
                .toList();
        return ResponseEntity.ok(responseList);
    }

    @GetMapping("/listar")
    public ResponseEntity<List<PetResponseDTO>> listarTodosOsPets(
            @RequestParam(required = false) String status,
            @RequestParam(required = false) String nome,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ) 
    {
        List<Pet> pets;
        if(status != null && !status.isBlank()){
            pets = petService.findPetByStatus(status);
        } else if (nome != null && !nome.isBlank()){
            pets = petService.findByName(nome);
        } else {
            pets = petService.findAllPaged(page, size);
        }
        List<PetResponseDTO> responseList = pets.stream()
                .map(petMapper::toResponse)
                .toList();
        return ResponseEntity.ok(responseList);
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<PetResponseDTO> atualizarPet(@PathVariable long id, @Valid @RequestBody PetUpdateDTO dto) {
        Pet petAtualizado = petService.atualizarPet(id, dto);
        return ResponseEntity.ok(petMapper.toResponse(petAtualizado));
    }
    @PutMapping("/{id}/status")
    public ResponseEntity <Void> atualizarStatusPet(@PathVariable long id, @RequestParam String status) {
        petService.atualizarStatus(id, status);
        return ResponseEntity.noContent().build();
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> removerPet(@PathVariable long id) {
        petService.deletarPet(id);
        return ResponseEntity.noContent().build();
    }
    @GetMapping("/{petId}/eventos")
    public ResponseEntity<List<Evento>> listarEventosPorPet(@PathVariable long petId) {
        List<Evento> eventos = petService.findEventoByPetId(petId);
        return ResponseEntity.ok(eventos);
    }
}