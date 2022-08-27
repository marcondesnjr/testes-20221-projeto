package io.github.marcondesnjr.sismovie.commands;

import io.github.marcondesnjr.sismovie.Amizade;
import io.github.marcondesnjr.sismovie.SisMovie;
import io.github.marcondesnjr.sismovie.Solicitacao;
import io.github.marcondesnjr.sismovie.Usuario;
import io.github.marcondesnjr.sismovie.dao.PersistenceException;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author José Marcondes do Nascimento Junior
 */
public class AceitarSolicitacao implements Command{

    public String execute(HttpServletRequest request, HttpServletResponse response){
        try {
            String email = request.getParameter("email");
            Usuario rem = SisMovie.localizarUsuario(email);
            Usuario dest = (Usuario) request.getSession().getAttribute("usrLog");
            Amizade.aceitarSolicitacao(new Solicitacao(rem, dest));
            response.sendRedirect(request.getContextPath()+"/home/");
            return null;
        } catch (PersistenceException ex) {
            return ErrorPages.PERSISTENCE_ERROR.getPAGE();
        } catch (IOException ex) {
            return ErrorPages.NOT_FOUND.getPAGE();
        }
    }
}
