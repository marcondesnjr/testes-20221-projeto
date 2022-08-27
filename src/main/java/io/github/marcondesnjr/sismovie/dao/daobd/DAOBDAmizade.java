package io.github.marcondesnjr.sismovie.dao.daobd;

import io.github.marcondesnjr.sismovie.Estado;
import io.github.marcondesnjr.sismovie.Permissao;
import io.github.marcondesnjr.sismovie.Solicitacao;
import io.github.marcondesnjr.sismovie.Usuario;
import io.github.marcondesnjr.sismovie.dao.DAOAmizade;
import io.github.marcondesnjr.sismovie.dao.PersistenceException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author José Marcondes do Nascimento Junior
 */
public class DAOBDAmizade implements DAOAmizade{
    
    private Connection conn;

    public DAOBDAmizade(Connection conn) {
        this.conn = conn;
    }
    
    
    @Override
    public void persistir(Solicitacao sol) throws PersistenceException{
        String sql = "INSERT INTO AMIZADE VALUES(?,?,?)";
        try(PreparedStatement ps = conn.prepareStatement(sql)){
            ps.setString(1, sol.getRemetente().getEmail());
            ps.setString(2, sol.getDestinatario().getEmail());
            ps.setInt(3, sol.getStatus());
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
    public List<Solicitacao> localizarRecebida(Usuario usr) throws PersistenceException {
        String sql = "SELECT * FROM AMIZADE WHERE destinatario = ? AND status = ?";
        try(PreparedStatement ps = conn.prepareStatement(sql)){
            ps.setString(1, usr.getEmail());
            ps.setInt(2, Solicitacao.PENDENTE);
            List<Solicitacao> sols = new ArrayList<>();
            try(ResultSet rs = ps.executeQuery()){
                while(rs.next()){
                    String rem = rs.getString("remetente");
                    Solicitacao sol = new Solicitacao(new DAOBDUsuario(conn).
                            localizar(rem), usr);
                    sols.add(sol);
                }
                conn.commit();
                return sols;
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
    public void atualizar(Solicitacao sol) throws PersistenceException {
        String sql = "UPDATE AMIZADE SET status = ? WHERE destinatario = ? AND remetente = ?";
        try(PreparedStatement ps = conn.prepareStatement(sql)){
            ps.setInt(1, sol.getStatus());
            ps.setString(2, sol.getDestinatario().getEmail());
            ps.setString(3, sol.getRemetente().getEmail());
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
    public List<Usuario> localizarAmigos(Usuario usuario) throws PersistenceException {
        String sql ="SELECT * FROM AMIZADE JOIN USUARIO ON remetente = email OR destinatario = email "
                + "WHERE (remetente = ? OR destinatario = ?) AND (status = ?) AND (email <> ?)";
        try(PreparedStatement ps = conn.prepareStatement(sql)){
            ps.setString(1, usuario.getEmail());
            ps.setString(2, usuario.getEmail());
            ps.setInt(3, Solicitacao.ACEITO);
            ps.setString(4, usuario.getEmail());
            try(ResultSet rs = ps.executeQuery()){
            List<Usuario> usrs = new ArrayList<>();
                while(rs.next()){
                    String nome = rs.getString("nome");
                    String sobrenome = rs.getString("sobrenome");
                    String email = rs.getString("email");
                    String apelido = rs.getString("apelido");
                    LocalDate dataNasc = rs.getDate("dt_nasc").toLocalDate();
                    String foto = rs.getString("foto");
                    String cidade = rs.getString("cidade");
                    Estado estado = Estado.valueOf(rs.getString("estado"));
                    Permissao per = Permissao.valueOf(rs.getString("permissao"));
                    Usuario usr = new Usuario(nome, sobrenome, email, null, dataNasc, cidade, estado,per);
                    usr.setFoto(foto);
                    usr.setApelido(apelido);
                    usrs.add(usr);
                }
                conn.commit();
                return usrs;
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
    public boolean existeSolicitacao(Usuario usr, Usuario other) throws PersistenceException{
        String sql ="SELECT * FROM AMIZADE_USUARIO "
                + "WHERE (remetente = ? AND destinatario = ?) OR (remetente = ? AND destinatario = ?)";
        try(PreparedStatement ps = conn.prepareStatement(sql)){
            ps.setString(1, usr.getEmail());
            ps.setString(2, other.getEmail());
            ps.setString(3, other.getEmail());
            ps.setString(4, usr.getEmail());
            try(ResultSet rs = ps.executeQuery()){
                return rs.next();
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
    public void deletar(String rem, String dest) throws PersistenceException {
        String sql = "DELETE FROM AMIZADE WHERE remetente = ? AND destinatario = ?";
        try(PreparedStatement ps = conn.prepareStatement(sql)){
            ps.setString(1, rem);
            ps.setString(2, dest);
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
    public void close(){
        try {
            conn.close();
        } catch (SQLException ex) {
            Logger.getLogger(DAOBDAmizade.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
