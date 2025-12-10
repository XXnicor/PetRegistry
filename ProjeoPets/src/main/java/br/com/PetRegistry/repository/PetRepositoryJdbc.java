package br.com.PetRegistry.repository;

import br.com.PetRegistry.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class PetRepositoryJdbc implements PetRepository {

    private final DataSource dataSource;

    @Autowired
    public PetRepositoryJdbc(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public Pet save(Pet pet) {
        if (pet.getId() != null && pet.getId() > 0) {
            return update(pet);
        }

        String sql = "INSERT INTO pet (nome_completo,foto_url,tipo,sexo,porte,status,idade_aproximada,data_entrada,historico_medico,observacoes," +
                "castrado,vacinado,sociavel_com_caes,sociavel_com_gatos,sociavel_com_criancas,lar_temporario_id)" +
                " VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection connection = dataSource.getConnection();
             PreparedStatement smt = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            smt.setString(1, pet.getNomeCompleto());
            smt.setString(2, pet.getFotoUrl());
            smt.setString(3, pet.getTipo().name());
            smt.setString(4, pet.getSexo().name());
            smt.setString(5, pet.getPorte().name());
            smt.setString(6, pet.getStatus().name());
            smt.setInt(7, pet.getIdadeAproximada());
            smt.setDate(8, Date.valueOf(pet.getDataEntrada()));
            smt.setString(9, pet.getHistoricoMedico());
            smt.setString(10, pet.getObservacoes());
            smt.setBoolean(11, pet.isCastrado());
            smt.setBoolean(12, pet.isVacinado());
            smt.setBoolean(13, pet.isSociavelComCaes());
            smt.setBoolean(14, pet.isSociavelComGatos());
            smt.setBoolean(15, pet.isSociavelComCriancas());

            Long larTemporarioId = pet.getLarTemporarioId();
            if (larTemporarioId != null) {
                smt.setLong(16, larTemporarioId);
            } else {
                smt.setNull(16, Types.BIGINT);
            }

            smt.executeUpdate();

            try (ResultSet rs = smt.getGeneratedKeys()) {
                if (rs.next()) {
                    pet.setId(rs.getLong(1));
                }
            }
            return pet;

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao salvar pet no banco de dados", e);
        }
    }

    @Override
    public Optional<Pet> findById(long id) {

        String sql = "SELECT id,nome_completo,foto_url,tipo,sexo,porte,status,idade_aproximada,data_entrada" +
                ",historico_medico,observacoes,castrado,vacinado,sociavel_com_caes,sociavel_com_gatos," +
                "sociavel_com_criancas,lar_temporario_id FROM pet WHERE id =?";

        try (Connection connection = dataSource.getConnection();
             PreparedStatement smt = connection.prepareStatement(sql)) {

            smt.setLong(1, id);

            try (ResultSet rs = smt.executeQuery()) {
                if (rs.next()) {
                    return Optional.of(mapResultSetToPet(rs));
                }
                return Optional.empty();
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao buscar pet no banco de dados", e);
        }
    }

    @Override
    public List<Pet> findByName(String nome) {
        String sql = "SELECT id,nome_completo,foto_url,tipo,sexo,porte,status,idade_aproximada,data_entrada" +
                ",historico_medico,observacoes,castrado,vacinado,sociavel_com_caes,sociavel_com_gatos," +
                "sociavel_com_criancas,lar_temporario_id FROM pet WHERE UPPER(nome_completo) LIKE UPPER(?) ORDER BY id";

        try (Connection connection = dataSource.getConnection();
             PreparedStatement smt = connection.prepareStatement(sql)) {

            smt.setString(1, "%" + nome + "%");

            try (ResultSet rs = smt.executeQuery()) {
                List<Pet> pets = new ArrayList<>();
                while (rs.next()) {
                    pets.add(mapResultSetToPet(rs));
                }
                return pets;
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao buscar pets por nome no banco de dados", e);
        }
    }

    @Override
    public List<Pet> findAllPaged(int offset, int limit) {
        String sql = "SELECT id,nome_completo,foto_url,tipo,sexo,porte,status,idade_aproximada,data_entrada," +
                "historico_medico,observacoes,castrado,vacinado,sociavel_com_caes,sociavel_com_gatos,sociavel_com_criancas,lar_temporario_id FROM pet " +
                "ORDER BY id LIMIT ? OFFSET ?";

        try (Connection connection = dataSource.getConnection();
             PreparedStatement smt = connection.prepareStatement(sql)) {

            smt.setInt(1, limit);
            smt.setInt(2, offset);

            try (ResultSet rs = smt.executeQuery()) {
                List<Pet> pets = new ArrayList<>();
                while (rs.next()) {
                    pets.add(mapResultSetToPet(rs));
                }
                return pets;
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao listar pets paginados no banco de dados", e);
        }
    }
    @Override
    public List<Pet> findByStatus(String status) {

        String sql = "SELECT id,nome_completo,foto_url,tipo,sexo,porte,status,idade_aproximada,data_entrada" +
                ",historico_medico,observacoes,castrado,vacinado,sociavel_com_caes,sociavel_com_gatos," +
                "sociavel_com_criancas,lar_temporario_id FROM pet WHERE status =?";

        try (Connection connection = dataSource.getConnection();
             PreparedStatement smt = connection.prepareStatement(sql)) {

            smt.setString(1,status);

            try (ResultSet rs = smt.executeQuery()) {
                List<Pet> petsStatus = new ArrayList<>();
                while (rs.next()) {
                    petsStatus.add(mapResultSetToPet(rs));
                }
                return petsStatus;
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao buscar pet no banco de dados", e);
        }
    }

    @Override
    public Pet update(Pet pet) {

        String sql = "UPDATE pet SET nome_completo = ?, foto_url = ?, tipo = ?, sexo = ?, porte = ?, status = ?," +
                " idade_aproximada = ?, data_entrada = ?, historico_medico = ?, observacoes = ?," +
                "castrado=?,vacinado=?,sociavel_com_caes=?,sociavel_com_gatos=?,sociavel_com_criancas=?," +
                "lar_temporario_id=? WHERE id = ?";

        try (Connection connection = dataSource.getConnection();
             PreparedStatement smt = connection.prepareStatement(sql)) {

            smt.setString(1, pet.getNomeCompleto());
            smt.setString(2, pet.getFotoUrl());
            smt.setString(3, pet.getTipo().name());
            smt.setString(4, pet.getSexo().name());
            smt.setString(5, pet.getPorte().name());
            smt.setString(6, pet.getStatus().name());
            smt.setInt(7, pet.getIdadeAproximada());
            smt.setDate(8, Date.valueOf(pet.getDataEntrada()));
            smt.setString(9, pet.getHistoricoMedico());
            smt.setString(10, pet.getObservacoes());
            smt.setBoolean(11, pet.isCastrado());
            smt.setBoolean(12, pet.isVacinado());
            smt.setBoolean(13, pet.isSociavelComCaes());
            smt.setBoolean(14, pet.isSociavelComGatos());
            smt.setBoolean(15, pet.isSociavelComCriancas());

            Long larTemporarioId = pet.getLarTemporarioId();
            if (larTemporarioId != null) {
                smt.setLong(16, larTemporarioId);
            } else {
                smt.setNull(16, Types.BIGINT);
            }

            smt.setLong(17, pet.getId());

            int linhasAfetadas = smt.executeUpdate();

            if (linhasAfetadas == 0) {
                throw new SQLException("Erro na atualização: Pet com ID: " + pet.getId());
            }

            return pet;


        } catch (SQLException e) {
            throw new RuntimeException("Erro ao atualizar pet no banco de dados", e);
        }
    }
    @Override
    public void delete(long id) {

        String sql = "DELETE FROM pet WHERE id = ?";

        try (Connection connection = dataSource.getConnection();
             PreparedStatement smt = connection.prepareStatement(sql)) {

            smt.setLong(1, id);

            int linhasAfetadas = smt.executeUpdate();

            if(linhasAfetadas == 0){
                throw new RuntimeException("Erro ao deletar: pet com ID: "+id);
            }

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao deletar pet no banco de dados"+e);
        }
    }

    @Override
    public List<Pet> findAll() {

        String sql= "SELECT id,nome_completo,foto_url,tipo,sexo,porte,status,idade_aproximada,data_entrada," +
                "historico_medico,observacoes,castrado,vacinado,sociavel_com_caes,sociavel_com_gatos,sociavel_com_criancas,lar_temporario_id FROM pet";

        try (Connection connection = dataSource.getConnection();
             PreparedStatement smt = connection.prepareStatement(sql)){

            try (ResultSet rs = smt.executeQuery()){

                List<Pet> pets = new ArrayList<>();

                while (rs.next()){
                    pets.add(mapResultSetToPet(rs));
                }
                return pets;
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao listar todos os pets do banco de dados"+e);
        }
    }

    @Override
    public List<Pet> findByProfileNomeCompletoContainingIgnoreCase(String nome) {
        String sql = "SELECT id,nome_completo,foto_url,tipo,sexo,porte,status,idade_aproximada,data_entrada," +
                "historico_medico,observacoes,castrado,vacinado,sociavel_com_caes,sociavel_com_gatos,sociavel_com_criancas,lar_temporario_id " +
                "FROM pet WHERE LOWER(nome_completo) LIKE LOWER(?)";

        try (Connection connection = dataSource.getConnection();
             PreparedStatement smt = connection.prepareStatement(sql)) {

            smt.setString(1, "%" + nome + "%");

            try (ResultSet rs = smt.executeQuery()) {
                List<Pet> pets = new ArrayList<>();
                while (rs.next()) {
                    pets.add(mapResultSetToPet(rs));
                }
                return pets;
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao buscar pets por nome: " + e.getMessage(), e);
        }
    }

    @Override
    public boolean existsById(Long id) {
        String sql = "SELECT COUNT(*) FROM pet WHERE id = ?";

        try (Connection connection = dataSource.getConnection();
             PreparedStatement smt = connection.prepareStatement(sql)) {

            smt.setLong(1, id);

            try (ResultSet rs = smt.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt(1) > 0;
                }
                return false;
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao verificar existência do pet: " + e.getMessage(), e);
        }
    }

    @Override
    public void deleteById(Long id) {
        delete(id);
    }

    @Override
    public List<Pet> findByLarTemporarioId(Long larTemporarioId) {
        String sql = "SELECT id,nome_completo,foto_url,tipo,sexo,porte,status,idade_aproximada,data_entrada," +
                "historico_medico,observacoes,castrado,vacinado,sociavel_com_caes,sociavel_com_gatos,sociavel_com_criancas,lar_temporario_id " +
                "FROM pet WHERE lar_temporario_id = ?";

        try (Connection connection = dataSource.getConnection();
             PreparedStatement smt = connection.prepareStatement(sql)) {

            smt.setLong(1, larTemporarioId);

            try (ResultSet rs = smt.executeQuery()) {
                List<Pet> pets = new ArrayList<>();
                while (rs.next()) {
                    pets.add(mapResultSetToPet(rs));
                }
                return pets;
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao buscar pets por lar temporário: " + e.getMessage(), e);
        }
    }

    private Pet mapResultSetToPet(ResultSet rs) throws SQLException {
        PetProfile profile = new PetProfile(
                rs.getString("nome_completo"),
                rs.getString("foto_url"),
                PetType.valueOf(rs.getString("tipo")),
                SexType.valueOf(rs.getString("sexo")),
                Porte.valueOf(rs.getString("porte")),
                rs.getInt("idade_aproximada"),
                rs.getDate("data_entrada").toLocalDate(),
                rs.getString("historico_medico"),
                rs.getString("observacoes"),
                rs.getBoolean("castrado"),
                rs.getBoolean("vacinado"),
                rs.getBoolean("sociavel_com_caes"),
                rs.getBoolean("sociavel_com_gatos"),
                rs.getBoolean("sociavel_com_criancas")
        );

        Pet pet = new Pet(
                PetStatus.valueOf(rs.getString("status")),
                profile,
                null // LarTemporario será carregado separado
        );

        pet.setId(rs.getLong("id"));
        return pet;
    }
}
