
package io.github.marcondesnjr.sismovie.dao.daobd;

import io.github.marcondesnjr.sismovie.Avaliacao;
import io.github.marcondesnjr.sismovie.Filme;
import io.github.marcondesnjr.sismovie.Usuario;
import io.github.marcondesnjr.sismovie.dao.CriadorFabrica;
import io.github.marcondesnjr.sismovie.dao.DAOAvaliacao;
import io.github.marcondesnjr.sismovie.dao.DAOUsuario;
import io.github.marcondesnjr.sismovie.dao.PersistenceException;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author José Marcondes do Nascimento Junior
 */
public class DAOBDAvaliacao implements DAOAvaliacao{

    private Connection conn;

    public DAOBDAvaliacao(Connection conn) {
        this.conn = conn;
    }
    
    
    @Override
    public Avaliacao persistir(Filme fl, Avaliacao avl,Usuario usr) throws PersistenceException {

        String sql = "INSERT INTO AVALIACAO(id_filme, email_usr,rating,descricao) VALUES (?,?,?,?)";
        try(PreparedStatement ps = conn.prepareStatement(sql)){
            ps.setInt(1, fl.getId());
            ps.setString(2, usr.getEmail());
            ps.setInt(3, avl.getRating());
            ps.setString(4, avl.getDesc());
            ps.executeUpdate();
            conn.commit();
            return avl;
        } catch (SQLException ex) {
            try {
                conn.rollback();
                throw new PersistenceException(ex);
            } catch (SQLException ex1) {
                throw new PersistenceException(ex1);
            }
        }
    
    }

    @Override
    public double avaliacaoMedia(Filme fl) throws PersistenceException {
        String sql = "{? = call avl_media(?)}";
        try(CallableStatement cs = conn.prepareCall(sql)){
            cs.registerOutParameter(1, Types.DOUBLE);
            cs.setInt(2, fl.getId());
            cs.execute();
            double media = cs.getDouble(1);
            conn.commit();
            return media;
        } catch (SQLException ex) {
            try {
                conn.rollback();
                throw new PersistenceException(ex);
            } catch (SQLException ex1) {
                throw new PersistenceException(ex1);
            }
        }
    }
    
    

    @Override
    public void close(){
        try {
            conn.close();
        } catch (SQLException ex) {
            Logger.getLogger(DAOBDAvaliacao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public List<Avaliacao> carregarAvaliacoes(Usuario usr) throws PersistenceException {
        String sql = "SELECT * FROM AVALIACAO WHERE email_usr = ?";
        List<Avaliacao> list = new ArrayList<>();
        try(PreparedStatement ps = conn.prepareStatement(sql)){
            ps.setString(1, usr.getEmail());
            try(ResultSet rs = ps.executeQuery()){
                while(rs.next()){
                    int rating = rs.getInt("rating");
                    String descricao = rs.getString("descricao");
                    Avaliacao avl = new Avaliacao(rating, descricao);
                    avl.setUsr(usr);
                    avl.setId(rs.getInt("id"));
                    list.add(avl);
                }
                conn.commit();
                return list;
            }
        } catch (SQLException ex) {
            try {
                conn.rollback();
                throw new PersistenceException(ex);
            } catch (SQLException ex1) {
                throw new PersistenceException(ex1);
            }
        }
    }

    @Override
    public List<Avaliacao> carregarAvaliacoes(Filme fm) throws PersistenceException {
        String sql = "SELECT * FROM AVALIACAO WHERE id_filme = ?";
        List<Avaliacao> list = new ArrayList<>();
        try(PreparedStatement ps = conn.prepareStatement(sql)){
            ps.setInt(1, fm.getId());
            try(ResultSet rs = ps.executeQuery()){
                while(rs.next()){
                    int rating = rs.getInt("rating");
                    String descricao = rs.getString("descricao");
                    Avaliacao avl = new Avaliacao(rating, descricao);
                    Usuario usr;
                    try(DAOUsuario dao = CriadorFabrica.criarFabrica(CriadorFabrica.BANCO_DE_DADOS).criarDaoUsuario();){
                        usr = dao.localizar(rs.getString("email_usr"));
                    }
                    avl.setFilme(fm);
                    avl.setUsr(usr);
                    list.add(avl);
                }
                conn.commit();
                return list;
            }
        } catch (SQLException ex) {
            try {
                conn.rollback();
                throw new PersistenceException(ex);
            } catch (SQLException ex1) {
                throw new PersistenceException(ex1);
            }
        }
    }
}
