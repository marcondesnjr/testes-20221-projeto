
package io.github.marcondesnjr.sismovie.gerenciadordados;

import io.github.marcondesnjr.sismovie.Grupo;
import io.github.marcondesnjr.sismovie.Topico;
import io.github.marcondesnjr.sismovie.dao.CriadorFabrica;
import io.github.marcondesnjr.sismovie.dao.DAOTopico;
import io.github.marcondesnjr.sismovie.dao.PersistenceException;

/**
 *
 * @author José Marcondes do Nascimento Junior
 */
public class GerenciadorTopico {
    public static Topico salvar(Topico tp, Grupo gp) throws PersistenceException{
        try(DAOTopico dao = CriadorFabrica.criarFabrica(CriadorFabrica.BANCO_DE_DADOS).criarDAOTopico()){
            return dao.persiste(tp, gp);
        }
    }
    public static Grupo carragarTopicos(Grupo gp) throws PersistenceException{
        try(DAOTopico dao = CriadorFabrica.criarFabrica(CriadorFabrica.BANCO_DE_DADOS).criarDAOTopico()){
            return dao.carregarTopicos(gp);
        }
    }
    public static Topico localizar(int id) throws PersistenceException{
        try(DAOTopico dao = CriadorFabrica.criarFabrica(CriadorFabrica.BANCO_DE_DADOS).criarDAOTopico()){
            return dao.localiza(id);
        }
    }
}
