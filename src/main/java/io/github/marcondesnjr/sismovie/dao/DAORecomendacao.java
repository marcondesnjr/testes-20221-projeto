
package io.github.marcondesnjr.sismovie.dao;

import io.github.marcondesnjr.sismovie.Recomendacao;
import io.github.marcondesnjr.sismovie.Usuario;

/**
 *
 * @author José Marcondes do Nascimento Junior
 */
public interface DAORecomendacao extends AutoCloseable{
    public void persiste(Recomendacao re, Usuario usr) throws PersistenceException;
    public void deletar(String rm, int fm, String dest) throws PersistenceException;
    public Usuario carregarRecomendacoes(Usuario usr) throws PersistenceException;
    @Override
    public void close();
    
}
