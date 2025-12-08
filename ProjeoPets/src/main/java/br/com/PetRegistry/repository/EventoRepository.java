package br.com.PetRegistry.repository;

import br.com.PetRegistry.model.Evento;
import br.com.PetRegistry.model.Pet;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class EventoRepository {

    private final DataSource dataSource;
    private final PetRepository petRepository;

    public EventoRepository(DataSource dataSource, PetRepository petRepository) {
        this.dataSource = dataSource;
        this.petRepository = petRepository;
    }

    public Evento save(Evento evento) {
        String sql = "INSERT INTO evento(data, tipoEvento, descricao, pet_id) VALUES (?, ?, ?, ?)";

        try (Connection connection = dataSource.getConnection();
             PreparedStatement smt = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            smt.setTimestamp(1, Timestamp.valueOf(evento.getData()));
            smt.setString(2, evento.getTipoEvento());
            smt.setString(3, evento.getDescricao());

            if (evento.getPet() != null) {
                smt.setLong(4, evento.getPet().getId());
            } else {
                smt.setNull(4, Types.BIGINT);
            }

            smt.executeUpdate();

            try (ResultSet rs = smt.getGeneratedKeys()) {
                if (rs.next()) {
                    evento.setId(rs.getLong(1));
                }
            }
            return evento;

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao salvar evento no banco de dados", e);
        }
    }

    public List<Evento> findByPetId(Long petId) {
        String sql = "SELECT id, data, tipoEvento, descricao, pet_id FROM evento WHERE pet_id = ?";
        List<Evento> eventos = new ArrayList<>();

        try (Connection connection = dataSource.getConnection();
             PreparedStatement smt = connection.prepareStatement(sql)) {

            smt.setLong(1, petId);

            try (ResultSet rs = smt.executeQuery()) {
                while (rs.next()) {
                    Pet pet = petRepository.findById(petId).orElse(null);

                    if (pet != null) {
                        String tipoEvento = rs.getString("tipoEvento");
                        String descricao = rs.getString("descricao");

                        Evento evento = new Evento(pet, tipoEvento, descricao);
                        evento.setId(rs.getLong("id"));

                        eventos.add(evento);
                    }
                }
            }
            return eventos;

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao buscar eventos por petId no banco de dados", e);
        }
    }
}
