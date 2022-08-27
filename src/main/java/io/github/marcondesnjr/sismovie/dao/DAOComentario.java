
package io.github.marcondesnjr.sismovie.dao;

import io.github.marcondesnjr.sismovie.Comentario;
import io.github.marcondesnjr.sismovie.Topico;

/**
 *
 * @author José Marcondes do Nascimento Junior
 */
public interface DAOComentario extends AutoCloseable{
    public Comentario persiste(Comentario com, Topico top)throws PersistenceException;
    public Topico carregarComentarios(Topico top) throws PersistenceException;

    @Override
    public void close();
    
}
