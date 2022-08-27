package io.github.marcondesnjr.sismovie.dao.daobd;

import io.github.marcondesnjr.sismovie.Estado;
import io.github.marcondesnjr.sismovie.Filme;
import io.github.marcondesnjr.sismovie.Grupo;
import io.github.marcondesnjr.sismovie.Permissao;
import io.github.marcondesnjr.sismovie.Topico;
import io.github.marcondesnjr.sismovie.Usuario;
import io.github.marcondesnjr.sismovie.dao.DAOTopico;
import io.github.marcondesnjr.sismovie.dao.PersistenceException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.Year;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author José Marcondes do Nascimento Junior
 */
public class DAOBDTopico implements DAOTopico {

    private Connection conn;

    public DAOBDTopico(Connection conn) {
        this.conn = conn;
    }

    @Override
    public Topico persiste(Topico tp, Grupo gp) throws PersistenceException {
        String sql = "INSERT INTO topico(grupo_id, email_usr, filme_id, comentario,titulo) VALUES (?, ?, ?, ?,?)";
        try (PreparedStatement ps = conn.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS)) {
            ps.setInt(1, gp.getId());
            ps.setString(2, tp.getCriador().getEmail());
            ps.setInt(3, tp.getFilme().getId());
            ps.setString(4, tp.getComentario());
            ps.setString(5, tp.getTitulo());
            ps.executeUpdate();
            try (ResultSet rs = ps.getGeneratedKeys()) {
                if (rs.next()) {
                    int id = rs.getInt("id");
                    tp.setId(id);
                }
            }
            conn.commit();
            return tp;
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
        } catch (SQLException ex) {
        }
    }

    @Override
    public Grupo carregarTopicos(Grupo gp) throws PersistenceException {
        String sql = "SELECT * FROM TOPICO T JOIN USUARIO ON email_usr = email"
                + " JOIN FILME F ON filme_id = F.id WHERE grupo_id = ?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, gp.getId());
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    int id = rs.getInt("id");
                    String titulo = rs.getString("titulo");
                    String desc = rs.getString("comentario");
                    
                    String nome = rs.getString("nome");
                    String sobrenome = rs.getString("sobrenome");
                    String email = rs.getString("email");
                    String apelido = rs.getString("apelido");
                    LocalDate dataNasc = rs.getDate("dt_nasc").toLocalDate();
                    String foto = rs.getString("foto");
                    String cidade = rs.getString("cidade");
                    Estado estado = Estado.valueOf(rs.getString("estado"));
                    Permissao per = Permissao.valueOf(rs.getString("permissao"));
                    Usuario usr = new Usuario(nome, sobrenome, email, null, dataNasc, cidade, estado, per);
                    usr.setFoto(foto);
                    usr.setApelido(apelido);

                    String fotof = rs.getString(18);
                    String titulof = rs.getString("titulo");
                    String sinopse = rs.getString("sinopse");
                    Year ano = Year.parse(rs.getString("ano"));
                    Filme fm = new Filme(fotof, titulof, sinopse, ano);
                    fm.setId(rs.getInt("filme_id"));

                    Topico tp = new Topico(titulo, desc, usr, fm);
                    tp.setId(id);
                    gp.addTopico(tp);
                }
                conn.commit();
                return gp;
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
    public Topico localiza(int id) throws PersistenceException {
        String sql = "SELECT * FROM TOPICO T JOIN USUARIO ON email_usr = email"
                + " JOIN FILME F ON filme_id = F.id WHERE T.id = ?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                Topico tp = null;
                if (rs.next()) {
                    String titulo = rs.getString("titulo");
                    String desc = rs.getString("comentario");
                    
                    String nome = rs.getString("nome");
                    String sobrenome = rs.getString("sobrenome");
                    String email = rs.getString("email");
                    String apelido = rs.getString("apelido");
                    LocalDate dataNasc = rs.getDate("dt_nasc").toLocalDate();
                    String foto = rs.getString("foto");
                    String cidade = rs.getString("cidade");
                    Estado estado = Estado.valueOf(rs.getString("estado"));
                    Permissao per = Permissao.valueOf(rs.getString("permissao"));
                    Usuario usr = new Usuario(nome, sobrenome, email, null, dataNasc, cidade, estado, per);
                    usr.setFoto(foto);
                    usr.setApelido(apelido);

                    String fotof = rs.getString(18);
                    String titulof = rs.getString("titulo");
                    String sinopse = rs.getString("sinopse");
                    Year ano = Year.parse(rs.getString("ano"));
                    Filme fm = new Filme(fotof, titulof, sinopse, ano);
                    fm.setId(rs.getInt("filme_id"));

                    tp = new Topico(titulo, desc, usr, fm);
                    tp.setId(id);
                }
                conn.commit();
                return tp;
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
