package io.github.marcondesnjr.sismovie.dao;

import io.github.marcondesnjr.sismovie.Usuario;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author José Marcondes do Nascimento Junior
 */
public interface DAOGenero extends AutoCloseable{

    public void persistir(String gen) throws PersistenceException;

    public Usuario excluir(String gen) throws PersistenceException;

    public List<Usuario> listar() throws PersistenceException;

    public List<Usuario> localizarNome(String nome) throws PersistenceException;

    public List<String> todosGeneros() throws PersistenceException;
    
    @Override
    public void close();

}
