
package io.github.marcondesnjr.sismovie.commands;

import io.github.marcondesnjr.sismovie.Amizade;
import io.github.marcondesnjr.sismovie.Usuario;
import io.github.marcondesnjr.sismovie.dao.PersistenceException;
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
public class RejeitarSolicitacao implements Command{

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {

        try {
            String rem = request.getParameter("email");
            Usuario dest = (Usuario) request.getSession().getAttribute("usrLog");
            Amizade.removerSolicitacão(rem, dest.getEmail());
            response.sendRedirect(request.getContextPath()+"/home/");
            return null;
        } catch (PersistenceException ex) {
            return ErrorPages.PERSISTENCE_ERROR.getPAGE();
        } catch (IOException ex) {
            return ErrorPages.NOT_FOUND.getPAGE();
        }
    }
    
}
