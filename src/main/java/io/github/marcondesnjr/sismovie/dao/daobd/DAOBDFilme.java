package io.github.marcondesnjr.sismovie.dao.daobd;

import io.github.marcondesnjr.sismovie.Filme;
import io.github.marcondesnjr.sismovie.Genero;
import io.github.marcondesnjr.sismovie.dao.DAOFilme;
import io.github.marcondesnjr.sismovie.dao.PersistenceException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.Year;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *
 * @author José Marcondes do Nascimento Junior
 */
public class DAOBDFilme implements DAOFilme {

    public static final String ORDER_BY_RATING = "rating";
    public static final String ORDER_BY_ANO = "ano";
    public static final String ORDER_BY_TITULO = "titulo";
    
    private Connection conn;

    public DAOBDFilme(Connection conn) {
        this.conn = conn;
    }

    @Override
    public void persistir(Filme fl) throws PersistenceException {
        String sql = "INSERT INTO FILME(foto, titulo, sinopse, ano) VALUES (?,?,?,?)";
        try (PreparedStatement ps = conn.prepareStatement(sql,PreparedStatement.RETURN_GENERATED_KEYS);) {
            ps.setString(1, fl.getFoto());
            ps.setString(2, fl.getTitulo());
            ps.setString(3, fl.getSinopse());
            ps.setInt(4, fl.getAno().getValue());
            ps.executeUpdate();
            try(ResultSet rs = ps.getGeneratedKeys()){
                if(rs.next()){
                    fl.setId(rs.getInt("id"));
                }
            }
            DAOBDGeneroFilme daoGen = new DAOBDGeneroFilme(conn);
            for (Genero gen : fl.getGeneros()) {
                daoGen.persistir(fl, gen.name());
            }
            DAOBDAtor daoAt = new DAOBDAtor(conn);
            for (String ator : fl.getAtores()) {
                daoAt.persistir(fl, ator);
            }
            DAOBDDiretor daoDR = new DAOBDDiretor(conn);
            for(String dir: fl.getDiretores()){
                daoDR.persiste(fl, dir);
            }
            conn.commit();
        }catch(Exception ex){
            try {
                conn.rollback();
            } catch (SQLException ex1) {
                throw new PersistenceException("Erro ao realizar ROLLBACK",ex);
            }
            throw new PersistenceException(ex);
        }
    }

    @Override
    public void excluir(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Filme localizar(int id) throws PersistenceException{
        String sql = "SELECT * FROM FILME WHERE id = ?";
            DAOBDGeneroFilme daoGF= new DAOBDGeneroFilme(conn);
            DAOBDAtor daoAt = new DAOBDAtor(conn);
            DAOBDDiretor daoD = new DAOBDDiretor(conn);
        try(PreparedStatement ps = conn.prepareStatement(sql)){
            ps.setInt(1, id);
            try(ResultSet rs = ps.executeQuery()){
                if(rs.next()){
                    String foto = rs.getString("foto");
                    String titulo = rs.getString("titulo");
                    String sinopse = rs.getString("sinopse");
                    Year ano = Year.parse(rs.getString("ano"));
                    Filme fm = new Filme(foto, titulo, sinopse, ano);
                    fm.setId(rs.getInt("id"));
                    fm.setAtores(daoAt.localizarAtorFilme(fm));
                    fm.setGeneros(daoGF.localizarGeneroFilme(fm));
                    fm.setDiretores(daoD.localizarDiretorFilme(fm));
                    conn.commit();
                    return fm;
                }
                return null;
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
    public void localizar(String nome) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void localizarGen(String genero) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void update(Filme fl) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void close(){
        try {
            conn.close();
        } catch (SQLException ex) {}
    }

    @Override
    public List<Filme> localizarUltimos(int num) throws PersistenceException {
        try(PreparedStatement ps = conn.prepareStatement("SELECT * FROM FILME ORDER BY data_inset DESC LIMIT ?")){
            ps.setInt(1, num);
            try(ResultSet rs = ps.executeQuery()){
                List<Filme> films = new ArrayList();
                DAOBDGeneroFilme daoGF= new DAOBDGeneroFilme(conn);
                DAOBDAtor daoAt = new DAOBDAtor(conn);
                DAOBDDiretor daoD = new DAOBDDiretor(conn);
                while(rs.next()){
                    String foto = rs.getString("foto");
                    String titulo = rs.getString("titulo");
                    String sinopse = rs.getString("sinopse");
                    Year ano = Year.of(rs.getInt("ano"));
                    Filme fl = new Filme(foto, titulo, sinopse, ano);
                    fl.setGeneros(daoGF.localizarGeneroFilme(fl));
                    fl.setAtores(daoAt.localizarAtorFilme(fl));
                    fl.setDiretores(daoD.localizarDiretorFilme(fl));
                    fl.setId(rs.getInt("id"));
                    films.add(fl);
                }
                conn.commit();
                return films;
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
    public List<Filme> localizar(String ord, String gen, String ator, String diretor, String tit, boolean desc) throws PersistenceException{
        StringBuilder builder = new StringBuilder("SELECT DISTINCT id,foto,titulo,sinopse,ano, avl_media(id) rating FROM FILME LEFT JOIN GENERO_FILME GF ON id = GF.id_filme "
                + " LEFT JOIN ATOR_FILME AT ON id = AT.id_filme LEFT JOIN DIRETOR_FILME D ON id = D.id_filme WHERE '1'='1'");
        if(gen != null)
            builder.append(" AND genero = ? ");
        if(ator != null)
            builder.append(" AND ator ILIKE ? ");
        if(diretor != null)
            builder.append(" AND diretor ILIKE ? ");
        if(tit != null){
            builder.append(" AND titulo ILIKE ? ");
        }
        builder.append(" ORDER BY ").append(ord);
        if(desc)
                builder.append(" DESC");
        String sql = builder.toString();
        
        try(PreparedStatement ps = conn.prepareStatement(sql)){
            int cont = 1;
            if(gen != null)
                ps.setString(cont++, gen);
            if(ator != null)
                ps.setString(cont++, "%"+ator+"%");
            if(diretor != null)
                ps.setString(cont++, "%"+diretor+"%");
             if(tit != null)
                ps.setString(cont++, "%"+tit+"%");
            try(ResultSet rs = ps.executeQuery()){
                List<Filme> list = new ArrayList<>();
                while(rs.next()){
                    String foto = rs.getString("foto");
                    String titulo = rs.getString("titulo");
                    String sinopse = rs.getString("sinopse");
                    Year ano = Year.parse(rs.getString("ano"));
                    Filme fm = new Filme(foto, titulo, sinopse, ano);
                    fm.setId(rs.getInt("id"));
                    list.add(fm);
                }
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

    private class DAOBDGeneroFilme {

        private final Connection conn;

        public DAOBDGeneroFilme(Connection conn) {
            this.conn = conn;
        }

        public void persistir(Filme fl, String gen) throws SQLException {
            try (PreparedStatement ps = conn.prepareStatement("INSERT INTO GENERO_FILME VALUES (?,?)")) {
                ps.setInt(1, fl.getId());
                ps.setString(2, gen);
                ps.executeUpdate();
            }
        }
        
        public List<Genero> localizarGeneroFilme(Filme fl) throws SQLException{
            String sql = "SELECT genero "
                    + "FROM GENERO_FILME "
                    + "WHERE id_filme = ?";
            try (PreparedStatement ps = conn.prepareStatement(sql)) {
                ps.setInt(1, fl.getId());
                List<Genero> generos = new ArrayList<>();
                try(ResultSet rs = ps.executeQuery()){
                    if(rs.next()){
                        generos.add(Genero.valueOf(rs.getString("genero")));
                    }
                    return generos;
                }
            }
        }
        
    }

    private class DAOBDAtor {

        private final Connection conn;

        public DAOBDAtor(Connection conn) {
            this.conn = conn;
        }

        public void persistir(Filme fl, String ator) throws SQLException {
            try (PreparedStatement ps = conn.prepareStatement("INSERT INTO ATOR_FILME VALUES (?,?)")) {
                ps.setInt(1, fl.getId());
                ps.setString(2, ator);
                ps.executeUpdate();
            }
        }
        
        public List<String> localizarAtorFilme(Filme fl) throws SQLException{
            String sql = "SELECT ator "
                    + "FROM ATOR_FILME "
                    + "WHERE id_filme = ?";
            try (PreparedStatement ps = conn.prepareStatement(sql)) {
                ps.setInt(1, fl.getId());
                List<String> atores = new ArrayList<>();
                try(ResultSet rs = ps.executeQuery()){
                    if(rs.next()){
                        atores.add(rs.getString("ator"));
                    }
                    return atores;
                }
            }
        }
    }

    private static class DAOBDDiretor {

        private final Connection conn;
        
        public DAOBDDiretor(Connection conn) {
            this.conn = conn;
        }
        
        public void persiste(Filme fl, String diretor) throws SQLException{
            try (PreparedStatement ps = conn.prepareStatement("INSERT INTO DIRETOR_FILME VALUES (?,?)")) {
                ps.setInt(1, fl.getId());
                ps.setString(2, diretor);
                ps.executeUpdate();
            }
        }
        
        public List<String> localizarDiretorFilme(Filme fl) throws SQLException{
            String sql = "SELECT diretor "
                    + "FROM DIRETOR_FILME "
                    + "WHERE id_filme = ?";
            try (PreparedStatement ps = conn.prepareStatement(sql)) {
                ps.setInt(1, fl.getId());
                List<String> diretores = new ArrayList<>();
                try(ResultSet rs = ps.executeQuery()){
                    if(rs.next()){
                        diretores.add(rs.getString("diretor"));
                    }
                    return diretores;
                }
            }
        }
        
    }

}
