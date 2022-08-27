package io.github.marcondesnjr.sismovie.dao;

import io.github.marcondesnjr.sismovie.Grupo;
import io.github.marcondesnjr.sismovie.Usuario;

/**
 *
 * @author José Marcondes do Nascimento Junior
 */
public interface DAOParticipante extends AutoCloseable{
    public Usuario carregarGrupos(Usuario usr)throws PersistenceException;
    public Grupo carregarParticipantes(Grupo gp) throws PersistenceException;
    public void persiste(Grupo gp, Usuario usr) throws PersistenceException;

    @Override
    public void close();
}
