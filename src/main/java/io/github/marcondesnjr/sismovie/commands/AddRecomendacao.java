
package io.github.marcondesnjr.sismovie.commands;

import io.github.marcondesnjr.sismovie.Filme;
import io.github.marcondesnjr.sismovie.Recomendacao;
import io.github.marcondesnjr.sismovie.Usuario;
import io.github.marcondesnjr.sismovie.dao.PersistenceException;
import io.github.marcondesnjr.sismovie.gerenciadordados.GerenciadorFilme;
import io.github.marcondesnjr.sismovie.gerenciadordados.GerenciadorRecomandacao;
import io.github.marcondesnjr.sismovie.gerenciadordados.GerenciadorUsuario;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author José Marcondes do Nascimento Junior
 */
public class AddRecomendacao implements Command{

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        try {
            String idFilme = request.getParameter("idFilme");
            String destEmail = request.getParameter("dest");
            Usuario usr = (Usuario) request.getSession().getAttribute("usrLog");
            Filme fm = GerenciadorFilme.localizar(Integer.parseInt(idFilme));
            Usuario dest = GerenciadorUsuario.localizar(destEmail);
            Recomendacao rec = new Recomendacao(usr, fm);
            GerenciadorRecomandacao.salvar(rec, dest);
            dest.addRecomendacao(rec);
            response.sendRedirect(request.getContextPath()+"/filme/"+idFilme);
            return null;
        } catch (PersistenceException ex) {
            Logger.getLogger(AddRecomendacao.class.getName()).log(Level.SEVERE, null, ex);
            return "persistenceError";
        } catch (IOException ex) {
            Logger.getLogger(AddRecomendacao.class.getName()).log(Level.SEVERE, null, ex);
            return "persistenceError";
        }
    }
    
}
