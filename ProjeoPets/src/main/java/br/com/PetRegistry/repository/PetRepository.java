package br.com.PetRegistry.repository;

import br.com.PetRegistry.model.Pet;

import java.sql.Connection;
import java.util.List;
import java.util.Optional;

public interface PetRepository {

    Pet save(Pet pet);

    Optional<Pet> findById(long id);

    Pet update (Pet pet);

    void delete (long id);

    List<Pet> findAll();

    List<Pet> findByStatus(String status);

    // Busca por nome (case-insensitive, parcial)
    List<Pet> findByName(String nome);

    // Listagem paginada (offset/limit)
    List<Pet> findAllPaged(int offset, int limit);

    // Métodos adicionais necessários
    List<Pet> findByProfileNomeCompletoContainingIgnoreCase(String nome);

    boolean existsById(Long id);

    void deleteById(Long id);

}
