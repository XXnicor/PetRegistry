package br.com.PetRegistry.controller;

import br.com.PetRegistry.DTORequests.LarTemporarioRequestDTO;
import br.com.PetRegistry.DTORequests.LarTemporarioUpdateDTO;
import br.com.PetRegistry.model.LarTemporario;
import br.com.PetRegistry.service.LarTemporarioService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@RestController
@RequestMapping("/lares-temporarios")
public class LarTemporarioController {

    private final LarTemporarioService larTemporarioService;

    @Autowired
    public LarTemporarioController(LarTemporarioService larTemporarioService) {
        this.larTemporarioService = larTemporarioService;
    }

    @PostMapping
    public ResponseEntity<LarTemporario> cadastrarLarTemporario(@Valid @RequestBody LarTemporarioRequestDTO dto, UriComponentsBuilder uriBuilder) {
        LarTemporario lar = larTemporarioService.cadastrarLar(dto);
        var uri = uriBuilder.path("/lares-temporarios/{id}").buildAndExpand(lar.getId()).toUri();
        return ResponseEntity.created(uri).body(lar);
    }

    @GetMapping("/{id}")
    public ResponseEntity<LarTemporario> buscarLarPorId(@PathVariable long id) {
        LarTemporario lar = larTemporarioService.buscarLarPorId(id);
        return ResponseEntity.ok(lar);
    }

    @GetMapping
    public ResponseEntity<List<LarTemporario>> listarTodosOsLares() {
        List<LarTemporario> lares = larTemporarioService.listarTodos();
        return ResponseEntity.ok(lares);
    }

    @PutMapping("/{id}")
    public ResponseEntity<LarTemporario> atualizarLarTemporario(@PathVariable long id, @Valid @RequestBody LarTemporarioUpdateDTO dto) {
        LarTemporario lar = larTemporarioService.atualizarLar(id, dto);
        return ResponseEntity.ok(lar);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> removerLarTemporario(@PathVariable long id) {
        larTemporarioService.removerLar(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/{larId}/pets/{petId}")
    public ResponseEntity<Void> alocarPet(@PathVariable long larId, @PathVariable long petId) {
        larTemporarioService.alocarPet(petId, larId);
        return ResponseEntity.noContent().build();
    }
}