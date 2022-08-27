
package io.github.marcondesnjr.sismovie.commands;

import io.github.marcondesnjr.sismovie.Avaliacao;
import io.github.marcondesnjr.sismovie.Filme;
import io.github.marcondesnjr.sismovie.Usuario;
import io.github.marcondesnjr.sismovie.dao.PersistenceException;
import io.github.marcondesnjr.sismovie.gerenciadordados.GerenciadorAvaliacao;
import io.github.marcondesnjr.sismovie.gerenciadordados.GerenciadorFilme;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author José Marcondes do Nascimento Junior
 */
public class AvaliarFilme implements Command{

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {

        try {
            int idFilme = Integer.parseInt(request.getParameter("idFilme"));
            int rating = Integer.parseInt(request.getParameter("rating"));
            String comment = request.getParameter("coment");
            Filme fm = GerenciadorFilme.localizar(idFilme);
            Avaliacao avl = new Avaliacao(rating, comment);
            avl.setFilme(fm);
            Usuario usr = (Usuario) request.getSession().getAttribute("usrLog");
            avl.setUsr(usr);
            GerenciadorAvaliacao.salvar(fm, avl, usr);
            response.sendRedirect("filme/"+idFilme);
            return null;
        } catch (IOException | PersistenceException ex) {
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, null, ex);
            return ErrorPages.PERSISTENCE_ERROR.getPAGE();
        }
    }
    
}
