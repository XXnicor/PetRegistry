package br.com.PetRegistry.repository;

import br.com.PetRegistry.model.Evento;
import org.springframework.beans.factory.annotation.Autowired;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Implementação JDBC desabilitada - Use EventoRepository (JPA) diretamente
 * Esta classe está aqui apenas para referência
 */
// @Repository - REMOVIDO para não conflitar com JPA
public class EventoRepositoryJdbc {

    private final DataSource dataSource;

    @Autowired
    public EventoRepositoryJdbc(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public Evento saveEvento(Evento evento) {
        String sql="INSERT INTO evento(data,tipoEvento,descricao) VALUES (?,?,?)";

        try(Connection connection = dataSource.getConnection();
            PreparedStatement smt = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)){

            smt.setTimestamp(1, Timestamp.valueOf(evento.getData()));
            smt.setString(2,evento.getTipoEvento());
            smt.setString(3,evento.getDescricao());

            smt.executeUpdate();

            try(ResultSet rs = smt.getGeneratedKeys()){
                if(rs.next()){
                    evento.setId(rs.getLong(1));
                }
            }
            return evento;

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao salvar evento no banco de dados",e);
        }
    }

    public List<Evento> findByPetId(Long petId) {
        // TODO: Implementação JDBC requer buscar o Pet primeiro
        // Por enquanto, retorna lista vazia
        // Recomenda-se usar o EventoRepository (JPA) diretamente
        return new ArrayList<>();
    }
}
