package io.github.marcondesnjr.sismovie.dao.daobd;

import io.github.marcondesnjr.sismovie.Usuario;
import io.github.marcondesnjr.sismovie.dao.DAOGenero;
import io.github.marcondesnjr.sismovie.dao.PersistenceException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author José Marcondes do Nascimento Junior
 */
public class DAOBDGenero implements DAOGenero{

    private final Connection conn;

    public DAOBDGenero(Connection conn) {
        this.conn = conn;
    }
        
    @Override
    public void persistir(String gen) throws PersistenceException {
        String sql = "INSERT INTO GENERO VALUES(?)";
        try(PreparedStatement ps = conn.prepareStatement(sql)){
            ps.setString(1, gen);
            ps.executeUpdate();
            conn.commit();
        } catch (SQLException ex) {
            try {
                conn.rollback();
                throw new PersistenceException(ex);
            } catch (SQLException ex1) {
                throw new PersistenceException("Erro ao tentar realizar ROLLBACK",ex1);
            }
        }
    }

    @Override
    public Usuario excluir(String gen) throws PersistenceException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Usuario> listar() throws PersistenceException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Usuario> localizarNome(String nome) throws PersistenceException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<String> todosGeneros() throws PersistenceException {
        String sql = "SELECT nome FROM GENERO";
        try(PreparedStatement ps = conn.prepareStatement(sql)){
            try(ResultSet rs = ps.executeQuery()){
                List<String> generos = new ArrayList<>();
                while(rs.next()){
                    generos.add(rs.getString("nome"));
                }
                conn.commit();
                return generos;
            }
        } catch (SQLException ex) {
            try {
                conn.rollback();
                throw new PersistenceException(ex);
            } catch (SQLException ex1) {
                throw new PersistenceException("Erro ao tentar realizar ROLLBACK",ex1);
            }
        }
    }

    @Override
    public void close(){
        try {
            conn.close();
        } catch (SQLException ex) {
            Logger.getLogger(DAOBDGenero.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
