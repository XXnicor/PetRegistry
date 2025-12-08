package br.com.PetRegistry.repository;

import br.com.PetRegistry.model.Adotante;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class AdotanteRepositoryJdbc implements AdotanteRepository{

    private final DataSource dataSource;

    @Autowired
    public AdotanteRepositoryJdbc(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public Adotante save(Adotante adotante) {
        String sql ="INSERT INTO adotante(nomeCompleto,cpf,contato,endereco,dataAprovacao) VALUES (?,?,?,?,?)";

        try(Connection connection = dataSource.getConnection();
            PreparedStatement smt = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)){

            smt.setString(1,adotante.getNomeCompleto());
            smt.setString(2,adotante.getCpf());
            smt.setString(3,adotante.getContato());
            smt.setString(4,adotante.getEndereco());
            smt.setDate(5, Date.valueOf(adotante.getDataAprovacao()));

            smt.executeUpdate();

            try(ResultSet rs = smt.getGeneratedKeys()){
                if(rs.next()){
                    adotante.setId(rs.getLong(1));
                }
            }
            return adotante;

        } catch (SQLException e) {
            throw new RuntimeException("Erro salvar o adotante ao banco de dados",e);
        }
    }

    @Override
    public Optional<Adotante> findById(Long id) {
        String sql="SELECT id,nomeCompleto,cpf,contato,endereco,dataAprovacao FROM adotante WHERE id=?";

        try(Connection connection = dataSource.getConnection();
            PreparedStatement smt = connection.prepareStatement(sql)){
            smt.setLong(1,id);

            try(ResultSet rs = smt.executeQuery()){
                if(rs.next()){
                    Adotante adotante = new Adotante(
                            rs.getLong("id")
                            , rs.getString("nomeCompleto")
                            , rs.getString("cpf")
                            , rs.getString("contato")
                            , rs.getString("endereco")
                            , rs.getDate("dataAprovacao").toLocalDate());

                    return Optional.of(adotante);
                }
                return Optional.empty();
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao buscar o adotante no banco de dados",e);
        }
    }

    @Override
    public List<Adotante> findAll() {

        String sql="SELECT id,nomeCompleto,cpf,contato,endereco,dataAprovacao FROM adotante";

        try(Connection connection = dataSource.getConnection();
            PreparedStatement smt = connection.prepareStatement(sql)){

            try(ResultSet rs = smt.executeQuery()){
                List<Adotante> adotantes = new ArrayList<>();

                while(rs.next()){
                    Adotante adotante = new Adotante(
                            rs.getLong("id")
                            , rs.getString("nomeCompleto")
                            , rs.getString("cpf")
                            ,rs.getString("contato")
                            , rs.getString("endereco")
                            , rs.getDate("dataAprovacao").toLocalDate());

                    adotantes.add(adotante);

                }
                return adotantes;
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao listar todos os adotantes no banco de dados",e);
        }
    }

    @Override
    public Adotante update(Adotante adotante) {
        String sql="UPDATE adotante SET nomeCompleto=?,cpf=?,contato=?,endereco=?,dataAprovacao=? WHERE id=?";

        try(Connection connection = dataSource.getConnection();
            PreparedStatement smt = connection.prepareStatement(sql)){

            smt.setString(1,adotante.getNomeCompleto());
            smt.setString(2,adotante.getCpf());
            smt.setString(3,adotante.getContato());
            smt.setString(4,adotante.getEndereco());
            smt.setDate(5, Date.valueOf(adotante.getDataAprovacao()));
            smt.setLong(6,adotante.getId());
            smt.executeUpdate();

            return adotante;
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao atualizar adotante no banco de dados",e);
        }
    }

    @Override
    public void deleteById(long id) {
       String sql="DELETE FROM adotante WHERE id=?";
        try(Connection connection = dataSource.getConnection();
            PreparedStatement smt = connection.prepareStatement(sql)){
            smt.setLong(1,id);
            smt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao deletar adotante no banco de dados",e);
        }
    }
}
