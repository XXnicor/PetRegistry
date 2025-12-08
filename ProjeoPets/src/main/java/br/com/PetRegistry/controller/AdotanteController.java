package br.com.PetRegistry.controller;

import br.com.PetRegistry.DTORequests.AdotanteRequestDTO;
import br.com.PetRegistry.DTORequests.AdotanteUpdateDTO;
import br.com.PetRegistry.model.Adotante;
import br.com.PetRegistry.service.AdotanteService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@RestController
@RequestMapping("/adotantes")
public class AdotanteController {

    private final AdotanteService adotanteService;

    @Autowired
    public AdotanteController(AdotanteService adotanteService) {
        this.adotanteService = adotanteService;
    }

    @PostMapping
    public ResponseEntity<Adotante> cadastrarAdotante(@Valid @RequestBody AdotanteRequestDTO dto, UriComponentsBuilder uriBuilder) {
        Adotante adotante = adotanteService.cadastrarAdotante(dto);
        var uri = uriBuilder.path("/adotantes/{id}").buildAndExpand(adotante.getId()).toUri();
        return ResponseEntity.created(uri).body(adotante);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Adotante> buscarAdotantePorId(@PathVariable long id) {
        Adotante adotante = adotanteService.buscarAdotantePorId(id);
        return ResponseEntity.ok(adotante);
    }

    @GetMapping
    public ResponseEntity<List<Adotante>> listarTodosAdotantes() {
        List<Adotante> adotantes = adotanteService.listarTodosAdotantes();
        return ResponseEntity.ok(adotantes);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Adotante> atualizarAdotante(@PathVariable long id, @Valid @RequestBody AdotanteUpdateDTO dto) {
        Adotante adotante = adotanteService.atualizarAdotante(id, dto);
        return ResponseEntity.ok(adotante);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> removerAdotante(@PathVariable long id) {
        adotanteService.removerAdotante(id);
        return ResponseEntity.noContent().build();
    }
}