package io.github.marcondesnjr.sismovie.dao.daobd;

import io.github.marcondesnjr.sismovie.Estado;
import io.github.marcondesnjr.sismovie.Grupo;
import io.github.marcondesnjr.sismovie.Permissao;
import io.github.marcondesnjr.sismovie.Usuario;
import io.github.marcondesnjr.sismovie.dao.DAOGrupo;
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
public class DAOBDGrupo implements DAOGrupo {

    private Connection conn;

    public DAOBDGrupo(Connection conn) {
        this.conn = conn;
    }

    @Override
    public Grupo persiste(Grupo grp) throws PersistenceException {
        String sql = "INSERT INTO grupo(nome, descricao, criador) VALUES (?, ?, ?)";
        try (PreparedStatement ps = conn.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS)) {
            ps.setString(1, grp.getNome());
            ps.setString(2, grp.getDescricao());
            ps.setString(3, grp.getCriador().getEmail());
            ps.executeUpdate();
            try (ResultSet rs = ps.getGeneratedKeys()) {
                if (rs.next()) {
                    grp.setId(rs.getInt("id"));
                }
            }
            conn.commit();
            return grp;
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

    protected static List<Grupo> handlerGrupo(ResultSet rs, Usuario usr) throws SQLException {
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

    @Override
    public Grupo localiza(int id) throws PersistenceException {
        String sql = "SELECT * FROM GRUPO JOIN USUARIO ON email = criador WHERE id = ?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                Grupo gp = null;
                if (rs.next()) {
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

                    String nomeg = rs.getString("nome");
                    String desc = rs.getString("descricao");
                    gp = new Grupo(nomeg, desc, usuario);
                    gp.setId(id);

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
    public List<Grupo> localizarTodos() throws PersistenceException {
        String sql = "SELECT * FROM GRUPO JOIN USUARIO ON email = criador";
        List<Grupo> list = new ArrayList<>();
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            try (ResultSet rs = ps.executeQuery()) {

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

                    int idGrupo = rs.getInt("id");
                    String nomeg = rs.getString("nome");
                    String desc = rs.getString("descricao");
                    Grupo gp = new Grupo(nomeg, desc, usuario);
                    gp.setId(idGrupo);
                    list.add(gp);
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
    public List<Grupo> localizarPorNome(String nome) throws PersistenceException {
        String sql = "SELECT * FROM GRUPO G JOIN USUARIO U ON U.email = G.criador WHERE G.nome ILIKE ?";
        List<Grupo> list = new ArrayList<>();
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, "%"+nome+"%");
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    String nomeUsr = rs.getString("nome");
                    String sobrenome = rs.getString("sobrenome");
                    String email = rs.getString("email");
                    String apelido = rs.getString("apelido");
                    LocalDate dataNasc = rs.getDate("dt_nasc").toLocalDate();
                    String foto = rs.getString("foto");
                    String cidade = rs.getString("cidade");
                    Estado estado = Estado.valueOf(rs.getString("estado"));
                    Permissao per = Permissao.valueOf(rs.getString("permissao"));
                    Usuario usuario = new Usuario(nomeUsr, sobrenome, email, null, dataNasc, cidade, estado, per);
                    usuario.setFoto(foto);
                    usuario.setApelido(apelido);

                    int idGrupo = rs.getInt("id");
                    String nomeg = rs.getString("nome");
                    String desc = rs.getString("descricao");
                    Grupo gp = new Grupo(nomeg, desc, usuario);
                    gp.setId(idGrupo);
                    list.add(gp);
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
