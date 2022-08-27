package io.github.marcondesnjr.sismovie.dao;

import io.github.marcondesnjr.sismovie.Solicitacao;
import io.github.marcondesnjr.sismovie.Usuario;
import java.util.List;

/**
 *
 * @author José Marcondes do Nascimento Junior
 */
public interface DAOAmizade extends AutoCloseable{  
    public void persistir(Solicitacao sol) throws PersistenceException;
    public List<Solicitacao> localizarRecebida(Usuario usr) throws PersistenceException;
    public void atualizar(Solicitacao sol) throws PersistenceException;
    public List<Usuario> localizarAmigos(Usuario usr) throws PersistenceException;
    public void deletar(String rem, String dest) throws PersistenceException;
    public boolean existeSolicitacao(Usuario usr, Usuario other) throws PersistenceException;
    @Override
    public void close();
}
