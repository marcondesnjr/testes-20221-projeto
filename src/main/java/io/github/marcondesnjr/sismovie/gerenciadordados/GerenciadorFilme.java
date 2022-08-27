package io.github.marcondesnjr.sismovie.gerenciadordados;

import io.github.marcondesnjr.sismovie.Filme;
import io.github.marcondesnjr.sismovie.dao.CriadorFabrica;
import io.github.marcondesnjr.sismovie.dao.DAOFilme;
import io.github.marcondesnjr.sismovie.dao.PersistenceException;
import java.util.List;

/**
 *
 * @author José Marcondes do Nascimento Junior
 */
public class GerenciadorFilme {

    public static final String ORDER_BY_RATING = "rating";
    public static final String ORDER_BY_ANO = "ano";
    public static final String ORDER_BY_TITULO = "titulo";
    
    public static List<Filme> lastFilmes(int n) throws PersistenceException {
        try (DAOFilme dao = CriadorFabrica.criarFabrica(CriadorFabrica.BANCO_DE_DADOS).criarDaoFilme();) {
            List<Filme> filmes = dao.localizarUltimos(n);
            return filmes;
        }
    }

    public static void salvar(Filme fl) throws PersistenceException {
        try (DAOFilme dao = CriadorFabrica.criarFabrica(CriadorFabrica.BANCO_DE_DADOS).criarDaoFilme();) {
            dao.persistir(fl);
        }
    }

    public static Filme localizar(int id) throws PersistenceException {
        try (DAOFilme dao = CriadorFabrica.criarFabrica(CriadorFabrica.BANCO_DE_DADOS).criarDaoFilme();) {
            Filme fm = dao.localizar(id);
            return fm;
        }
    }
    public static List<Filme> localizar(String ord, String gen, String ator, String diretor,String titulo, boolean desc) throws PersistenceException{
        try (DAOFilme dao = CriadorFabrica.criarFabrica(CriadorFabrica.BANCO_DE_DADOS).criarDaoFilme();) {
            return dao.localizar(ord, gen, ator, diretor, titulo, desc);
        }
    }

}
