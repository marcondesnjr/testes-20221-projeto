
package io.github.marcondesnjr.sismovie.gerenciadordados;

import io.github.marcondesnjr.sismovie.Grupo;
import io.github.marcondesnjr.sismovie.Usuario;
import io.github.marcondesnjr.sismovie.dao.CriadorFabrica;
import io.github.marcondesnjr.sismovie.dao.DAOParticipante;
import io.github.marcondesnjr.sismovie.dao.PersistenceException;

/**
 *
 * @author José Marcondes do Nascimento Junior
 */
public class GerenciadorParticipantes {
    public static Usuario carregarGrupos(Usuario usr) throws PersistenceException{
        try(DAOParticipante dao = CriadorFabrica.criarFabrica(CriadorFabrica.BANCO_DE_DADOS).criarDAOParticipante()){
            return dao.carregarGrupos(usr);
        }
    }
    
    public static Grupo carregarParticipantes(Grupo gp) throws PersistenceException{
        try(DAOParticipante dao = CriadorFabrica.criarFabrica(CriadorFabrica.BANCO_DE_DADOS).criarDAOParticipante()){
            return dao.carregarParticipantes(gp);
        }
    }
    
    public static void salvar(Grupo gp, Usuario usr) throws PersistenceException{
        try(DAOParticipante dao = CriadorFabrica.criarFabrica(CriadorFabrica.BANCO_DE_DADOS).criarDAOParticipante()){
            dao.persiste(gp, usr);
        }
    }
}
