
package io.github.marcondesnjr.sismovie.dao;

import io.github.marcondesnjr.sismovie.Grupo;
import io.github.marcondesnjr.sismovie.Topico;

/**
 *
 * @author José Marcondes do Nascimento Junior
 */
public interface DAOTopico extends AutoCloseable{
    public Topico persiste(Topico tp, Grupo gp) throws PersistenceException;
    public Grupo carregarTopicos(Grupo gp) throws PersistenceException;
    public Topico localiza(int id) throws PersistenceException;
    @Override
    public void close();
    
}
