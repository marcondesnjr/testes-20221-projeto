
package io.github.marcondesnjr.sismovie.dao;

import io.github.marcondesnjr.sismovie.Avaliacao;
import io.github.marcondesnjr.sismovie.Filme;
import io.github.marcondesnjr.sismovie.Usuario;
import java.util.List;

/**
 *
 * @author José Marcondes do Nascimento Junior
 */
public interface DAOAvaliacao extends AutoCloseable{  
    public Avaliacao persistir(Filme fl, Avaliacao avl, Usuario usr) throws PersistenceException;
    public double avaliacaoMedia(Filme fl) throws PersistenceException;
    public List<Avaliacao> carregarAvaliacoes(Filme fm) throws PersistenceException;
    public List<Avaliacao> carregarAvaliacoes(Usuario usr) throws PersistenceException;
    @Override
    public void close();
}
