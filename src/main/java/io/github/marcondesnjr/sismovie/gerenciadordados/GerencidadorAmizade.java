package io.github.marcondesnjr.sismovie.gerenciadordados;

import io.github.marcondesnjr.sismovie.Solicitacao;
import io.github.marcondesnjr.sismovie.Usuario;
import io.github.marcondesnjr.sismovie.dao.CriadorFabrica;
import io.github.marcondesnjr.sismovie.dao.DAOAmizade;
import io.github.marcondesnjr.sismovie.dao.PersistenceException;
import java.util.List;

/**
 *
 * @author José Marcondes do Nascimento Junior
 */
public class GerencidadorAmizade {

    public static void salvar(Solicitacao sa) throws PersistenceException {
        try (DAOAmizade dao = CriadorFabrica.criarFabrica(CriadorFabrica.BANCO_DE_DADOS)
                .criarDAOAmizade()) {
            dao.persistir(sa);
        }
    }

    public static List<Solicitacao> getSolitacoesRecebidas(Usuario usr) throws PersistenceException {
        try (DAOAmizade dao = CriadorFabrica.criarFabrica(CriadorFabrica.BANCO_DE_DADOS)
                .criarDAOAmizade()) {
            List<Solicitacao> list = dao.localizarRecebida(usr);
            return list;
        }
    }

    public static void atualizar(Solicitacao sol) throws PersistenceException {
        try (DAOAmizade dao = CriadorFabrica.criarFabrica(CriadorFabrica.BANCO_DE_DADOS)
                .criarDAOAmizade()) {
            dao.atualizar(sol);
        }
    }

    public static List<Usuario> localizarAmigos(Usuario usr) throws PersistenceException {
        try (DAOAmizade dao = CriadorFabrica.criarFabrica(CriadorFabrica.BANCO_DE_DADOS)
                .criarDAOAmizade()) {
            List<Usuario> list = dao.localizarAmigos(usr);
            return list;
        }
    }

    public static void removerSolicitacao(String rem, String dest) throws PersistenceException {
        try (DAOAmizade dao = CriadorFabrica.criarFabrica(CriadorFabrica.BANCO_DE_DADOS)
                .criarDAOAmizade()) {
            dao.deletar(rem, dest);
        }
    }

    public static boolean exists(Usuario usr, Usuario other) throws PersistenceException{
        try (DAOAmizade dao = CriadorFabrica.criarFabrica(CriadorFabrica.BANCO_DE_DADOS)
                .criarDAOAmizade()) {
            return dao.existeSolicitacao(usr, other);
        }
    }
    
}
