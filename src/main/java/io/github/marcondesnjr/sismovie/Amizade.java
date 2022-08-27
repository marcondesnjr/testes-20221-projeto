package io.github.marcondesnjr.sismovie;

import io.github.marcondesnjr.sismovie.dao.PersistenceException;
import io.github.marcondesnjr.sismovie.gerenciadordados.GerencidadorAmizade;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author José Marcondes do Nascimento Junior
 */
public class Amizade {
    private static List<Solicitacao> solicitacoes = new ArrayList<>();
    
    public static void addSolicitacao(Usuario rem, Usuario dest) throws PersistenceException{
        Solicitacao sol = new Solicitacao(rem, dest);
        solicitacoes.add(sol);
        GerencidadorAmizade.salvar(sol);
    }
        
    public static List<Solicitacao> getSolicitacoesRecebidas(Usuario usr) throws PersistenceException{
        return GerencidadorAmizade.getSolitacoesRecebidas(usr);
    }
    
    public static void aceitarSolicitacao(Solicitacao sol) throws PersistenceException{
        sol.setStatus(Solicitacao.ACEITO);
        GerencidadorAmizade.atualizar(sol);
    }
    
    public static List<Usuario> getAmigos(Usuario usr) throws PersistenceException{
        return GerencidadorAmizade.localizarAmigos(usr);
    }
    
    public static void removerSolicitacão(String rem, String dest) throws PersistenceException{
        GerencidadorAmizade.removerSolicitacao(rem, dest);
    }
     
}
