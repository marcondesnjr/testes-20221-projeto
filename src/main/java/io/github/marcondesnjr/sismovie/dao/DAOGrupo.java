package io.github.marcondesnjr.sismovie.dao;

import io.github.marcondesnjr.sismovie.Grupo;
import io.github.marcondesnjr.sismovie.Usuario;
import java.util.List;

/**
 *
 * @author José Marcondes do Nascimento Junior
 */
public interface DAOGrupo extends AutoCloseable{
    public Grupo persiste(Grupo grp) throws PersistenceException;
    public Grupo localiza(int id) throws PersistenceException;
    public List<Grupo> localizarTodos() throws PersistenceException;
    public List<Grupo> localizarPorNome(String nome) throws PersistenceException;
    @Override
    public void close();    
}
