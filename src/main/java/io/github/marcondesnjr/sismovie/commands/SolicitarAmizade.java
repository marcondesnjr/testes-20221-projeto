package io.github.marcondesnjr.sismovie.commands;

import io.github.marcondesnjr.sismovie.Amizade;
import io.github.marcondesnjr.sismovie.SisMovie;
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
public class SolicitarAmizade implements Command{

    public String execute(HttpServletRequest request, HttpServletResponse response){
        try {
            String email = request.getParameter("email");
            Usuario dest = SisMovie.localizarUsuario(email);
            Usuario rem = (Usuario) request.getSession().getAttribute("usrLog");
            Amizade.addSolicitacao(rem, dest);
            String url = request.getContextPath();
            response.sendRedirect(request.getContextPath()+"/usr/"+email);
            return null;
        } catch (PersistenceException ex) {
            Logger.getLogger(SolicitarAmizade.class.getName()).log(Level.SEVERE, null, ex);
            return ErrorPages.PERSISTENCE_ERROR.getPAGE();
        } catch (IOException ex) {
            Logger.getLogger(SolicitarAmizade.class.getName()).log(Level.SEVERE, null, ex);
            return "ioError";
        }
    }
}
