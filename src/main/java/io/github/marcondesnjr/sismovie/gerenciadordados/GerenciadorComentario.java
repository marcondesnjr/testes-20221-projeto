
package io.github.marcondesnjr.sismovie.gerenciadordados;

import io.github.marcondesnjr.sismovie.Comentario;
import io.github.marcondesnjr.sismovie.Topico;
import io.github.marcondesnjr.sismovie.dao.CriadorFabrica;
import io.github.marcondesnjr.sismovie.dao.DAOComentario;
import io.github.marcondesnjr.sismovie.dao.PersistenceException;

/**
 *
 * @author José Marcondes do Nascimento Junior
 */
public class GerenciadorComentario {
    public static Comentario salvar(Comentario c, Topico tp) throws PersistenceException{
        try(DAOComentario dao = CriadorFabrica.criarFabrica(CriadorFabrica.BANCO_DE_DADOS).criarDAOComentario()){
            return dao.persiste(c, tp);
        }
    }
    public static Topico carregarComentarios(Topico tp) throws PersistenceException{
        try(DAOComentario dao = CriadorFabrica.criarFabrica(CriadorFabrica.BANCO_DE_DADOS).criarDAOComentario()){
            return dao.carregarComentarios(tp);
        }
    }
}
