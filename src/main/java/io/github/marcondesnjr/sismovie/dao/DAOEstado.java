package io.github.marcondesnjr.sismovie.dao;

import io.github.marcondesnjr.sismovie.Estado;
import java.util.List;

/**
 *
 * @author José Marcondes do Nascimento Junior
 */
public interface DAOEstado extends AutoCloseable{
    public void persistir(String estado) throws PersistenceException;
    public void excluir(String estado) throws PersistenceException;
    public List<Estado> todosEstados() throws PersistenceException;
    @Override
    public void close();
}
