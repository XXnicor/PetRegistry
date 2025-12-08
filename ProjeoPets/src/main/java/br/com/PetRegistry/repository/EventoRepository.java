package br.com.PetRegistry.repository;

import br.com.PetRegistry.model.Evento;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EventoRepository extends JpaRepository<Evento, Long> {

    // O Spring Data JPA vai gerar a query "SELECT e FROM Evento e WHERE e.petId = ?1"
    List<Evento> findByPetId(Long petId);
}
