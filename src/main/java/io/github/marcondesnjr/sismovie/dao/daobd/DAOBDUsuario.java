package io.github.marcondesnjr.sismovie.dao.daobd;

import io.github.marcondesnjr.sismovie.dao.AlreadyExistsException;
import io.github.marcondesnjr.sismovie.Estado;
import io.github.marcondesnjr.sismovie.Permissao;
import io.github.marcondesnjr.sismovie.Usuario;
import io.github.marcondesnjr.sismovie.dao.PersistenceException;
import io.github.marcondesnjr.sismovie.dao.DAOUsuario;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DAOBDUsuario implements DAOUsuario {

    private final Connection conn;

    public DAOBDUsuario(Connection conn) {
        this.conn = conn;
    }
    
    
    @Override
    public void persistir(Usuario usuario) throws PersistenceException, AlreadyExistsException{
        String sql = "INSERT INTO usuario(nome, sobrenome, email, senha, " +
                "apelido, dt_nasc, foto, cidade, estado, permissao) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, usuario.getNome());
            ps.setString(2, usuario.getSobrenome());
            ps.setString(3, usuario.getEmail());
            ps.setString(4, usuario.getSenha());
            ps.setString(5, usuario.getApelido());
            ps.setDate(6, Date.valueOf(usuario.getDataNasc()));
            ps.setString(7, usuario.getFoto());
            ps.setString(8, usuario.getCidade());
            ps.setString(9, usuario.getEstado().name());
            ps.setString(10, usuario.getPermissao().name());
            ps.executeUpdate();
            conn.commit();
        } catch (SQLException ex) {
            Usuario usr = localizar(usuario.getEmail());
            if(usr != null){
                throw new AlreadyExistsException(ex);
            }
            throw new PersistenceException(ex);
        }
    }


    @Override
    public void excluir(String email) throws PersistenceException{
        String sql = "DELETE FROM USUARIO WHERE email = ?";
        try(PreparedStatement ps = conn.prepareStatement(sql)){
            ps.setString(1, email);
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
    public List<Usuario> listar() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Usuario localizar(String login) throws PersistenceException {
        try (PreparedStatement ps = conn.prepareStatement("SELECT * FROM USUARIO WHERE email = ?")) {
            ps.setString(1, login);
            try(ResultSet rs = ps.executeQuery()){
                List<Usuario> usr = handlerUsuario(rs);
                return usr.size() > 0?usr.get(0):null;
            }
        } catch (SQLException ex) {
            throw new PersistenceException(ex);
        }
    }
 
    @Override
    public List<Usuario> localizarNome(String nome) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Usuario localizar(String login, String senha) throws PersistenceException {
        try (PreparedStatement ps = conn.prepareStatement("SELECT * FROM USUARIO WHERE email = ? AND senha = ?")) {
            ps.setString(1, login);
            ps.setString(2, senha);
            try(ResultSet rs = ps.executeQuery()){
                List<Usuario> usr = handlerUsuario(rs);
                conn.commit();
                return usr.size() > 0? usr.get(0):null;
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
    public List<Usuario> localizar() throws PersistenceException {
        String sql = "SELECT * FROM USUARIO";
        try(Statement stm = conn.createStatement()){
            try(ResultSet rs = stm.executeQuery(sql)){
                return handlerUsuario(rs);
            }
        } catch (SQLException ex) {
            throw new PersistenceException(ex);
        }
    }

    @Override
    public List<Usuario> perquisarNome(String nome) throws PersistenceException {
        String sql = "SELECT * FROM USUARIO WHERE nome ILIKE ? OR sobrenome ILIKE ?";
        try(PreparedStatement ps = conn.prepareStatement(sql)){
            ps.setString(1, "%" + nome + "%");
            ps.setString(2, "%" + nome + "%");
           try(ResultSet rs = ps.executeQuery()){
               return handlerUsuario(rs);
           } 
        } catch (SQLException ex) {
            throw new PersistenceException(ex);
        }
    }
    
    
    protected static List<Usuario> handlerUsuario(ResultSet rs) throws SQLException{
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
                return usrs;
    }

    @Override
    public void close(){
        try {
            conn.close();
        } catch (SQLException ex) {
            Logger.getLogger(DAOBDUsuario.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void editar(Usuario usuario) throws PersistenceException {
        String sql = "UPDATE usuario SET senha=?, nome=?, sobrenome=?, apelido=?, foto=?, dt_nasc=?,cidade=?, estado=?, permissao=?"
                + " WHERE email = ?";
        try(PreparedStatement ps = conn.prepareStatement(sql)){
            ps.setString(1, usuario.getSenha());
            ps.setString(2, usuario.getNome());
            ps.setString(3, usuario.getSobrenome());
            ps.setString(4, usuario.getApelido());
            ps.setString(5, usuario.getFoto());
            ps.setDate(6, Date.valueOf(usuario.getDataNasc()));
            ps.setString(7, usuario.getCidade());
            ps.setString(8, usuario.getEstado().name());
            ps.setString(9, usuario.getPermissao().name());
            ps.setString(10, usuario.getEmail());
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
    

}
