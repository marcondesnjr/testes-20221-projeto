
package io.github.marcondesnjr.sismovie.commands;

import io.github.marcondesnjr.sismovie.Avaliacao;
import io.github.marcondesnjr.sismovie.Filme;
import io.github.marcondesnjr.sismovie.Usuario;
import io.github.marcondesnjr.sismovie.dao.PersistenceException;
import io.github.marcondesnjr.sismovie.gerenciadordados.GerenciadorAvaliacao;
import io.github.marcondesnjr.sismovie.gerenciadordados.GerenciadorFilme;
import io.github.marcondesnjr.sismovie.gerenciadordados.GerencidadorAmizade;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author José Marcondes do Nascimento Junior
 */
public class ExbFilme implements Command{

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {

        try {
            Integer id = Integer.parseInt(request.getParameter("id"));
            Filme fm = GerenciadorFilme.localizar(id);
            double media = GerenciadorAvaliacao.avaliacaoMedia(fm);
            request.setAttribute("filme", fm);
            request.setAttribute("media", String.format("%.2f", media));
            List<Avaliacao> avls = GerenciadorAvaliacao.carregarAvaliacoes(fm);
            request.setAttribute("avaliacoes", avls);
            Usuario usr = (Usuario) request.getSession().getAttribute("usrLog");
            if(usr != null){
                request.setAttribute("amigos", GerencidadorAmizade.localizarAmigos(usr));
            }
            return "pages/exb-filme.jsp";
        } catch (PersistenceException ex) {
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, null, ex);
            return ErrorPages.PERSISTENCE_ERROR.getPAGE();
        }
        
    }
    
}
