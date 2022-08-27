package io.github.marcondesnjr.sismovie.dao.daobd;

import io.github.marcondesnjr.sismovie.Estado;
import io.github.marcondesnjr.sismovie.Grupo;
import io.github.marcondesnjr.sismovie.Permissao;
import io.github.marcondesnjr.sismovie.Usuario;
import io.github.marcondesnjr.sismovie.dao.DAOParticipante;
import io.github.marcondesnjr.sismovie.dao.PersistenceException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author José Marcondes do Nascimento Junior
 */
public class DAOBDParticipante implements DAOParticipante {

    private Connection conn;

    public DAOBDParticipante(Connection conn) {
        this.conn = conn;
    }

    @Override
    public Usuario carregarGrupos(Usuario usr) throws PersistenceException {
        String sql = "SELECT * FROM GRUPO_PARTICIPANTE JOIN GRUPO ON id = grupo_id WHERE usr_email = ?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, usr.getEmail());
            try (ResultSet rs = ps.executeQuery()) {
                usr.setGrupos(multiHandlerGp(rs, usr));
            }
            conn.commit();
            return usr;
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
    public Grupo carregarParticipantes(Grupo gp) throws PersistenceException {
        String sql = "SELECT * FROM GRUPO_PARTICIPANTE JOIN USUARIO ON email = usr_email WHERE grupo_id = ?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, gp.getId());
            try (ResultSet rs = ps.executeQuery()) {
                gp.setParticipantes(multiHandlerUsr(rs));
            }
            conn.commit();
            return gp;
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
    public void persiste(Grupo gp, Usuario usr) throws PersistenceException {
        String sql = "INSERT INTO grupo_participante(grupo_id, usr_email) VALUES (?, ?)";
        try (PreparedStatement ps = conn.prepareStatement(sql)){
            ps.setInt(1, gp.getId());
            ps.setString(2, usr.getEmail());
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

    private List<Grupo> multiHandlerGp(ResultSet rs, Usuario usr) throws SQLException {
        List<Grupo> list = new ArrayList<>();
        while (rs.next()) {
            int id = rs.getInt("id");
            String nome = rs.getString("nome");
            String desc = rs.getString("descricao");
            Grupo gp = new Grupo(nome, desc, usr);
            gp.setId(id);
            list.add(gp);
        }
        return list;
    }

    private List<Usuario> multiHandlerUsr(ResultSet rs) throws SQLException {
        List<Usuario> list = new ArrayList<>();
        while (rs.next()) {
            String nome = rs.getString("nome");
            String sobrenome = rs.getString("sobrenome");
            String email = rs.getString("email");
            String apelido = rs.getString("apelido");
            LocalDate dataNasc = rs.getDate("dt_nasc").toLocalDate();
            String foto = rs.getString("foto");
            String cidade = rs.getString("cidade");
            Estado estado = Estado.valueOf(rs.getString("estado"));
            Permissao per = Permissao.valueOf(rs.getString("permissao"));
            Usuario usuario = new Usuario(nome, sobrenome, email, null, dataNasc, cidade, estado, per);
            usuario.setFoto(foto);
            usuario.setApelido(apelido);
            list.add(usuario);
        }
        return list;
    }

    @Override
    public void close(){
        try {
            conn.close();
        } catch (SQLException ex) {}
    }
}
