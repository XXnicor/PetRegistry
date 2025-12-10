package br.com.PetRegistry.repository;

import br.com.PetRegistry.model.LarTemporario;

import java.util.List;
import java.util.Optional;

public interface LarTemporarioRepository {

    LarTemporario saveLarTemporario(LarTemporario larTemporario);

    LarTemporario updateLarTemporario(LarTemporario larTemporario);

    Optional<LarTemporario> findLarTemporarioById(long id);

    List<LarTemporario> findAllLarTemporario();

    boolean deleteLarTemporarioById(long id);
}
