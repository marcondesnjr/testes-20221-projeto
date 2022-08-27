package io.github.marcondesnjr.sismovie.dao.daobd;

import io.github.marcondesnjr.sismovie.Comentario;
import io.github.marcondesnjr.sismovie.Estado;
import io.github.marcondesnjr.sismovie.Permissao;
import io.github.marcondesnjr.sismovie.Topico;
import io.github.marcondesnjr.sismovie.Usuario;
import io.github.marcondesnjr.sismovie.dao.DAOComentario;
import io.github.marcondesnjr.sismovie.dao.PersistenceException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author José Marcondes do Nascimento Junior
 */
public class DAOBDComentario implements DAOComentario {

    private Connection conn;

    public DAOBDComentario(Connection conn) {
        this.conn = conn;
    }

    @Override
    public Comentario persiste(Comentario com, Topico tpc) throws PersistenceException {
        String sql = "INSERT INTO comentario(comentario, tpc_id, usr_email) VALUES (?, ?, ?)";
        try (PreparedStatement ps = conn.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS)) {
            ps.setString(1, com.getTexto());
            ps.setInt(2, tpc.getId());
            ps.setString(3, com.getAutor().getEmail());
            ps.executeUpdate();
            try (ResultSet rs = ps.getGeneratedKeys()) {
                if (rs.next()) {
                    com.setId(rs.getInt("id"));                   
                }
                conn.commit();
                return com;
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
    public Topico carregarComentarios(Topico top) throws PersistenceException {
        String sql = "SELECT * FROM COMENTARIO JOIN USUARIO ON usr_email = email WHERE tpc_id = ?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, top.getId());
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    String texto = rs.getString("comentario");
                    int id = rs.getInt("id");

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

                    Comentario com = new Comentario(texto, usr);
                    com.setId(id);
                    top.addComentario(com);
                }
                conn.commit();
                return top;
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
