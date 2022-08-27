package io.github.marcondesnjr.sismovie.gerenciadordados;

import io.github.marcondesnjr.sismovie.Recomendacao;
import io.github.marcondesnjr.sismovie.Usuario;
import io.github.marcondesnjr.sismovie.dao.CriadorFabrica;
import io.github.marcondesnjr.sismovie.dao.DAORecomendacao;
import io.github.marcondesnjr.sismovie.dao.PersistenceException;

/**
 *
 * @author José Marcondes do Nascimento Junior
 */
public class GerenciadorRecomandacao {
    public static void salvar(Recomendacao re, Usuario usr) throws PersistenceException{
        try(DAORecomendacao dao = CriadorFabrica.criarFabrica(CriadorFabrica.BANCO_DE_DADOS).criarDAORecomendacao()){
            dao.persiste(re, usr);
        }
    }
    
    public static Usuario carregarRecomendacoes(Usuario usr) throws PersistenceException{
        try(DAORecomendacao dao = CriadorFabrica.criarFabrica(CriadorFabrica.BANCO_DE_DADOS).criarDAORecomendacao()){
            return dao.carregarRecomendacoes(usr);
        }
    }
}
