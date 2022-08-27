package io.github.marcondesnjr.sismovie.gerenciadordados;

import io.github.marcondesnjr.sismovie.Avaliacao;
import io.github.marcondesnjr.sismovie.Filme;
import io.github.marcondesnjr.sismovie.Usuario;
import io.github.marcondesnjr.sismovie.dao.CriadorFabrica;
import io.github.marcondesnjr.sismovie.dao.DAOAvaliacao;
import io.github.marcondesnjr.sismovie.dao.PersistenceException;
import java.util.List;

/**
 *
 * @author José Marcondes do Nascimento Junior
 */
public class GerenciadorAvaliacao {

    public static Avaliacao salvar(Filme fm, Avaliacao avl, Usuario usr) throws PersistenceException {
        try (DAOAvaliacao dao = CriadorFabrica.criarFabrica(CriadorFabrica.BANCO_DE_DADOS).criarDAOAvaliacao();) {
            Avaliacao arv = dao.persistir(fm, avl, usr);
            return arv;
        }
    }

    public static double avaliacaoMedia(Filme fm) throws PersistenceException {
        try (DAOAvaliacao dao = CriadorFabrica.criarFabrica(CriadorFabrica.BANCO_DE_DADOS).criarDAOAvaliacao();) {
            double media = dao.avaliacaoMedia(fm);
            return media;
        }
    }

    public static List<Avaliacao> carregarAvaliacoes(Usuario usr) throws PersistenceException {
        try (DAOAvaliacao dao = CriadorFabrica.criarFabrica(CriadorFabrica.BANCO_DE_DADOS).criarDAOAvaliacao();) {
            List<Avaliacao> avls = dao.carregarAvaliacoes(usr);
            return avls;
        }
    }

    public static List<Avaliacao> carregarAvaliacoes(Filme fm) throws PersistenceException {
        try (DAOAvaliacao dao = CriadorFabrica.criarFabrica(CriadorFabrica.BANCO_DE_DADOS).criarDAOAvaliacao();) {
            List<Avaliacao> avls = dao.carregarAvaliacoes(fm);
            return avls;
        }
    }
}
