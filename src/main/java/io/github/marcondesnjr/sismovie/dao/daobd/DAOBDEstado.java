package io.github.marcondesnjr.sismovie.dao.daobd;

import io.github.marcondesnjr.sismovie.Estado;
import io.github.marcondesnjr.sismovie.dao.DAOEstado;
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
public class DAOBDEstado implements DAOEstado{

    private final Connection conn;

    public DAOBDEstado(Connection conn) {
        this.conn = conn;
    }
    
    @Override
    public void persistir(String estado) throws PersistenceException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void excluir(String estado) throws PersistenceException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Estado> todosEstados() throws PersistenceException {
        String sql = "SELECT * FROM ESTADO";
        try(PreparedStatement ps = conn.prepareStatement(sql)){
            try(ResultSet rs = ps.executeQuery()){
                List<Estado> ests = new ArrayList<>();
                while(rs.next()){
                    ests.add(Estado.valueOf(rs.getString("sigla")));
                }
                conn.commit();
                return ests;
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
            Logger.getLogger(DAOBDEstado.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
}
