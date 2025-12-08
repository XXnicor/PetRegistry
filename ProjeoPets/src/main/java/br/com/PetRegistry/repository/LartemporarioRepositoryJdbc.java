package br.com.PetRegistry.repository;


import br.com.PetRegistry.model.LarTemporario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class LartemporarioRepositoryJdbc implements LarTemporarioRepository {

    private final DataSource dataSource;

    @Autowired
    public LartemporarioRepositoryJdbc(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public LarTemporario saveLarTemporario(LarTemporario larTemporario) {
        String sql="INSERT INTO larTemporario(nomeResponsavel,contato, enderecoCompleto,capacidadeMaxima,semVagas)"+
                " VALUES (?,?,?,?,?)";

        try(Connection connection = dataSource.getConnection();
             PreparedStatement smt = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)){

            smt.setString(1,larTemporario.getNomeResponsavel());
            smt.setString(2,larTemporario.getContato());
            smt.setString(3,larTemporario.getEnderecoCompleto());
            smt.setInt(4,larTemporario.getCapacidadeMaxima());
            smt.setBoolean(5,larTemporario.isSemVagas());


            smt.executeUpdate();

            try(ResultSet rs = smt.getGeneratedKeys()){
                if(rs.next()){
                    larTemporario.setId(rs.getLong(1));
                }
            }
            return larTemporario;

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao salvar o lar temporário no banco de dados",e);
        }
    }

    @Override
    public Optional<LarTemporario> findLarTemporarioById(long id) {

        String sql = "SELECT id,nomeResponsavel,contato,enderecoCompleto,capacidadeMaxima,semVagas FROM larTemporario WHERE id=?";

        try (Connection connection = dataSource.getConnection();
             PreparedStatement smt = connection.prepareStatement(sql)) {

            smt.setLong(1, id);

            try (ResultSet rs = smt.executeQuery()) {
                if (rs.next()) {
                    LarTemporario larTemporario = new LarTemporario(
                            rs.getLong("id")
                            , rs.getString("nomeResponsavel")
                            , rs.getString("contato")
                            , rs.getString("enderecoCompleto")
                            , rs.getInt("capacidadeMaxima")
                            , rs.getBoolean("semVagas"));

                    return Optional.of(larTemporario);
                }
                return Optional.empty();

            }
        }catch (SQLException e) {
                throw new RuntimeException("Erro ao encontrar o lar temporário no banco de dados", e);
            }
        }
    @Override
    public List<LarTemporario> findAllLarTemporario() {

        String sql="SELECT id,nomeResponsavel,contato,enderecoCompleto,capacidadeMaxima,semVagas FROM larTemporario";

        try(Connection connection = dataSource.getConnection();
            PreparedStatement smt = connection.prepareStatement(sql)){

            try(ResultSet rs = smt.executeQuery()){

                List<LarTemporario> larTemporarios = new ArrayList<>();

                while(rs.next()){
                    LarTemporario larTemporario = new LarTemporario(
                            rs.getLong("id")
                            , rs.getString("nomeResponsavel")
                            , rs.getString("contato")
                            , rs.getString("enderecoCompleto")
                            , rs.getInt("capacidadeMaxima")
                            , rs.getBoolean("semVagas"));

                    larTemporarios.add(larTemporario);
                }
                return larTemporarios;
            }

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao listar todos os lar temporários no banco de dados",e);
        }
    }

    @Override
    public boolean deleteLarTemporarioById(long id) {
        if(id <= 0){
            throw new IllegalArgumentException("ID inválido");
        }
        String sql="DELETE FROM larTemporario WHERE id=?";
        try(Connection connection = dataSource.getConnection();
            PreparedStatement smt = connection.prepareStatement(sql)){
            smt.setLong(1,id);
            
            int linhasAfetadas = smt.executeUpdate();
            
            return linhasAfetadas > 0;
            
        } catch (SQLException ex) {
            throw new RuntimeException("Erro ao deletar lar temporario com ID: " + id,ex);
        }
    }

    @Override
    public LarTemporario updateLarTemporario(LarTemporario larTemporario) {
        String sql = "UPDATE larTemporario SET nomeResponsavel = ?, contato = ?, " +
                "enderecoCompleto = ?, capacidadeMaxima = ?, semVagas = ? WHERE id = ?";

        try (Connection connection = dataSource.getConnection();
             PreparedStatement smt = connection.prepareStatement(sql)) {

            smt.setString(1, larTemporario.getNomeResponsavel());
            smt.setString(2, larTemporario.getContato());
            smt.setString(3, larTemporario.getEnderecoCompleto());
            smt.setInt(4, larTemporario.getCapacidadeMaxima());
            smt.setBoolean(5, larTemporario.isSemVagas());
            smt.setLong(6, larTemporario.getId());

            smt.executeUpdate();
            return larTemporario;

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao atualizar o lar temporário no banco de dados", e);
        }
    }
}
