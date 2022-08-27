
package io.github.marcondesnjr.sismovie.dao.daobd;

import io.github.marcondesnjr.sismovie.Estado;
import io.github.marcondesnjr.sismovie.Filme;
import io.github.marcondesnjr.sismovie.Permissao;
import io.github.marcondesnjr.sismovie.Recomendacao;
import io.github.marcondesnjr.sismovie.Usuario;
import io.github.marcondesnjr.sismovie.dao.DAORecomendacao;
import io.github.marcondesnjr.sismovie.dao.PersistenceException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.Year;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author José Marcondes do Nascimento Junior
 */
public class DAOBDRecomendacao implements DAORecomendacao{

    private Connection conn;

    public DAOBDRecomendacao(Connection conn) {
        this.conn = conn;
    }
    
    
    
    @Override
    public void persiste(Recomendacao re, Usuario dest) throws PersistenceException {
        String sql = "INSERT INTO recomendacao(filme, rem, dest) VALUES (?, ?, ?)";
        try(PreparedStatement ps = conn.prepareStatement(sql)){
            ps.setInt(1, re.getFilme().getId());
            ps.setString(2, re.getRem().getEmail());
            ps.setString(3, dest.getEmail());
            ps.executeUpdate();
            conn.commit();
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
    public void deletar(String rm, int fm, String dest) throws PersistenceException {
        String sql = "DELETE FROM RECOMENDADAO WHERE filme = ?, rem = ?, dest = ?";
        try(PreparedStatement ps = conn.prepareStatement(sql)){
            ps.setInt(1, fm);
            ps.setString(2, rm);
            ps.setString(3, dest);
            ps.executeUpdate();
            conn.commit();
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
    public Usuario carregarRecomendacoes(Usuario usr) throws PersistenceException{
        String sql = "SELECT * FROM RECOMENDACAO JOIN FILME ON id = filme "
                + "JOIN USUARIO U ON rem = email WHERE dest = ?";
        try(PreparedStatement ps = conn.prepareStatement(sql)){
            ps.setString(1, usr.getEmail());
            List<Recomendacao> list = new ArrayList<>();
            try(ResultSet rs = ps.executeQuery()){
                while(rs.next()){
                    
                    String fotof = rs.getString(5);
                    String titulo = rs.getString("titulo");
                    String sinopse = rs.getString("sinopse");
                    Year ano = Year.parse(rs.getString("ano"));
                    Filme fm = new Filme(fotof, titulo, sinopse, ano);
                    fm.setId(rs.getInt("id"));
                
                    String nome = rs.getString("nome");
                    String sobrenome = rs.getString("sobrenome");
                    String email = rs.getString("email");
                    String apelido = rs.getString("apelido");
                    LocalDate dataNasc = rs.getDate("dt_nasc").toLocalDate();
                    String foto = rs.getString("foto");
                    String cidade = rs.getString("cidade");
                    Estado estado = Estado.valueOf(rs.getString("estado"));
                    Permissao per = Permissao.valueOf(rs.getString("permissao"));
                    Usuario usuarioRem = new Usuario(nome, sobrenome, email, null, dataNasc, cidade, estado,per);
                    usuarioRem.setFoto(foto);
                    usuarioRem.setApelido(apelido);
                    Recomendacao re = new Recomendacao(usr, fm);
                    list.add(re);
                }
                usr.setRecomendacoes(list);
                conn.commit();
                return usr;
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
    public void close() {
        try {
            conn.close();
        } catch (SQLException ex) {}
    }
    
}
