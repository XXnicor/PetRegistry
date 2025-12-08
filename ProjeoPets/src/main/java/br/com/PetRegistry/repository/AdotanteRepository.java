package br.com.PetRegistry.repository;

import br.com.PetRegistry.model.Adotante;

import java.util.List;
import java.util.Optional;

public interface AdotanteRepository {

    Adotante save(Adotante adotante);
    Optional<Adotante> findById(Long id);
    List<Adotante> findAll();
    Adotante update(Adotante adotante);
    void deleteById(long id);
}
