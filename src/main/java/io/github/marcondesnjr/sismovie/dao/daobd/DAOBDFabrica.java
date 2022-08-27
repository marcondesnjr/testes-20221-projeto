
package io.github.marcondesnjr.sismovie.dao.daobd;

import io.github.marcondesnjr.sismovie.dao.DAOAmizade;
import io.github.marcondesnjr.sismovie.dao.DAOAvaliacao;
import io.github.marcondesnjr.sismovie.dao.DAOComentario;
import io.github.marcondesnjr.sismovie.dao.DAOEstado;
import io.github.marcondesnjr.sismovie.dao.DAOGenero;
import io.github.marcondesnjr.sismovie.dao.FabricaDAO;
import io.github.marcondesnjr.sismovie.dao.DAOFilme;
import io.github.marcondesnjr.sismovie.dao.DAOGrupo;
import io.github.marcondesnjr.sismovie.dao.DAOParticipante;
import io.github.marcondesnjr.sismovie.dao.DAORecomendacao;
import io.github.marcondesnjr.sismovie.dao.DAOTopico;
import io.github.marcondesnjr.sismovie.dao.DAOUsuario;
import java.sql.Connection;

/**
 *
 * @author José Marcondes do Nascimento Junior
 */
public class DAOBDFabrica implements FabricaDAO{

    private Connection conn;
    
    public DAOBDFabrica(Connection conn) {
        this.conn = conn;
    }
 
    @Override
    public DAOUsuario criarDaoUsuario() {
        return new DAOBDUsuario(conn);
    }

    @Override
    public DAOFilme criarDaoFilme() {
        return new DAOBDFilme(conn);
    }

    @Override
    public DAOGenero criarDAOGenero() {
        return new DAOBDGenero(conn);
    }

    @Override
    public DAOEstado criarDAOEstado() {
        return new DAOBDEstado(conn);
    }

    @Override
    public DAOAmizade criarDAOAmizade() {
        return new DAOBDAmizade(conn);
    }

    @Override
    public DAOAvaliacao criarDAOAvaliacao() {
        return new DAOBDAvaliacao(conn);
    }

    @Override
    public DAOGrupo criarDAOGrupo() {
        return new DAOBDGrupo(conn);
    }

    @Override
    public DAOParticipante criarDAOParticipante() {
        return new DAOBDParticipante(conn);
    }

    @Override
    public DAOTopico criarDAOTopico() {
        return new DAOBDTopico(conn);
    }

    @Override
    public DAOComentario criarDAOComentario() {
        return new DAOBDComentario(conn);
    }

    @Override
    public DAORecomendacao criarDAORecomendacao() {
        return new DAOBDRecomendacao(conn);
    }
    
    
    
}
