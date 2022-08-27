package io.github.marcondesnjr.sismovie.dao;

import io.github.marcondesnjr.sismovie.Usuario;
import java.util.List;

public interface DAOUsuario extends AutoCloseable{

    public void persistir(Usuario usuario) throws PersistenceException, AlreadyExistsException;

    public void excluir(String email) throws PersistenceException;

    public void editar(Usuario usuario) throws PersistenceException;
    
    public List<Usuario> listar();

    public Usuario localizar(String login) throws PersistenceException;

    public List<Usuario> localizarNome(String nome);

    public Usuario localizar(String login, String senha) throws PersistenceException;
    
    public List<Usuario> localizar() throws PersistenceException;
    
    public List<Usuario> perquisarNome(String nome) throws PersistenceException;
 
    @Override
    public void close();
    
}
